package com.example.demo.dto;

import java.time.LocalDate;

public class CourseDto {
	private int id;
	private String courseName;
	private LocalDate duration;
	private int fee;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public LocalDate getDuration() {
		return duration;
	}
	public void setDuration(LocalDate duration) {
		this.duration = duration;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}

}
