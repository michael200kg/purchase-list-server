package com.michael200kg.purchaseserver.converters;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface ModelDtoBiConverter<D, M> {

    M dtoToEntity(D dto);

    D entityToDto(M entity);

    default Set<M> dtoListToEntitySet(List<D> dtoList) {
        return dtoList.stream().map(this::dtoToEntity).collect(Collectors.toSet());
    }

    default List<D> entityListToDtoList(List<M> entityList) {
        return entityList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    default List<D> entitySetToDtoList(Set<M> entityList) {
        return entityList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
