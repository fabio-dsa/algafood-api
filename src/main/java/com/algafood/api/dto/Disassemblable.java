package com.algafood.api.dto;

public interface Disassemblable<D, E> {

    E toEntity(D dto);
}
