
package hls_rocc0_vadd
// import chisel3._
import chisel3.util._
import chisel3.experimental.{IntParam, BaseModule}
import Chisel._
// import chisel3.experimental.dontTouch
import freechips.rocketchip.config.{Parameters, Field}
import freechips.rocketchip.tile._
import freechips.rocketchip.util._
import vivadoHLS._

class rocc0_vadd() extends BlackBox() with HasBlackBoxResource {
	val io = IO(new Bundle {
		val ap_clk = Clock(INPUT)
		val ap_rst = Bool(INPUT)
		val ap_start = Bool(INPUT)
		val length_a_req_full_n = Bool(INPUT)
		val length_a_rsp_empty_n = Bool(INPUT)
		val length_a_datain = Bits(INPUT, width = 32)
		val b_c_req_full_n = Bool(INPUT)
		val b_c_rsp_empty_n = Bool(INPUT)
		val b_c_datain = Bits(INPUT, width = 32)
		val ap_done = Bool(OUTPUT)
		val ap_idle = Bool(OUTPUT)
		val ap_ready = Bool(OUTPUT)
		val length_a_req_din = Bool(OUTPUT)
		val length_a_req_write = Bool(OUTPUT)
		val length_a_rsp_read = Bool(OUTPUT)
		val length_a_address = Bits(OUTPUT, width = 32)
		val length_a_dataout = Bits(OUTPUT, width = 32)
		val length_a_size = Bits(OUTPUT, width = 32)
		val b_c_req_din = Bool(OUTPUT)
		val b_c_req_write = Bool(OUTPUT)
		val b_c_rsp_read = Bool(OUTPUT)
		val b_c_address = Bits(OUTPUT, width = 32)
		val b_c_dataout = Bits(OUTPUT, width = 32)
		val b_c_size = Bits(OUTPUT, width = 32)
		val ap_return = Bits(OUTPUT, width = 32)
	})
    addResource("/vsrc/rocc0_vadd.v")
}
//class HLSrocc0_vaddBlackbox() extends Module {
//	val scalar_io_dataWidths = List()
//	val scalar_io_argLoc = List() //Lists the argument number of the scalar_io
//	val ap_bus_addrWidths = List(64,64)
//	val ap_bus_dataWidths = List(32,32)
//	val ap_bus_argLoc = List(0,1)
//	val io = IO(new Bundle {
//	val ap = IO(new ApCtrlIO(dataWidth = 32))
//	val ap_bus = IO(HeterogeneousBag(ap_bus_addrWidths.zip(ap_bus_dataWidths).map {
//      case (aw, dw) => new ApBusIO(dw, aw)
//    }))

//})

//	val bb = Module(new rocc0_vadd())

//	bb.io.ap_start := io.ap.start
//	io.ap.done := bb.io.ap_done
//	io.ap.idle := bb.io.ap_idle
//	io.ap.ready := bb.io.ap_ready
//	io.ap.rtn := bb.io.ap_return
//	bb.io.ap_rst := reset
//	bb.io.ap_clk := clock
//	io.ap_bus(0).req.din := bb.io.length_a_req_din 
//	bb.io.length_a_req_full_n := io.ap_bus(0).req_full_n 
//	io.ap_bus(0).req_write := bb.io.length_a_req_write
//	bb.io.length_a_rsp_empty_n := io.ap_bus(0).rsp_empty_n
//	io.ap_bus(0).rsp_read := bb.io.length_a_rsp_read
//	io.ap_bus(0).req.address := bb.io.length_a_address
//	bb.io.length_a_datain := io.ap_bus(0).rsp.datain
//	io.ap_bus(0).req.dataout := bb.io.length_a_dataout
//	io.ap_bus(0).req.size := bb.io.length_a_size
//	io.ap_bus(1).req.din := bb.io.b_c_req_din 
//	bb.io.b_c_req_full_n := io.ap_bus(1).req_full_n 
//	io.ap_bus(1).req_write := bb.io.b_c_req_write
//	bb.io.b_c_rsp_empty_n := io.ap_bus(1).rsp_empty_n
//	io.ap_bus(1).rsp_read := bb.io.b_c_rsp_read
//	io.ap_bus(1).req.address := bb.io.b_c_address
//	bb.io.b_c_datain := io.ap_bus(1).rsp.datain
//	io.ap_bus(1).req.dataout := bb.io.b_c_dataout
//	io.ap_bus(1).req.size := bb.io.b_c_size
//}
class HLSrocc0_vaddBlackbox() extends Module {
  val scalar_io_dataWidths = List()
  val scalar_io_argLoc = List() //Lists the argument number of the scalar_io
  val ap_bus_addrWidths = List(64,64)
  val ap_bus_dataWidths = List(32,32)
  val ap_bus_argLoc = List(0,1)
  val io = IO(new Bundle {
      val ap = new ApCtrlIO(dataWidth = 32) })
  val bb = Module(new rocc0_vadd())
}
