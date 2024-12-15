package com.example.day39exercise.DOT;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CourseDTO {

    @NotEmpty(message = "name cannot be empty")
    private final String name;

}
