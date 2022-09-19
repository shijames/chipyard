#include "/scratch/james.shi/centrifuge/chipyard/tools/centrifuge/scripts/sw_aux/sw_helper/rocc.h"
#define ACCEL_WRAPPER
#include "accel.h"
uint64_t  vadd(uint64_t length_a, uint64_t b_c)
{
  uint64_t ret_val;

    #define XCUSTOM_ACC 0
        ROCC_INSTRUCTION_DSS(XCUSTOM_ACC, ret_val, length_a, b_c, 0);
      ROCC_BARRIER();
    return ret_val;
}