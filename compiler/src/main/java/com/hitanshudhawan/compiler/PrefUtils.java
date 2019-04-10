package com.hitanshudhawan.compiler;

import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.VariableElement;

class PrefUtils {

    private static Set<String> supportedVariables = new HashSet<String>() {{
        add(boolean.class.getCanonicalName());
        add(float.class.getCanonicalName());
        add(int.class.getCanonicalName());
        add(long.class.getCanonicalName());
        add(String.class.getCanonicalName());
    }};

    static boolean isVariableSupported(VariableElement variableElement) {
        return supportedVariables.contains(variableElement.asType().toString());
    }

    static String getSharedPreferencesMethodName(VariableElement variableElement) {
        if (!isVariableSupported(variableElement))
            throw new UnsupportedOperationException(variableElement.asType().toString() + " is not supported");

        String variableType = variableElement.asType().toString();
        if (variableType.equals(boolean.class.getCanonicalName())) {
            return "Boolean";
        } else if (variableType.equals(float.class.getCanonicalName())) {
            return "Float";
        } else if (variableType.equals(int.class.getCanonicalName())) {
            return "Int";
        } else if (variableType.equals(long.class.getCanonicalName())) {
            return "Long";
        } else if (variableType.equals(String.class.getCanonicalName())) {
            return "String";
        }
        return null;
    }

    static String getSharedPreferencesDefaultValue(VariableElement variableElement) {
        if (!isVariableSupported(variableElement))
            throw new UnsupportedOperationException(variableElement.asType().toString() + " is not supported");

        String variableType = variableElement.asType().toString();
        if (variableElement.getConstantValue() != null) {
            Object defaultValue = variableElement.getConstantValue();
            if (variableType.equals(float.class.getCanonicalName())) {
                return defaultValue + "F";
            } else if (variableType.equals(String.class.getCanonicalName())) {
                return "\"" + defaultValue + "\"";
            } else {
                return defaultValue.toString();
            }
        } else {
            if (variableType.equals(boolean.class.getCanonicalName())) {
                return "false";
            } else if (variableType.equals(float.class.getCanonicalName())) {
                return "0.0F";
            } else if (variableType.equals(int.class.getCanonicalName())) {
                return "0";
            } else if (variableType.equals(long.class.getCanonicalName())) {
                return "0L";
            } else if (variableType.equals(String.class.getCanonicalName())) {
                return "\"\"";
            }
        }
        return null;
    }

}
