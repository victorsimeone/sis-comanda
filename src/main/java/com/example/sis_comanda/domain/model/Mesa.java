package com.example.sis_comanda.domain.model;

import com.example.sis_comanda.domain.model.enums.StatusMesa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "T_MESA")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "NUMERO", nullable = false, unique = true)
    private Long numero;

    @Column(name = "STATUS", nullable = false)
    private StatusMesa statusMesa;

    @OneToMany(mappedBy = "mesa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comanda> comandas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GARCOM")
    private Garcom garcom;

}
