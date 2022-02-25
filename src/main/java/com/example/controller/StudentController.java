package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;
import com.example.request.CreateStudentRequest;
import com.example.request.UpdateStudentRequest;
import com.example.response.StudentResponse;
import com.example.service.StudentService;

@RestController
@RequestMapping("/api/student/")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@GetMapping("getAll")
	public List<StudentResponse> getAllStudents () {
		List<Student> studentList = studentService.getAllStudents();
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		
		return studentResponseList;
	}
	
	@PostMapping("create")
	public StudentResponse createStudent (@Valid @RequestBody CreateStudentRequest createStudentRequest) {
		Student student = studentService.createStudent(createStudentRequest);
		
		return new StudentResponse(student);
	}
	
	@PutMapping("update")
	public StudentResponse updateStudent (@Valid @RequestBody UpdateStudentRequest updateStudentRequest) {
		Student student = studentService.updateStudent(updateStudentRequest);
		
		return new StudentResponse(student);
	}
	
	/*@DeleteMapping("delete")
	public String deleteStudent (@RequestParam long id) {
		return studentService.deleteStudent(id);
	}*/
	
	@DeleteMapping("delete/{id}")
	public String deleteStudent (@PathVariable long id) {
		return studentService.deleteStudent(id);
	}
	
	@GetMapping("getByFirstName/{firstName}")
	public List<StudentResponse> getByFirstName (@PathVariable String firstName) {
		List<Student> studentList = studentService.getByFirstName(firstName);
		
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		
		return studentResponseList;
	}
	
	@GetMapping("getByFirstNameAndLastName/{firstName}/{lastName}")
	public StudentResponse getByFirstNameAndLastName (@PathVariable String firstName, 
			@PathVariable String lastName) {
		return new StudentResponse(studentService.getByFirstNameAndLastName(firstName, lastName));
	}
	
	@GetMapping("getByFirstNameOrLastName/{firstName}/{lastName}")
	public List<StudentResponse> getByFirstNameOrLastName (@PathVariable String firstName, 
			@PathVariable String lastName) {
		List<Student> studentList = studentService.getByFirstNameOrLastName(firstName, lastName);
		
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		
		return studentResponseList;
	}
}
