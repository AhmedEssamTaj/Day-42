package com.example.day39exercise.Service;

import com.example.day39exercise.ApiResponse.ApiException;
import com.example.day39exercise.DOT.CourseDTO;
import com.example.day39exercise.DOT.CourseOutDOT;
import com.example.day39exercise.DOT.TeacherOutDOT;
import com.example.day39exercise.Model.Address;
import com.example.day39exercise.Model.Course;
import com.example.day39exercise.Model.Teacher;
import com.example.day39exercise.Repository.AddressRepository;
import com.example.day39exercise.Repository.CourseRepository;
import com.example.day39exercise.Repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;
    private final CourseRepository courseRepository;

    public List<TeacherOutDOT> getAll(){
        List<Teacher> teachers = teacherRepository.findAll();
        List < TeacherOutDOT> teacherOutDOTS = new ArrayList<>();
        for (Teacher t : teachers) {
            if (t.getCourses().isEmpty()){

                teacherOutDOTS.add(new TeacherOutDOT(t.getName(),t.getEmail(),null));

            }else {
//                teacherOutDOTS.add(new TeacherOutDOT(t.getName(),t.getEmail(), fixCourseList(t.getCourses())));
                teacherOutDOTS.add(new TeacherOutDOT(t.getName(),t.getEmail(), fixCourseList(t.getCourses())));
            }
        }
        return teacherOutDOTS;

    }

    public void addTeacher (Teacher teacher){
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Integer id, Teacher teacher){
        Teacher oldTeacher = teacherRepository.findTeacherById(id);
        if (oldTeacher==null){
            throw new ApiException("no teacher with this id was found");
        }

        oldTeacher.setName(teacher.getName());
        oldTeacher.setAge(teacher.getAge());
        oldTeacher.setSalary(teacher.getSalary());
        oldTeacher.setEmail(teacher.getEmail());
        teacherRepository.save(oldTeacher);
    }

    public void deleteTeacher (Integer id){
        Teacher oldTeacher = teacherRepository.findTeacherById(id);
        Address oldAddress=addressRepository.findAddressById(id);
        if (oldTeacher==null){
            throw new ApiException("no teacher with this id was found");
        }
        oldTeacher.setAddress(null);
        addressRepository.delete(oldAddress);
        teacherRepository.delete(oldTeacher);

    }
    public Teacher getTeacherDetails (Integer id){
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null){
            throw new ApiException("no teacher with this id");
        }
        return teacher;
    }
    public String getTeacherNameByCourse (Integer courseId){
        Course course = courseRepository.findCourseById(courseId);
        if (course == null){
            throw new ApiException("no course with this id was found ");
        }
        return course.getTeacher().getName();
    }

    public List<CourseDTO> fixCourseList (Set<Course> courses){
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course c : courses){
            courseDTOS.add(new CourseDTO(c.getName()));
        }
        return courseDTOS;
    }
}
