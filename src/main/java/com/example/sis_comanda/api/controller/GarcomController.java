package com.example.sis_comanda.api.controller;

import com.example.sis_comanda.api.http.resources.request.GarcomRequest;
import com.example.sis_comanda.api.http.resources.response.ClienteResponse;
import com.example.sis_comanda.api.http.resources.response.GarcomResponse;
import com.example.sis_comanda.domain.model.Garcom;
import com.example.sis_comanda.domain.service.GarcomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/garcom")
@Tag(name = "Garçom", description = "Recursos relacionados ao garçom")
public class GarcomController {

    private final GarcomService garcomService;

    public GarcomController(GarcomService garcomService) {
        this.garcomService = garcomService;
    }

    @GetMapping("/{idGarcom}")
    @Operation(description = "Buscar Garçom", summary = "Buscar Garçom")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "404", description = "Garçom não encontrado")
    })
    private ResponseEntity<GarcomResponse> buscarGarcom(@PathVariable(name = "idGarcom")Long idGarcom) {
        Garcom garcom = garcomService.buscarGarcom(idGarcom);
        GarcomResponse garcomResponse = convertEntityToGarcomResponse(garcom);
        return ResponseEntity.ok(garcomResponse);
    }

    @GetMapping
    @Operation(description = "Buscar Garçons", summary = "Buscar Garçons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de requisição")
    })
    public ResponseEntity<List<GarcomResponse>> buscarGarcons() {
        List<Garcom> garcons = garcomService.buscarGarcons();
        List<GarcomResponse> garcomResponses = garcons.stream()
                .map(this::convertEntityToGarcomResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(garcomResponses);
    }

    @PostMapping
    @Operation(description = "Criar Garçom", summary = "Criar Garçom")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "404", description = "Pagina não encontrada")
    })
    private ResponseEntity<GarcomResponse> salvarGarcom(@RequestBody GarcomRequest garcomRequest) {
        Garcom garcom = convertGarocomResquestToEntity(garcomRequest);
        garcomService.salvar(garcom);
        return ResponseEntity.ok(convertEntityToGarcomResponse(garcom));
    }

    @DeleteMapping("/{idGarcom}")
    @Operation(description = "Deletar Garçom", summary = "Deletar Garçom")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Garçom não encontrado")
    })
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
