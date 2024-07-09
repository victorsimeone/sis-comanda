package com.example.sis_comanda.api.controller;


import com.example.sis_comanda.api.http.resources.request.ItemRequest;
import com.example.sis_comanda.api.http.resources.response.ClienteResponse;
import com.example.sis_comanda.api.http.resources.response.ItemResponse;
import com.example.sis_comanda.domain.model.Item;
import com.example.sis_comanda.domain.service.ItemService;
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
@RequestMapping(value = "/item")
@Tag(name = "Item", description = "Recursos relacionados ao Item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{idItem}")
    @Operation(description = "Buscar Item", summary = "Buscar Item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "404", description = "Item não encontrado")
    })
    private ResponseEntity<ItemResponse> buscarItem(@PathVariable(name = "idItem")Long idItem) {
        Item item = itemService.buscarItem(idItem);
        ItemResponse itemResponse = convertEntityToItemResponse(item);
        return ResponseEntity.ok(itemResponse);
    }

    @GetMapping
    @Operation(description = "Buscar Itens", summary = "Buscar Itens")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de requisição")
    })
    public ResponseEntity<List<ItemResponse>> buscarItens(){
        List<Item> itens = itemService.buscarItens();
        List<ItemResponse> itemResponses = itens.stream()
                .map(this::convertEntityToItemResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(itemResponses);
    }

    @PostMapping
    @Operation(description = "Criar item", summary = "Criar item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "404", description = "Pagina não encontrada")
    })
    private ResponseEntity<ItemResponse> criarItem(@RequestBody ItemRequest itemRequest) {
        Item item = convertItemRequestToEntity(itemRequest);
        itemService.salvar(item);
        return ResponseEntity.ok(convertEntityToItemResponse(item));
    }

    @PutMapping("/{idItem}")
    @Operation(description = "Atualizar Item", summary = "Atualizar Item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "404", description = "Item não encontrado")
    })
    private ResponseEntity<ItemResponse> atualizarItem(@PathVariable(name = "idItem")Long idItem, @RequestBody ItemRequest itemRequest) {
        Item itemToAtualizar = convertItemRequestToEntity(itemRequest);
        Item itemAtualizado = itemService.atualizar(idItem, itemToAtualizar);
        return ResponseEntity.ok(convertEntityToItemResponse(itemAtualizado));
    }

    @DeleteMapping("/{idItem}")
    @Operation(description = "Deletar Item", summary = "Deletar Item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Item não encontrado")
    })
    private ResponseEntity<ItemResponse> deletarItem(@PathVariable(name = "idItem")Long idItem) {
        itemService.deletar(idItem);
        return ResponseEntity.noContent().build();
    }


    private ItemResponse convertEntityToItemResponse(Item item){
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setId(item.getId());
        itemResponse.setNome(item.getNome());
        itemResponse.setPreco(item.getPreco());
        itemResponse.setComanda(item.getComanda());
        return itemResponse;
    }

    private Item convertItemRequestToEntity(ItemRequest itemRequest){
        Item item = new Item();
        item.setNome(itemRequest.getNome());
        item.setPreco(itemRequest.getPreco());
        item.setComanda(itemRequest.getComanda());
        return item;
    }

}
