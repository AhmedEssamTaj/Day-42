package com.example.day39exercise.Controller;

import com.example.day39exercise.ApiResponse.ApiResponse;
import com.example.day39exercise.DOT.CourseDTO;
import com.example.day39exercise.Model.Course;
import com.example.day39exercise.Model.Teacher;
import com.example.day39exercise.Service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/course")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;


    @GetMapping("/get-all")
    public ResponseEntity getAll (){
        return ResponseEntity.status(200).body(courseService.getAll());
    }
    @PostMapping("/add")
    public ResponseEntity addCourse(@RequestBody @Valid CourseDTO courseDTO) {
        courseService.addCourse(courseDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully added course"));
    }
    @PutMapping("/update/{courseId}")
    public ResponseEntity updateCourse(@PathVariable Integer courseId,@RequestBody @Valid CourseDTO courseDTO) {
        courseService.updateCourse(courseId,courseDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully updated course"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted course"));
    }
    @PutMapping("/assign/{courseId}/{teacherId}")
    public ResponseEntity assignCourseToTeacher (@PathVariable Integer courseId,@PathVariable Integer teacherId){
        courseService.assignCourseToTeacher(courseId,teacherId);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully assigned course to teacher"));
    }

    @GetMapping("/get-students/{courseId}")
    public ResponseEntity getStudentsByCourse (@PathVariable Integer courseId){
        return ResponseEntity.status(200).body(courseService.getStudentsInCourse(courseId));
    }

}
