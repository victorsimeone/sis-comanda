package com.example.sis_comanda.api.controller;

import com.example.sis_comanda.api.http.resources.request.ClienteRequest;
import com.example.sis_comanda.api.http.resources.response.ClienteResponse;
import com.example.sis_comanda.domain.model.Cliente;
import com.example.sis_comanda.domain.service.ClienteService;
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
@RequestMapping(value = "/cliente")
@Tag(name = "Cliente", description = "Recursos relacionados ao cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{idCliente}")
    @Operation(description = "Buscar Cliente", summary = "Buscar Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    private ResponseEntity<ClienteResponse> buscarCliente(@PathVariable(name = "idCliente") Long idCliente) {
        Cliente cliente = clienteService.buscarCliente(idCliente);
        ClienteResponse clienteResponse = convertEntityToClienteResponse(cliente);
        return ResponseEntity.ok(clienteResponse);
    }

    @GetMapping
    @Operation(description = "Buscar Clientes", summary = "Buscar Clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de requisição")
    })
    public ResponseEntity<List<ClienteResponse>> buscarClientes() {
        List<Cliente> clientes = clienteService.buscarClientes();
        List<ClienteResponse> clienteResponses = clientes.stream()
                .map(this::convertEntityToClienteResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clienteResponses);
    }

    @PostMapping
    @Operation(description = "Criar Cliente", summary = "Criar Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "404", description = "Pagina não encontrada")
     })
    private ResponseEntity<ClienteResponse> criarCliente(@RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = convertClienteResquestToEntity(clienteRequest);
        clienteService.salvar(cliente);
        return ResponseEntity.ok(convertEntityToClienteResponse(cliente));
    }

    @PutMapping("/{idCliente}")
    @Operation(description = "Atualizar Cliente", summary = "Atualizar Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    private ResponseEntity<ClienteResponse> atualizarCliente(@PathVariable(name = "idCliente") Long idCliente, @RequestBody ClienteRequest clienteRequest) {
        Cliente clienteToAtualizar = convertClienteResquestToEntity(clienteRequest);
        Cliente clienteAtualizado = clienteService.atualizar(idCliente, clienteToAtualizar);
        return ResponseEntity.ok(convertEntityToClienteResponse(clienteAtualizado));

    }

    @DeleteMapping("/{idCliente}")
    @Operation(description = "Deletar Cliente", summary = "Deletar Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    private ResponseEntity<ClienteResponse> deletarCliente(@PathVariable(name = "idCliente") Long idCliente) {
        clienteService.deletar(idCliente);
        return ResponseEntity.noContent().build();
    }

    private ClienteResponse convertEntityToClienteResponse(Cliente cliente) {
        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setId(cliente.getId());
        clienteResponse.setNome(cliente.getNome());
        clienteResponse.setTelefone(cliente.getTelefone());
        clienteResponse.setComandas(cliente.getComandas());
        return clienteResponse;
    }

    private Cliente convertClienteResquestToEntity(ClienteRequest clienteRequest) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequest.getNome());
        cliente.setTelefone(clienteRequest.getTelefone());
        return cliente;
    }
}
