package com.example.sis_comanda.api.http.resources.request;

import com.example.sis_comanda.domain.model.Comanda;
import com.example.sis_comanda.domain.model.Garcom;
import com.example.sis_comanda.domain.model.enums.StatusMesa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MesaRequest {

    @Schema(description = "Numero da mesa")
    private Long numero;

    @Schema(description = "Status da mesa")
    private StatusMesa statusMesa;

    @Schema(description = "Comandas vinculadas a mesa")
    private List<Comanda> comandas;

    @Schema(description = "Garcom vinculado a mesa")
    private Garcom garcom;

}
