package org.dars.student_crud.repository;

import java.util.Optional;

import org.dars.student_crud.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	boolean existsByMobile(long mobile);

	Optional<Student> findByName(String name);

}
