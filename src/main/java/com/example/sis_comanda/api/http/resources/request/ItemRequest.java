package com.example.sis_comanda.api.http.resources.request;

import com.example.sis_comanda.domain.model.Comanda;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {

    @Schema(description = "Nome do item")
    private String nome;

    @Schema(description = "Preço do item")
    private Double preco;

    @Schema(description = "Comanda que o item está vinculado")
    private Comanda comanda;

}
