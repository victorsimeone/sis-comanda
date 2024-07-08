package com.example.sis_comanda.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "T_COMANDA")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "DATA_HORA_ABERTURA", nullable = false)
    private LocalDateTime dataHoraAbertura;

    @Column(name = "DATA_HORA_FECHAMENTO", nullable = false)
    private LocalDateTime dataHoraFechamento;

    @OneToMany(mappedBy = "comanda", fetch = FetchType.LAZY)
    private List<Item> itens;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MESA")
    private Mesa mesa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;
}
