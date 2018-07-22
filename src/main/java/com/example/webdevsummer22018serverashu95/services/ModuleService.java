package com.example.webdevsummer22018serverashu95.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer22018serverashu95.models.Course;
import com.example.webdevsummer22018serverashu95.models.Module;
import com.example.webdevsummer22018serverashu95.repositories.CourseRepository;
import com.example.webdevsummer22018serverashu95.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 999999999)
public class ModuleService 
{
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ModuleRepository moduleRepository;
	
	@GetMapping("api/course/{courseId}/modules")
	public List<Module> findAllModulesForCourse(@PathVariable ("courseId") int courseId)
	{
		Optional<Course> c = courseRepository.findById(courseId);
		if (c.isPresent())
		{
			Course course = c.get();
			return course.getModule();
		}
		else
		{
			return new ArrayList<Module>();
		}
	}
	
	@PostMapping("api/course/{courseId}/module")
	public Module createModule(@PathVariable ("courseId") int courseId, 
			                   @RequestBody Module newModule)
	{
		Optional <Course> c = courseRepository.findById(courseId);
		
		if (c.isPresent())
		{
			Course course = c.get();
			newModule.setCourse(course);
			 return moduleRepository.save(newModule);
			
		}
		else
		{
			return new Module();
		}
	}
	
	@DeleteMapping("api/course/{courseId}/module/{moduleId}/deleteModule")
	public  void delete(@PathVariable ("courseId") int courseId, 
			              @PathVariable ("moduleId") int moduleId)
	{
Optional<Module> data  = moduleRepository.findById(moduleId);
		
		if (data.isPresent())
		{
			Module module = data.get();
			if (module.getCourse().getId() == courseId)
			{
				moduleRepository.delete(module);
			
			}
		
		}
		
	}
	
	@GetMapping("api/course/{courseId}/module/{moduleId}")
	public Module findModuleById(@PathVariable ("courseId") int courseId, 
			              @PathVariable ("moduleId") int moduleId)
	{
Optional<Module> data  = moduleRepository.findById(moduleId);
		
		if (data.isPresent())
		{
			Module module = data.get();
			if (module.getCourse().getId() == courseId)
			{
				return module;
			
			}
			
			return new Module();
		
		}
		return new Module();
		
	}
	
	@GetMapping("api/course/findAllModules")
	public 	List<Module> findAllModules()
	{
		return (List<Module>) moduleRepository.findAll();
	}
	
	
	@PutMapping("api/course/{courseId}/module")
	public void updateModule(@PathVariable ("courseId") int courseId,
			                  @RequestBody Module module)
	{
	      Optional<Course> c = courseRepository.findById(courseId);
	      
	      if (c.isPresent())
	      {
	    	  Optional<Module> m = moduleRepository.findById(module.getId());
	    	  
	    	  if (m.isPresent())
	    	  {
	    		  Module oldModule = m.get();
	    		  oldModule.setTitle(module.getTitle());
	    		  moduleRepository.save(oldModule);
	    	  }
	    	  
	      }
	}
	
	
}