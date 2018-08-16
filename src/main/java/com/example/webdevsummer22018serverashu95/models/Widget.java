package com.example.webdevsummer22018serverashu95.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Widget 
{
	

	
	@ManyToOne
	@JsonIgnore
	private Lesson lesson;
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		private String title;
		private String widgetType;
		private int lorder;
		private String text;
		private String link;                   //Link,Image
		private String layout;                 //List
		private int size;                      //Heading
		private boolean visibility;



		
		public boolean isVisibility() 
		{
			return visibility;
		}
		public void setVisibility(boolean visibility)
		{
			this.visibility = visibility;
		}
		public Lesson getLesson() 
		{
			return lesson;
		}
		public void setLesson(Lesson lesson) 
		{
			this.lesson = lesson;
		}
		public int getId() 
		{
			return id;
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
		public String getWidgetType()
		{
			return widgetType;
		}
		public void setWidgetType(String widgetType)
		{
			this.widgetType = widgetType;
		}
		public int getLorder() 
		{
			return lorder;
		}
		public void setLorder(int lorder)
		{
			this.lorder = lorder;
		}
		public String getText() 
		{
			return text;
		}
		public void setText(String text) 
		{
			this.text = text;
		}
		public String getLink()
		{
			return link;
		}
		public void setLink(String link)
		{
			this.link = link;
		}
		public String getLayout() 
		{
			return layout;
		}
		public void setLayout(String layout) 
		{
			this.layout = layout;
		}
		public int getSize() 
		{
			return size;
		}
		public void setSize(int size) 
		{
			this.size = size;
		}                     
		
		
}
		
		
