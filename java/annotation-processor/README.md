# Annotation Processor Example

## Annotation processing steps

- Build starts in java compiler. (java compiler knows all processors, So If we want to create new one, we need to tell to compiler about that.)
- Starts all Annotation Processors which is not executed.(Every processor has its own implementation)
- Loop over annotated elements inside the processor
- Finds annotated classes, methods, fields.
- Generate a new class with metadata of founded classes, methods, fields. (This is the place where you generate code.)
- Create new file and write your generated string as a class.
- Compiler checks if all annotation processors are executed. If not, start to next round.
- Then it compiles the code

## Annotation Description

```java
    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.TYPE)
        public @interface NewIntent {
    }
```

### @interface

This annotation tells to compiler this is the custom annotation.NewIntent is the name of our custom annotation.

### @Target

What is your target? What do you want to annotate? Class or method? Constructor or field? Maybe you want to annotate another annotation? Here is the enum list that you can use as target. I copy-paste it from java.lang.annotation package.

```java
public enum ElementType {
    TYPE, //If you want to annotate class, interface, enum..
    FIELD, //If you want to annotate field (includes enum constants)
    METHOD, //If you want to annotate method
    PARAMETER, //If you want to annotate parameter
    CONSTRUCTOR, //If you want to annotate constructor
    LOCAL_VARIABLE, //..
    ANNOTATION_TYPE, //..
    PACKAGE, //..
    TYPE_PARAMETER, //..(java 8)
    TYPE_USE; //..(java 8)

    private ElementType() {
    }
}
```

### @Retention

This annotation indicates how the custom annotation is stored. There are 3 types of retention.

- SOURCE : analyses by compiler and never stored
- CLASS : stored into class file and not retained in runtime
- RUNTIME : store into class file and usable in runtime(by reflection)

### Processor

This is the where magic happens. You spend most of time here If you want to do code generation.

```java
public class NewIntentProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {}

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) {}

    @Override
    public Set<String> getSupportedAnnotationTypes() {}

    @Override
    public SourceVersion getSupportedSourceVersion() {}
}

````

> init(): gives you paintbrushes to start painting. Filer(to generate file), Messager(debugging), Utility classes. You can get these classes with processing environment.

>process(): brain of your processor. Starts rounding and gives you annotated classes, methods, fields, annotation etc. It gives you all annotated elements here. And you start doing all calculation and generate your new class file here.

> getSupportedAnnotationTypes(): We return only our custom annotation set in this method. We can say that return value of this method will be given to us as process method’s first parameter.

> getSupportedSourceVersion(): We always return latest java version.

## Annotation Element

```java
package com.example;	// PackageElement

public class Foo {		// TypeElement

	private int a;		// VariableElement
	private Foo other; 	// VariableElement

	public Foo () {} 	// ExecuteableElement

	public void setA ( 	// ExecuteableElement
	                 int newA	// TypeElement
	                 ) {}
}
```