package org.dars.student_crud.service;

import java.util.HashMap;
import java.util.Map;

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
			student.setPercentage((student.getMaths()+student.getScience()+student.getSocial())/3.0);
			repository.save(student);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Record Added Success");
			map.put("data", student);
			return new ResponseEntity<Object>(map,HttpStatus.CREATED);
		}
	}

}
