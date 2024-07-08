package com.example.sis_comanda.api.controller;

import com.example.sis_comanda.api.http.resources.request.GarcomRequest;
import com.example.sis_comanda.api.http.resources.response.GarcomResponse;
import com.example.sis_comanda.domain.model.Garcom;
import com.example.sis_comanda.domain.service.GarcomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/garcom")
public class GarcomController {

    private final GarcomService garcomService;

    public GarcomController(GarcomService garcomService) {
        this.garcomService = garcomService;
    }

    @GetMapping("/{idGarcom}")
    private ResponseEntity<GarcomResponse> buscarGarcom(@PathVariable(name = "idGarcom")Long idGarcom) {
        Garcom garcom = garcomService.buscarGarcom(idGarcom);
        GarcomResponse garcomResponse = convertEntityToGarcomResponse(garcom);
        return ResponseEntity.ok(garcomResponse);
    }

    @GetMapping
    public ResponseEntity<List<GarcomResponse>> buscarGarcons() {
        List<Garcom> garcons = garcomService.buscarGarcons();
        List<GarcomResponse> garcomResponses = garcons.stream()
                .map(this::convertEntityToGarcomResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(garcomResponses);
    }

    @PostMapping
    private ResponseEntity<GarcomResponse> salvarGarcom(@RequestBody GarcomRequest garcomRequest) {
        Garcom garcom = convertGarocomResquestToEntity(garcomRequest);
        garcomService.salvar(garcom);
        return ResponseEntity.ok(convertEntityToGarcomResponse(garcom));
    }

    @DeleteMapping("/{idGarcom}")
    private ResponseEntity<GarcomResponse> deletarGarcom(@PathVariable (name = "idGarcom")Long idGarcom) {
        garcomService.deletar(idGarcom);
        return ResponseEntity.noContent().build();
    }

    private GarcomResponse convertEntityToGarcomResponse(Garcom garcom) {
        GarcomResponse garcomResponse = new GarcomResponse();
        garcomResponse.setId(garcom.getId());
        garcomResponse.setNome(garcom.getNome());
        garcomResponse.setMesas(garcom.getMesas());
        return garcomResponse;
    }

    private Garcom convertGarocomResquestToEntity(GarcomRequest garcomRequest) {
        Garcom garcom = new Garcom();
        garcom.setNome(garcomRequest.getNome());
        garcom.setMesas(garcomRequest.getMesas());
        return garcom;
    }
}
