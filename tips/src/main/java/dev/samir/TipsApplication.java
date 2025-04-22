package dev.samir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
@SpringBootApplication
public class TipsApplication {

	/**
	 * Main method to run the Spring Boot application.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(TipsApplication.class, args);
	}

}
