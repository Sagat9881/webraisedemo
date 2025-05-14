package ru.apzakharov.demo.webraise;

import org.springframework.boot.SpringApplication;

public class TestWebraiseApplication {

	public static void main(String[] args) {
		SpringApplication.from(WebRaiseDemoApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
