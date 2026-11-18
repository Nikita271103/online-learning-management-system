package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Assignment {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private String title;
private String description;
private int courseId;
private int instructorId;
private int dueDate;
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
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getCourseId() {
	return courseId;
}
public void setCourseId(int courseId) {
	this.courseId = courseId;
}
public int getInstructorId() {
	return instructorId;
}
public void setInstructorId(int instructorId) {
	this.instructorId = instructorId;
}
public int getDueDate() {
	return dueDate;
}
public void setDueDate(int dueDate) {
	this.dueDate = dueDate;
}

}
