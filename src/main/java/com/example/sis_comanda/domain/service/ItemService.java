package com.example.sis_comanda.domain.service;

import com.example.sis_comanda.domain.model.Item;

import java.util.List;

public interface ItemService {

    Item salvar(Item item);

    Item atualizar(Long idItem, Item item);

    Item buscarItem(Long idItem);

    List<Item> buscarItens();

    void deletar(Long idItem);

}
