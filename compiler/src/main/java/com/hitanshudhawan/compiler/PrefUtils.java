package com.hitanshudhawan.compiler;

import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.VariableElement;

class PrefUtils {

    private static Set<String> supportedVariables = new HashSet<String>() {{
        add(boolean.class.getCanonicalName());
        add(Boolean.class.getCanonicalName());
        add(float.class.getCanonicalName());
        add(Float.class.getCanonicalName());
        add(int.class.getCanonicalName());
        add(Integer.class.getCanonicalName());
        add(long.class.getCanonicalName());
        add(Long.class.getCanonicalName());
        add(String.class.getCanonicalName());
    }};

    static boolean isVariableSupported(VariableElement variableElement) {
        return supportedVariables.contains(variableElement.asType().toString());
    }

    static String getSharedPreferencesMethodName(VariableElement variableElement) {
        String variableType = variableElement.asType().toString();
        if (variableType.equals(boolean.class.getCanonicalName()) || variableType.equals(Boolean.class.getCanonicalName())) {
            return "Boolean";
        } else if (variableType.equals(float.class.getCanonicalName()) || variableType.equals(Float.class.getCanonicalName())) {
            return "Float";
        } else if (variableType.equals(int.class.getCanonicalName()) || variableType.equals(Integer.class.getCanonicalName())) {
            return "Int";
        } else if (variableType.equals(long.class.getCanonicalName()) || variableType.equals(Long.class.getCanonicalName())) {
            return "Long";
        } else if (variableType.equals(String.class.getCanonicalName())) {
            return "String";
        } else {
            throw new UnsupportedOperationException(variableElement.asType().toString() + " is not supported");
        }
    }

}
