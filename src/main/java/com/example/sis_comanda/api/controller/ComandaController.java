package com.example.sis_comanda.api.controller;

import com.example.sis_comanda.api.http.resources.request.ComandaRequest;
import com.example.sis_comanda.api.http.resources.response.ComandaResponse;
import com.example.sis_comanda.domain.model.Comanda;
import com.example.sis_comanda.domain.service.ComandaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/comanda")
public class ComandaController {

    private final ComandaService comandaService;

    public ComandaController(ComandaService comandaService) {
        this.comandaService = comandaService;
    }

    @GetMapping("/{idComanda}")
    private ResponseEntity<ComandaResponse> buscarComanda(@PathVariable(name = "idComanda")Long idComanda) {
        Comanda comanda = comandaService.buscarComanda(idComanda);
        ComandaResponse comandaResponse = convertEntityToComandaResponse(comanda);
        return ResponseEntity.ok(comandaResponse);
    }

    @GetMapping
    public ResponseEntity<List<ComandaResponse>> buscarComandas() {
        List<Comanda> comandas = comandaService.buscarComandas();
        List<ComandaResponse> comandaResponses = comandas.stream()
                .map(this::convertEntityToComandaResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(comandaResponses);
    }

    @PostMapping
    private ResponseEntity<ComandaResponse> criarComanda(@RequestBody ComandaRequest comandaRequest) {
        Comanda comanda = convertComandaResquestToEntity(comandaRequest);
        comandaService.salvar(comanda);
        return ResponseEntity.ok(convertEntityToComandaResponse(comanda));
    }

    @PutMapping("/{idComanda}")
    private ResponseEntity<ComandaResponse> atualizarComanda(@PathVariable(name = "idComanda")Long idComanda, @RequestBody ComandaRequest comandaRequest) {
        Comanda comandaToAtualizar = convertComandaResquestToEntity(comandaRequest);
        Comanda comandaAtualizada = comandaService.atualizar(idComanda, comandaToAtualizar);
        return ResponseEntity.ok(convertEntityToComandaResponse(comandaAtualizada));

    }

    @DeleteMapping("/{idComanda}")
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
