package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Teacher;

public interface TeacherService {

	public Teacher getTeacherById(Long id);
	
	public Teacher createNewTeacher();
	
	public void saveTeacher(Teacher teacher);
	
	public List<Teacher> getAllTeachers();
	
	public void deleteTeacherById(Long teacherId);
}
