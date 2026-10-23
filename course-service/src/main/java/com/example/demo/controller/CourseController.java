package com.example.demo.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CourseRepository;
import com.example.demo.model.Course;

@RequestMapping("/courses")
@RestController
public class CourseController {

	private final CourseRepository courseRepository;
	
	public CourseController(CourseRepository courseRepository)
	{
		this.courseRepository=courseRepository;
	}
	
	@PostMapping
	public Course add(@RequestBody Course course)
	{
		return courseRepository.save(course);
	}
	@GetMapping("/{id}")
	public Course findCourseById(@PathVariable("id") int id)
	{
		return courseRepository.findById(id).orElse(null);
		
	}
	@GetMapping
	public List<Course> findAllCourses()
	{
		return courseRepository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public int deleteCourseById(@PathVariable("id") int id)
	{
		Course c=findCourseById(id);
		if(c!=null)
		{
			courseRepository.deleteById(id);
			return 1;
		}
		else return 0;
		
	}
	
	@PutMapping("/{id}")
	public Course updateCourse(@PathVariable("id") int id,@RequestBody Course course)
	{
		Course c=findCourseById(id);
		if(c!=null)
		{
			return courseRepository.save(course);
		}
		else throw new RuntimeException("course not available");
	}
}
