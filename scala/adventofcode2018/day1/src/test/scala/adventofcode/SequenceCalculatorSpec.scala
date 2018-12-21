package adventofcode

import org.scalatest._

class SequenceCalculatorSpec extends FunSpec {
  describe("#run") {
    it("should take a seq of numbers and return the value") {
      assert(SequenceCalculator.run(Seq(1, -1, 5, -6)) == -1)
    }
  }
}
