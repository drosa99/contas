package br.com.contas.exception;

import br.com.contas.util.Messages;
import br.com.contas.util.Strings;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleErrorTranslator {

    @Autowired
    private Messages messages;

    public SimpleErrorTranslator() {
    }

    private String message(String i18nKey, Object... args) {
        if (messages == null) {
            return Strings.formatSafe(i18nKey, args);
        }
        return messages.get(i18nKey, args);
    }

    public SimpleError translate(SimpleError error) {
        if (error == null) {
            return null;
        }

        if (StringUtils.isBlank(error.getCode()) && StringUtils.isNotBlank(error.getMessage())) {
            String key = error.getMessage().concat(".code");
            String code = message(key);
            error.setCode(code.equals(key) ? null : code);
        }
        error.setMessage(message(error.getMessage(), error.getMessageArgs()));
        if (error.getDetails() != null && !error.getDetails().isEmpty()) {
            for (SimpleError detailError : error.getDetails()) {
                translate(detailError);
            }
        }
        return error;
    }
}
