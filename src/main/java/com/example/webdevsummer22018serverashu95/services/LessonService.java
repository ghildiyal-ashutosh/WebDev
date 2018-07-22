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
import com.example.webdevsummer22018serverashu95.models.Lesson;
import com.example.webdevsummer22018serverashu95.models.Module;
import com.example.webdevsummer22018serverashu95.repositories.CourseRepository;
import com.example.webdevsummer22018serverashu95.repositories.LessonRepository;
import com.example.webdevsummer22018serverashu95.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 999999999)
public class LessonService 
{
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/course/module/lesson/findAllLessons")
	public List<Lesson> findAllLessons()
	{
		return (List<Lesson>) lessonRepository.findAll();
	}
	
	//api/course/CID/module/MID/lesson
	
	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public Lesson createLesson(@PathVariable ("moduleId") int moduleId, 
			                   @PathVariable ("courseId") int courseId,
			                    @RequestBody Lesson lesson)
	{
		Optional<Course> c= courseRepository.findById(courseId);
		if (c.isPresent())
		{
			Optional<Module> module = moduleRepository.findById(moduleId);
			
			if (module.isPresent())
			{
				Module m = module.get();
				lesson.setModule(m);
				return lessonRepository.save(lesson);
				
			}
			else
			{
			return new Lesson();
			}
		}
		else
		{
		
		return new Lesson();
		}
	}
		
	
	
	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public List<Lesson> findAllLessonForModule(@PathVariable ("moduleId") int moduleId, 
                                               @PathVariable ("courseId") int courseId)
	{
		{
			Optional<Course> c= courseRepository.findById(courseId);
			if (c.isPresent())
			{
				Optional<Module> module = moduleRepository.findById(moduleId);
				
				if (module.isPresent())
				{
					Module m = module.get();
					
					return m.getLessons();
				}
				else
				{
		          return new ArrayList<Lesson>();
				}
			}
			return new ArrayList<Lesson>();
		}
	}	
	
	@DeleteMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}")
	public void deleteLesson(@PathVariable ("moduleId") int moduleId, 
                                               @PathVariable ("courseId") int courseId,
                                                @PathVariable ("lessonId") int lessonId)
	
		{
			Optional<Lesson> data  = lessonRepository.findById(lessonId);
					
					if (data.isPresent())
					{
						Lesson lesson = data.get();
						if (lesson.getModule().getId() == moduleId)
						{
							Optional<Module> data1 = moduleRepository.findById(moduleId);
							
							if(data1.isPresent())
							{
								Module module = data1.get();
								
								if (module.getCourse().getId() == courseId)
									lessonRepository.delete(lesson);
							}
						
						}
					
					}
					
				}
	
	@GetMapping ("api/course/{courseId}/module/{moduleId}/lesson/{lessonId}")
	public Lesson findLessonById(@PathVariable ("moduleId") int moduleId, 
				  				@PathVariable ("courseId") int courseId,
				  				@PathVariable ("lessonId") int lessonId)
	{
		Lesson failure = new Lesson();
		Optional<Lesson> data  = lessonRepository.findById(lessonId);
		
		if (data.isPresent())
		{
			Lesson lesson = data.get();
			if (lesson.getModule().getId() == moduleId)
			{
				Optional<Module> data1 = moduleRepository.findById(moduleId);
				
				if(data1.isPresent())
				{
					Module module = data1.get();
					
					if (module.getCourse().getId() == courseId)
						return lesson;
					
					return failure;
				}
				return failure;
			
			}
			return failure;
		
		}
		return failure;
		
	}
	
	
	@PutMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}")
	public void updateLesson(@PathVariable ("moduleId") int moduleId, 
			                   @PathVariable ("courseId") int courseId,
			                   @PathVariable ("lessonId") int lessonId,
			                    @RequestBody Lesson newLesson)
	{
		Optional<Course> c= courseRepository.findById(courseId);
		if (c.isPresent())
		{
			Optional<Module> module = moduleRepository.findById(moduleId);
			
			if (module.isPresent())
			{
				Module m = module.get();
				if (m.getCourse() == c.get())
				{
				Optional<Lesson> lesson = lessonRepository.findById(lessonId);
				if (lesson.isPresent())
				{
					Lesson less = lesson.get();
					if(less.getModule() == m)
					{
						less.setTitle(newLesson.getTitle());
						lessonRepository.save(less);
					}
				}
				}
				
			}
		
	}
		
}
}
