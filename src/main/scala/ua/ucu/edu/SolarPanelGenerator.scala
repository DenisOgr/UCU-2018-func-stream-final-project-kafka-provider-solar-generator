package ua.ucu.edu

import ua.ucu.edu.models.Signal

import scala.util.Random

object SolarPanelGenerator {
  var random: Random.type = scala.util.Random

  def generate(panel_uid: String): Signal =  Signal(
    panel_uid,
    random.nextDouble(),
    random.nextDouble(),
    random.nextDouble(),
    random.nextDouble(),
    random.nextDouble()
  )
}
