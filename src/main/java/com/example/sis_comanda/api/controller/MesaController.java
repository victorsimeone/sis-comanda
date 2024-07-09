package com.example.sis_comanda.api.controller;

import com.example.sis_comanda.api.http.resources.request.MesaRequest;
import com.example.sis_comanda.api.http.resources.response.ClienteResponse;
import com.example.sis_comanda.api.http.resources.response.MesaResponse;
import com.example.sis_comanda.domain.model.Mesa;
import com.example.sis_comanda.domain.service.MesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/mesa")
@Tag(name = "Mesa", description = "Recursos relacionados a mesa")
public class MesaController {

    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping("/{idMesa}")
    @Operation(description = "Buscar Mesa", summary = "Buscar Mesa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "404", description = "Mesa não encontrado")
    })
    private ResponseEntity<MesaResponse> buscarMesa(@PathVariable(name = "idMesa")Long idMesa) {
        Mesa mesa = mesaService.buscarMesa(idMesa);
        MesaResponse mesaResponse = convertEntityToMesaResponse(mesa);
        return ResponseEntity.ok(mesaResponse);
    }

    @GetMapping
    @Operation(description = "Buscar Mesas", summary = "Buscar Meses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de requisição")
    })
    public ResponseEntity<List<MesaResponse>> buscarMesas() {
        List<Mesa> mesas = mesaService.buscarMesas();
        List<MesaResponse> mesaResponses = mesas.stream()
                .map(this::convertEntityToMesaResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(mesaResponses);
    }

    @PostMapping("/{idMesa}")
    @Operation(description = "Criar Mesa", summary = "Criar Mesa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "404", description = "Pagina não encontrada")
    })
    private ResponseEntity<MesaResponse> criarMesa(@RequestBody MesaRequest mesaRequest) {
        Mesa mesa = convertMesaRequestToEntity(mesaRequest);
        mesaService.salvar(mesa);
        return ResponseEntity.ok(convertEntityToMesaResponse(mesa));
    }

    @DeleteMapping("/{ìdMesa}")
    @Operation(description = "Deletar Mesa", summary = "Deletar Mesa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    private ResponseEntity<MesaResponse> deletarMesa(@PathVariable(name = "idMesa")Long idMesa) {
        mesaService.deletar(idMesa);
        return ResponseEntity.noContent().build();
    }

    private MesaResponse convertEntityToMesaResponse(Mesa mesa) {
        MesaResponse mesaResponse = new MesaResponse();
        mesaResponse.setId(mesa.getId());
        mesaResponse.setNumero(mesa.getNumero());
        mesaResponse.setStatusMesa(mesa.getStatusMesa());
        mesaResponse.setComandas(mesa.getComandas());
        mesaResponse.setGarcom(mesa.getGarcom());
        return mesaResponse;
    }

    private Mesa convertMesaRequestToEntity(MesaRequest mesaRequest) {
        Mesa mesa = new Mesa();
        mesa.setNumero(mesaRequest.getNumero());
        mesa.setStatusMesa(mesaRequest.getStatusMesa());
        mesa.setComandas(mesaRequest.getComandas());
        mesa.setGarcom(mesaRequest.getGarcom());
        return mesa;
    }

}
