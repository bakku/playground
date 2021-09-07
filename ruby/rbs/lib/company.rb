# frozen_string_literal: true

class Company
  attr_reader :employees

  def initialize(employees)
    @employees = employees
  end
end
