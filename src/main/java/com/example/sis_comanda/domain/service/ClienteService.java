package com.example.sis_comanda.domain.service;

import com.example.sis_comanda.domain.model.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente salvar(Cliente cliente);

    Cliente atualizar(Long idCliente, Cliente cliente);

    Cliente buscarCliente(Long idCliente);

    List<Cliente> buscarClientes();

    void deletar(Long idCliente);

}
