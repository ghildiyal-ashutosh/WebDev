package com.example.webdevsummer22018serverashu95.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.example.webdevsummer22018serverashu95.models.Widget;
import com.example.webdevsummer22018serverashu95.repositories.CourseRepository;
import com.example.webdevsummer22018serverashu95.repositories.LessonRepository;
import com.example.webdevsummer22018serverashu95.repositories.ModuleRepository;
import com.example.webdevsummer22018serverashu95.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 999999999)
public class WidgetService 
{
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired 
	WidgetRepository widgetRepository;
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidget()
	{
		return (List<Widget>) widgetRepository.findAll();
	}
	
	@GetMapping("api/widget/{widgetId}")
	public Widget findWidgetById(@PathVariable ("widgetId") int widgetId)
	{
		Optional<Widget> w = widgetRepository.findById(widgetId);
		if (w.isPresent())
			return w.get();
		else
			return new Widget();
		
	}
	
	
	
	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/widget")
	public List<Widget> saveWidgets(@PathVariable ("moduleId") int moduleId, 
			                   @PathVariable ("courseId") int courseId,
			                   @PathVariable ("lessonId") int lessonId,
			                    @RequestBody List <Widget> widgetList)
	{
		Optional<Course> c= courseRepository.findById(courseId);
		if (c.isPresent())
		{
			Optional<Module> module = moduleRepository.findById(moduleId);
			
			if (module.isPresent())
			{
			
				Optional<Lesson> lesson = lessonRepository.findById(lessonId);
				
				if (lesson.isPresent())
				{
					Lesson l = lesson.get();
					List<Widget> existingWidget = l.getWidgets();
					
					for(Widget widget: existingWidget)
					{
		                widgetRepository.deleteById(widget.getId());
		            }
					List<Widget> savedWidget = new ArrayList<Widget>();
					
					Set<String> titles = new HashSet<String>();
					
					for (Widget widget: widgetList)
					{
						if (titles.contains(widget.getTitle()))
						{
					          List<Widget> arr = new ArrayList<Widget>();
					          
					          
					          widget.setText("Negative");
					          arr.add(widget);
					          return arr;
					      }
						
				else
				{
						widget.setLesson(l);
						widget.setVisibility(true);
						widgetRepository.save(widget);
						savedWidget.add(widget);
						titles.add(widget.getTitle());
						
				}
					
					
				}
					return savedWidget;
				}
				else
				{
				return new ArrayList <Widget>();
			}
			}
			
			else
			{
			return new ArrayList<Widget>();
			}
		}
		else
		{
		
		return new ArrayList <Widget>();
		}
	}
	
	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/widget")
	public List<Widget> findWidgetsForLesson(@PathVariable ("moduleId") int moduleId, 
                                       @PathVariable ("courseId") int courseId,
                                       @PathVariable ("lessonId") int lessonId)
	{
		{
			Optional<Course> c= courseRepository.findById(courseId);
			if (c.isPresent())
			{
				Optional<Module> module = moduleRepository.findById(moduleId);
				
				if (module.isPresent())
				{
					Optional<Lesson> lesson = lessonRepository.findById(lessonId);
					
					if (lesson.isPresent())
					{
						return (lesson.get().getWidgets());
					}
					return new ArrayList<Widget>();
				}
				else
				{
		          return new ArrayList<Widget>();
				}
			}
			return new ArrayList<Widget>();
		}
	}
	
	
	@PutMapping("api/widget/{widgetId}")
	public Widget updateWidget(@PathVariable ("widgetId") int widgetId,
			                    @RequestBody Widget newWidget)
	{
		Optional<Widget> w = widgetRepository.findById(widgetId);
				if (w.isPresent())
				{
				   Widget widget = w.get();
				   widget.setLayout(newWidget.getLayout());
				   widget.setLink(newWidget.getLink());
				   widget.setLorder(newWidget.getLorder());
				   widget.setSize(newWidget.getSize());
				   widget.setText(newWidget.getText());
				   widget.setTitle(newWidget.getTitle());
				   widget.setWidgetType(newWidget.getWidgetType());
				   
				   widgetRepository.save(widget);
				   return widget;
				}
		
		return new Widget();
	}
	
	@DeleteMapping("api/widget/{widgetId}")
	public void deleteWidget(@PathVariable ("widgetId") int widgetId)
	{
		
		widgetRepository.deleteById(widgetId);
		
	}
	
}
