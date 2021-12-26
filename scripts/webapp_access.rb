require "net/http"
require "json"
require "optparse"
require "logger"

class WebappAccess
  ACCESS_HOST = "localhost"
  ACCESS_PORT = 8080

  def initialize(args)
    # ロガーの設定
    @logger = Logger.new(STDOUT)
    @logger.level = Logger::INFO
    @args = args
    @params = parse_arguments
  end

  def parse_arguments
    opt = OptionParser.new
    params = {}

    opt.on("-c", "--count=VAL", "number of requests") { |v| v }
    opt.on("-s", "--sleep=VAL", "sleep time (millisecond)") { |v| v }
    opt.on("-n", "--name=VAL", "display name") { |v| v }
    
    opt.parse!(@args, into: params)

    @logger.debug("count = [#{params[:count]}], sleep = [#{params[:sleep]}], name = [#{params[:name]}]")

    params
  end

  def access
    http = Net::HTTP.new(ACCESS_HOST, ACCESS_PORT)

    # すでにあるセッションを削除
    http.get("/goodbye")
    # セッションを作成
    response = http.get("/?name=#{@params[:name]}")
    session_value = response['set-cookie'].split(';')[0]
    
    headers = { "Cookie" => session_value }
    
    @params[:count].to_i.times do |i|
      # セッション付きで送付
      response = http.get("/", headers)
    
      body = JSON.parse(response.body, symbolize_names: true)
    
      @logger.info("send count = [#{i}], return code = [#{response.code}], body = [#{body[:content]}]")
      sleep(@params[:sleep].to_f/1000)
    end        
  end
end
