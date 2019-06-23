package io.ashimjk.clone.using_byte_stream.java;

import java.util.Vector;

public class Example {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        // Make a Vector
        Vector original = new Vector();

        // Make a StringBuffer and add it to the Vector
        StringBuffer text = new StringBuffer("The quick brown fox");
        original.addElement(text);

        // Clone the vector and print out the contents
        Vector clone = (Vector) original.clone();
        System.out.println("A. After cloning");
        printVectorContents(original, "original");
        printVectorContents(clone, "clone");
        System.out.println("--------------------------------------------------------");
        System.out.println();

        // Add another object (an Integer) to the clone and
        // print out the contents
        clone.addElement(5);
        System.out.println("B. After adding an Integer to the clone");
        printVectorContents(original, "original");
        printVectorContents(clone, "clone");
        System.out.println("--------------------------------------------------------");
        System.out.println();

        // Change the StringBuffer contents
        text.append(" jumps over the lazy dog.");
        System.out.println("C. After modifying one of original's elements");
        printVectorContents(original, "original");
        printVectorContents(clone, "clone");
        System.out.println("--------------------------------------------------------");
        System.out.println();
    }

    private static void printVectorContents(Vector v, String name) {
        System.out.println("  Contents of \"" + name + "\":");

        // For each element in the vector, print out the index, the
        // class of the element, and the element itself
        for (int i = 0; i < v.size(); i++) {
            Object element = v.elementAt(i);
            System.out.println("   " + i + " (" + element.getClass().getName() + "): " + element);
        }
        System.out.println();
    }

}