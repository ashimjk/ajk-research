package io.ashimjk.json_merge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.IOException;

public class ExampleMain {

    public static void main(String[] args) throws IOException {

        String inputJson = getInputJson();

        Employee existingEmployee =
            Employee.of(
                "John", "Dev", 1000, "222-222-222", Address.of("101 Blue Dr", "SunBridge", "23456"));

        printEmployee(existingEmployee, "existing object");

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectReader objectReader = objectMapper.readerForUpdating(existingEmployee);

        Employee updatedEmployee = objectReader.readValue(inputJson);

        printEmployee(updatedEmployee, "updated object");
    }

    private static String getInputJson() {
        String inputJson =
            "{\"name\":\"Jake\",\"salary\":3000,\"address\":{\"street\":\"101 Blue Dr\",\"city\":\"White Smoke\"}}";
        System.out.println("input json: " + inputJson);

        return inputJson;
    }

    private static void printEmployee(Employee existingEmployee, String title) {

        System.out.println(title + " : " + existingEmployee);
        System.out.println(title + " hascode: " + System.identityHashCode(existingEmployee));
        System.out.println(
            title
                + " nested (Address) hascode: "
                + System.identityHashCode(existingEmployee.getAddress()));
    }
}
