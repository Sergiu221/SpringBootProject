package com.sergiu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.sergiu.files.FileCustomCSV;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.getPoolConfig().setMaxIdle(30);
		jedisConnectionFactory.getPoolConfig().setMinIdle(10);
		return jedisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, FileCustomCSV> redisTemplate() {
		final RedisTemplate<String, FileCustomCSV> template = new RedisTemplate<String, FileCustomCSV>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
}
