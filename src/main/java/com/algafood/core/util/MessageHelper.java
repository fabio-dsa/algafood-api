package com.algafood.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Locale;

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

    @Nullable
    public String getMessage(String code, String ...args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    public String getMessage(MessageSourceResolvable resolvable) throws NoSuchMessageException {
        return messageSource.getMessage(resolvable, LocaleContextHolder.getLocale());
    }
}
