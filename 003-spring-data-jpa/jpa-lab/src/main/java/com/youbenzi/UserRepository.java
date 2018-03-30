package com.youbenzi;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByName(String name);

	public List<User> findByAgeLessThan(int age);

	@Query(value = "select * from user", nativeQuery = true)
	public List<User> listUsers();
	
	@Modifying
	@Transactional
	@Query(value = "update user set age = ?1 where id = ?2", nativeQuery = true)
	public void updateAge(int age, long id);
	
}