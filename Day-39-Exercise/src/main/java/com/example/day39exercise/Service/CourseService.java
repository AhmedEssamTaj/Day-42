package com.example.day39exercise.Service;

import com.example.day39exercise.ApiResponse.ApiException;
import com.example.day39exercise.DOT.CourseDTO;
import com.example.day39exercise.DOT.CourseOutDOT;
import com.example.day39exercise.DOT.StudentDOT;
import com.example.day39exercise.Model.Course;
import com.example.day39exercise.Model.Student;
import com.example.day39exercise.Model.Teacher;
import com.example.day39exercise.Repository.CourseRepository;
import com.example.day39exercise.Repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public List<CourseOutDOT> getAll() {

        List<Course> courses = courseRepository.findAll();
        List<CourseOutDOT> courseOutDOTS = new ArrayList<>();
        for (Course c : courses) {
            if (c.getStudents() == null) {
                courseOutDOTS.add(new CourseOutDOT(c.getName(),null));
            } else {
                courseOutDOTS.add(new CourseOutDOT(c.getName(),fixStudentList(c.getStudents())));
            }
        }
        return courseOutDOTS;
    }

    public void addCourse(CourseDTO courseDTO) {

        Course course = new Course();
        course.setName(courseDTO.getName());
        courseRepository.save(course);
    }

    // method to assign a teacher to a course
    public void assignCourseToTeacher(Integer courseId, Integer teacherId) {
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        Course course = courseRepository.findCourseById(courseId);

        if (teacher == null || course == null) {
            throw new ApiException("Cannot assign course to this teacher");
        }
        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    public void updateCourse(Integer courseId, CourseDTO courseDTO) {
        Course oldCourse = courseRepository.findCourseById(courseId);
        if (oldCourse == null) {
            throw new ApiException("no course with this id was found");
        }
        oldCourse.setName(courseDTO.getName());
        courseRepository.save(oldCourse);
    }

    public void deleteCourse(Integer id) {
        Course course = courseRepository.findCourseById(id);
        if (course == null) {
            throw new ApiException("no course with this id was found");
        }
        courseRepository.delete(course);
    }

    public List<StudentDOT> fixStudentList(Set<Student> students) {
        List<StudentDOT> studentDOTs = new ArrayList<>();
        for (Student s : students) {
            studentDOTs.add(new StudentDOT(s.getName(), s.getAge(), s.getMajor()));
        }
        return studentDOTs;
    }

    public List<StudentDOT> getStudentsInCourse(Integer courseId) {
        Course course = courseRepository.findCourseById(courseId);
        if (course == null) {
            throw new ApiException("no course with this id was found");
        }

       List<StudentDOT>studentDOTS = new ArrayList<>();
        for (Student s : course.getStudents()) {
            studentDOTS.add(new StudentDOT(s.getName(), s.getAge(), s.getMajor()));
        }
        return studentDOTS;
    }

}
