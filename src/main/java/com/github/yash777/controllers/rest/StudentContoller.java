package com.github.yash777.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.yash777.exceptions.JDBCEntityNotFoundException;
import com.github.yash777.repository.jdbc.Student;
import com.github.yash777.repository.orm.StudentEntity;
import com.github.yash777.services.StudentService;

@RestController
@RequestMapping("/student")
public class StudentContoller {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/jdbc/lsitAll")
	public String getAllStudents() {
		return studentService.getAllStudents();
	}
	
	@GetMapping("/jdbc/findById/{id}")
	public Student findById(@PathVariable int id) throws JDBCEntityNotFoundException {
		return studentService.getStudentByID(id);
	}
	
	@PostMapping("/jdbc/save")
	public void save(Student student){
		studentService.save( student );
	}
	
	// http://localhost:8090/student/orm/lsitAll
	// http://localhost:8090/student/orm/findById/1
	@RequestMapping("/orm/lsitAll")
	public List<StudentEntity> getAllStudentsEntity() {
		return studentService.getAllStudentsEntity();
	}
	@GetMapping("/orm/findById/{id}")
	public StudentEntity findByIdEntity(@PathVariable int id) {
		return studentService.getStudentByIDEntity(id);
	}
}
