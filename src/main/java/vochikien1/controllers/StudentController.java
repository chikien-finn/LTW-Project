package vochikien1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import vochikien1.entities.Student;
import vochikien1.services.StudentService;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    // ===== READ: Danh sách + tìm kiếm =====
    @GetMapping("/students")
    public String listStudents(
            @RequestParam(name = "name", required = false) String name,
            Model model) {

        List<Student> list;

        if (name != null && !name.isEmpty()) {
            list = studentService.searchByName(name);
        } else {
            list = studentService.getAllStudents();
        }

        model.addAttribute("students", list);
        return "students";
    }

    // ===== READ: Chi tiết =====
    @GetMapping("/student/{id}")
    public String viewDetail(@PathVariable Integer id, Model model) {
        Student s = studentService.getStudentById(id);
        if (s != null) {
            model.addAttribute("student", s);
            return "student-detail";
        }
        return "redirect:/students";
    }

    // ===== CREATE: Hiển thị form thêm =====
    @GetMapping("/student/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    // ===== CREATE: Xử lý thêm =====
    @PostMapping("/student/save")
    public String saveStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    // ===== UPDATE: Hiển thị form sửa =====
    @GetMapping("/student/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Student s = studentService.getStudentById(id);
        if (s != null) {
            model.addAttribute("student", s);
            return "student-form";
        }
        return "redirect:/students";
    }

    // ===== DELETE =====
    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }


}
