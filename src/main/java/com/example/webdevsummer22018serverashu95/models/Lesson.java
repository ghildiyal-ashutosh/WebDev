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
public class Lesson 

{
	
	@OneToMany(mappedBy = "lesson",orphanRemoval=true)
	private List<Widget> widgets;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	
	@OneToMany(mappedBy = "lesson",orphanRemoval=true)
	private List<Topic> topics;
	
	
	public List<Topic> getTopics() 
	{
		return topics;
	}

	public void setTopics(List<Topic> topics) 
	{
		this.topics = topics;
	}

	

	@ManyToOne
	@JsonIgnore
	private Module module;

	public int getId() 
	{
		return id;
	}

	public List<Widget> getWidgets() 
	{
		return widgets;
	}

	public void setWidgets(List<Widget> widgets) 
	{
		this.widgets = widgets;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public Module getModule() 
	{
		return module;
	}

	public void setModule(Module module)
	{
		this.module = module;
	}
	
}