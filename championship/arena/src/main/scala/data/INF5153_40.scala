package data

import eu.ace_design.island.arena.Teams
import eu.ace_design.island.bot.IExplorerRaid

trait INF5153_40 extends Teams {


  val teams:  Map[String, Class[_ <: IExplorerRaid]] = Map(
    //"isd" ->  classOf[ca.uqam.inf5153.island.isd.Explorer],
    "ise" ->  classOf[ca.uqam.inf5153.island.ise.Explorer],
    "isg" ->  classOf[ca.uqam.inf5153.island.isg.Explorer],
    //"isj" ->  classOf[ca.uqam.inf5153.island.isj.Explorer],
    //"isl" ->  classOf[ca.uqam.inf5153.island.isl.Explorer],
    //"iso" ->  classOf[ca.uqam.inf5153.island.iso.Explorer],
    "isr" ->  classOf[ca.uqam.inf5153.island.isr.Explorer],
    "iss" ->  classOf[ca.uqam.inf5153.island.iss.Explorer],
    "ist" ->  classOf[ca.uqam.inf5153.island.ist.Explorer],
    //"isu" ->  classOf[ca.uqam.inf5153.island.isu.Explorer],
    "isw" ->  classOf[ca.uqam.inf5153.island.isw.Explorer]
    //"isz" ->  classOf[ca.uqam.inf5153.island.isz.Explorer]
  )

  def all:  Map[String, Class[_ <: IExplorerRaid]] = teams

}