
package vochikien1.controllers;
import org.springframework.web.bind.annotation.RestController;
import vochikien1.modules.Students; // Đúng với cấu trúc thư mục của bạn
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class HelloController {
    // Bai 1
    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot API";
    }
    //câu 2
    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Xin chào " + name;
    }
    @GetMapping("/students/search")
    public String search(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int page) {
        return "keyword=" + keyword + ", page=" + page;
    }
    //bài 3
    @GetMapping("/student/{id}")
    public String getStudent(@PathVariable int id) {
        return "Sinh viên có mã: " + id;
    }
    //bài 4
    @GetMapping("/student")
    public Students getStudent() {
        return new Students(1, "Võ Chí Kiên", 20);
    }
    //bài 5
    @GetMapping("/students")
    public List<Students> getStudents() {
        List<Students> list = new ArrayList<>();
        list.add(new Students(1, "A", 20));
        list.add(new Students(2, "B", 21));
        return list;
    }






















    // // Bai 2
    // @GetMapping("/greet")
    // public String greet(@RequestParam String name) {
    //     return "Hello " + name;
    // }

    // @GetMapping("/students/search")
    // public String search(
    //         @RequestParam String keyword,
    //         @RequestParam(defaultValue = "1") int page) {
    //     return "keyword= " + keyword + ", page= " + page;
    // }

    // // Bai 3
    // @GetMapping("/students/{id}")
    // public String getStudent(@PathVariable int id) {
    //     return "Student ID: " + id;
    // }

    // // Bai 4
    // @GetMapping("/student")
    // public Student getStudent() {
    //     return new Student(1, "Khanh", 20);
    // }

    // // Bai 5
    // @GetMapping("/students")
    // public List<Student> getStudents() {
    //     List<Student> list = new ArrayList<>();
    //     list.add(new Student(1, "Kien", 20));
    //     list.add(new Student(2, "Hoang", 20));
    //     return list;
    // }
}