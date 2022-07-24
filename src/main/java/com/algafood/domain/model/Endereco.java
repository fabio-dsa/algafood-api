package com.algafood.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Embeddable
public class Endereco {

    @Column(name = "end_cep")
    private String cep;

    @Column(name = "end_logradouro")
    private String logradouro;

    @Column(name = "end_numero")
    private String numero;

    @Column(name = "end_complemento")
    private String complemento;

    @Column(name = "end_bairro")
    private String bairro;

    @ManyToOne
    @JoinColumn(name = "end_cidade_id")
    private Cidade cidade;
}
