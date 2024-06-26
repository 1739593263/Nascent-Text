package com.nascent.meta.enums;

/**
 * File types Enums
 */
public enum FileTypeEnums {

    DIR("Directory", "dir"),
    FILE("File", "file"),
    GROUP("Group", "group");

    private final String text;

    private final String value;

    FileTypeEnums(String text, String value) {
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
