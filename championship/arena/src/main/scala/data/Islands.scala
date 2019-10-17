package data

import eu.ace_design.island.dsl.DiSLand
import eu.ace_design.island.map.IslandMap
import eu.ace_design.island.map.processes.AssignPitch
import eu.ace_design.island.stdlib.WhittakerDiagrams

object Islands extends DiSLand {

  val s01 = 0x447F85B2DE4A00C7L
  lazy val week01: IslandMap = {
    createIsland shapedAs ellipsis(75.percent, 33.percent, 47) withSize 1600 having 2000.faces builtWith Seq(
      plateau(10), flowing(rivers = 25, distance = 0.9), withMoisture(soils.wet, distance = 300),
      AssignPitch, usingBiomes(WhittakerDiagrams.caribbean)) usingSeed s01
  }

  val s02 = 0x447F85B2DE4A00C7L
  lazy val week02: IslandMap = {
    createIsland shapedAs ellipsis(62.percent, 42.percent, 90) withSize 1600 having 2000.faces builtWith Seq(
      plateau(30), flowing(rivers = 5, distance = 0.9), withMoisture(soils.normal, distance = 900),
      AssignPitch, usingBiomes(WhittakerDiagrams.caribbean)) //usingSeed s02
  }

  val s03 = 0x447F85B2DE4A00C7L
  lazy val week03: IslandMap = {
    createIsland shapedAs donut(60.percent, 25.percent) withSize 1600 having 2000.faces builtWith Seq(
      plateau(10), flowing(rivers = 10, distance = 0.6), withMoisture(soils.normal, distance = 100),
      AssignPitch, usingBiomes(WhittakerDiagrams.caribbean)) //usingSeed s02
  }

}