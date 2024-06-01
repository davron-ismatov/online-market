package com.example.order.mapper;

import java.util.List;
import java.util.Set;

public interface EntityMapper<D,E> {
    D toDTO(E e);
    E toEntity(D d);
    List<D> toDTOs(List<E> eSet);
    List<E> toEntities(List<D> dSet);
}
