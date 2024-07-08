package com.example.sis_comanda.domain.repository;

import com.example.sis_comanda.domain.model.Garcom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GarcomRepository extends JpaRepository<Garcom, Long> {
}
