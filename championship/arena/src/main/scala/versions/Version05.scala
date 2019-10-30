package versions

import data._
import eu.ace_design.island.arena.Run
import eu.ace_design.island.bot.IExplorerRaid
import eu.ace_design.island.game.{Directions, Plane}
import eu.ace_design.island.map.IslandMap
import eu.ace_design.island.map.resources.Resource
import eu.ace_design.island.stdlib.Resources._

object Version05 extends Run with INF5153_40 {

  override val number: String = "05"
  override def outputDir: String = s"../simulations/$number"
  override def timeout = 5000

  override val seed: Long                = Islands.s05
  override lazy val theIsland: IslandMap = Islands.week05

  override val crew: Int    = 2
  override val budget: Int  = 15000
  override val plane: Plane = Plane(1, 1, Directions.EAST)
  override val objectives: Set[(Resource, Int)] = Set((WOOD, 1000))


  override def players:  Map[String, Class[_ <: IExplorerRaid]] =
    all - "isj" - "isr" - "isw" - "isz"

}
