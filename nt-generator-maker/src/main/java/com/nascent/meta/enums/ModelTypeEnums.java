package com.nascent.meta.enums;

/**
 * Model Type Enums
 */
public enum ModelTypeEnums {
    STRING("Str", "String"),
    BOOLEAN("Boolean", "boolean");

    private final String text;
    private final String value;

    ModelTypeEnums(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
