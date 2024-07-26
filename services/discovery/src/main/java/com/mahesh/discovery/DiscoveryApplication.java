package com.mahesh.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**The @EnableEurekaServer annotation is a Spring Boot annotation that enables the Eureka Server feature
 in a Spring Boot application. It is used to create a Eureka Server,
which is a registry of services that allows clients to discover and communicate with each other.
 **/

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryApplication.class, args);
	}

}
