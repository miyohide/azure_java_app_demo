require "minitest/autorun"
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
end
