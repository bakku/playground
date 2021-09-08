# frozen_string_literal: true

RSpec.describe Routes::Home do
  let(:app) { App.freeze.app }

  context "get /" do
    it "should redirect to /home" do
      resp = get "/"

      expect(resp.status).to eq(302)
      expect(resp.location).to eq("/home")
    end
  end

  context "get /home" do
    it "should return an HTML with click count" do
      resp = get "/home"

      expect(resp.status).to eq(200)
      expect(resp.body).to include("This page was opened 1 times")
    end
  end
end
