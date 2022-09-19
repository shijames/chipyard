// ==============================================================
// File generated by Vivado(TM) HLS - High-Level Synthesis from C, C++ and SystemC
// Version: 2018.2
// Copyright (C) 1986-2018 Xilinx, Inc. All Rights Reserved.
// 
// ==============================================================

#ifndef XTL0_VADD_H
#define XTL0_VADD_H

#ifdef __cplusplus
extern "C" {
#endif

/***************************** Include Files *********************************/
#ifndef __linux__
#include "xil_types.h"
#include "xil_assert.h"
#include "xstatus.h"
#include "xil_io.h"
#else
#include <stdint.h>
#include <assert.h>
#include <dirent.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/mman.h>
#include <unistd.h>
#include <stddef.h>
#endif
#include "xtl0_vadd_hw.h"

/**************************** Type Definitions ******************************/
#ifdef __linux__
typedef uint8_t u8;
typedef uint16_t u16;
typedef uint32_t u32;
#else
typedef struct {
    u16 DeviceId;
    u32 Control_BaseAddress;
} XTl0_vadd_Config;
#endif

typedef struct {
    u32 Control_BaseAddress;
    u32 IsReady;
} XTl0_vadd;

/***************** Macros (Inline Functions) Definitions *********************/
#ifndef __linux__
#define XTl0_vadd_WriteReg(BaseAddress, RegOffset, Data) \
    Xil_Out32((BaseAddress) + (RegOffset), (u32)(Data))
#define XTl0_vadd_ReadReg(BaseAddress, RegOffset) \
    Xil_In32((BaseAddress) + (RegOffset))
#else
#define XTl0_vadd_WriteReg(BaseAddress, RegOffset, Data) \
    *(volatile u32*)((BaseAddress) + (RegOffset)) = (u32)(Data)
#define XTl0_vadd_ReadReg(BaseAddress, RegOffset) \
    *(volatile u32*)((BaseAddress) + (RegOffset))

#define Xil_AssertVoid(expr)    assert(expr)
#define Xil_AssertNonvoid(expr) assert(expr)

#define XST_SUCCESS             0
#define XST_DEVICE_NOT_FOUND    2
#define XST_OPEN_DEVICE_FAILED  3
#define XIL_COMPONENT_IS_READY  1
#endif

/************************** Function Prototypes *****************************/
#ifndef __linux__
int XTl0_vadd_Initialize(XTl0_vadd *InstancePtr, u16 DeviceId);
XTl0_vadd_Config* XTl0_vadd_LookupConfig(u16 DeviceId);
int XTl0_vadd_CfgInitialize(XTl0_vadd *InstancePtr, XTl0_vadd_Config *ConfigPtr);
#else
int XTl0_vadd_Initialize(XTl0_vadd *InstancePtr, const char* InstanceName);
int XTl0_vadd_Release(XTl0_vadd *InstancePtr);
#endif

void XTl0_vadd_Start(XTl0_vadd *InstancePtr);
u32 XTl0_vadd_IsDone(XTl0_vadd *InstancePtr);
u32 XTl0_vadd_IsIdle(XTl0_vadd *InstancePtr);
u32 XTl0_vadd_IsReady(XTl0_vadd *InstancePtr);
void XTl0_vadd_EnableAutoRestart(XTl0_vadd *InstancePtr);
void XTl0_vadd_DisableAutoRestart(XTl0_vadd *InstancePtr);
u32 XTl0_vadd_Get_return(XTl0_vadd *InstancePtr);

void XTl0_vadd_Set_a(XTl0_vadd *InstancePtr, u64 Data);
u64 XTl0_vadd_Get_a(XTl0_vadd *InstancePtr);
void XTl0_vadd_Set_b(XTl0_vadd *InstancePtr, u64 Data);
u64 XTl0_vadd_Get_b(XTl0_vadd *InstancePtr);
void XTl0_vadd_Set_c(XTl0_vadd *InstancePtr, u64 Data);
u64 XTl0_vadd_Get_c(XTl0_vadd *InstancePtr);
void XTl0_vadd_Set_length_r(XTl0_vadd *InstancePtr, u32 Data);
u32 XTl0_vadd_Get_length_r(XTl0_vadd *InstancePtr);

void XTl0_vadd_InterruptGlobalEnable(XTl0_vadd *InstancePtr);
void XTl0_vadd_InterruptGlobalDisable(XTl0_vadd *InstancePtr);
void XTl0_vadd_InterruptEnable(XTl0_vadd *InstancePtr, u32 Mask);
void XTl0_vadd_InterruptDisable(XTl0_vadd *InstancePtr, u32 Mask);
void XTl0_vadd_InterruptClear(XTl0_vadd *InstancePtr, u32 Mask);
u32 XTl0_vadd_InterruptGetEnabled(XTl0_vadd *InstancePtr);
u32 XTl0_vadd_InterruptGetStatus(XTl0_vadd *InstancePtr);

#ifdef __cplusplus
}
#endif

#endif
