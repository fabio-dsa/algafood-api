package com.algafood.domain.service;

import com.algafood.core.util.MessageHelper;
import com.algafood.domain.exception.BusinessException;
import com.algafood.domain.model.Estado;
import com.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    private final EstadoRepository estadoRepository;

    private final MessageHelper messageHelper;

    @Autowired
    public EstadoService(EstadoRepository estadoRepository, MessageHelper messageHelper) {
        this.estadoRepository = estadoRepository;
        this.messageHelper = messageHelper;
    }

    public Estado save(Estado estado) {
        return this.estadoRepository.save(estado);
    }

    public Estado findById(Long id) {
        return this.estadoRepository
                .findById(id)
                .orElseThrow(() -> new BusinessException(messageHelper.getMessage("state.not-found")));
    }

    public List<Estado> findAll() {
        return this.estadoRepository.findAllByOrderByNome();
    }
}
