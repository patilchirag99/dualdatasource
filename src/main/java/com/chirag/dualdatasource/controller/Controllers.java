package com.chirag.dualdatasource.controller;

import com.chirag.dualdatasource.college.entity.College;
import com.chirag.dualdatasource.college.repository.CollegeRepository;
import com.chirag.dualdatasource.course.repository.CourseRepository;
import com.chirag.dualdatasource.dto.Response;
import com.chirag.dualdatasource.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controllers {

    @Autowired
    CollegeRepository collegeRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Response getResponse(){
        Response response = new Response();
        response.setColleges(collegeRepository.findAll());
        response.setStudents(studentRepository.findAll());
        response.setCourses(courseRepository.findAll());
        return response;
    }
}
