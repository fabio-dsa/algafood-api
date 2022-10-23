package com.algafood.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
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

    public String getMessage(MessageSourceResolvable resolvable) throws NoSuchMessageException {
        return messageSource.getMessage(resolvable, LocaleContextHolder.getLocale());
    }
}
