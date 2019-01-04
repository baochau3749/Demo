package com.example.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.hamcrest.Matchers.containsString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.verify;
import static org.mockito.ArgumentMatchers.any;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.http.HttpStatus;

import static org.mockito.BDDMockito.given;

import com.example.demo.domain.Teacher;
import com.example.demo.service.TeacherService;

@RunWith(SpringRunner.class)
@WebMvcTest(TeacherController.class)
public class TeacherControllerTest {

	@MockBean
	private TeacherService teacherService;

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void testShowAddTeacherForm() throws Exception {
		
		 // Arrange
		Teacher expectedTeacher = teacherService.createNewTeacher();		
		given(teacherService.createNewTeacher()).willReturn(expectedTeacher);
		
		// Act
		MockHttpServletRequestBuilder request = get("/teacher").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		// Assert
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(result.getModelAndView().getViewName()).isEqualTo("teacher-form");
		assertThat(result.getModelAndView().getModel().get("teacher")).isEqualTo(expectedTeacher);
	}
	
	@Test
	public void testSaveTeacher() throws Exception {
		
		String teacher = "{ "
				+ "\"firstName\": \"a\", "
				+ "\"lastName\":\"b\", "
				+ "\"email\":\"a@gmail.com\" "
				+ "}";
		
		MockHttpServletRequestBuilder request = post("/saveTeacher")
				.content(teacher)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(request).andReturn();
		
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.FOUND.value());
		verify(teacherService).saveTeacher(any());

	}
	
	@Test
	public void testShowAllTeachers() throws Exception {
		
		Teacher teacher1 = new Teacher("C", "D", "Cd@gmail.com", "111-111-1111");
		Teacher teacher2 = new Teacher("E", "D", "Ed@gmail.com", "222-222-2222");
		
		ArrayList<Teacher> teacherList = Lists.newArrayList(teacher1, teacher2);

		given(teacherService.getAllTeachers()).willReturn(teacherList);
		
		MockHttpServletRequestBuilder request = get("/showTeachers")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(request).andReturn();
		
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(result.getModelAndView().getViewName()).isEqualTo("teacher-list");
		assertThat(result.getModelAndView().getModel().get("teachers")).isEqualTo(teacherList);
	}

	@Test
	public void testDeleteTeacherById() throws Exception {
		
		Long id = 1L;
		
		MvcResult result = mvc.perform(get("/deleteTeacher?teacherId=1")).andReturn();
		
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.FOUND.value());
		assertThat(result.getModelAndView().getViewName()).isEqualTo("redirect:/showTeachers");
		verify(teacherService).deleteTeacherById(id);
	}
	
	@Test
	public void testUpdateExistingTeacher() throws Exception {
		
		Teacher teacher = new Teacher("C", "D", "Cd@gmail.com","111-111-1111");
		teacher.setTeacherId(1L);
		
		given(teacherService.getTeacherById(1L)).willReturn(teacher);
		
		MockHttpServletRequestBuilder request = get("/updateTeacher/1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(request).andReturn();
		
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(result.getModelAndView().getViewName()).isEqualTo("teacher-form");
		assertThat(result.getModelAndView().getModel().get("teacher")).isEqualTo(teacher);
		
	}

}
