package com.hitanshudhawan.compiler;

import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

class Utils {

    public static String capitalize(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    // TODO
    static String defaultVariableValue(VariableElement variableElement) {
        TypeMirror variableType = variableElement.asType();
        if (variableType.getKind() == TypeKind.BOOLEAN || variableType.toString().equals(Boolean.class.getCanonicalName())) {
            return "false";
        } else if (variableType.getKind() == TypeKind.FLOAT || variableType.toString().equals(Float.class.getCanonicalName())) {
            return "0.0F";
        } else if (variableType.getKind() == TypeKind.INT || variableType.toString().equals(Integer.class.getCanonicalName())) {
            return "0";
        } else if (variableType.getKind() == TypeKind.LONG || variableType.toString().equals(Long.class.getCanonicalName())) {
            return "0L";
        } else if (variableType.toString().equals(String.class.getCanonicalName())) {
            return "null";
        } else {
            throw new UnsupportedOperationException(variableElement.asType().toString() + " is not supported");
        }
    }

}
