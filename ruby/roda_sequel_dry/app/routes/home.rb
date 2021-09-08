# frozen_string_literal: true

require "roda"

module Routes
  class Home < Roda
    route do |r|
      r.is do
        r.get do
          pc = Models::PageClick.find_or_create(page: "home").tap do |p|
            p.clicks = p.clicks + 1
            p.save
          end

          Views::Home.new.call(clicks: pc.clicks).to_s
        end
      end
    end
  end
end
