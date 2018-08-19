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
import com.example.webdevsummer22018serverashu95.models.Topic;
import com.example.webdevsummer22018serverashu95.repositories.CourseRepository;
import com.example.webdevsummer22018serverashu95.repositories.LessonRepository;
import com.example.webdevsummer22018serverashu95.repositories.ModuleRepository;
import com.example.webdevsummer22018serverashu95.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 999999999)
public class TopicService 
{
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	TopicRepository topicRepository;
	
	@GetMapping("/api/course/module/lesson/topic/findAllTopics")
	public List<Topic> findAllTopics()
	{
		return (List<Topic>) topicRepository.findAll();
	}
	
	
	
	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic")
	public Topic createTopic(@PathVariable ("moduleId") int moduleId, 
			                   @PathVariable ("courseId") int courseId,
			                   @PathVariable ("lessonId") int lessonId,
			                    @RequestBody Topic topic)
	{
		Optional<Course> c= courseRepository.findById(courseId);
		if (c.isPresent())
		{
			Optional<Module> module = moduleRepository.findById(moduleId);
			
			if (module.isPresent())
			{
			Optional<Lesson> l = lessonRepository.findById(lessonId) ;
			  if (l.isPresent()){
					
				Lesson lesson = l.get();
				topic.setLesson(lesson);
				return topicRepository.save(topic);
				}
				else
				{
					return new Topic();
				}
			
				
				
			}
			else
			{
			return new Topic();
			}
		}
		else
		{
		
		return new Topic();
		}
	}
		
	
	
	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topics")
	public List<Topic> findAllTopicsForLesson(@PathVariable ("moduleId") int moduleId, 
                                               @PathVariable ("courseId") int courseId,
                                               @PathVariable ("lessonId") int lessonId)
	{
		
		Optional<Lesson> l = lessonRepository.findById(lessonId);
		if (l.isPresent())
		{
			Lesson less = l.get();
			return less.getTopics();
		}
		else
		{
			return new ArrayList<Topic>();
		}
		
	}	
	
	@DeleteMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic/{topicId}")
	public void deleteTopic(@PathVariable ("moduleId") int moduleId, 
                                               @PathVariable ("courseId") int courseId,
                                                @PathVariable ("lessonId") int lessonId,
                                                @PathVariable ("topicId") int topicId)
	
		{
		Optional<Topic> topic = topicRepository.findById(topicId);
		{
			if (topic.isPresent())
			{
				Topic t = topic.get();
			
		topicRepository.delete(t);
					
				}
		}
		}
	
	@GetMapping ("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic/{topicId}")
	public Topic findTopicById(@PathVariable ("moduleId") int moduleId, 
				  				@PathVariable ("courseId") int courseId,
				  				@PathVariable ("lessonId") int lessonId,
				  				@PathVariable ("topicId") int topicId)
	{
		
		
		Optional<Topic> t = topicRepository.findById(topicId);
		if (t.isPresent())
		{
		Topic topic = t.get();
		return topic;
		}
		else
		{
			return new Topic();
		}
	}
	
	
	@PutMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic/{topicId}")
	public void updateLesson(@PathVariable ("moduleId") int moduleId, 
			                   @PathVariable ("courseId") int courseId,
			                   @PathVariable ("lessonId") int lessonId,
			                   @PathVariable ("topicId") int topicId,
			                    @RequestBody Topic newTopic)
	{
		Optional<Topic> t= topicRepository.findById(topicId);
		if (t.isPresent())
		{
			Topic topic = t.get();
			topic.setTitle(newTopic.getTitle());
			topicRepository.save(topic);
		}
		
		
}
}
