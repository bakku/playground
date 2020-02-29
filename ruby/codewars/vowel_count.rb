# Return the number (count) of vowels in the given string.
#
# We will consider a, e, i, o, and u as vowels for this Kata.
#
# The input string will only consist of lower case letters and/or spaces.

require "test/unit/assertions"

def solution(input)
  input.split("").select { |c| "aeiou".include?(c) }.count
end

class KataTest
  class << self
    include Test::Unit::Assertions
  end

  def self.run_tests
    assert_equal 5, solution("abracadabra")
  end
end

KataTest.run_tests
