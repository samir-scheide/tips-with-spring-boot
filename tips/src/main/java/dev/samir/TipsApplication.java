package dev.samir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Mais class containing the main method to run the Spring Boot application.
 * This class is annotated with @SpringBootApplication, which is a convenience
 * annotation that adds all of the following:
 * - @Configuration: Tags the class as a source of bean definitions for the application context.
 * - @EnableAutoConfiguration: Enables Spring Boot's auto-configuration mechanism.
 * - @ComponentScan: Enables component scanning so that the application can find and register beans.
 */
@EnableScheduling
@EnableRedisRepositories
@SpringBootApplication
public class TipsApplication {

	/**
	 * Main method to run the Spring Boot application.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(TipsApplication.class, args);
	}
	
	@Bean
	RedisConnectionFactory connectionFactory() {
		return new LettuceConnectionFactory();
	}

	@Bean
	RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		// The GenericJackson2JsonRedisSerializer uses Jackson to serialize and deserialize
		// objects to and from JSON, which is a widely used format for data interchange.
		// This allows for storing complex objects in Redis without needing to convert them to strings or other formats.
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}

}
