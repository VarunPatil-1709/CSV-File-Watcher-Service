package com.student_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student_app.dtos.RequestDto;
import com.student_app.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/studnet")
public class StudentController {
	private StudentService studentService;
	private StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@PostMapping("/register")
	private ResponseEntity<RequestDto> registerUser(@RequestBody @Valid RequestDto student) {
		RequestDto response = studentService.createStudent(student);
		
		return ResponseEntity.status(201).body(response);
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<RequestDto> getUser(@PathVariable Long id){
		RequestDto response = studentService.getStudent(id);
		return ResponseEntity.ok(response);
		
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<Void> deleteUser(@PathVariable Long id){
		studentService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	
	
	

}
