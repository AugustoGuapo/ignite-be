package com.ogam.ignite.utils;

public class Utils {

    private Utils() {}

    public static <T> T updateField(T field, T newField) {
        return newField == null ? field : newField;
    }
}
