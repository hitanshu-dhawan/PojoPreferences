package com.hitanshudhawan.compiler;

import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.VariableElement;

class PrefUtils {

    private static Set<String> supportedVariables = new HashSet<String>() {{
        add(Variable.BOOLEAN);
        add(Variable.FLOAT);
        add(Variable.INT);
        add(Variable.LONG);
        add(Variable.STRING);
        add(Variable.STRING_SET);
    }};

    static boolean isVariableSupported(VariableElement variableElement) {
        return supportedVariables.contains(variableElement.asType().toString());
    }

    static int getSharedPreferencesMinApi(VariableElement variableElement) {
        if (!isVariableSupported(variableElement))
            throw new UnsupportedOperationException(variableElement.asType().toString() + " is not supported");

        String variableType = variableElement.asType().toString();
        if (variableType.equals(Variable.STRING_SET)) {
            return 11;
        }
        return 1;
    }

    static String getSharedPreferencesMethodName(VariableElement variableElement) {
        if (!isVariableSupported(variableElement))
            throw new UnsupportedOperationException(variableElement.asType().toString() + " is not supported");

        String variableType = variableElement.asType().toString();
        if (variableType.equals(Variable.BOOLEAN)) {
            return "Boolean";
        } else if (variableType.equals(Variable.FLOAT)) {
            return "Float";
        } else if (variableType.equals(Variable.INT)) {
            return "Int";
        } else if (variableType.equals(Variable.LONG)) {
            return "Long";
        } else if (variableType.equals(Variable.STRING)) {
            return "String";
        } else if (variableType.equals(Variable.STRING_SET)) {
            return "StringSet";
        }
        return null;
    }

    static String getSharedPreferencesDefaultValue(VariableElement variableElement) {
        if (!isVariableSupported(variableElement))
            throw new UnsupportedOperationException(variableElement.asType().toString() + " is not supported");

        String variableType = variableElement.asType().toString();
        if (variableElement.getConstantValue() != null) {
            Object defaultValue = variableElement.getConstantValue();
            if (variableType.equals(Variable.FLOAT)) {
                return defaultValue + "F";
            } else if (variableType.equals(Variable.STRING)) {
                return "\"" + defaultValue + "\"";
            } else {
                return defaultValue.toString();
            }
        } else {
            if (variableType.equals(Variable.BOOLEAN)) {
                return "false";
            } else if (variableType.equals(Variable.FLOAT)) {
                return "0.0F";
            } else if (variableType.equals(Variable.INT)) {
                return "0";
            } else if (variableType.equals(Variable.LONG)) {
                return "0L";
            } else if (variableType.equals(Variable.STRING)) {
                return "\"\"";
            } else if (variableType.equals(Variable.STRING_SET)) {
                return "null";
            }
        }
        return null;
    }

}
