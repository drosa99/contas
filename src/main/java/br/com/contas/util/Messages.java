package br.com.contas.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Messages implements MessageSourceAware {

    private MessageSource messageSource;

    public String get(String key, Object... args) {
        return get(LocaleContextHolder.getLocale(), key, args);
    }

    public String get(Locale locale, String key, Object... args) {
        if (StringUtils.isBlank(key)) {
            return key;
        }
        if (this.messageSource == null) {
            return Strings.formatSafe(key, args);
        }
        try {
            return this.messageSource.getMessage(key, args, locale);
        } catch (Exception e) {
            return Strings.formatSafe(key, args);
        }
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
