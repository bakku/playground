# frozen_string_literal: true

require "rubygems"
require "bundler/setup"

require_relative "./config"

Config.setup!
run App.freeze.app
