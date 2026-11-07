package com.khethelihle.vhs;

import org.springframework.boot.SpringApplication;

public class TestVhsApplication {

	public static void main(String[] args) {
		SpringApplication.from(VhsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
