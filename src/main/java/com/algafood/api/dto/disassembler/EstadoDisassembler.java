package com.algafood.api.dto.disassembler;

import com.algafood.api.dto.Disassemblable;
import com.algafood.api.dto.EstadoDTO;
import com.algafood.domain.model.Estado;
import org.springframework.stereotype.Component;

@Component
public class EstadoDisassembler implements Disassemblable<EstadoDTO, Estado> {

    @Override
    public Estado toEntity(EstadoDTO dto) {
        return Estado.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .build();
    }
}
