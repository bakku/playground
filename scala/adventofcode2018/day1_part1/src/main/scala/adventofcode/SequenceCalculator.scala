package adventofcode

object SequenceCalculator {
  def run(numbers: Seq[Int]) = numbers.foldLeft(0)(_ + _)
}
