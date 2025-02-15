#!/usr/bin/env bash

# this script is based on the firesim build toolchains script

# exit script if any command fails
set -e
set -o pipefail

# On macOS, use GNU readlink from 'coreutils' package in Homebrew/MacPorts
if [ "$(uname -s)" = "Darwin" ] ; then
    READLINK=greadlink
else
    READLINK=readlink
fi

# If BASH_SOURCE is undefined, we may be running under zsh, in that case
# provide a zsh-compatible alternative
DIR="$(dirname "$($READLINK -f "${BASH_SOURCE[0]:-${(%):-%x}}")")"
CHIPYARD_DIR="$(dirname "$DIR")"

# Allow user to override MAKE
[ -n "${MAKE:+x}" ] || MAKE=$(command -v gnumake || command -v gmake || command -v make)
readonly MAKE

usage() {
    echo "usage: ${0} [OPTIONS] [riscv-tools | esp-tools]"
    echo ""
    echo "Installation Types"
    echo "   riscv-tools: if set, builds the riscv toolchain (this is also the default)"
    echo "   esp-tools: if set, builds esp-tools toolchain used for the hwacha vector accelerator"
    echo ""
    echo "Options"
    echo "   --prefix PREFIX       : Install destination. If unset, defaults to $CONDA_PREFIX/riscv-tools"
    echo "                           or $CONDA_PREFIX/esp-tools"
    echo "   --clean-after-install : Run make clean in calls to module_make and module_build"
    echo "   --skip-validate       : Skip prompt checking for conda"
    echo "   --help -h             : Display this message"
    exit "$1"
}

error() {
    echo "${0##*/}: ${1}" >&2
}
die() {
    error "$1"
    exit "${2:--1}"
}

TOOLCHAIN="riscv-tools"
CLEANAFTERINSTALL=""
RISCV=""
SKIP_VALIDATE=false

# getopts does not support long options, and is inflexible
while [ "$1" != "" ];
do
    case $1 in
        -h | --help | help )
            usage 3 ;;
        -p | --prefix )
            shift
            RISCV=$(realpath $1) ;;
        --clean-after-install )
            CLEANAFTERINSTALL="true" ;;
        riscv-tools | esp-tools)
            TOOLCHAIN=$1 ;;
        --skip-validate)
            SKIP_VALIDATE=true;
            ;;
        * )
            error "invalid option $1"
            usage 1 ;;
    esac
    shift
done

if [ "$SKIP_VALIDATE" = false ]; then
    if [ -z ${CONDA_DEFAULT_ENV+x} ]; then
        error "ERROR: No conda environment detected. Did you activate the conda environment (e.x. 'conda activate chipyard')?"
        exit 1
    fi
fi

if [ -z "$RISCV" ] ; then
      RISCV="$CONDA_PREFIX/$TOOLCHAIN"
fi

XLEN=64

echo "Installing extra toolchain utilities/tests to $RISCV"

# install risc-v tools
export RISCV="$RISCV"

cd "${CHIPYARD_DIR}"

SRCDIR="$(pwd)/toolchains/${TOOLCHAIN}"
[ -d "${SRCDIR}" ] || die "unsupported toolchain: ${TOOLCHAIN}"
. ./scripts/build-util.sh

echo '==>  Installing Spike'
# disable boost explicitly for https://github.com/riscv-software-src/riscv-isa-sim/issues/834
# since we don't have it in our requirements
module_all riscv-isa-sim --prefix="${RISCV}" --with-boost=no --with-boost-asio=no --with-boost-regex=no
# build static libfesvr library for linking into firesim driver (or others)
echo '==>  Installing libfesvr static library'
OLDCLEANAFTERINSTALL=$CLEANAFTERINSTALL
CLEANAFTERINSTALL=""
module_make riscv-isa-sim libfesvr.a
cp -p "${SRCDIR}/riscv-isa-sim/build/libfesvr.a" "${RISCV}/lib/"
CLEANAFTERINSTALL=$OLDCLEANAFTERINSTALL

echo '==>  Installing Proxy Kernel'
CC= CXX= module_all riscv-pk --prefix="${RISCV}" --host=riscv${XLEN}-unknown-elf

echo '==>  Installing RISC-V tests'
module_all riscv-tests --prefix="${RISCV}/riscv${XLEN}-unknown-elf" --with-xlen=${XLEN}

# Common tools (not in any particular toolchain dir)

echo '==>  Installing libgloss'
CC= CXX= SRCDIR="$(pwd)/toolchains" module_all libgloss --prefix="${RISCV}/riscv${XLEN}-unknown-elf" --host=riscv${XLEN}-unknown-elf

echo "Extra Toolchain Utilities/Tests Build Complete!"
