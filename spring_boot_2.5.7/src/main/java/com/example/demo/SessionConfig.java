package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

@Configuration
@EnableRedisHttpSession
public class SessionConfig extends AbstractHttpSessionApplicationInitializer {
  @Bean
  public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
      return new GenericJackson2JsonRedisSerializer();
  }
  @Bean
  public static ConfigureRedisAction configureRedisAction() {
      return ConfigureRedisAction.NO_OP;
  }
}
