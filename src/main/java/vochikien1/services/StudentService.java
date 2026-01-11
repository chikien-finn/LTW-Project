package vochikien1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vochikien1.Repository.StudentRepository;
import vochikien1.entities.Student;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    // 1. Lấy tất cả danh sách (Đã có)
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // 2. Tìm kiếm sinh viên theo ID (Dùng cho trang Chi tiết)
    public Student getStudentById(Integer id) {
        Optional<Student> optional = repository.findById(id);
        return optional.orElse(null); // Trả về student nếu thấy, ngược lại trả về null
    }

    // 3. Tìm kiếm sinh viên theo tên (Dùng cho chức năng Tìm kiếm)
    public List<Student> searchByName(String name) {
        // Hàm này yêu cầu bạn phải khai báo trong StudentRepository trước
        return repository.findByNameContainingIgnoreCase(name);
    }
}