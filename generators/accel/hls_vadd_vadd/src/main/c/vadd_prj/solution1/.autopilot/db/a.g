#!/bin/sh
lli=${LLVMINTERP-lli}
exec $lli \
    /scratch/james.shi/centrifuge/chipyard/generators/accel/hls_vadd_vadd/src/main/c/vadd_prj/solution1/.autopilot/db/a.g.bc ${1+"$@"}
