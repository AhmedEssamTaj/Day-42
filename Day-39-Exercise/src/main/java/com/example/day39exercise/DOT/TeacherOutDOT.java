package com.example.day39exercise.DOT;

import com.example.day39exercise.Model.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TeacherOutDOT {

    private String name;

    private String email;

    private List <CourseDTO> courseDTOS;

}
