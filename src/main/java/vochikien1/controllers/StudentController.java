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

    // ===== API JSON =====
    @GetMapping("/api/students")
    @ResponseBody
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // ===== LIST + SEARCH (THEO NAME) =====
    @GetMapping("/students")
    public String listStudents(
            @RequestParam(name = "keyword", required = false) String keyword,
            Model model) {

        List<Student> students;

        if (keyword != null && !keyword.trim().isEmpty()) {
            students = studentService.searchByName(keyword);
        } else {
            students = studentService.getAllStudents();
        }

        model.addAttribute("students", students);
        model.addAttribute("keyword", keyword);
        return "students";
    }

    // ===== DETAIL =====
    @GetMapping("/student/{id}")
    public String viewDetail(@PathVariable String id, Model model) {
        Student s = studentService.getStudentById(id);
        if (s != null) {
            model.addAttribute("student", s);
            return "student-detail";
        }
        return "redirect:/students";
    }

    // ===== ADD FORM =====
    @GetMapping("/student/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    // ===== SAVE (ADD + UPDATE) =====
    @PostMapping("/student/save")
    public String saveStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    // ===== EDIT FORM =====
    @GetMapping("/student/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Student s = studentService.getStudentById(id);
        if (s != null) {
            model.addAttribute("student", s);
            return "student-form";
        }
        return "redirect:/students";
    }

    // ===== DELETE =====
    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
