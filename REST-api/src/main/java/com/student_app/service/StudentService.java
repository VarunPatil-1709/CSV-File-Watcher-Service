package com.student_app.service;

import org.springframework.stereotype.Service;

import com.student_app.dtos.RequestDto;
import com.student_app.entity.Student;
import com.student_app.repo.StudentRepo;

@Service
public class StudentService {
	private StudentRepo studentRepo;
	
	private StudentService(StudentRepo studentRepo) {
		this.studentRepo = studentRepo;
	}
	
	public RequestDto createStudent(RequestDto request) {
		if(request.getAge()!= null && request.getAge()<0) {
			throw new IllegalArgumentException("Age must be valid");
		}
		
		Student student = new Student();
		student.setName(request.getName());
		student.setAge(request.getAge());
		student.setNickname(request.getNickname());
		
		Student saved = studentRepo.save(student);
		
		RequestDto response = new RequestDto();
		response.setName(saved.getName());
		response.setAge(saved.getAge());
		response.setNickname(saved.getNickname());
		
		return response;
	}
	
	public RequestDto getStudent(Long id) {
		if(id == null || id <=0) {
			throw new IllegalArgumentException("Id Should Be valid");
		}
		Student student = studentRepo.findById(id)
			    .orElseThrow(() -> new RuntimeException("Student not found"));
		
		RequestDto response = new RequestDto();
		response.setName(student.getName());
		response.setAge(student.getAge());
		response.setNickname(student.getNickname());
		
		return response;
	}
	
	public void deleteUser(Long id) {
		if(id == null || id <=0) {
			throw new IllegalArgumentException("Id Should Be valid");
		}
		Student student = studentRepo.findById(id)
				.orElseThrow(()-> new RuntimeException("User Not Found"));

		studentRepo.delete(student);
	}
	

}
