package com.github.yash777.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.yash777.exceptions.JDBCEntityNotFoundException;
import com.github.yash777.repository.jdbc.Student;
import com.github.yash777.repository.jdbc.StudentDAO;
import com.github.yash777.repository.orm.StudentDAORepository;
import com.github.yash777.repository.orm.StudentEntity;

@Service
public class StudentService {
	
	@Autowired
	private StudentDAO studentDAO;
	
	@Autowired
	private StudentDAORepository studentRepository;
	
	public String getAllStudents() {
		List<Student> allStudents = studentDAO.getAllStudents();
		if( allStudents.size() > 0 ) {
			return allStudents.toString();
		}
		
		// All business logic is here i.e. Data related calculations and all.
		return "No records Found.";
	}

	public Student getStudentByID(int id) throws JDBCEntityNotFoundException {
		Student student = studentDAO.getStudentByID(id);
		if( student == null ) {
			throw new JDBCEntityNotFoundException("Data not available for the requested id.");
		}
		return student;
	}

	public void save(Student student) {
		studentDAO.addStudent( student );
	}
	
	
	public List<StudentEntity> getAllStudentsEntity() {
		return studentRepository.findAll();
		
		/*List<StudentEntity> list = new ArrayList<>();
		studentRepository.findAll().forEach(e -> list.add(e));
		return list;*/
	}
	public StudentEntity getStudentByIDEntity( int id ) {
		// findOne, findAllById, findAll
		Optional<StudentEntity> student = studentRepository.findById( id );
		if( !student.isPresent() ) {
			System.out.println("Record Not found for the provided ID.");
			// NoSuchElementException - if there is no value present
		}
		
		return student.get();
		//return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
	}

}
