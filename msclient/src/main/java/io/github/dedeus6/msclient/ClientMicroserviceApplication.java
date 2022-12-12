package io.github.dedeus6.msclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ClientMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientMicroserviceApplication.class, args);
	}

}
