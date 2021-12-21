require "net/http"

uri = URI.parse("http://localhost:8080/")
response = Net::HTTP.get_response(uri)

p "return code = [#{response.code}], body = [#{response.body}]"
