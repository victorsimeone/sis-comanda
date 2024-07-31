package com.example.sis_comanda.api.controller;

import com.example.sis_comanda.api.http.resources.request.ComandaRequest;
import com.example.sis_comanda.api.http.resources.response.ClienteResponse;
import com.example.sis_comanda.api.http.resources.response.ComandaResponse;
import com.example.sis_comanda.domain.model.Comanda;
import com.example.sis_comanda.domain.service.ComandaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/comanda")
@Tag(name = "Comanda", description = "Recursos relacionados a comanda")
@SecurityRequirement(name = "Bearer Authentication")
public class ComandaController {

    private final ComandaService comandaService;

    public ComandaController(ComandaService comandaService) {
        this.comandaService = comandaService;
    }

    @GetMapping("/{idComanda}")
    @Operation(description = "Buscar Comanda", summary = "Buscar Comanda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "404", description = "Comanda não encontrada")
    })
    private ResponseEntity<ComandaResponse> buscarComanda(@PathVariable(name = "idComanda")Long idComanda) {
        Comanda comanda = comandaService.buscarComanda(idComanda);
        ComandaResponse comandaResponse = convertEntityToComandaResponse(comanda);
        return ResponseEntity.ok(comandaResponse);
    }

    @GetMapping
    @Operation(description = "Buscar Comandas", summary = "Buscar Comandas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de requisição")
    })
    public ResponseEntity<List<ComandaResponse>> buscarComandas() {
        List<Comanda> comandas = comandaService.buscarComandas();
        List<ComandaResponse> comandaResponses = comandas.stream()
                .map(this::convertEntityToComandaResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(comandaResponses);
    }

    @PostMapping
    @Operation(description = "Criar Comanda", summary = "Criar Comanda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "404", description = "Pagina não encontrada")
    })
    private ResponseEntity<ComandaResponse> criarComanda(@RequestBody ComandaRequest comandaRequest) {
        Comanda comanda = convertComandaResquestToEntity(comandaRequest);
        comandaService.salvar(comanda);
        return ResponseEntity.ok(convertEntityToComandaResponse(comanda));
    }

    @PutMapping("/{idComanda}")
    @Operation(description = "Atualizar Comanda", summary = "Atualizar Comanda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    private ResponseEntity<ComandaResponse> atualizarComanda(@PathVariable(name = "idComanda")Long idComanda, @RequestBody ComandaRequest comandaRequest) {
        Comanda comandaToAtualizar = convertComandaResquestToEntity(comandaRequest);
        Comanda comandaAtualizada = comandaService.atualizar(idComanda, comandaToAtualizar);
        return ResponseEntity.ok(convertEntityToComandaResponse(comandaAtualizada));

    }

    @DeleteMapping("/{idComanda}")
    @Operation(description = "Deletar Comanda", summary = "Deletar Comanda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Comanda não encontrada")
    })
    private ResponseEntity<ComandaResponse> deletarComanda(@PathVariable(name = "idComanda")Long idComanda) {
        comandaService.deletar(idComanda);
        return ResponseEntity.noContent().build();
    }

    private ComandaResponse convertEntityToComandaResponse(Comanda comanda) {
        ComandaResponse comandaResponse = new ComandaResponse();
        comandaResponse.setId(comanda.getId());
        comandaResponse.setDataHoraAbertura(comanda.getDataHoraAbertura());
        comandaResponse.setDataHoraFechamento(comanda.getDataHoraFechamento());
        comandaResponse.setItens(comanda.getItens());
        comandaResponse.setMesa(comanda.getMesa());
        comandaResponse.setCliente(comanda.getCliente());
        return comandaResponse;
    }

    private Comanda convertComandaResquestToEntity(ComandaRequest comandaRequest) {
        Comanda comanda = new Comanda();
        comanda.setDataHoraAbertura(comandaRequest.getDataHoraAbertura());
        comanda.setDataHoraFechamento(comandaRequest.getDataHoraFechamento());
        comanda.setItens(comanda.getItens());
        comanda.setMesa(comanda.getMesa());
        comanda.setCliente(comanda.getCliente());
        return comanda;
    }
}
