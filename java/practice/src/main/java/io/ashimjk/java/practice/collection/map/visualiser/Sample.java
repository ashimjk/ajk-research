package io.ashimjk.java.practice.collection.map.visualiser;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * A simple program that shows at the terminal,
 * the structure of sample HashMap and TreeMap instances.
 *
 * @author Manoel Campos da Silva Filho
 */
public class Sample {

    private final Console console;

    public Sample() {
        this.console = new Console();
        this.printTreeMap();
        this.printHashMap();
    }

    private void printTreeMap() {
        TreeMap<String, Object> treeMap1 = new TreeMap<>();
        this.insertSampleMapData(treeMap1);
        System.out.println(treeMap1.getClass().getSimpleName());
        new TreeMapVisualiser(this.console).visualise(treeMap1);
        System.out.println();
    }

    private void printHashMap() {
        HashMap<String, Object> hashMap1 = new HashMap<>();
        this.insertSampleMapData(hashMap1);
        System.out.println(hashMap1.getClass().getSimpleName());
        new HashMapVisualiser(this.console).visualise(hashMap1);
        System.out.println();
    }

    private void insertSampleMapData(Map<String, Object> map) {
        map.put("name", "Manoel Campos");
        map.put("country", "Brazil");
        map.put("age", 35);
        map.put("height", 1.8);
        map.put("gender", 'M');
        map.put("site", "http://github.com/manoelcampos");
    }

    public static void main(String[] args) {
        new Sample();
    }

}
