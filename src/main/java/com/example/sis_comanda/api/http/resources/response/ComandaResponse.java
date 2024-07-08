package com.example.sis_comanda.api.http.resources.response;

import com.example.sis_comanda.domain.model.Cliente;
import com.example.sis_comanda.domain.model.Item;
import com.example.sis_comanda.domain.model.Mesa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComandaResponse {

    @Schema(description = "ID da comanda")
    private Long id;

    @Schema(description = "Data de abertura da comanda")
    private LocalDateTime dataHoraAbertura;

    @Schema(description = "Data de fechamento da comanda")
    private LocalDateTime dataHoraFechamento;

    @Schema(description = "Itens da comanda")
    private List<Item> itens;

    @Schema(description = "Mesa vinculada a comanda")
    private Mesa mesa;

    @Schema(description = "Cliente vinculado a comanda")
    private Cliente cliente;

}
