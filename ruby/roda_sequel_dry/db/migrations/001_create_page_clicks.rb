# frozen_string_literal: true

Sequel.migration do
  change do
    create_table(:page_clicks) do
      primary_key :id
      String :page, null: false, index: true
      Bignum :clicks, default: 0
      DateTime :created_at, null: false
      DateTime :updated_at, null: false
    end
  end
end
