// package vochikien1.entities;
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.Table;
// import jakarta.persistence.Id;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;



// @Entity

// @Table(name = "Student")
// public class Student {
//     @Id
//     @Column(name = "ID")
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer id;
//     @Column(name = "name")
//     private String name;
//     @Column(name = "age")
//     private int age;
//     @Column(name = "email")
//     private String email;
//     @Column(name = "gender")
//     private String gender;
//     public String getGender() {
//         return gender;
//     }
//     public void setGender(String gender) {
//         this.gender = gender;
//     }
//     public Student() {
//     }
//     public Student( String name, int age, String email, String gender) {
//         this.name = name;
//         this.age = age;
//         this.email = email;
//         this.gender = gender;
//     }
//     public Integer getId() {
//         return id;
//     }
//     public void setId(Integer id) {
//         this.id = id;
//     }
//     public String getName() {
//         return name;
//     }
//     public void setName(String name) {
//         this.name = name;
//     }
//     public int getAge() {
//         return age;
//     }
//     public void setAge(int age) {
//         this.age = age;
//     }
//     public String getEmail() {
//         return email;
//     }
//     public void setEmail(String email) {
//         this.email = email;
//     }
// }

package vochikien1.entities;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
public class Student {

    @Id
    private String id;

    @NotBlank(message = "Tên không được để trống")
    private String name;

    @Min(16)
    @Max(100)
    private int age;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String gender;

    public Student() {}

    // getter / setter
}

