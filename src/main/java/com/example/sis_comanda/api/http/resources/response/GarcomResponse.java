package com.example.sis_comanda.api.http.resources.response;

import com.example.sis_comanda.domain.model.Mesa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GarcomResponse {

    @Schema(description = "ID do garçom")
    private Long id;

    @Schema(description = "Nome do garçom")
    private String nome;

    @Schema(description = "Mesas que o garçom serve")
    private List<Mesa> mesas;

}
