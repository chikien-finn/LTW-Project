package vochikien1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vochikien1.entities.Student;
import vochikien1.services.StudentService;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String listStudents(@RequestParam(name = "name", required = false) String name, Model model) {
        List<Student> list;
        
        // Nếu người dùng có nhập tên vào ô tìm kiếm
        if (name != null && !name.isEmpty()) {
            list = studentService.searchByName(name);
        } else {
            // Nếu không tìm kiếm, hiển thị tất cả
            list = studentService.getAllStudents();
        }
        
        model.addAttribute("students", list);
        return "students";
    }
    @GetMapping("/student/{id}")
    public String viewDetail(@PathVariable("id") Integer id, Model model) {
        Student s = studentService.getStudentById(id);
        if (s != null) {
            model.addAttribute("student", s);
            return "student-detail";
        }
        return "redirect:/students"; // Nếu không thấy ID thì quay về danh sách
    }
}
