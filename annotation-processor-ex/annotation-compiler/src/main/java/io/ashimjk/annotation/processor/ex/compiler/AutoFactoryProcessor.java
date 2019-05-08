package io.ashimjk.annotation.processor.ex.compiler;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import io.ashimjk.annotation.processor.ex.factory.AutoElement;
import io.ashimjk.annotation.processor.ex.factory.AutoFactory;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AutoService(Processor.class)
public class AutoFactoryProcessor extends AbstractProcessor {

    private Filer filer; // For writing file
    private Messager messager; // for log purpose

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        this.note("Annotation Processor Started");

        Map<ClassName, List<ElementInfo>> classes = this.getClassNamesByAutoFactoryAnnotation(roundEnvironment);

        this.getClassNamesByAutoElementAnnotation(roundEnvironment, classes);

        try {
            new FactoryBuilder(filer, classes).generate();

        } catch (IOException e) {
            error(e.getMessage());
        }

        this.note("Annotation Processor Ended");
        return false;
    }

    private void getClassNamesByAutoElementAnnotation(final RoundEnvironment roundEnvironment,
                                                      final Map<ClassName, List<ElementInfo>> result) {

        roundEnvironment.getElementsAnnotatedWith(AutoElement.class)
                .stream()
                .filter(this::isClassType)
                .forEach(annotatedElement -> updateClassesResult(result, annotatedElement));

    }

    private Map<ClassName, List<ElementInfo>> getClassNamesByAutoFactoryAnnotation(RoundEnvironment roundEnvironment) {

        Map<ClassName, List<ElementInfo>> result = new HashMap<>();

        roundEnvironment.getElementsAnnotatedWith(AutoFactory.class)
                .stream()
                .filter(this::isInterfaceType)
                .map(annotatedElement -> ClassName.get((TypeElement) annotatedElement))
                .filter(className -> !result.containsKey(className))
                .forEach(className -> result.put(className, new ArrayList<>()));

        return result;
    }

    private void updateClassesResult(Map<ClassName, List<ElementInfo>> result, Element annotatedElement) {

        AutoElement autoElement = annotatedElement.getAnnotation(AutoElement.class);
        TypeElement typeElement = (TypeElement) annotatedElement;
        ClassName className = ClassName.get(typeElement);
        List<? extends TypeMirror> list = typeElement.getInterfaces();

        list.stream()
                .map(this::getName)
                .filter(result::containsKey)
                .forEach(typeName -> result.get(typeName).add(new ElementInfo(autoElement.tag(), className)));

    }

    private boolean isInterfaceType(Element annotatedElement) {
        return annotatedElement.getKind() == ElementKind.INTERFACE;
    }

    private boolean isClassType(Element annotatedElement) {
        return annotatedElement.getKind() == ElementKind.CLASS;
    }

    private ClassName getName(TypeMirror typeMirror) {
        String rawString = typeMirror.toString();
        int dotPosition = rawString.lastIndexOf(".");
        String packageName = rawString.substring(0, dotPosition);
        String className = rawString.substring(dotPosition + 1);
        return ClassName.get(packageName, className);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(AutoFactory.class.getCanonicalName());
        annotations.add(AutoElement.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private void error(String message) {
        messager.printMessage(Diagnostic.Kind.ERROR, message);
    }

    private void note(String message) {
        messager.printMessage(Diagnostic.Kind.NOTE, message);
    }
}