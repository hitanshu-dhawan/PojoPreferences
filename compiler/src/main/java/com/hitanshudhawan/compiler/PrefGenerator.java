package com.hitanshudhawan.compiler;

import com.hitanshudhawan.annotations.Pref;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import static com.hitanshudhawan.compiler.PrefUtils.getSharedPreferencesDefaultValue;
import static com.hitanshudhawan.compiler.PrefUtils.getSharedPreferencesMethodName;
import static com.hitanshudhawan.compiler.PrefUtils.isVariableSupported;
import static com.hitanshudhawan.compiler.Utils.capitalize;

class PrefGenerator {

    private Elements elements;
    private Filer filer;
    private Messager messager;

    PrefGenerator(Elements elements, Messager messager, Filer filer) {
        this.elements = elements;
        this.filer = filer;
        this.messager = messager;
    }

    void generateClass(TypeElement typeElement) {

        String packageName = elements.getPackageOf(typeElement).getQualifiedName().toString();
        String className = typeElement.getSimpleName().toString();
        String generatedClassName = className + "Pref";

        TypeSpec.Builder prefClass = getPrefClass(packageName, generatedClassName);

        for (VariableElement variableElement : ElementFilter.fieldsIn(typeElement.getEnclosedElements())) {
            if (variableElement.getAnnotation(Pref.class) == null) break;
            if (!isVariableSupported(variableElement)) {
                messager.printMessage(Diagnostic.Kind.ERROR, variableElement.asType().toString() + " is not supported", variableElement);
                break;
            }

            prefClass.addMethod(getSetterMethod(packageName, generatedClassName, variableElement));

            prefClass.addMethod(getGetterMethod(packageName, generatedClassName, variableElement));

        }

        try {
            JavaFile.builder(packageName, prefClass.build()).build().writeTo(filer);
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, e.toString(), typeElement);
        }
    }

    private TypeSpec.Builder getPrefClass(String packageName, String generatedClassName) {
        return TypeSpec
                .classBuilder(ClassName.get(packageName, generatedClassName))
                .addModifiers(Modifier.PUBLIC);
    }

    private MethodSpec getSetterMethod(String packageName, String generatedClassName, VariableElement variableElement) {
        String variableName = variableElement.getSimpleName().toString();
        return MethodSpec
                .methodBuilder("set" + capitalize(variableName))
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(ClassName.get("android.content", "Context"), "context")
                .addParameter(TypeName.get(variableElement.asType()), variableName)
                .beginControlFlow("if ($T.VERSION.SDK_INT >= 9)", ClassName.get("android.os", "Build"))
                .addStatement("context.getSharedPreferences($S, $T.MODE_PRIVATE).edit().put$N($S, $N).apply()", getSetterStatementArgs(packageName, generatedClassName, variableName, variableElement))
                .nextControlFlow("else")
                .addStatement("context.getSharedPreferences($S, $T.MODE_PRIVATE).edit().put$N($S, $N).commit()", getSetterStatementArgs(packageName, generatedClassName, variableName, variableElement))
                .endControlFlow()
                .build();
    }

    private MethodSpec getGetterMethod(String packageName, String generatedClassName, VariableElement variableElement) {
        String variableName = variableElement.getSimpleName().toString();
        return MethodSpec
                .methodBuilder("get" + capitalize(variableName))
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(TypeName.get(variableElement.asType()))
                .addParameter(ClassName.get("android.content", "Context"), "context")
                .addStatement("return context.getSharedPreferences($S, $T.MODE_PRIVATE).get$N($S, $N)", getGetterStatementArgs(packageName, generatedClassName, variableName, variableElement))
                .build();
    }

    private Object[] getSetterStatementArgs(String packageName, String generatedClassName, String variableName, VariableElement variableElement) {
        return new Object[]{
                packageName + "." + generatedClassName,
                ClassName.get("android.content", "Context"),
                getSharedPreferencesMethodName(variableElement),
                variableName,
                variableName
        };
    }

    private Object[] getGetterStatementArgs(String packageName, String generatedClassName, String variableName, VariableElement variableElement) {
        return new Object[]{
                packageName + "." + generatedClassName,
                ClassName.get("android.content", "Context"),
                getSharedPreferencesMethodName(variableElement),
                variableName,
                getSharedPreferencesDefaultValue(variableElement)
        };
    }

}
