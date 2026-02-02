package vochikien1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import vochikien1.repository.StudentRepository;
import vochikien1.entities.Student;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    // ===== READ: Lấy tất cả =====
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // ===== READ: Lấy theo ID =====
    public Student getStudentById(Integer id) {
        Optional<Student> optional = repository.findById(id);
        return optional.orElse(null);
    }

    // ===== READ: Tìm theo tên =====
    public List<Student> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    // ===== CREATE + UPDATE =====
    public void saveStudent(Student student) {
        repository.save(student);
        /*
         * Nếu student.id == null  → INSERT
         * Nếu student.id != null  → UPDATE
         * JPA tự xử lý
         */
    }

    // ===== DELETE =====
    public void deleteStudent(Integer id) {
        repository.deleteById(id);
    }

}



