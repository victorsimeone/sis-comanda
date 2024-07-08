package com.example.sis_comanda.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "T_GARCOM")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Garcom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "garcom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Mesa> mesas;
}
