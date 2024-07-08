package com.example.sis_comanda.domain.service;

import com.example.sis_comanda.domain.exception.NotFoundException;
import com.example.sis_comanda.domain.model.Garcom;
import com.example.sis_comanda.domain.repository.GarcomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarcomServiceImpl implements GarcomService {

    private final GarcomRepository garcomRepository;

    public GarcomServiceImpl(GarcomRepository garcomRepository) {

        this.garcomRepository = garcomRepository;
    }


    @Override
    public Garcom salvar(Garcom garcom) {
        return garcomRepository.save(garcom);
    }

    @Override
    public Garcom buscarGarcom(Long idGarcom) {
        return garcomRepository.findById(idGarcom)
                .orElseThrow(
                        ()-> new NotFoundException("Garcom não encontrado")
                );
    }

    @Override
    public List<Garcom> buscarGarcons() {
        return garcomRepository.findAll();
    }

    @Override
    public void deletar(Long idGarcom) {
        garcomRepository.deleteById(idGarcom);
    }

}
