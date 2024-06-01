package com.example.product.mapper;

import java.util.List;

public interface EntityMapper<D,E>{
    D toDto(E e);
    E toEntity(D d);
    List<D> toDTOs(List<E> eList);

}
