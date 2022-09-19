// ==============================================================
// File generated by Vivado(TM) HLS - High-Level Synthesis from C, C++ and SystemC
// Version: 2018.2
// Copyright (C) 1986-2018 Xilinx, Inc. All Rights Reserved.
// 
// ==============================================================

/***************************** Include Files *********************************/
#include "xtl0_vadd.h"

/************************** Function Implementation *************************/
#ifndef __linux__
int XTl0_vadd_CfgInitialize(XTl0_vadd *InstancePtr, XTl0_vadd_Config *ConfigPtr) {
    Xil_AssertNonvoid(InstancePtr != NULL);
    Xil_AssertNonvoid(ConfigPtr != NULL);

    InstancePtr->Control_BaseAddress = ConfigPtr->Control_BaseAddress;
    InstancePtr->IsReady = XIL_COMPONENT_IS_READY;

    return XST_SUCCESS;
}
#endif

void XTl0_vadd_Start(XTl0_vadd *InstancePtr) {
    u32 Data;

    Xil_AssertVoid(InstancePtr != NULL);
    Xil_AssertVoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    Data = XTl0_vadd_ReadReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_AP_CTRL) & 0x80;
    XTl0_vadd_WriteReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_AP_CTRL, Data | 0x01);
}

u32 XTl0_vadd_IsDone(XTl0_vadd *InstancePtr) {
    u32 Data;

    Xil_AssertNonvoid(InstancePtr != NULL);
    Xil_AssertNonvoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    Data = XTl0_vadd_ReadReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_AP_CTRL);
    return (Data >> 1) & 0x1;
}

u32 XTl0_vadd_IsIdle(XTl0_vadd *InstancePtr) {
    u32 Data;

    Xil_AssertNonvoid(InstancePtr != NULL);
    Xil_AssertNonvoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    Data = XTl0_vadd_ReadReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_AP_CTRL);
    return (Data >> 2) & 0x1;
}

u32 XTl0_vadd_IsReady(XTl0_vadd *InstancePtr) {
    u32 Data;

    Xil_AssertNonvoid(InstancePtr != NULL);
    Xil_AssertNonvoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    Data = XTl0_vadd_ReadReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_AP_CTRL);
    // check ap_start to see if the pcore is ready for next input
    return !(Data & 0x1);
}

void XTl0_vadd_EnableAutoRestart(XTl0_vadd *InstancePtr) {
    Xil_AssertVoid(InstancePtr != NULL);
    Xil_AssertVoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    XTl0_vadd_WriteReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_AP_CTRL, 0x80);
}

void XTl0_vadd_DisableAutoRestart(XTl0_vadd *InstancePtr) {
    Xil_AssertVoid(InstancePtr != NULL);
    Xil_AssertVoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    XTl0_vadd_WriteReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_AP_CTRL, 0);
}

u32 XTl0_vadd_Get_return(XTl0_vadd *InstancePtr) {
    u32 Data;

    Xil_AssertNonvoid(InstancePtr != NULL);
    Xil_AssertNonvoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    Data = XTl0_vadd_ReadReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_AP_RETURN);
    return Data;
}
void XTl0_vadd_Set_a(XTl0_vadd *InstancePtr, u64 Data) {
    Xil_AssertVoid(InstancePtr != NULL);
    Xil_AssertVoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    XTl0_vadd_WriteReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_A_DATA, (u32)(Data));
    XTl0_vadd_WriteReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_A_DATA + 4, (u32)(Data >> 32));
}

u64 XTl0_vadd_Get_a(XTl0_vadd *InstancePtr) {
    u64 Data;

    Xil_AssertNonvoid(InstancePtr != NULL);
    Xil_AssertNonvoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    Data = XTl0_vadd_ReadReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_A_DATA);
    Data += (u64)XTl0_vadd_ReadReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_A_DATA + 4) << 32;
    return Data;
}

void XTl0_vadd_Set_b(XTl0_vadd *InstancePtr, u64 Data) {
    Xil_AssertVoid(InstancePtr != NULL);
    Xil_AssertVoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    XTl0_vadd_WriteReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_B_DATA, (u32)(Data));
    XTl0_vadd_WriteReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_B_DATA + 4, (u32)(Data >> 32));
}

u64 XTl0_vadd_Get_b(XTl0_vadd *InstancePtr) {
    u64 Data;

    Xil_AssertNonvoid(InstancePtr != NULL);
    Xil_AssertNonvoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    Data = XTl0_vadd_ReadReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_B_DATA);
    Data += (u64)XTl0_vadd_ReadReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_B_DATA + 4) << 32;
    return Data;
}

void XTl0_vadd_Set_c(XTl0_vadd *InstancePtr, u64 Data) {
    Xil_AssertVoid(InstancePtr != NULL);
    Xil_AssertVoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    XTl0_vadd_WriteReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_C_DATA, (u32)(Data));
    XTl0_vadd_WriteReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_C_DATA + 4, (u32)(Data >> 32));
}

u64 XTl0_vadd_Get_c(XTl0_vadd *InstancePtr) {
    u64 Data;

    Xil_AssertNonvoid(InstancePtr != NULL);
    Xil_AssertNonvoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    Data = XTl0_vadd_ReadReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_C_DATA);
    Data += (u64)XTl0_vadd_ReadReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_C_DATA + 4) << 32;
    return Data;
}

void XTl0_vadd_Set_length_r(XTl0_vadd *InstancePtr, u32 Data) {
    Xil_AssertVoid(InstancePtr != NULL);
    Xil_AssertVoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    XTl0_vadd_WriteReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_LENGTH_R_DATA, Data);
}

u32 XTl0_vadd_Get_length_r(XTl0_vadd *InstancePtr) {
    u32 Data;

    Xil_AssertNonvoid(InstancePtr != NULL);
    Xil_AssertNonvoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    Data = XTl0_vadd_ReadReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_LENGTH_R_DATA);
    return Data;
}

void XTl0_vadd_InterruptGlobalEnable(XTl0_vadd *InstancePtr) {
    Xil_AssertVoid(InstancePtr != NULL);
    Xil_AssertVoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    XTl0_vadd_WriteReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_GIE, 1);
}

void XTl0_vadd_InterruptGlobalDisable(XTl0_vadd *InstancePtr) {
    Xil_AssertVoid(InstancePtr != NULL);
    Xil_AssertVoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    XTl0_vadd_WriteReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_GIE, 0);
}

void XTl0_vadd_InterruptEnable(XTl0_vadd *InstancePtr, u32 Mask) {
    u32 Register;

    Xil_AssertVoid(InstancePtr != NULL);
    Xil_AssertVoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    Register =  XTl0_vadd_ReadReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_IER);
    XTl0_vadd_WriteReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_IER, Register | Mask);
}

void XTl0_vadd_InterruptDisable(XTl0_vadd *InstancePtr, u32 Mask) {
    u32 Register;

    Xil_AssertVoid(InstancePtr != NULL);
    Xil_AssertVoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    Register =  XTl0_vadd_ReadReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_IER);
    XTl0_vadd_WriteReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_IER, Register & (~Mask));
}

void XTl0_vadd_InterruptClear(XTl0_vadd *InstancePtr, u32 Mask) {
    Xil_AssertVoid(InstancePtr != NULL);
    Xil_AssertVoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    XTl0_vadd_WriteReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_ISR, Mask);
}

u32 XTl0_vadd_InterruptGetEnabled(XTl0_vadd *InstancePtr) {
    Xil_AssertNonvoid(InstancePtr != NULL);
    Xil_AssertNonvoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    return XTl0_vadd_ReadReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_IER);
}

u32 XTl0_vadd_InterruptGetStatus(XTl0_vadd *InstancePtr) {
    Xil_AssertNonvoid(InstancePtr != NULL);
    Xil_AssertNonvoid(InstancePtr->IsReady == XIL_COMPONENT_IS_READY);

    return XTl0_vadd_ReadReg(InstancePtr->Control_BaseAddress, XTL0_VADD_CONTROL_ADDR_ISR);
}

