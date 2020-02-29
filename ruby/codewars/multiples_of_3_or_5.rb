# If we list all the natural numbers below 10 that are multiples
# of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
#
# Finish the solution so that it returns the sum of all the multiples of 3 or 5 below the number passed in.
#
# Note: If the number is a multiple of both 3 and 5, only count it once.

require "test/unit/assertions"

def solution(number)
  (1...number).to_a.select { |n| n % 3 == 0 || n % 5 == 0 }.sum
end

class KataTest
  class << self
    include Test::Unit::Assertions
  end

  def self.run_tests
    assert_equal 23,   solution(10)
    assert_equal 78,   solution(20)
    assert_equal 9168, solution(200)
  end
end

KataTest.run_tests
