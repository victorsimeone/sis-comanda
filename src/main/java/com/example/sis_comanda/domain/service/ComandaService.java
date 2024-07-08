package com.example.sis_comanda.domain.service;

import com.example.sis_comanda.domain.model.Comanda;

import java.util.List;

public interface ComandaService {

    Comanda salvar(Comanda comanda);

    Comanda buscarComanda(Long idComanda);

    Comanda atualizar(Long idComanda, Comanda comanda);

    List<Comanda> buscarComandas();

    void deletar(Long idComanda);
}
