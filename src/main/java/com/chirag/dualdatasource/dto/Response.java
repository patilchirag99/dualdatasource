package com.chirag.dualdatasource.dto;

import com.chirag.dualdatasource.college.entity.College;
import com.chirag.dualdatasource.student.entity.Student;
import lombok.Data;

import java.util.List;

@Data
public class Response {
    List<Student> students;
    List<College> colleges;
}
