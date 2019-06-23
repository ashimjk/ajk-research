package io.ashimjk.subtype;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ExampleMain {

    public static void main(String[] args) throws IOException {

        View v = new View();
        List<Shape> shapes = Arrays.asList(Rectangle.of(3, 6), Circle.of(5, new Circle.Area()));
        v.setShapes(shapes);

        System.out.println("-- serializing --");
        ObjectMapper om = new ObjectMapper();
        om.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        String s = om.writerWithDefaultPrettyPrinter().writeValueAsString(v);
        System.out.println(s);

        System.out.println("-- deserializing --");
        View view = om.readValue(s, View.class);
        System.out.println(view);
    }
}
