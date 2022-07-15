package com.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "table")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String chave;

    private String descricao;
}
