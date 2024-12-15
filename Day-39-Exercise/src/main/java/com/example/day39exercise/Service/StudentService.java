package com.example.day39exercise.Service;

import com.example.day39exercise.ApiResponse.ApiException;
import com.example.day39exercise.DOT.StudentDOT;
import com.example.day39exercise.Model.Course;
import com.example.day39exercise.Model.Student;
import com.example.day39exercise.Repository.CourseRepository;
import com.example.day39exercise.Repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<StudentDOT> getAll (){
        List<Student> students = studentRepository.findAll();
        List<StudentDOT> studentDOTS = new ArrayList<>();

        for (Student s: students){
            studentDOTS.add(new StudentDOT(s.getName(),s.getAge(),s.getMajor()));
        }
        return studentDOTS;
    }

    public void addStudent (StudentDOT studentDOT){

        Student student = new Student();
        student.setName(studentDOT.getName());
        student.setAge(studentDOT.getAge());
        student.setMajor(studentDOT.getMajor());
        studentRepository.save(student);
    }

    public void updateStudent(Integer id,StudentDOT studentDOT){
        Student oldStudent = studentRepository.findStudentById(id);
        if (oldStudent == null){
            throw new ApiException("no student with this id was found");
        }
        oldStudent.setName(studentDOT.getName());
        oldStudent.setAge(studentDOT.getAge());
        oldStudent.setMajor(studentDOT.getMajor());
        studentRepository.save(oldStudent);
    }

    public void deleteStudent (Integer id){
        Student oldStudent = studentRepository.findStudentById(id);
        if (oldStudent == null){
            throw new ApiException("no student with this id was found");
        }
        studentRepository.delete(oldStudent);
    }

    public void assignStudentToCourse(Integer studentId, Integer courseId){
        Student student = studentRepository.findStudentById(studentId);
        Course course = courseRepository.findCourseById(courseId);

        if (student == null || course == null){
            throw new ApiException("assign field Course or student does not exist");
        }

        student.getCourses().add(course);
        course.getStudents().add(student);
        courseRepository.save(course);
        studentRepository.save(student);
    }

    public void changeMajor (Integer studentId, String major){
        Student student = studentRepository.findStudentById(studentId);
        if (student == null){
            throw new ApiException("no student with this id was found");
        }
        student.setMajor(major);
        student.setCourses(null);
        studentRepository.save(student);
    }


}
