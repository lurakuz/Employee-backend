package net.javaguides.springboot.utils;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@AllArgsConstructor
public class CommonMessageBundle {

    private MessageSource messageSource;

    private static final Locale DEFAULT_LOCALE = Locale.US;

    public String getMessage(String data) {
        return messageSource.getMessage(data, null, DEFAULT_LOCALE);
    }
}