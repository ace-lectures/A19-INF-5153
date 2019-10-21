package versions

import data.INF5153_40
import eu.ace_design.island.arena.{Retrospective, Run}

object VersionMVP extends Retrospective with INF5153_40 {

  override val outputDir = "../simulations/_mvp"

  override val weeks: Set[Run] = Set(Version01, Version02, Version03)

  override def players =  all - "isz"

  trigger

}
