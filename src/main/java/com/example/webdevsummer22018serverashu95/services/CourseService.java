package com.example.webdevsummer22018serverashu95.services;

import java.util.Date;
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
import com.example.webdevsummer22018serverashu95.repositories.CourseRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 999999999)   // allow requests from all other domains 
public class CourseService 
{
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/api/course")
	public List<Course> findAllCourses()
	{
		return (List<Course>) courseRepository.findAll();
	}
	
	@PostMapping("api/course/createCourse")
	public Course createCourse(@RequestBody Course course)
	{
		Date date = new Date();
		course.setCreated(date);
		course.setOwnedBy("Prof");
		return courseRepository.save(course);
		
	}
	
	@PutMapping("api/course/updateCourse/courseId/{courseId}/status/{ownedBy}")
	public void updateCourse(@PathVariable ("courseId") int courseId, 
			@PathVariable("ownedBy") String ownedBy)
	{
		Optional<Course> course = courseRepository.findById(courseId);
		
		if (course.isPresent())
		{
		 Course c = (Course) course.get();
		 c.setOwnedBy(ownedBy);
		 Date date = new Date();
		 c.setModified(date);
		 courseRepository.save(c);
		 
		}
	}
	
	@DeleteMapping("api/course/deleteCourse/{courseId}")
	public void deleteCourse(@PathVariable ("courseId") int courseId)
	{
		courseRepository.deleteById(courseId);
	}
	
	@GetMapping("api/course/findCourse/{courseId}")
	public Course findCourseById (@PathVariable ("courseId") int courseId)
	{
		Optional<Course> c = courseRepository.findById(courseId);
		
		if (c.isPresent())
		{
			return (c.get());
		}
		else
		{
		return new Course();
		}
	}

}
