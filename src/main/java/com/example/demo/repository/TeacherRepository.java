package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

//	public void deleteByTeacherId(Long id);
}
