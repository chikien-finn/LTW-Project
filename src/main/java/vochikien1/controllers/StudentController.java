// package vochikien1.controllers;

// import vochikien1.entities.Student;
// import vochikien1.services.StudentService;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @Controller
// public class StudentController {

//     @Autowired
//     private StudentService studentService;

//     // ===== API JSON =====
//     @GetMapping("/api/students")
//     @ResponseBody
//     public List<Student> getAllStudents() {
//         return studentService.getAllStudents();
//     }

//     // ===== LIST + SEARCH (ID hoặc NAME) =====
//     @GetMapping("/students")
//     public String listStudents(
//             @RequestParam(name = "keyword", required = false) String keyword,
//             Model model) {

//         List<Student> students;

//         if (keyword != null && !keyword.trim().isEmpty()) {

//             // keyword là số → tìm theo ID
//             if (keyword.matches("\\d+")) {
//                 Student s = studentService.getStudentById(
//                         Integer.parseInt(keyword)
//                 );
//                 students = (s != null) ? List.of(s) : List.of();
//             }
//             // keyword là chữ → tìm theo tên
//             else {
//                 students = studentService.searchByName(keyword);
//             }

//         } else {
//             students = studentService.getAllStudents();
//         }

//         model.addAttribute("students", students);
//         model.addAttribute("keyword", keyword);
//         return "students";
//     }

//     // ===== DETAIL =====
//     @GetMapping("/student/{id}")
//     public String viewDetail(@PathVariable Integer id, Model model) {
//         Student s = studentService.getStudentById(id);
//         if (s != null) {
//             model.addAttribute("student", s);
//             return "student-detail";
//         }
//         return "redirect:/students";
//     }

//     // ===== ADD FORM =====
//     @GetMapping("/student/add")
//     public String showAddForm(Model model) {
//         model.addAttribute("student", new Student());
//         return "student-form";
//     }

//     // ===== SAVE (ADD + UPDATE) =====
//     @PostMapping("/student/save")
//     public String saveStudent(@ModelAttribute Student student) {
//         studentService.saveStudent(student);
//         return "redirect:/students";
//     }

//     // ===== EDIT FORM =====
//     @GetMapping("/student/edit/{id}")
//     public String showEditForm(@PathVariable Integer id, Model model) {
//         Student s = studentService.getStudentById(id);
//         if (s != null) {
//             model.addAttribute("student", s);
//             return "student-form";
//         }
//         return "redirect:/students";
//     }

//     // ===== DELETE =====
//     @GetMapping("/student/delete/{id}")
//     public String deleteStudent(@PathVariable Integer id) {
//         studentService.deleteStudent(id);
//         return "redirect:/students";
//     }
// }



package vochikien1.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vochikien1.entities.Student;
import vochikien1.services.StudentService;

import java.util.List;

@Controller
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // ===== API JSON =====
    @GetMapping("/api/students")
    @ResponseBody
    public List<Student> apiGetAll() {
        return service.getAllStudents();
    }

    @PostMapping("/api/students")
    @ResponseBody
    public Student apiCreate(@Valid @RequestBody Student student) {
        return service.save(student);
    }

    // ===== LIST + SEARCH =====
    @GetMapping("/students")
    public String list(
            @RequestParam(required = false) String keyword,
            Model model) {

        List<Student> students;

        if (keyword == null || keyword.isBlank()) {
            students = service.getAllStudents();
        } else {
            Student s = service.getStudentById(keyword);
            students = (s != null)
                    ? List.of(s)
                    : service.searchByName(keyword);
        }

        model.addAttribute("students", students);
        model.addAttribute("keyword", keyword);
        return "students";
    }


    // ===== ADD =====
    @GetMapping("/student/add")
    public String addForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    @PostMapping("/student/save")
    public String save(
            @Valid @ModelAttribute Student student,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "student-form";
        }

        service.save(student);
        return "redirect:/students";
    }

    // ===== EDIT =====
    @GetMapping("/student/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Student student = service.getStudentById(id);
        if (student == null) return "redirect:/students";
        model.addAttribute("student", student);
        return "student-form";
    }

    // ===== DELETE =====
    @GetMapping("/student/delete/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/students";
    }

    // ===== DETAIL =====
    @GetMapping("/student/{id}")
    public String detail(@PathVariable String id, Model model) {

        Student student = service.getStudentById(id);

        if (student == null) {
            return "redirect:/students";
        }

        model.addAttribute("student", student);
        return "student-detail";
    }
}

