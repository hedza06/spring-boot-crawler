package com.spring.crawler;

import com.spring.crawler.spiders.TarantulaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrawlerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CrawlerApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CrawlerApplication.class);
	}

	@Bean
	CommandLineRunner commandLineRunner(TarantulaService tarantulaService) {
		return args -> tarantulaService.fetchBasicData();
	}
}
