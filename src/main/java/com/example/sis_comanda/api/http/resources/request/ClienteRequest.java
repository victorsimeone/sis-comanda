package com.example.sis_comanda.api.http.resources.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {

    @Schema(description = "Nome da pessoa")
    private String nome;


    @NotNull(message = "Telefone não pode ser nulo!")
    @NotEmpty(message = "Telefone não pode estar vazio!")
    @Schema(description = "Telefone")
    private String telefone;

}
