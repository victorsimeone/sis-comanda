package com.example.sis_comanda.api.http.resources.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeycloakRequest {

    private String usuario;
    private String senha;
}
