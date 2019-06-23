package io.ashimjk.clone.shallow;

public class App {

    public static void main(String[] args) {
        Department d = new Department(1, "IT");
        Employee e1 = new Employee(100, "ashim", d);

        print(e1);

        Employee e2 = Employee.clone(e1);
        print(e2);
    }

    private static void print(Employee e) {
        System.out.println(e);
    }
}
