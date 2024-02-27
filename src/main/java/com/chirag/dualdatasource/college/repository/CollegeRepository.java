package com.chirag.dualdatasource.college.repository;

import com.chirag.dualdatasource.college.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<College,Integer> {
}
