package com.github.yash777.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
 * ~[spring-tx-5.0.8.RELEASE.jar:5.0.8.RELEASE]
 * at org.springframework.dao.support.DataAccessUtils.nullableSingleResult(DataAccessUtils.java:97)
 * at org.springframework.jdbc.core.JdbcTemplate.queryForObject(JdbcTemplate.java:779)
 * 
 * @author yashwanth.m
 *
 */
public class StudentsRowMapper implements RowMapper<Student>{

	@Override
	public Student mapRow(ResultSet row, int rowNum) throws SQLException {
		
		Student student = new Student();
		student.setId( row.getInt("id") );
		student.setName( row.getString("name") );
		student.setAge( row.getInt("age") );
		return student;
	}
}
