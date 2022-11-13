package com.example.studentsmanagement.api;

import com.example.studentsmanagement.exceptions.StudentEmptyNameException;
import com.example.studentsmanagement.exceptions.invalidUniversityClassException;
import com.example.studentsmanagement.model.Student;
import com.example.studentsmanagement.service.StudentService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {

  private StudentService studentService;

  @Autowired
  public StudentController(StudentService studentService){
    this.studentService = studentService;
  }

  @GetMapping
  @RequiresPermissions("student:read")
  public List<Student> getAllStudents(){
    return studentService.getAllStudents();
  }

  @GetMapping("/name")
  //../name?name=zhangsan
  public List<Student> getStudents(@RequestParam String name){
    return studentService.getStudentsByName(name);
  }

  @GetMapping("/contain_name")
  //../contain_name?name=T
  public List<Student> getStudentsContainName(@RequestParam String name){
    return studentService.getStudentsContainName(name);
  }

  @GetMapping("/class")
  //../class?year=2022&number=100
  public List<Student> getStudentsInClass(@RequestParam int year,
                                          @RequestParam int number){
    return studentService.getStudentsInClass(year, number);
  }

  @RequestMapping("/register")
  @PostMapping
  public ResponseEntity<String> registerStudent(@RequestBody Student student){
    try{
      Student saveStudent = studentService.addStudent(student);
      return ResponseEntity.ok("Register student." + student.toString());
    }catch (StudentEmptyNameException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PostMapping(path = "assignclass/{sid}/{cid}")
  public ResponseEntity<String> assignClass(@PathVariable("sid") Long studentId,
                                            @PathVariable("cid") Long classId){
    try{
      Student updatedStudent = studentService.assignClass(studentId, classId);
      return ResponseEntity.ok("Register student." + updatedStudent.toString());
    }catch (StudentEmptyNameException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }catch(invalidUniversityClassException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

}
