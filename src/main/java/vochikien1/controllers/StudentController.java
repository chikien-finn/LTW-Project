package vochikien1.controllers;

import vochikien1.entities.Student;
import vochikien1.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    // ===================== API JSON =====================

    // GET ALL
    @GetMapping("/api/students")
    @ResponseBody
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // GET BY ID
    @GetMapping("/api/students/{id}")
    @ResponseBody
    public Student getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    // SEARCH (name hoặc id)
    @GetMapping("/api/students/search")
    @ResponseBody
    public List<Student> searchStudents(@RequestParam String keyword) {

        if (keyword.matches("\\d+")) {
            Student s = studentService.getStudentById(Integer.parseInt(keyword));
            return (s != null) ? List.of(s) : List.of();
        } else {
            return studentService.searchByName(keyword);
        }
    }

    // CREATE
    @PostMapping("/api/students")
    @ResponseBody
    public Student createStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        return student;
    }

    // UPDATE
    @PutMapping("/api/students/{id}")
    @ResponseBody
    public Student updateStudent(@PathVariable Integer id,
                                 @RequestBody Student student) {
        student.setId(id);
        studentService.saveStudent(student);
        return student;
    }

    // DELETE
    @DeleteMapping("/api/students/{id}")
    @ResponseBody
    public String deleteStudentApi(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return "Deleted successfully";
    }

    // ===================== MVC (GIỮ NGUYÊN) =====================

    @GetMapping("/students")
    public String listStudents(
            @RequestParam(name = "keyword", required = false) String keyword,
            Model model) {

        List<Student> students;

        if (keyword != null && !keyword.trim().isEmpty()) {

            if (keyword.matches("\\d+")) {
                Student s = studentService.getStudentById(
                        Integer.parseInt(keyword)
                );
                students = (s != null) ? List.of(s) : List.of();
            } else {
                students = studentService.searchByName(keyword);
            }

        } else {
            students = studentService.getAllStudents();
        }

        model.addAttribute("students", students);
        model.addAttribute("keyword", keyword);
        return "students";
    }

    @GetMapping("/student/{id}")
    public String viewDetail(@PathVariable Integer id, Model model) {
        Student s = studentService.getStudentById(id);
        if (s != null) {
            model.addAttribute("student", s);
            return "student-detail";
        }
        return "redirect:/students";
    }

    @GetMapping("/student/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    @PostMapping("/student/save")
    public String saveStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/student/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Student s = studentService.getStudentById(id);
        if (s != null) {
            model.addAttribute("student", s);
            return "student-form";
        }
        return "redirect:/students";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}