package com.family;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@SpringBootApplication
@ComponentScan(basePackages = {"com.family"})
@EnableTransactionManagement
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.family.**.mapper")
public class FamilyApplication {

	public static void main(String[] args) {
		SpringApplication.run( FamilyApplication.class, args);
	}

}
