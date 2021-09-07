# frozen_string_literal: true

class Employee
  attr_reader :name, :age

  def initialize(name, age)
    @name = name
    @age = age
  end
end
