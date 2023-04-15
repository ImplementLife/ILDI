package com.impllife.ildi.processor;

import com.google.auto.service.AutoService;
import com.impllife.ildi.annotation.Component;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.Set;

@SupportedAnnotationTypes("com.impllife.ildi.annotation.Component")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class ComponentProcessor extends BaseProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        info("ComponentProcessor.process is run");
        for (Element clazz : roundEnv.getElementsAnnotatedWith(Component.class)) {
            if (!(clazz instanceof TypeElement)) continue;
            TypeElement typeElement = (TypeElement) clazz;
            for (Element element : typeElement.getEnclosedElements()) {
                if (!(element instanceof VariableElement)) continue;
                VariableElement variableElement = (VariableElement) element;
                if (!variableElement.getModifiers().contains(Modifier.FINAL)) {

                    err("Class '%s' is annotated as @Component, but field '%s' is not declared as final",
                        typeElement.getSimpleName(), variableElement.getSimpleName());
                }
            }
        }
        return false; // Claiming that annotations have been processed by this processor
    }
}
