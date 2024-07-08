package com.example.sis_comanda.domain.service;

import com.example.sis_comanda.domain.exception.NotFoundException;
import com.example.sis_comanda.domain.model.Comanda;
import com.example.sis_comanda.domain.repository.ComandaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComandaServiceImpl implements ComandaService {

    private final ComandaRepository comandaRepository;

    public ComandaServiceImpl(ComandaRepository comandaRepository) {
        this.comandaRepository = comandaRepository;
    }

    @Override
    public Comanda salvar(Comanda comanda) {
        return comandaRepository.save(comanda);
    }

    @Override
    public Comanda buscarComanda(Long idComanda) {
        return comandaRepository.findById(idComanda)
                .orElseThrow(
                        ()-> new NotFoundException("Comanda não encontrado")
                );
    }

    @Override
    public Comanda atualizar(Long idComanda, Comanda comanda) {
        Comanda comandaParaAtualizar = buscarComanda(idComanda);
        BeanUtils.copyProperties(comanda, comandaParaAtualizar, "id", "id_cliente", "id_mesa");
        return comandaRepository.save(comandaParaAtualizar);
    }

    @Override
    public List<Comanda> buscarComandas() {
        return comandaRepository.findAll();
    }

    @Override
    public void deletar(Long idComanda) {
        comandaRepository.deleteById(idComanda);
    }



}
