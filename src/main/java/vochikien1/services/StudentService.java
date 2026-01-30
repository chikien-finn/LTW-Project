// package vochikien1.services;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.GetMapping;

// import vochikien1.Repository.StudentRepository;
// import vochikien1.entities.Student;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class StudentService {

//     @Autowired
//     private StudentRepository repository;

//     // ===== READ: Lấy tất cả =====
//     public List<Student> getAllStudents() {
//         return repository.findAll();
//     }

//     // ===== READ: Lấy theo ID =====
//     public Student getStudentById(Integer id) {
//         Optional<Student> optional = repository.findById(id);
//         return optional.orElse(null);
//     }

//     // ===== READ: Tìm theo tên =====
//     public List<Student> searchByName(String name) {
//         return repository.findByNameContainingIgnoreCase(name);
//     }

//     // ===== CREATE + UPDATE =====
//     public void saveStudent(Student student) {
//         repository.save(student);
//         /*
//          * Nếu student.id == null  → INSERT
//          * Nếu student.id != null  → UPDATE
//          * JPA tự xử lý
//          */
//     }

//     // ===== DELETE =====
//     public void deleteStudent(Integer id) {
//         repository.deleteById(id);
//     }

// }

package vochikien1.services;

import org.springframework.stereotype.Service;
import vochikien1.entities.Student;
import vochikien1.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student getStudentById(String id) {
        return repo.findById(id).orElse(null);
    }

    public List<Student> searchByName(String keyword) {
        return repo.findByNameContainingIgnoreCase(keyword);
    }

    public Student save(Student student) {

        // ⭐ DÒNG QUYẾT ĐỊNH
        if (student.getId() != null && student.getId().isBlank()) {
            student.setId(null); // cho Mongo tự sinh
        }

        return repo.save(student);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}

