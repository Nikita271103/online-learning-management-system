package com.example.demo.controller;

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
import com.example.demo.client.InstructorClient;
import com.example.demo.dao.AssignmentRepository;
import com.example.demo.dto.CourseDto;
import com.example.demo.dto.InstructorDto;
import com.example.demo.model.Assignment;

@RequestMapping("/assignment")
@RestController
public class AssignmentController {
	private final AssignmentRepository assignmentRepository;
	private final CourseClient courseClient;
	private final InstructorClient instructorClient;
	public AssignmentController(AssignmentRepository assignmentRepository, CourseClient courseClient, InstructorClient instructorClient) {
		
		this.assignmentRepository = assignmentRepository;
		this.courseClient = courseClient;
		this.instructorClient = instructorClient;
	}
	@PostMapping
	public String add(@RequestBody Assignment assignment)
	{
		CourseDto c= courseClient.findCourseById(assignment.getCourseId());
		if(c==null)return "course not found";
		InstructorDto i=instructorClient.findInstructorById(assignment.getInstructorId());
		if(i==null)return "instruct not found";
		
		assignmentRepository.save(assignment);
		return "data insert";
		
	}
	@GetMapping("/{id}")
public Assignment findAssignmentById(@PathVariable("id") int id)
	{
		return assignmentRepository.findById(id).orElse(null);
	}
	@GetMapping
	public List<Assignment> findAllAssignment()
	{
		return assignmentRepository.findAll();
	}
	@DeleteMapping("/{id}")
	public int deleteById(@PathVariable("id") int id)
	{
		Assignment a=findAssignmentById(id);
		if(a!=null) {
			assignmentRepository.deleteById(id);
		return 1;
		}
		else return 0;
	}
	@PutMapping("/{id}")
	public String updateById(@PathVariable("id")int id,@RequestBody Assignment as)
	{
		Assignment a=findAssignmentById(id);
		if(a!=null)
		{
			CourseDto c= courseClient.findCourseById(as.getCourseId());
			if(c==null)return "course not found";
			InstructorDto i=instructorClient.findInstructorById(as.getInstructorId());
			if(i==null)return "instruct not found";
			 as.setId(id);
		    assignmentRepository.save(as);
		   
		    return "data update";
		}
		else return "data not update";
	}

}
