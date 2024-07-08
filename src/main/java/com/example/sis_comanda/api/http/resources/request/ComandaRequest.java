package com.example.sis_comanda.api.http.resources.request;

import com.example.sis_comanda.domain.model.Cliente;
import com.example.sis_comanda.domain.model.Mesa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComandaRequest {

    @Schema(description = "Data de abertura da comanda")
    private LocalDateTime dataHoraAbertura;

    @Schema(description = "Data de fechamento da comanda")
    private LocalDateTime dataHoraFechamento;

    @Schema(description = "Cliente vinculado a comanda")
    private Cliente cliente;

    @Schema(description = "Mesa viculada a comanda")
    private Mesa mesa;

}
