# frozen_string_literal: true

class Runner
  def self.run
    employees = [
      Employee.new("Paul", 16)
    ]

    Company.new(employees)
  end
end
