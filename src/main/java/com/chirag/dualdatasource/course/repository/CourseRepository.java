package com.chirag.dualdatasource.course.repository;

import com.chirag.dualdatasource.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
}
