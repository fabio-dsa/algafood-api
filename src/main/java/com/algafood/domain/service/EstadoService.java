package com.algafood.domain.service;

import com.algafood.core.util.MessageHelper;
import com.algafood.domain.exception.BusinessException;
import com.algafood.domain.exception.ResourceNotFoundException;
import com.algafood.domain.model.Estado;
import com.algafood.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final EstadoRepository estadoRepository;

    private final MessageHelper messageHelper;

    public Estado save(Estado estado) {
        Optional<Estado> saved = this.estadoRepository.findByNomeIgnoreCase(estado.getNome());

        if(saved.isPresent()) {
            throw new BusinessException(messageHelper.getMessage("state.duplicated"));
        }

        return this.estadoRepository.save(estado);
    }

    public Estado update(Long id, Estado estado) {
        this.findById(id);

        Optional<Estado> saved = this.estadoRepository.findByNomeIgnoreCase(estado.getNome());

        if(saved.isPresent() && !saved.get().getId().equals(id)) {
            throw new BusinessException(messageHelper.getMessage("state.duplicated"));
        }

        estado.setId(id);

        return this.estadoRepository.save(estado);
    }

    public Estado findById(Long id) {
        return this.estadoRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageHelper.getMessage("state.not-found")));
    }

    public List<Estado> findAll() {
        return this.estadoRepository.findAllByOrderByNome();
    }
}
