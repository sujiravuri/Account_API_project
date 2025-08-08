package com.bank.AccountAPI.Account.API.Project.Service;

import com.bank.AccountAPI.Account.API.Project.DTO.KycRequest;
import com.bank.AccountAPI.Account.API.Project.DTO.KycResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class KycVerificationService {

    private final WebClient webClient;

    public KycVerificationService(WebClient.Builder builder) {
        this.webClient = builder.build();
    }


    public KycResponse verifyCustomer(String customerId) {
        String url = "http://localhost:8081/kyc/verify";

        KycRequest request = new KycRequest();
        request.setCustomerId(customerId);
        request.setName("Dummy Name");
        request.setEmail("dummy@verified.com");

       return webClient.post()
                .uri(url)
                .bodyValue(request)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        Mono.error(new RuntimeException("Client error calling KYC API")))
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new RuntimeException("Server error calling KYC API")))
                .bodyToMono(KycResponse.class)
                .block();
    }
}
