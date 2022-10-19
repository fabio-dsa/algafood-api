package com.algafood.domain.repository;

import com.algafood.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    List<Estado> findAllByOrderByNome();

    Optional<Estado> findByNomeIgnoreCase(String nome);
}
