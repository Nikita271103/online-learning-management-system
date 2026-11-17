package com.example.demo.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.InstructorRepository;
import com.example.demo.model.Instructor;

@RequestMapping("/instructor")
@RestController
public class InstructorController {

	private final InstructorRepository instructorRepository;
	public InstructorController(InstructorRepository instructorRepository)
	{
		this.instructorRepository=instructorRepository;
	}
	
	@PostMapping
	public Instructor add(@RequestBody Instructor instructor)
	{
		return instructorRepository.save(instructor);
	}
	@GetMapping("/{id}")
	public Instructor findInstructorById(@PathVariable("id")int id)
	{
		return instructorRepository.findById(id).orElse(null);
		
	}
	@GetMapping 
	public List<Instructor> findAllInstructor()
	{
		return instructorRepository.findAll();
	}
	@DeleteMapping("/{id}")
	public int deleteInstructorById(@PathVariable("id") int id)
	{
		Instructor instructor=findInstructorById(id);
		
		if(instructor!=null)
		{
			instructorRepository.deleteById(id);
			return 1;
		}
		else return 0;
	}
	@PutMapping("/{id}")
	public Instructor updateById(@PathVariable("id") int id,@RequestBody Instructor instructorr)
	{
		Instructor instructor=findInstructorById(id);
		if(instructor!=null)
		{
			return instructorRepository.save(instructorr);
			
		}
		else
		{
			throw new RuntimeException("not found");		}
	}
	
}
