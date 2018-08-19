package com.example.webdevsummer22018serverashu95.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Topic {
	

	@ManyToOne
	@JsonIgnore
	private Lesson lesson;
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String title;
		
		public Lesson getLesson() {
			return lesson;
		}
		public void setLesson(Lesson lesson) {
			this.lesson = lesson;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}

}
