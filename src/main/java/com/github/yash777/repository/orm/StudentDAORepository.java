package com.github.yash777.repository.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDAORepository extends JpaRepository<StudentEntity, Integer> {

}

/* https://www.callicoder.com/spring-boot-rest-api-tutorial-with-mysql-jpa-hibernate/
Note that, we have annotated the interface with @Repository annotation.
This tells Spring to bootstrap the repository during component scan.

Great! That is all you have to do in the repository layer.
You will now be able to use JpaRepository’s methods like save(), findOne(), findAll(), count(), delete() etc.

You don’t need to implement these methods. They are already implemented by Spring Data JPA’s SimpleJpaRepository.
This implementation is plugged in by Spring automatically at runtime.
*/