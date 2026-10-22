package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.StudentRepository;
import com.example.demo.model.Student;

@RequestMapping("/students")
@RestController
public class StudentController {

	private final StudentRepository studentRepository;
	public StudentController(StudentRepository studentRepository)
	{
		this.studentRepository=studentRepository;
	}
	
	@PostMapping
	public Student add(@RequestBody Student student)
	{
		return studentRepository.save(student);
	}
	
	@GetMapping("/{id}")
	public Student findStudentById(@PathVariable("id") int id)
	{
		return studentRepository.findById(id).orElse(null);
	}
	
	@GetMapping
	public List<Student> findAllStudents()
	{
		return studentRepository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public int deleteStudentById(@PathVariable("id") int id)
	{
		Student stu=findStudentById(id);
		if(stu!=null)
			{
			studentRepository.deleteById(id);
			return 1;
			}
		else return 0;
	}
	
	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable("id")int id, @RequestBody Student student )
	{
		Student stu=findStudentById(id);
		if(stu!=null)
		{
			return studentRepository.save(student);
		}
		else throw new RuntimeException("Student not found");
	}
}
