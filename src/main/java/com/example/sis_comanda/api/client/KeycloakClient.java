package com.example.sis_comanda.api.client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import com.example.sis_comanda.api.http.resources.response.KeycloakResponse;
import com.example.sis_comanda.api.http.resources.request.KeycloakRequest;
import com.example.sis_comanda.domain.exception.NotFoundException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.BodyInserters;

@Service
public class KeycloakClient {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String baseKeycloakUrl;

    public KeycloakResponse getKeycloakCredentials(KeycloakRequest request) {

        WebClient client = WebClient.builder()
                .baseUrl(baseKeycloakUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();

        return client.post()
                .uri("/protocol/openid-connect/token")
                .body(BodyInserters.fromFormData("username", request.getUsuario())
                        .with("password", request.getSenha())
                        .with("grant_type", "password")
                        .with("client_id", "cliente-externo")
                        .with("scope", "openid"))
                .retrieve()
                .bodyToFlux(KeycloakResponse.class)
                .onErrorMap(e -> new NotFoundException("Credenciais não encontradas."))
                .blockLast();
    }

}
