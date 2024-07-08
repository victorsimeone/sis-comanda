package com.example.sis_comanda.api.controller;


import com.example.sis_comanda.api.http.resources.request.ItemRequest;
import com.example.sis_comanda.api.http.resources.response.ItemResponse;
import com.example.sis_comanda.domain.model.Item;
import com.example.sis_comanda.domain.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{idItem}")
    private ResponseEntity<ItemResponse> buscarItem(@PathVariable(name = "idItem")Long idItem) {
        Item item = itemService.buscarItem(idItem);
        ItemResponse itemResponse = convertEntityToItemResponse(item);
        return ResponseEntity.ok(itemResponse);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponse>> buscarItens(){
        List<Item> itens = itemService.buscarItens();
        List<ItemResponse> itemResponses = itens.stream()
                .map(this::convertEntityToItemResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(itemResponses);
    }

    @PostMapping
    private ResponseEntity<ItemResponse> criarItem(@RequestBody ItemRequest itemRequest) {
        Item item = convertItemRequestToEntity(itemRequest);
        itemService.salvar(item);
        return ResponseEntity.ok(convertEntityToItemResponse(item));
    }

    @PutMapping("/{idItem}")
    private ResponseEntity<ItemResponse> atualizarItem(@PathVariable(name = "idItem")Long idItem, @RequestBody ItemRequest itemRequest) {
        Item itemToAtualizar = convertItemRequestToEntity(itemRequest);
        Item itemAtualizado = itemService.atualizar(idItem, itemToAtualizar);
        return ResponseEntity.ok(convertEntityToItemResponse(itemAtualizado));
    }

    @DeleteMapping("/{idItem}")
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
