package org.pranit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@ImportResource("classpath:/applicationContext.xml")
public class RestHr1Application {

	public static void main(String[] args) {
		SpringApplication.run(RestHr1Application.class, args);
	}
}
