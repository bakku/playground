package adventofcode

import scala.io.Source

object AdventOfCode extends App {
  val nums = Source.fromFile("nums.txt").getLines.toList.map(_.toInt)
  println(SequenceCalculator.run(nums))
}
