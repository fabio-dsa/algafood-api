package com.algafood.api.dto.assembler;

import com.algafood.api.dto.Assemblable;
import com.algafood.api.dto.EstadoDTO;
import com.algafood.domain.model.Estado;
import org.springframework.stereotype.Component;

@Component
public class EstadoAssembler implements Assemblable<Estado, EstadoDTO> {
    @Override
    public EstadoDTO toDTO(Estado entity) {
        return EstadoDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .build();
    }
}
