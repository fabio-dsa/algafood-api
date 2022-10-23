package com.algafood.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class EstadoDTO {

    private Long id;

    @NotBlank
    @Setter
    private String nome;
}
