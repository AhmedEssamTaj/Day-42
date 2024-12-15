package com.example.day39exercise.DOT;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentDOT {

    @NotEmpty(message = "name cannot be empty")
    private String name;

    @NotNull(message = "age cannot be empty")
    @Min(9)
    private Integer age;

    @NotEmpty(message = "major cannot be empty")
    private String major;

}
