package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.Teacher;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherRepositoryTest {

	@Autowired
	private TeacherRepository teacherRepository;
	private Long teacherId1, teacherId2, teacherId3;
	
//	@Autowired
//	public TeacherRepositoryTest(TeacherRepository teacherRepository) {
//		this.teacherRepository = teacherRepository;
//	}
	
	@Before
	public void setUp() throws Exception {
		
		System.out.println("Setup");
		Teacher teacher1 = new Teacher("firstName1","lastName1","teacher1@gmail.com","111-111-1111");
		Teacher teacher2 = new Teacher("firstName2","lastName2","teacher2@gmail.com","222-222-2222");
		Teacher teacher3 = new Teacher("firstName3","lastName3","teacher3@gmail.com","333-333-3333");
		
		teacherRepository.save(teacher1);
		teacherRepository.save(teacher2);
		teacherRepository.save(teacher3);
		
		teacherId1 = teacher1.getTeacherId();
		teacherId2 = teacher2.getTeacherId();
		teacherId3 = teacher3.getTeacherId();
		
		System.out.println("Teacher id: " + teacherId1);
		System.out.println("Teacher id: " + teacherId2);
		System.out.println("Teacher id: " + teacherId3);
	}
	
	@After
	public void tearDown() throws Exception {
		System.out.println("Tearing down");
		teacherRepository.deleteById(teacherId1);
		teacherRepository.deleteById(teacherId2);
		teacherRepository.deleteById(teacherId3);
	}

	@Test
	public void testFindById() {
		System.out.println("Finding");
		Optional<Teacher> teacher = teacherRepository.findById(teacherId2);
		
		System.out.println(teacherId2);
		
		assertThat(teacher.isPresent()).isTrue();
		assertThat(teacher.get().getTeacherId()).isEqualTo(teacherId2);
		assertThat(teacher.get().getFirstName()).isEqualTo("firstName2");
		assertThat(teacher.get().getLastName()).isEqualTo("lastName2");
		assertThat(teacher.get().getEmail()).isEqualTo("teacher2@gmail.com");
		assertThat(teacher.get().getPhoneNumber()).isEqualTo("222-222-2222");
	}
	
	@Test
	@DirtiesContext
	public void testSave() {
		System.out.println("Saving");
		Teacher teacher = new Teacher("firstName","lastName","teacher@gmail.com", "000-000-0000");
		
		teacherRepository.save(teacher);
		System.out.println("The teacher id = " + teacher.getTeacherId());
		Optional<Teacher> savedTeacher = teacherRepository.findById(teacher.getTeacherId());
		
		assertThat(savedTeacher.isPresent()).isTrue();
		assertThat(savedTeacher.get().getTeacherId()).isEqualTo(teacher.getTeacherId());
		assertThat(savedTeacher.get().getFirstName()).isEqualTo("firstName");
		assertThat(savedTeacher.get().getLastName()).isEqualTo("lastName");
		assertThat(savedTeacher.get().getEmail()).isEqualTo("teacher@gmail.com");
		assertThat(savedTeacher.get().getPhoneNumber()).isEqualTo("000-000-0000");
	}
	

	
	@Test
	public void testFindAll() {
		System.out.println("Find all");
	 List<Teacher> teachers = (List<Teacher>) teacherRepository.findAll();
	 
	 assertThat(teachers.size()).isEqualTo(3);
	 
	 System.out.println("List size: " + teachers.size());
	}
	
//	@Test
//	@DirtiesContext
//	public void testDeletebyId() {
//		
//	System.out.println("Deleting");
//	System.out.println("Deleting id " + teacherId2);
//	
//		teacherRepository.deleteById(teacherId2);
//		
//		Optional<Teacher> teacher = teacherRepository.findById(teacherId2);
//		
//		assertThat(teacher.isPresent()).isFalse();
//	}
	
}
