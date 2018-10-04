package com.qa.mySpringBootDatabaseApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.mySpringBootDatabaseApp.model.MySpringBootDataModel;

@Repository
public interface MySpringBootRepository extends JpaRepository<MySpringBootDataModel,Long> 
{

	public MySpringBootDataModel findByName(String name);
	
	public MySpringBootDataModel findByAddress(String address); 
	
	public MySpringBootDataModel findByAge(Integer age);
	
	
}
