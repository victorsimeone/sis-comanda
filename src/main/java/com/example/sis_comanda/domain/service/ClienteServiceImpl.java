package com.example.sis_comanda.domain.service;

import com.example.sis_comanda.domain.exception.BusinessException;
import com.example.sis_comanda.domain.exception.NotFoundException;
import com.example.sis_comanda.domain.model.Cliente;
import com.example.sis_comanda.domain.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Override
    public Cliente salvar(Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findByTelefoneIgnoreCase(cliente.getTelefone());

        if (clienteOptional.isPresent()) {
            throw new BusinessException("Já existe um cliente com esse telefone");
        }

        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente buscarCliente(Long idCliente) {
        return clienteRepository.findById(idCliente)
                .orElseThrow(
                        ()-> new NotFoundException("Cliente não encontrado")
                );

    }

    @Override
    public Cliente atualizar(Long idCliente, Cliente cliente) {
        Cliente clienteParaAtualizar = buscarCliente(idCliente);
        BeanUtils.copyProperties(cliente, clienteParaAtualizar, "id");
        return clienteRepository.save(clienteParaAtualizar);
    }

    @Override
    public List<Cliente> buscarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public void deletar(Long idCliente) {
        clienteRepository.deleteById(idCliente);
    }
}
