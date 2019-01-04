package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Teacher;
import com.example.demo.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

	private TeacherRepository teacherRepository;
	
	@Autowired
	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}

	public Teacher createNewTeacher() {
		return new Teacher();
	}
	
	@Override
	public Teacher getTeacherById(Long id) {
		
		Optional<Teacher> teacher = teacherRepository.findById(id);
		
		if (teacher.isPresent()) {
			return teacher.get();
		}
		
		return null;
	}

	@Override
	public void saveTeacher(Teacher teacher) {
		teacherRepository.save(teacher);
	}

	@Override
	public List<Teacher> getAllTeachers() {
		return (List<Teacher>) teacherRepository.findAll();
	}

	@Override
	public void deleteTeacherById(Long teacherId) {
		teacherRepository.deleteById(teacherId);
	}

}
