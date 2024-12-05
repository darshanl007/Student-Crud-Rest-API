package org.dars.student_crud.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.dars.student_crud.dto.Student;
import org.dars.student_crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	@Autowired
	StudentRepository repository;

	public ResponseEntity<Object> save(Student student) {
		if (repository.existsByMobile(student.getMobile())) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "Mobile Number Already Exists");
			return new ResponseEntity<Object>(map, HttpStatus.UNPROCESSABLE_ENTITY);
		} else {
			student.setPercentage((student.getMaths() + student.getScience() + student.getSocial()) / 3.0);
			repository.save(student);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Record Added Success");
			map.put("data", student);
			return new ResponseEntity<Object>(map, HttpStatus.CREATED);
		}
	}

	public ResponseEntity<Object> save(List<Student> students) {
		for (Student student : students) {
			if (repository.existsByMobile(student.getMobile())) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "Mobile Number Already Exists");
				return new ResponseEntity<Object>(map, HttpStatus.UNPROCESSABLE_ENTITY);
			} else {
				student.setPercentage((student.getMaths() + student.getScience() + student.getSocial()) / 3.0);
			}
		}
		repository.saveAll(students);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "Multiple Record Saved Success");
		map.put("data", students);
		return new ResponseEntity<Object>(map, HttpStatus.CREATED);
	}

	public ResponseEntity<Object> fetchAll() {
		List<Student> students = repository.findAll();
		if (students.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Data Present in Database");
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Record Found Success");
			map.put("data", students);
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> fetchById(int id) {
		Optional<Student> optional = repository.findById(id);
		if (optional.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Record Found with Id : " + id);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Record Found Success");
			map.put("data", optional.get());
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

}
