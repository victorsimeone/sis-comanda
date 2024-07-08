package com.example.sis_comanda.core.mapper;

import com.example.sis_comanda.api.http.resources.request.ClienteRequest;
import com.example.sis_comanda.domain.model.Cliente;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClienteMapper {
    @Mapping(target = "id", ignore = true)
    Cliente mapRequestToEntity(ClienteRequest clienteRequest);
}
