package com.yefan.study.boot.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class SpringBootServerSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootServerSampleApplication.class, args);
	}
}
