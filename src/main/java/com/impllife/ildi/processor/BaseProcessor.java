package com.impllife.ildi.processor;

import javax.annotation.processing.AbstractProcessor;
import javax.tools.Diagnostic;

public abstract class BaseProcessor extends AbstractProcessor {
    protected void err(String text, Object ...value) {
        super.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, String.format("[ILDI] " + text, value));
    }
    protected void info(String text, Object ...value) {
        super.processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, String.format("[ILDI] " + text, value));
    }
}
