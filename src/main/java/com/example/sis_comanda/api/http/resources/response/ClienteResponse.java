package com.example.sis_comanda.api.http.resources.response;

import com.example.sis_comanda.domain.model.Comanda;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

    @Schema(description = "ID do cliente")
    private Long id;

    @Schema(description = "Nome da pessoa")
    private String nome;

    @Schema(description = "Telefone")
    private String telefone;

    @Schema(description = "Comanda do Cliente")
    private List<Comanda> comandas;

}
