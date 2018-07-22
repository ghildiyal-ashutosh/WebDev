package com.example.webdevsummer22018serverashu95.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Module 
{
	
	@OneToMany(mappedBy = "module",orphanRemoval=true)
	private List<Lesson> lessons;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	
	@ManyToOne
	@JsonIgnore
	private Course course;

	public int getId() 
	{
		return id;
	}

	public List<Lesson> getLessons() 
	{
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) 
	{
		this.lessons = lessons;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Course getCourse() 
	{
		return course;
	}

	public void setCourse(Course course) 
	{
		this.course = course;
	}
	

}
