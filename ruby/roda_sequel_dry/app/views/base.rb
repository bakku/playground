# frozen_string_literal: true

require "dry/view"

module Views
  class Base < Dry::View
    config.paths = [File.join(__dir__, "templates")]
    config.layout = "application"
  end
end
