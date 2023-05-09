package ru.strict.util;

import lombok.Data;

@Data
public class Token implements Cloneable {
    private final String token;
    private final String secret;
    private final String algorithm;

    @Override
    public Token clone() {
        try {
            return (Token) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
