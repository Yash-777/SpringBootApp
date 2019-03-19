package com.github.yash777.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring JDBC provides a layer on top of JDBC. It used concepts like JDBCTemplate.
 * <br />
 * 1. Mapping parameters to queries.<br />
 * 2. Liquidating ResultSets to beans.<br />
 * 
 * @author yashwanth.m
 *
 */
@Transactional
@Repository
public class StudentDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addStudent(Student student) {
		String sql = "INSERT INTO student (id, name, age) values (?, ?, ?)";
		jdbcTemplate.update(sql, student.getId(), student.getName(), student.getAge());
	}
	public void updateStudent(Student student) {
		String sql = "UPDATE student SET age=? WHERE name=?";
		jdbcTemplate.update(sql, student.getAge(), student.getName());
	}
	public void deleteStudent(int id) {
		String sql = "DELETE FROM student WHERE id=?";
		jdbcTemplate.update(sql, id);
	}
	
	/**
	 * RowMapper «
	 * Spring JDBC provides RowMapper interface that is used to map row with a java object.
	 */
	public List<Student> getAllStudents() {
		String sql = "SELECT id, name, age FROM student";
		RowMapper<Student> rowMapper = new StudentsRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}
	
	/**
	 * BeanPropertyRowMapper «
	 * Spring JDBC provides BeanPropertyRowMapper that implements RowMapper. We can directly use it in place of custom RowMapper.
	 * We use BeanPropertyRowMapper in the scenario when table column name and our entity class fields name are same.
	 */
	public Student getStudentByID( int studentId ) {
		String sql = "SELECT id, name, age FROM student where id= ?";
		RowMapper<Student> rowMapper = new BeanPropertyRowMapper<Student>( Student.class );
		try {
			return this.jdbcTemplate.queryForObject(sql, rowMapper, studentId);
		} catch (EmptyResultDataAccessException e ) {
			System.err.println("EmptyResultDataAccessException « "+e.getLocalizedMessage());
		}
		return null;
	}
}