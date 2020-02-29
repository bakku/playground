# In this little assignment you are given a string
# of space separated numbers, and have to return
# the highest and lowest number.
#
# Example:
#
# high_and_low("1 2 3 4 5")  # return "5 1"
# high_and_low("1 2 -3 4 5") # return "5 -3"
# high_and_low("1 9 3 4 -5") # return "9 -5"
#
# Notes:
#
# * All numbers are valid Int32, no need to validate them.
# * There will always be at least one number in the input string.
# * Output string must be two numbers separated
#   by a single space, and highest number is first.

require "test/unit/assertions"

def solution(numbers)
  numbers.split(" ").map(&:to_i).yield_self do |nums|
    nums.max.to_s + " " + nums.min.to_s
  end
end

class KataTest
  class << self
    include Test::Unit::Assertions
  end

  def self.run_tests
    assert_equal "542 -214", solution("4 5 29 54 4 0 -214 542 -64 1 -3 6 -6")
  end
end

KataTest.run_tests
