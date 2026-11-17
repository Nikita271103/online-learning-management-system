package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.CourseClient;
import com.example.demo.client.StudentClient;
import com.example.demo.dao.EnrollmentRepository;
import com.example.demo.dto.CourseDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.model.Enrollment;

@RequestMapping("/enrollment")
@RestController
public class EnrollmentController {

	private final EnrollmentRepository enrollmentRepository;
	private final StudentClient studentClient;
	private final CourseClient courseClient;
	
	public EnrollmentController(EnrollmentRepository enrollmentRepository,StudentClient studentClient,CourseClient courseClient)
	{
		this.enrollmentRepository=enrollmentRepository;
		this.studentClient=studentClient;
		this.courseClient=courseClient;
	}
	@PostMapping
	public String add(@RequestBody Enrollment enrollment)
	{
	     StudentDto stu=studentClient.findStudentById(enrollment.getStudentId());
	     if(stu==null)return "student not found";
	     
	     CourseDto c=courseClient.findCourseById(enrollment.getCourseId());
	     if(c==null)return "course not found";
	     
	   
	     enrollment.setEnrollmentDate(LocalDate.now());
	     
	     enrollmentRepository.save(enrollment);
	     return "data insert";
	     
	}
	@GetMapping("/{id}")
	public Enrollment findEnrollmentById(@PathVariable("id")int id)
	{
		return enrollmentRepository.findById(id).orElse(null);
	}
	
	@GetMapping
	public List<Enrollment> findAllEnrollments()
	{
		return enrollmentRepository.findAll();
	}
	@DeleteMapping("/{id}")
	public String deleteEnrollmentById(@PathVariable("id") int id)
	{
		Enrollment e=findEnrollmentById(id);
		if(e!=null)
		{
		enrollmentRepository.deleteById(id);
		return "data delete";
		}
		else return "enrollment not found";
	}
	@PutMapping("/{id}")
	public String updateEnrollment(@PathVariable("id") int id,@RequestBody Enrollment en)
	{
		Enrollment e=findEnrollmentById(id);
		if(e!=null)
		{ StudentDto stu=studentClient.findStudentById(en.getStudentId());
	     if(stu==null)return "student not found";
	     
	     CourseDto c=courseClient.findCourseById(en.getCourseId());
	     if(c==null)return "course not found";
	     en.setId(id);
		enrollmentRepository.save(en);
		return "data update";
		}
		else throw new RuntimeException("enroll not found");
	}
	
	
}
