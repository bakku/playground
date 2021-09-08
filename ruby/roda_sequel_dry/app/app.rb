# frozen_string_literal: true

require "roda"

class App < Roda
  route do |r|
    opts[:loader].reload if Config.env.development?

    r.root do
      r.redirect "/home"
    end

    r.on "home" do
      r.run Routes::Home
    end
  end
end
