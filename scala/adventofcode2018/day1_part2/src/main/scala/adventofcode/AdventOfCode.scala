package adventofcode

import scala.io.Source

object AdventOfCode extends App {
  val originalNums = Source.fromFile("nums.txt").getLines.toList.map(_.toInt)
  //val originalNums = Seq(3, 3, 4, -2, -4)
  println(recCheckList(0, originalNums, Seq[Int]()))

  def recCheckList(curr: Int, nums: Seq[Int], known: Seq[Int]): Int = nums match {
    case Nil => recCheckList(curr, originalNums, known)
    case h :: t => {
      val res = curr+h

      if (known.contains(res)) res
      else recCheckList(res, t, known :+ res)
    }
  }
}
