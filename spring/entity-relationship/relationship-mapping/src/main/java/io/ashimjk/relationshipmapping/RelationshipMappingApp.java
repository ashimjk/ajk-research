package io.ashimjk.relationshipmapping;

import io.ashimjk.relationshipmapping.model.Passport;
import io.ashimjk.relationshipmapping.model.Student;
import io.ashimjk.relationshipmapping.repository.CourseRepository;
import io.ashimjk.relationshipmapping.repository.EmployeeRepository;
import io.ashimjk.relationshipmapping.repository.JpaPassportRepository;
import io.ashimjk.relationshipmapping.repository.JpaStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class RelationshipMappingApp implements CommandLineRunner {

    //    private final StudentRepository studentRepository;
    private final JpaStudentRepository jpaStudentRepository;
    private final JpaPassportRepository jpaPassportRepository;
    private final CourseRepository courseRepository;
    private final EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(RelationshipMappingApp.class, args);
    }

    @Override
    public void run(String... args) {
//        try {
//            studentRepository.save();
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        studentRepository.save2();
//        studentRepository.find();

//        jpaTest();

        saveEmployee();
    }

    private void jpaTest() {
        Passport passport = new Passport("Z789");
        jpaPassportRepository.save(passport);

        Student student = new Student("ashim", passport);
        passport.setStudent(student);

        jpaStudentRepository.save(student);
    }

    // Fails because of transaction is closed as soon as findById is called
    // @Transactional
    public void jpaLazyFind() {
        final Student student = jpaStudentRepository.findById(6L).get();
        System.out.println(student);
        System.out.println(student.getPassport());
    }

    public void saveEmployee() {
        employeeRepository.save();
        employeeRepository.getAll();
    }

}
