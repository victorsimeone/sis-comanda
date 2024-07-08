package com.example.sis_comanda.domain.service;

import com.example.sis_comanda.domain.model.Mesa;

import java.util.List;

public interface MesaService {

    Mesa salvar (Mesa mesa);

    Mesa buscarMesa(Long IdMesa);

    List<Mesa> buscarMesas();

    void deletar (Long IdMesa);

}
