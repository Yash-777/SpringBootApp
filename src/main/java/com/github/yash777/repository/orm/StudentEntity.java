package com.github.yash777.repository.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class StudentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "id")
	private Integer id; // JpaRepository<StudentEntity, Integer[Primary Key type]>
	@Column(name = "name")
	private String name;
	@Column(name = "age")
	private int age;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
/*
org.hibernate.AnnotationException: No identifier specified for entity: com.github.yash777.repository.orm.StudentEntity

You are missing a field annotated with @Id. Each @Entity needs an @Id - this is the primary key in the database.

If you don't want your entity to be persisted in a separate table, but rather be a part of other entities, you can use @Embeddable instead of @Entity.

*/
