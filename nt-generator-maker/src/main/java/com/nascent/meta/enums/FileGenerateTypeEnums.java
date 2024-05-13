package com.nascent.meta.enums;

/**
 * Generated Method's Type Enums
 */
public enum FileGenerateTypeEnums {
    DYNAMIC("Dynamic", "dynamic"),
    STATIC("Static", "static");

    private final String text;

    private final String value;

    FileGenerateTypeEnums(String text, String value){
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
