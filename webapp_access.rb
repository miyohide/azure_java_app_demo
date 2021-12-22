require "net/http"
require "json"

ACCESS_HOST = "localhost"
ACCESS_PORT = 8080

http = Net::HTTP.new(ACCESS_HOST, ACCESS_PORT)

# すでにあるセッションを削除
http.get("/goodbye")
# セッションを作成
response = http.get("/?name=1234567890")
session_value = response['set-cookie'].split(';')[0]

headers = { "Cookie" => session_value }

10.times do |i|
  # セッション付きで送付
  response = http.get("/", headers)

  body = JSON.parse(response.body, symbolize_names: true)

  p "send count = [#{i}], return code = [#{response.code}], body = [#{body[:content]}]"
  sleep(1)
end
