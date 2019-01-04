package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.Teacher;
import com.example.demo.service.TeacherService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	
	@Autowired
	private TeacherService teacherService;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("starting ......");
		
//		logger.info("starting.................");
//		logger.debug("debug");
//		logger.warn("warn");
//		logger.error("error");
//		logger.trace("trace");
//		
		
		Teacher teacher1 = new Teacher("a","b","C@gmail.com","222-111-1123");
		Teacher teacher2 = new Teacher("c","d", "D@yahoo.com", "111-222-1231");
		
//		teacherService.saveTeacher(teacher1);
//		teacherService.saveTeacher(teacher2);
	}

}

