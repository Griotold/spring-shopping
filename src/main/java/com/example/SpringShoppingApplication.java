package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody
@SpringBootApplication
public class SpringShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringShoppingApplication.class, args);
	}

	@GetMapping("/")
	public String helloWorld() {
		return "Hello World!";
	}
}
