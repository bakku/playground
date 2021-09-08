# frozen_string_literal: true

require "zeitwerk"
require "sequel"
require "roda"

class Config
  class << self
    def setup!
      setup_zeitwerk
      setup_sequel
    end

    def apply_migrations
      Sequel.extension :migration
      Sequel::Migrator.run(db, "db/migrations")
    end

    def env
      @_env ||= Class.new do
        def development? = inner_env == "development"

        def test? = inner_env == "test"

        def to_s = inner_env

        private

        def inner_env = @_inner_env ||= ENV.fetch("APP_ENV", "development")
      end.new
    end

    def db_url
      @_db_url ||= begin
        path = File.expand_path("../db/#{env}.db", __FILE__)
        "sqlite://#{path}"
      end
    end

    def db
      @_db ||= Sequel.connect(db_url)
    end
    alias connect_to_db! db

    private

    def setup_zeitwerk
      loader = Zeitwerk::Loader.new
      loader.push_dir("#{__dir__}/app")

      if env.development?
        Roda.opts[:loader] = loader
        loader.enable_reloading
      end

      loader.setup
    end

    def setup_sequel
      Sequel::Model.plugin :timestamps, allow_manual_update: true, update_on_create: true

      connect_to_db!

      apply_migrations if env.test?
    end
  end
end
