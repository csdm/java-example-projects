package com.claudiodimauro.it.ApiDataFetch;


import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FetchDataFromRestApiResttemplateApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FetchDataFromRestApiResttemplateApplication.class, args);
		
		
		
	}
	
	@PostConstruct
	public void boh() {
		System.out.println("Boh post");
	}

}
