package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

@Configuration
@EnableRedisHttpSession
public class SessionConfig extends AbstractHttpSessionApplicationInitializer {
  @Value("${spring.redis.host}")
  private String redis_host;
  @Value("${spring.redis.password}")
  private String redis_pass;
  @Value("${spring.redis.port}")
  private String redis_port;
  @Bean
  public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
      return new GenericJackson2JsonRedisSerializer();
  }
  @Bean
  public static ConfigureRedisAction configureRedisAction() {
      return ConfigureRedisAction.NO_OP;
  }

  @Bean
  public LettuceConnectionFactory connectionFactory() {
      RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
      redisStandaloneConfiguration.setHostName(this.redis_host);
      redisStandaloneConfiguration.setPassword(this.redis_pass);
      redisStandaloneConfiguration.setPort(Integer.parseInt(this.redis_port));
      LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder().useSsl().build();
      return new LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfiguration);
  }
}
