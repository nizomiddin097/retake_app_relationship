package uz.pdp.retake_app_relationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.retake_app_relationship.entity.Student;
import uz.pdp.retake_app_relationship.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
//1
    @GetMapping("/read/forMinistry")
    public Page<Student> getStudentListForMinistry(@RequestParam int page){
        Pageable pageable = PageRequest.of(page,10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }
 //2
 @GetMapping("/read/university/{universityId}")
 public Page<Student> getStudentListForUniversity(@PathVariable int universityId, @RequestParam int page){
     Pageable pageable = PageRequest.of(page,10);

     Page<Student> studentPage = studentRepository.findAllByGroup_Faculty_UniversityId(universityId);

     return studentPage;
 }
 @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
        Student student1 = new Student();
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        studentRepository.save(student1);
        return "Student added";
 }

    @PutMapping("/add")
    public String editStudent(@PathVariable Integer id,@RequestBody Student student){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student1 = optionalStudent.get();
            student1.setFirstName(student.getFirstName());
            student1.setLastName(student.getLastName());
            studentRepository.save(student1);
            return "Student edited";
        }
        return "Student not found";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        studentRepository.deleteById(id);
        return " Student deleted";
    }


}
