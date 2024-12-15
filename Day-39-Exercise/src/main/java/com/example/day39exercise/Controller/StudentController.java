package com.example.day39exercise.Controller;


import com.example.day39exercise.ApiResponse.ApiResponse;
import com.example.day39exercise.DOT.StudentDOT;
import com.example.day39exercise.Model.Teacher;
import com.example.day39exercise.Service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get-all")
    public ResponseEntity getAll (){
        return ResponseEntity.status(200).body(studentService.getAll());
    }
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid StudentDOT studentDOT) {
        studentService.addStudent(studentDOT);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully added student"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable Integer id,@RequestBody @Valid StudentDOT studentDOT) {
        studentService.updateStudent(id, studentDOT);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully updated student"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted student"));
    }

    @PutMapping("/assign-student-course/{studentId}/{courseId}")
    public ResponseEntity assignStudentToCourse (@PathVariable Integer studentId, @PathVariable Integer courseId){
        studentService.assignStudentToCourse(studentId, courseId);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully assigned student to this course"));
    }

    @PutMapping("/change-major/{id}/{major}")
    public ResponseEntity changeMajor (@PathVariable Integer id,@PathVariable String major){
        studentService.changeMajor(id, major);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully changed major"));
    }
}
