package com.example.sis_comanda.api.controller;
import com.example.sis_comanda.api.client.KeycloakClient;
import com.example.sis_comanda.api.http.resources.response.KeycloakResponse;
import com.example.sis_comanda.api.http.resources.request.KeycloakRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/keycloak")
@Tag(name = "Keycloak", description = "obtém crendenciais do keycloak")
public class KeycloakController {

    private final KeycloakClient keycloakClient;

    public KeycloakController(KeycloakClient keycloakClient) {
        this.keycloakClient = keycloakClient;
    }

    @PostMapping
    @Operation(description = "Realiza requisição de login no keycloak com dados do json de request, retornando credenciais em caso de login realizado com sucesso.", summary = "Obtém credenciais do keycloak")
    public ResponseEntity<?> getKeycloakCredentials(@RequestBody KeycloakRequest keycloakRequest) {
        
        KeycloakResponse keycloakResponse = keycloakClient.getKeycloakCredentials(keycloakRequest);

        return ResponseEntity.ok(keycloakResponse.getAccess_token());
    }

}
