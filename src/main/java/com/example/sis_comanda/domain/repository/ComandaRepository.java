package com.example.sis_comanda.domain.repository;

import com.example.sis_comanda.domain.model.Comanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Long> {

}
