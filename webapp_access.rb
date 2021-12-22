require "net/http"
require "json"
require "optparse"

ACCESS_HOST = "localhost"
ACCESS_PORT = 8080

# 引数解析
opt = OptionParser.new
params = {}

opt.on("-c VAL") { |v| v }
opt.on("-s VAL") { |v| v }

opt.parse!(ARGV, into: params)

http = Net::HTTP.new(ACCESS_HOST, ACCESS_PORT)

# すでにあるセッションを削除
http.get("/goodbye")
# セッションを作成
response = http.get("/?name=1234567890")
session_value = response['set-cookie'].split(';')[0]

headers = { "Cookie" => session_value }

params[:c].to_i.times do |i|
  # セッション付きで送付
  response = http.get("/", headers)

  body = JSON.parse(response.body, symbolize_names: true)

  p "send count = [#{i}], return code = [#{response.code}], body = [#{body[:content]}]"
  sleep(params[:s].to_i)
end
