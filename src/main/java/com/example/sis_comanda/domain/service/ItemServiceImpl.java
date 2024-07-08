package com.example.sis_comanda.domain.service;

import com.example.sis_comanda.domain.exception.NotFoundException;
import com.example.sis_comanda.domain.model.Item;
import com.example.sis_comanda.domain.repository.ItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }


    @Override
    public Item salvar(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item buscarItem(Long idItem) {
        return itemRepository.findById(idItem)
                .orElseThrow(
                        ()-> new NotFoundException("Item não encontrado")
                );
    }

    @Override
    public Item atualizar(Long idItem, Item item) {
        Item itemParaAtualizar = buscarItem(idItem);
        BeanUtils.copyProperties(item, itemParaAtualizar, "id", "id_comanda");
        return itemRepository.save(itemParaAtualizar);
    }

    @Override
    public List<Item> buscarItens() {
        return itemRepository.findAll();
    }

    @Override
    public void deletar(Long idItem) {
        itemRepository.deleteById(idItem);
    }
}
