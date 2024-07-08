package com.example.sis_comanda.domain.model.enums;

public enum StatusMesa {

    DISPONIVEL("DISPONÍVEL"),
    OCUPADO("OCUPADO");

    private String descricao;

    StatusMesa(String descricao) { this.descricao = descricao; }

    public String getDescricao() { return descricao; }

}