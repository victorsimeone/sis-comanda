package com.example.sis_comanda.api.http.resources.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeycloakResponse {

   private String access_token;
   private String expires_in;
   private String refresh_expires_in;
   private String refresh_token;
   private String token_type;
   private String id_token;
   private String session_state;
   private String scope;
}
