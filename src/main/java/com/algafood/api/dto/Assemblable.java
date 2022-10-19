package com.algafood.api.dto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface Assemblable<E, D> {

    D toDTO(E entity);

    default List<D> toDTOList(List<E> entities) {
        if (Objects.isNull(entities)) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
