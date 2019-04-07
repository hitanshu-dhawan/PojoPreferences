package com.hitanshudhawan.compiler;

import com.hitanshudhawan.annotations.Pref;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import static com.hitanshudhawan.compiler.Utils.capitalize;
import static com.hitanshudhawan.compiler.Utils.defaultVariableValue;
import static com.hitanshudhawan.compiler.Utils.getTypeElements;
import static com.hitanshudhawan.compiler.Utils.isVariableSupported;
import static com.hitanshudhawan.compiler.Utils.sharedPreferencesMethodName;

public class Processor extends AbstractProcessor {

    private Elements elements;
    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        elements = processingEnvironment.getElementUtils();
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {

        for (TypeElement typeElement : getTypeElements(annotations, roundEnvironment.getRootElements())) {

            String packageName = elements.getPackageOf(typeElement).getQualifiedName().toString();
            String className = typeElement.getSimpleName().toString();
            String generatedClassName = className + "Pref";

            // class
            TypeSpec.Builder classBuilder = TypeSpec.classBuilder(ClassName.get(packageName, generatedClassName))
                    .addModifiers(Modifier.PUBLIC);

            for (VariableElement variableElement : ElementFilter.fieldsIn(typeElement.getEnclosedElements())) {
                if (variableElement.getAnnotation(Pref.class) == null) break;
                if (!isVariableSupported(variableElement)) {
                    messager.printMessage(Diagnostic.Kind.ERROR, variableElement.asType().toString() + " is not supported", variableElement);
                    break;
                }

                String variableName = variableElement.getSimpleName().toString();

                // setter
                classBuilder.addMethod(
                        MethodSpec.methodBuilder("set" + capitalize(variableName))
                                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                                .returns(void.class)
                                .addParameter(ClassName.get("android.content", "Context"), "context")
                                .addParameter(TypeName.get(variableElement.asType()), variableName)
                                .beginControlFlow("if ($T.VERSION.SDK_INT >= 9)", ClassName.get("android.os", "Build"))
                                .addStatement("context.getSharedPreferences($S, $T.MODE_PRIVATE).edit().$N($S, $N).apply()", setterStatementArgs(packageName, generatedClassName, variableName, variableElement))
                                .nextControlFlow("else")
                                .addStatement("context.getSharedPreferences($S, $T.MODE_PRIVATE).edit().$N($S, $N).commit()", setterStatementArgs(packageName, generatedClassName, variableName, variableElement))
                                .endControlFlow()
                                .build()
                );

                // getter
                classBuilder.addMethod(
                        MethodSpec.methodBuilder("get" + capitalize(variableName))
                                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                                .returns(TypeName.get(variableElement.asType()))
                                .addParameter(ClassName.get("android.content", "Context"), "context")
                                .addStatement("return context.getSharedPreferences($S, $T.MODE_PRIVATE).$N($S, $N)", getterStatementArgs(packageName, generatedClassName, variableName, variableElement))
                                .build()
                );
            }

            try {
                JavaFile.builder(packageName, classBuilder.build())
                        .build()
                        .writeTo(filer);
            } catch (IOException e) {
                messager.printMessage(Diagnostic.Kind.ERROR, e.toString(), typeElement);
            }

        }

        return true;
    }

    private Object[] setterStatementArgs(String packageName, String generatedClassName, String variableName, VariableElement variableElement) {
        return new Object[]{
                packageName + "." + generatedClassName,
                ClassName.get("android.content", "Context"),
                sharedPreferencesMethodName("put", variableElement),
                variableName,
                variableName
        };
    }

    private Object[] getterStatementArgs(String packageName, String generatedClassName, String variableName, VariableElement variableElement) {
        return new Object[]{
                packageName + "." + generatedClassName,
                ClassName.get("android.content", "Context"),
                sharedPreferencesMethodName("get", variableElement),
                variableName,
                defaultVariableValue(variableElement)
        };
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new HashSet<String>() {{
            add(Pref.class.getCanonicalName());
        }};
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

}
