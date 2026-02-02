package vochikien1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vochikien1.entities.Student;
import java.util.List;

public interface StudentRepository
        extends JpaRepository<Student, Integer> {
        List<Student> findByNameContainingIgnoreCase(String name);
}




