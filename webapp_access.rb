require "net/http"
require "json"

# すでにあるセッションを削除
uri = URI.parse("http://localhost:8080/goodbye")
Net::HTTP.get_response(uri)

# セッションを作成
uri = URI.parse("http://localhost:8080/?name=1234567890")
response = Net::HTTP.get_response(uri)
session_value = response['set-cookie'].split(';')[0]

# セッション付きで送付
uri = URI.parse("http://localhost:8080/")
http = Net::HTTP.new(uri.host, uri.port)
headers = { "Cookie" => session_value }
response = http.get(uri.path, headers)

body = JSON.parse(response.body, symbolize_names: true)

p "return code = [#{response.code}], body = [#{body[:content]}]"
