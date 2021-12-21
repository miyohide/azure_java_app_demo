require "net/http"
require "json"

uri = URI.parse("http://localhost:8080/")
response = Net::HTTP.get_response(uri)

body = JSON.parse(response.body, symbolize_names: true)

p "return code = [#{response.code}], body = [#{body[:content]}]"
