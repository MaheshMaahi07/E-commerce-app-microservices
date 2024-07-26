package com.mahesh.config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/** The @EnableConfigServer annotation is a Spring Boot annotation that enables the Config Server feature
in a Spring Boot application. It is used to create
a Config Server that can manage and serve configuration data to other applications. **/


@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}

