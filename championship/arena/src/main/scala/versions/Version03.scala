package versions

import data._
import eu.ace_design.island.arena.Run
import eu.ace_design.island.bot.IExplorerRaid
import eu.ace_design.island.game.{Directions, Plane}
import eu.ace_design.island.map.IslandMap
import eu.ace_design.island.map.resources.Resource
import eu.ace_design.island.stdlib.Resources._

object Version03 extends Run with INF5153_40 {

  override val number: String = "03"
  override def outputDir: String = s"../simulations/_mvp/$number"

  override val seed: Long                = Islands.s03
  override lazy val theIsland: IslandMap = Islands.week03

  override val crew: Int    = 2
  override val budget: Int  = 30000
  override val plane: Plane = Plane(1, 1, Directions.EAST)
  override val objectives: Set[(Resource, Int)] = Set((WOOD, 1000))

  override def players:  Map[String, Class[_ <: IExplorerRaid]] = all //- "isz" - "isu" - "isj" - "isd"

}
