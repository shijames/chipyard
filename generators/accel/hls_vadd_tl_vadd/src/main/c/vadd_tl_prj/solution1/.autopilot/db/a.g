#!/bin/sh
lli=${LLVMINTERP-lli}
exec $lli \
    /scratch/james.shi/centrifuge/chipyard/generators/accel/hls_vadd_tl_vadd/src/main/c/vadd_tl_prj/solution1/.autopilot/db/a.g.bc ${1+"$@"}
