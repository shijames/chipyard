package chipyard
import chisel3._
import freechips.rocketchip.diplomacy.{LazyModule, ValName}
import freechips.rocketchip.config.{Parameters, Config}
import testchipip.{WithBlockDevice, BlockDeviceKey, BlockDeviceConfig}
import freechips.rocketchip.tile._
import freechips.rocketchip.subsystem._
import freechips.rocketchip.system.DefaultConfig
import freechips.rocketchip.rocket._
import freechips.rocketchip.tilelink._     
import freechips.rocketchip.devices.tilelink._
import freechips.rocketchip._
import testchipip._
import sifive.blocks.devices.uart.{PeripheryUARTKey,UARTParams}

import sifive.blocks.devices.uart._
import java.io.File
// import ConfigValName._
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

class HLSRocketConfig extends Config(
  // new WithHLSTop ++
  // new WithBootROM ++
  // new freechips.rocketchip.subsystem.WithInclusiveCache ++
  new WithHLSRoCCExample ++ 
  new freechips.rocketchip.subsystem.WithNBigCores(1) ++
  // new freechips.rocketchip.system.BaseConfig)
  new chipyard.config.AbstractConfig)

class WithHLSTop extends Config((site, here, up) => {
  case BuildTop => (clock: Clock, reset: Bool, p: Parameters) =>
      Module(LazyModule(new TopWithHLS()(p)).module)
      })

class TopWithHLS(implicit p: Parameters) extends Top 
    with HasPeripheryHLStl0_vaddAXI {
  override lazy val module = new TopWithHLSModule(this)
}

class TopWithHLSModule(l: TopWithHLS) extends TopModule(l)
    with HasPeripheryHLStl0_vaddAXIImp
