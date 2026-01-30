// package vochikien1.Repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import vochikien1.entities.Student;
// import java.util.List;

// public interface StudentRepository
//         extends JpaRepository<Student, Integer> {
//         List<Student> findByNameContainingIgnoreCase(String name);
// }


package vochikien1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vochikien1.entities.Student;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {

    List<Student> findByNameContainingIgnoreCase(String name);
}

