package org.dars.student_crud.controller;

import java.util.List;

import org.dars.student_crud.dto.Student;
import org.dars.student_crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	StudentService service;

	// Adding one student record
	@PostMapping("/students")
	public ResponseEntity<Object> saveStudent(@RequestBody Student student) {
		return service.save(student);
	}

	// Adding multiple student records
	@PostMapping("/students/multiple")
	public ResponseEntity<Object> saveStudent(@RequestBody List<Student> students) {
		return service.save(students);
	}

	// Fetch All Records
	@GetMapping("/students")
	public ResponseEntity<Object> fetchStudents() {
		return service.fetchAll();
	}

	// Fetch By Id
	@GetMapping("/students/{id}")
	public ResponseEntity<Object> fetchById(@PathVariable int id) {
		return service.fetchById(id);
	}

	// Fetch By Name
	@GetMapping("/students/name/{name}")
	public ResponseEntity<Object> fetchByName(@PathVariable String name) {
		return service.fetchByName(name);
	}

	// Fetch By Mobile
	@GetMapping("/students/mobile/{mobile}")
	public ResponseEntity<Object> fetchByMobile(@PathVariable long mobile) {
		return service.fetchByMobile(mobile);
	}

}
