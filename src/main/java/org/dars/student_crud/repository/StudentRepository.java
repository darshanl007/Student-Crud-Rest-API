package org.dars.student_crud.repository;

import org.dars.student_crud.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
