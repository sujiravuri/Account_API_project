package com.bank.AccountAPI.Account.API.Project;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Banking API", version = "1.0", description = "Banking domain operations and KYC integration"))
public class AccountApiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApiProjectApplication.class, args);
	}

}
