package com.example.demo.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;

import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.demo.domain.Teacher;
import com.example.demo.repository.TeacherRepository;

public class TeacherServiceImplTest {

	@Mock
	private TeacherRepository teacherRepository;
	private TeacherServiceImpl teacherServiceImpl;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		teacherServiceImpl = new TeacherServiceImpl(teacherRepository);
	}
	
	@Test
	public void testGetTeacherById() {
		Optional<Teacher> teacher = Optional.of(new Teacher("A","B","C", "D"));
		
		given(teacherRepository.findById(anyLong())).willReturn(teacher);
		
		Teacher newTeacher = teacherServiceImpl.getTeacherById(0L);
		
		assertThat(newTeacher).isNotNull();
		assertThat(newTeacher.getFirstName()).isEqualTo("A");
		assertThat(newTeacher.getLastName()).isEqualTo("B");
		assertThat(newTeacher.getEmail()).isEqualTo("C");
	}

	@Test
	public void testSaveTeacher() {
		Teacher teacher = new Teacher("A","B", "C", "D");
		
		teacherServiceImpl.saveTeacher(teacher);
		
		verify(teacherRepository).save(teacher);
	}

	@Test
	public void testGetAllTeachers() {
		teacherServiceImpl.getAllTeachers();
		verify(teacherRepository).findAll();
	}
	
	@Test
	public void testDeleteTeacherById() {
		
		Long teacherId = 1L;
		
		teacherServiceImpl.deleteTeacherById(teacherId);
		
		verify(teacherRepository).deleteById(teacherId);
	}
}
