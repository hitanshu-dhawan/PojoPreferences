package com.hitanshudhawan.compiler;

import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

class Utils {

    static Set<TypeElement> getTypeElements(Set<? extends TypeElement> annotations, Set<? extends Element> elements) {
        Set<TypeElement> typeElements = new HashSet<>();
        for (Element element : elements) {
            if (element instanceof TypeElement) {
                boolean found = false;
                for (Element subElement : element.getEnclosedElements()) {
                    for (AnnotationMirror mirror : subElement.getAnnotationMirrors()) {
                        for (Element annotation : annotations) {
                            if (mirror.getAnnotationType().asElement().equals(annotation)) {
                                typeElements.add((TypeElement) element);
                                found = true;
                                break;
                            }
                        }
                        if (found) break;
                    }
                    if (found) break;
                }
            }
        }
        return typeElements;
    }

    static String capitalize(String name) {
        if (name != null && name.length() != 0) {
            char[] chars = name.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            return new String(chars);
        } else {
            return name;
        }
    }

    static boolean isVariableSupported(VariableElement variableElement) {
        TypeMirror variableType = variableElement.asType();
        return variableType.getKind() == TypeKind.BOOLEAN
                || variableType.toString().equals(Boolean.class.getCanonicalName())
                || variableType.getKind() == TypeKind.FLOAT
                || variableType.toString().equals(Float.class.getCanonicalName())
                || variableType.getKind() == TypeKind.INT
                || variableType.toString().equals(Integer.class.getCanonicalName())
                || variableType.getKind() == TypeKind.LONG
                || variableType.toString().equals(Long.class.getCanonicalName())
                || variableType.toString().equals(String.class.getCanonicalName());
    }

    static String sharedPreferencesMethodName(String type, VariableElement variableElement) {
        if (type == null || (!type.equals("put") && !type.equals("get")))
            throw new IllegalArgumentException("type should be \"put\" or \"get\"");

        TypeMirror variableType = variableElement.asType();
        if (variableType.getKind() == TypeKind.BOOLEAN || variableType.toString().equals(Boolean.class.getCanonicalName())) {
            return type + "Boolean";
        } else if (variableType.getKind() == TypeKind.FLOAT || variableType.toString().equals(Float.class.getCanonicalName())) {
            return type + "Float";
        } else if (variableType.getKind() == TypeKind.INT || variableType.toString().equals(Integer.class.getCanonicalName())) {
            return type + "Int";
        } else if (variableType.getKind() == TypeKind.LONG || variableType.toString().equals(Long.class.getCanonicalName())) {
            return type + "Long";
        } else if (variableType.toString().equals(String.class.getCanonicalName())) {
            return type + "String";
        } else {
            throw new UnsupportedOperationException(variableElement.asType().toString() + " is not supported");
        }
    }

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
