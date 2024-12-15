package com.example.day39exercise.DOT;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CourseOutDOT {

    private String name;

    private List<StudentDOT> studentDOTS;
}
