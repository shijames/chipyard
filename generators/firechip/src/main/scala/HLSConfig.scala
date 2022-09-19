package firesim.firesim

import chisel3._
import freechips.rocketchip._
import freechips.rocketchip.tile._
import freechips.rocketchip.rocket._
import freechips.rocketchip.subsystem._
import freechips.rocketchip.diplomacy._
import freechips.rocketchip.config.{Parameters, Config}
import freechips.rocketchip.tilelink._
import freechips.rocketchip.devices.tilelink._
import freechips.rocketchip.devices.debug.HasPeripheryDebugModuleImp
import freechips.rocketchip.config.Parameters
import freechips.rocketchip.util.{HeterogeneousBag}
import freechips.rocketchip.amba.axi4.AXI4Bundle
import freechips.rocketchip.config.{Field, Parameters}
import freechips.rocketchip.diplomacy.LazyModule
import utilities.{Subsystem, SubsystemModuleImp}
import icenet._
import firesim.util.DefaultFireSimHarness
import testchipip._
import testchipip.SerialAdapter.SERIAL_IF_WIDTH
import tracegen.{HasTraceGenTiles, HasTraceGenTilesModuleImp}
import sifive.blocks.devices.uart._
import java.io.File

import firesim.bridges._
import firesim.util.{WithNumNodes}
import firesim.configs._

import FireSimValName._
import hls_rocc0_vadd.HLSrocc0_vaddControl
import hls_tl0_vadd._

class WithHLSRoCCExample extends Config((site, here, up) => {
  case BuildRoCC => Seq(

    (p: Parameters) => {
        val hls_rocc0_vadd = LazyModule(new HLSrocc0_vaddControl(OpcodeSet.custom0)(p))
        hls_rocc0_vadd
    },

    (p: Parameters) => {
        val translator = LazyModule(new TranslatorExample(OpcodeSet.custom3)(p))
        translator 
    })
})

class HLSFireSimRocketChipConfig extends Config(
  new WithBootROM ++
  new WithPeripheryBusFrequency(BigInt(3200000000L)) ++
  new WithExtMemSize(0x400000000L) ++ // 16GB
  new WithoutTLMonitors ++
  new WithUARTKey ++
  new WithNICKey ++
  new WithBlockDevice ++
  new WithRocketL2TLBs(1024) ++
  new WithPerfCounters ++
  new WithInclusiveCache ++
  new WithoutClockGating ++
  new WithDefaultMemModel ++
  new WithDefaultFireSimBridges ++
  new WithHLSRoCCExample ++
  new freechips.rocketchip.system.DefaultConfig)

class FireSimTopWithHLSDUT(implicit p: Parameters) extends FireSimDUT
    with HasPeripheryHLStl0_vaddAXI {
  override lazy val module = new FireSimTopWithHLSModuleImp(this)
}

class FireSimTopWithHLSModuleImp[+L <: FireSimTopWithHLSDUT](l: L) extends FireSimModuleImp(l)
    with HasPeripheryHLStl0_vaddAXIImp

class FireSimTopWithHLS (implicit p: Parameters) extends DefaultFireSimHarness(() => new FireSimTopWithHLSDUT)

class FireSimTopWithHLSNoNICDUT(implicit p: Parameters) extends FireSimNoNICDUT
    with HasPeripheryHLStl0_vaddAXI {
  override lazy val module = new FireSimTopWithHLSNoNICModuleImp(this)
}

class FireSimTopWithHLSNoNICModuleImp[+L <: FireSimTopWithHLSNoNICDUT](l: L) extends FireSimNoNICModuleImp(l)
    with HasPeripheryHLStl0_vaddAXIImp

class FireSimTopWithHLSNoNIC (implicit p: Parameters) extends DefaultFireSimHarness(() => new FireSimTopWithHLSNoNICDUT)
