package com.algafood.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageHelper {
    private final MessageSource messageSource;

    @Autowired
    public MessageHelper(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String chave) {
        return messageSource.getMessage(chave, null, LocaleContextHolder.getLocale());
    }
}
