package com.example.webdevsummer22018serverashu95.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdevsummer22018serverashu95.models.Module;


public interface ModuleRepository extends CrudRepository<Module,Integer>
{

	/**
	@Query("SELECT m FROM Module m WHERE m.id=:moduleId AND m.courseId=:courseId")
	Optional<Module> findModuleById(
		@Param("courseId") int courseId, 
		@Param("moduleId") int moduleId);
**/
}
