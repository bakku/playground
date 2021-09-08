# frozen_string_literal: true

require "dry/view"

module Views
  class Home < Base
    config.paths = [File.join(__dir__, "templates")]
    config.template = "pages/home"

    expose :clicks
  end
end
