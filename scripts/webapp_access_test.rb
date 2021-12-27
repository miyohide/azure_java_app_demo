require "minitest/autorun"
require "webmock/minitest"
require "json"
require_relative "webapp_access"

class WebappAccessTest < Minitest::Test
  def test_parse_arguments_short_options
    @web_access = WebappAccess.new(["-c", "100", "-s", "200", "-n", "hoge"])
    h = {count: "100", sleep: "200", name: "hoge"}
    assert_equal h, @web_access.parse_arguments
  end

  def test_parse_arguments_long_options
    @web_access = WebappAccess.new(["--count", "100", "--sleep", "200", "--name", "hoge"])
    h = {count: "100", sleep: "200", name: "hoge"}
    assert_equal h, @web_access.parse_arguments
  end

  def test_access
    name_param = "hoge1"
    cookie_data = "data1"
    stub_request(:any, "localhost:8080/goodbye")
    stub_request(:any, "localhost:8080/?name=#{name_param}").
      to_return(headers: {"set-cookie" => "#{cookie_data};data2;data3"})
    stub_request(:any, "localhost:8080/").
      with(headers: {"Cookie" => /#{cookie_data}/}).
      to_return(body: {content: "aaa"}.to_json)
    @web_access = WebappAccess.new(["--count", "3", "--sleep", "200", "--name", name_param])
    @web_access.access
  end
end
