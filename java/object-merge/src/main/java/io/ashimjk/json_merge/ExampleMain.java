package io.ashimjk.json_merge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ExampleMain {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(Address.of(1L, "101 Blue Dr", "SunBridge", "23456"));

        Employee existingEmployee = Employee.of("John", "Dev", 1000, "222-222-222", addresses);

        printEmployee(existingEmployee, "existing object");

        Employee newUpdate = getEmployeeFromJson();
        Employee updatedEmployee = mergeEmployee(existingEmployee, newUpdate);

//        mergeId(existingEmployee.getAddresses(), updatedEmployee.getAddresses());
        updateId(existingEmployee.getAddresses(), updatedEmployee.getAddresses(), Address::getId, (id, address) -> address.setId(id));

        printEmployee(updatedEmployee, "updated object");
    }

    private static Employee mergeEmployee(Employee existingEmployee, Employee newEmployee) throws IOException {
        Employee original = mapper.readValue(mapper.writeValueAsString(existingEmployee), Employee.class);
        ObjectReader objectReader = mapper.readerForUpdating(original);
        return objectReader.readValue(mapper.writeValueAsString(newEmployee));
    }

    private static <T> List<T> updateId(List<T> original,
                                     List<T> updated,
                                     Function<T, Long> getIdFunction,
                                     BiConsumer<Long, T> setIdFunction) {

        if (!Objects.isNull(original) && !Objects.isNull(updated)) {

            int length = Math.min(original.size(), updated.size());
            for (int i = 0; i < length; i++) {

                Long id = getIdFunction.apply(original.get(i));
                setIdFunction.accept(id, updated.get(i));
            }
        }

        return updated;
    }

    private static void mergeId(List<Address> existingAddresses, List<Address> updatedAddresses) {
        if (!Objects.isNull(existingAddresses) && !Objects.isNull(updatedAddresses)) {

            int addressLength = Math.min(existingAddresses.size(), updatedAddresses.size());
            for (int i = 0; i < addressLength; i++) {
                updatedAddresses.get(i).setId(existingAddresses.get(i).getId());
            }
        }
    }

    @SneakyThrows
    private static Employee getEmployeeFromJson() {
        //language=JSON
        String inputJson =
                "{\n" +
                        "  \"name\": \"Jake\",\n" +
                        "  \"salary\": 3000,\n" +
                        "  \"addresses\": [\n" +
                        "    {\n" +
                        "      \"street\": \"101 Blue Dr\",\n" +
                        "      \"city\": \"White Smoke\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"street\": \"102 Blue Dr\",\n" +
                        "      \"city\": \"Black Smoke\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}";

        return mapper.readValue(inputJson, Employee.class);
    }

    private static void printEmployee(Employee existingEmployee, String title) {

        System.out.println(title + " : " + existingEmployee);
        System.out.println(title + " hascode: " + System.identityHashCode(existingEmployee));
        System.out.println(title + " nested (Address) hascode: " + System.identityHashCode(existingEmployee.getAddresses()));
    }

}
