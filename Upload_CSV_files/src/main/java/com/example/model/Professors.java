package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Professors")
public class Professors {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="subject")
	private String subject;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Professors(long id, String name, String subject) {
		super();
		this.id = id;
		this.name = name;
		this.subject = subject;
	}
	
 public Professors() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Professors [id=" + id + ", name=" + name + ", subject=" + subject + "]";
	}
	
}
