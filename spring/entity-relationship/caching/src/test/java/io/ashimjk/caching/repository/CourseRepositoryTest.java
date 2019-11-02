package io.ashimjk.caching.repository;

import io.ashimjk.caching.CachingApp;
import io.ashimjk.caching.model.Course;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
@SpringBootApplication(scanBasePackageClasses = CachingApp.class)
@ExtendWith(SpringExtension.class)
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    @Transactional
    @DisplayName("JPA First Level Caching")
    void testFirstLevelCaching() {
        final Course firstCourse = courseRepository.findById(10001L).orElseThrow(RuntimeException::new);
        log.info("First Course : {}", firstCourse);

        final Course secondCourse = courseRepository.findById(10001L).orElseThrow(RuntimeException::new);
        log.info("Second Course : {}", secondCourse);

        assertNotNull(firstCourse.getCourseName());
        assertNotNull(secondCourse.getCourseName());
    }

    @Test
    @DisplayName("JPA Second Level Caching")
    void testSecondLevelCaching() {
        final Course firstCourse = courseRepository.findById(10001L).orElseThrow(RuntimeException::new);
        log.info("First Course : {}", firstCourse);

        final Course secondCourse = courseRepository.findById(10001L).orElseThrow(RuntimeException::new);
        log.info("Second Course : {}", secondCourse);

        assertNotNull(firstCourse.getCourseName());
        assertNotNull(secondCourse.getCourseName());
    }

    @Test
    @DirtiesContext
    @DisplayName("Hibernate specific soft delete")
    void testSoftDelete() {
        courseRepository.deleteById(10001L);

        final Course course = courseRepository.findById(10001L).orElse(null);

        assertNull(course);
    }

}