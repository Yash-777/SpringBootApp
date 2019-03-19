package com.github.yash777.repository.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * To avoid mismatches between Java Entities and SQL Tables.
 * Use annotations with names values.
 * <ol>
 * <li>@Table(name = “Task”) </li>
 * <li>@Id </li>
 * <li>@GeneratedValue </li>
 * <li>@Column(name = “columnName”) if `name with value` is not specified
 * then it uses `column_Name` provided by PhysicalNamingStrategy
 * <a href="https://docs.jboss.org/hibernate/orm/5.1/userguide/html_single/chapters/domain/naming.html">
 * docs.jboss.org/hibernate</a></li>
 * </ol>
 * 
 * The role of the org.hibernate.boot.model.naming.ImplicitNamingStrategy contract to determine a 
 * logical name when the mapping did not provide an explicit name. When an entity does not explicitly name
 * the database table that it maps to, we need to implicitly determine that table name.
 * 
 * @author yashwanth.m
 *
 */
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
