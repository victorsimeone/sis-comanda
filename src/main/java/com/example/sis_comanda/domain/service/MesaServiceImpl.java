package com.example.sis_comanda.domain.service;

import com.example.sis_comanda.domain.exception.NotFoundException;
import com.example.sis_comanda.domain.model.Mesa;
import com.example.sis_comanda.domain.repository.MesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaServiceImpl implements MesaService {

    private final MesaRepository mesaRepository;

    public MesaServiceImpl(MesaRepository mesaRepository) {

        this.mesaRepository = mesaRepository;
    }

    @Override
    public Mesa salvar(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    @Override
    public Mesa buscarMesa(Long idMesa) {
        return mesaRepository.findById(idMesa)
                .orElseThrow(
                        ()-> new NotFoundException("Mesa não encontrada")
                );
    }

    @Override
    public List<Mesa> buscarMesas() {
        return mesaRepository.findAll();
    }

    @Override
    public void deletar(Long IdMesa) {
        mesaRepository.deleteById(IdMesa);
    }
}
