package com.example.sis_comanda.domain.service;

import com.example.sis_comanda.domain.model.Garcom;

import java.util.List;

public interface GarcomService {

    Garcom salvar(Garcom garcom);

    Garcom buscarGarcom(Long idGarcom);

    List<Garcom> buscarGarcons();

    void deletar(Long idGarcom);
}
