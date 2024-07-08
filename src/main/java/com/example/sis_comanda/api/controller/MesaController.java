package com.example.sis_comanda.api.controller;

import com.example.sis_comanda.api.http.resources.request.MesaRequest;
import com.example.sis_comanda.api.http.resources.response.MesaResponse;
import com.example.sis_comanda.domain.model.Mesa;
import com.example.sis_comanda.domain.service.MesaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/mesa")
public class MesaController {

    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping("/{idMesa}")
    private ResponseEntity<MesaResponse> buscarMesa(@PathVariable(name = "idMesa")Long idMesa) {
        Mesa mesa = mesaService.buscarMesa(idMesa);
        MesaResponse mesaResponse = convertEntityToMesaResponse(mesa);
        return ResponseEntity.ok(mesaResponse);
    }

    @GetMapping
    public ResponseEntity<List<MesaResponse>> buscarMesas() {
        List<Mesa> mesas = mesaService.buscarMesas();
        List<MesaResponse> mesaResponses = mesas.stream()
                .map(this::convertEntityToMesaResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(mesaResponses);
    }

    @PostMapping("/{idMesa}")
    private ResponseEntity<MesaResponse> criarMesa(@RequestBody MesaRequest mesaRequest) {
        Mesa mesa = convertMesaRequestToEntity(mesaRequest);
        mesaService.salvar(mesa);
        return ResponseEntity.ok(convertEntityToMesaResponse(mesa));
    }

    @DeleteMapping("/{ìdMesa}")
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
