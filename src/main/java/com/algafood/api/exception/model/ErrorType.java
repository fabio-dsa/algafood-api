package com.algafood.api.exception.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorType {
    RECURSO_NAO_ENCONTRADO("Recurso não encontrado", HttpStatus.NOT_FOUND),
    ERRO_NEGOCIO("Violação de regra de negócio", HttpStatus.BAD_REQUEST),
    MENSAGEM_INCOMPREENSIVEL("Mensagem incompreensível", HttpStatus.BAD_REQUEST),
    PARAMETRO_INVALIDO("Parâmetro inválido", HttpStatus.BAD_REQUEST),
    PARAMETRO_OBRIGATORIO("Parâmetro obrigatório", HttpStatus.BAD_REQUEST),
    METODO_HTTP_NAO_SUPORTADO("Método HTTP não suportado", HttpStatus.BAD_REQUEST),
    DADOS_INVALIDOS("Dados inválidos", HttpStatus.BAD_REQUEST),
    ERRO_INTERNO("Erro interno do servidor", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String descricao;

    private final HttpStatus statusHttp;
}
