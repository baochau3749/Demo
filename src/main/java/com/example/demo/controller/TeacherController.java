package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Teacher;
import com.example.demo.service.TeacherService;

@Controller
@RequestMapping("/")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmer);
	}
	
	@GetMapping("/addTeacher")
	public String showAddTeacherForm(Model teacherModel) {
		
		teacherModel.addAttribute("teacher", null);
		return "teacher-form";
	}
	
	@GetMapping("/showTeachers")
	public String showAllTeachers(Model model) {
		
		List<Teacher> teacherList = teacherService.getAllTeachers();
		model.addAttribute("teachers", teacherList);
		return "teacher-list";
	}
	
	@GetMapping("/deleteTeacher")
	public String deleteTeacher(@RequestParam("teacherId") Long teacherId) {

		teacherService.deleteTeacherById(teacherId);
		return "redirect:/showTeachers";
	}
	
	@GetMapping("/updateTeacher/{teacherId}")
	public String updateExistingTeacher(@PathVariable("teacherId")Long teacherId, Model model) {
		
		Teacher teacher = teacherService.getTeacherById(teacherId);
		model.addAttribute("teacher", teacher);
		return "teacher-form";
		
	}
	
	@PostMapping("/saveTeacher")
	public String saveTeacher(
			@Valid @ModelAttribute("teacher")Teacher teacher, 
			BindingResult bindingResult, 
			Model model) 
	{
		
		System.out.println("Teacher = " + teacher);
		
		if (bindingResult.hasErrors()) {
			return "teacher-form";
		}
		
		teacherService.saveTeacher(teacher);
		return "redirect:/showTeachers";
	}
	
	
}
