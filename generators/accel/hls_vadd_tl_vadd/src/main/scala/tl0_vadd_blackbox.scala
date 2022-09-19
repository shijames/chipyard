
package hls_tl0_vadd
import Chisel._
import freechips.rocketchip.config.{Parameters, Field}
import freechips.rocketchip.tile._
import freechips.rocketchip.util._

class tl0_vadd() extends BlackBox() {
val C_S_AXI_CONTROL_DATA_WIDTH = 32
val C_S_AXI_CONTROL_ADDR_WIDTH = 7
val C_S_AXI_DATA_WIDTH = 32
val C_M_AXI_GMEM0_ID_WIDTH = 1
val C_M_AXI_GMEM0_ADDR_WIDTH = 64
val C_M_AXI_GMEM0_DATA_WIDTH = 32
val C_M_AXI_GMEM0_AWUSER_WIDTH = 1
val C_M_AXI_GMEM0_ARUSER_WIDTH = 1
val C_M_AXI_GMEM0_WUSER_WIDTH = 1
val C_M_AXI_GMEM0_RUSER_WIDTH = 1
val C_M_AXI_GMEM0_BUSER_WIDTH = 1
val C_M_AXI_GMEM0_USER_VALUE = 0
val C_M_AXI_GMEM0_PROT_VALUE = 0
val C_M_AXI_GMEM0_CACHE_VALUE = 3
val C_M_AXI_DATA_WIDTH = 32
val C_S_AXI_CONTROL_WSTRB_WIDTH = (32 / 8)
val C_S_AXI_WSTRB_WIDTH = (32 / 8)
val C_M_AXI_GMEM0_WSTRB_WIDTH = (32 / 8)
val C_M_AXI_WSTRB_WIDTH = (32 / 8)
	val io = new Bundle {
		val ap_clk = Clock(INPUT)
		val ap_rst_n = Bits(INPUT, width = 1)
		val m_axi_gmem0_AWREADY = Bits(INPUT, width = 1)
		val m_axi_gmem0_WREADY = Bits(INPUT, width = 1)
		val m_axi_gmem0_ARREADY = Bits(INPUT, width = 1)
		val m_axi_gmem0_RVALID = Bits(INPUT, width = 1)
		val m_axi_gmem0_RDATA = Bits(INPUT, width = C_M_AXI_GMEM0_DATA_WIDTH)
		val m_axi_gmem0_RLAST = Bits(INPUT, width = 1)
		val m_axi_gmem0_RID = Bits(INPUT, width = C_M_AXI_GMEM0_ID_WIDTH)
		val m_axi_gmem0_RUSER = Bits(INPUT, width = C_M_AXI_GMEM0_RUSER_WIDTH)
		val m_axi_gmem0_RRESP = Bits(INPUT, width = 2)
		val m_axi_gmem0_BVALID = Bits(INPUT, width = 1)
		val m_axi_gmem0_BRESP = Bits(INPUT, width = 2)
		val m_axi_gmem0_BID = Bits(INPUT, width = C_M_AXI_GMEM0_ID_WIDTH)
		val m_axi_gmem0_BUSER = Bits(INPUT, width = C_M_AXI_GMEM0_BUSER_WIDTH)
		val s_axi_control_AWVALID = Bits(INPUT, width = 1)
		val s_axi_control_AWADDR = Bits(INPUT, width = C_S_AXI_CONTROL_ADDR_WIDTH)
		val s_axi_control_WVALID = Bits(INPUT, width = 1)
		val s_axi_control_WDATA = Bits(INPUT, width = C_S_AXI_CONTROL_DATA_WIDTH)
		val s_axi_control_WSTRB = Bits(INPUT, width = C_S_AXI_CONTROL_WSTRB_WIDTH)
		val s_axi_control_ARVALID = Bits(INPUT, width = 1)
		val s_axi_control_ARADDR = Bits(INPUT, width = C_S_AXI_CONTROL_ADDR_WIDTH)
		val s_axi_control_RREADY = Bits(INPUT, width = 1)
		val s_axi_control_BREADY = Bits(INPUT, width = 1)
		val m_axi_gmem0_AWVALID = Bits(OUTPUT, width = 1)
		val m_axi_gmem0_AWADDR = Bits(OUTPUT, width = C_M_AXI_GMEM0_ADDR_WIDTH)
		val m_axi_gmem0_AWID = Bits(OUTPUT, width = C_M_AXI_GMEM0_ID_WIDTH)
		val m_axi_gmem0_AWLEN = Bits(OUTPUT, width = 8)
		val m_axi_gmem0_AWSIZE = Bits(OUTPUT, width = 3)
		val m_axi_gmem0_AWBURST = Bits(OUTPUT, width = 2)
		val m_axi_gmem0_AWLOCK = Bits(OUTPUT, width = 2)
		val m_axi_gmem0_AWCACHE = Bits(OUTPUT, width = 4)
		val m_axi_gmem0_AWPROT = Bits(OUTPUT, width = 3)
		val m_axi_gmem0_AWQOS = Bits(OUTPUT, width = 4)
		val m_axi_gmem0_AWREGION = Bits(OUTPUT, width = 4)
		val m_axi_gmem0_AWUSER = Bits(OUTPUT, width = C_M_AXI_GMEM0_AWUSER_WIDTH)
		val m_axi_gmem0_WVALID = Bits(OUTPUT, width = 1)
		val m_axi_gmem0_WDATA = Bits(OUTPUT, width = C_M_AXI_GMEM0_DATA_WIDTH)
		val m_axi_gmem0_WSTRB = Bits(OUTPUT, width = C_M_AXI_GMEM0_WSTRB_WIDTH)
		val m_axi_gmem0_WLAST = Bits(OUTPUT, width = 1)
		val m_axi_gmem0_WID = Bits(OUTPUT, width = C_M_AXI_GMEM0_ID_WIDTH)
		val m_axi_gmem0_WUSER = Bits(OUTPUT, width = C_M_AXI_GMEM0_WUSER_WIDTH)
		val m_axi_gmem0_ARVALID = Bits(OUTPUT, width = 1)
		val m_axi_gmem0_ARADDR = Bits(OUTPUT, width = C_M_AXI_GMEM0_ADDR_WIDTH)
		val m_axi_gmem0_ARID = Bits(OUTPUT, width = C_M_AXI_GMEM0_ID_WIDTH)
		val m_axi_gmem0_ARLEN = Bits(OUTPUT, width = 8)
		val m_axi_gmem0_ARSIZE = Bits(OUTPUT, width = 3)
		val m_axi_gmem0_ARBURST = Bits(OUTPUT, width = 2)
		val m_axi_gmem0_ARLOCK = Bits(OUTPUT, width = 2)
		val m_axi_gmem0_ARCACHE = Bits(OUTPUT, width = 4)
		val m_axi_gmem0_ARPROT = Bits(OUTPUT, width = 3)
		val m_axi_gmem0_ARQOS = Bits(OUTPUT, width = 4)
		val m_axi_gmem0_ARREGION = Bits(OUTPUT, width = 4)
		val m_axi_gmem0_ARUSER = Bits(OUTPUT, width = C_M_AXI_GMEM0_ARUSER_WIDTH)
		val m_axi_gmem0_RREADY = Bits(OUTPUT, width = 1)
		val m_axi_gmem0_BREADY = Bits(OUTPUT, width = 1)
		val s_axi_control_AWREADY = Bits(OUTPUT, width = 1)
		val s_axi_control_WREADY = Bits(OUTPUT, width = 1)
		val s_axi_control_ARREADY = Bits(OUTPUT, width = 1)
		val s_axi_control_RVALID = Bits(OUTPUT, width = 1)
		val s_axi_control_RDATA = Bits(OUTPUT, width = C_S_AXI_CONTROL_DATA_WIDTH)
		val s_axi_control_RRESP = Bits(OUTPUT, width = 2)
		val s_axi_control_BVALID = Bits(OUTPUT, width = 1)
		val s_axi_control_BRESP = Bits(OUTPUT, width = 2)
		val interrupt = Bits(OUTPUT, width = 1)
	}
}
