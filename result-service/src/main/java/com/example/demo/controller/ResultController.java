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

import com.example.demo.client.AssignmentClient;
import com.example.demo.client.EnrollmentClient;
import com.example.demo.client.StudentClient;
import com.example.demo.dao.ResultRepository;
import com.example.demo.dto.AssignmentDto;
import com.example.demo.dto.EnrollmentDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.model.Result;

@RequestMapping("/result")
@RestController
public class ResultController {
private final ResultRepository resultRepository;
private final StudentClient studentClient;
private final AssignmentClient assignmentClient;
private final EnrollmentClient enrollmentClient;
public ResultController(ResultRepository resultRepository, StudentClient studentClient,AssignmentClient assignmentClient, EnrollmentClient enrollmentClient) {
	
	this.resultRepository = resultRepository;
	this.studentClient = studentClient;
	this.assignmentClient = assignmentClient;
	this.enrollmentClient = enrollmentClient;
}

@PostMapping
public String add(@RequestBody Result result)
{
	StudentDto s= studentClient.findStudentById(result.getStudentId());
	if(s==null)return "Student not found";
	AssignmentDto a= assignmentClient.findAssignmentById(result.getAssignmentId());
	if(a==null)return "assignment not found";
	EnrollmentDto e= enrollmentClient.findEnrollmentById(result.getEnrollmentId());
	if(e==null)return "enrollmentt not found";
	result.setResultDate(LocalDate.now());
	resultRepository.save(result);
	return "data insert";
	}
@GetMapping("/{id}")
public Result findResultById(@PathVariable("id") int id)
	{
		return resultRepository.findById(id).orElse(null);
	}
	@GetMapping
	public List<Result> findAllResult()
	{
		return resultRepository.findAll();
	}
	@DeleteMapping("/{id}")
	public int deleteById(@PathVariable("id") int id)
	{
		Result a=findResultById(id);
		if(a!=null) {
			resultRepository.deleteById(id);
		return 1;
		}
		else return 0;
	}
	@PutMapping("/{id}")
	public String updateById(@PathVariable("id")int id,@RequestBody Result result)
	{
		Result re=findResultById(id);
		if(re!=null)
		{
			StudentDto s= studentClient.findStudentById(result.getStudentId());
			if(s==null)return "Student not found";
			AssignmentDto a= assignmentClient.findAssignmentById(result.getAssignmentId());
			if(a==null)return "assignment not found";
			EnrollmentDto e= enrollmentClient.findEnrollmentById(result.getEnrollmentId());
			if(e==null)return "enrollmentt not found";
			result.setId(id);
			resultRepository.save(result);
			return "data update";
		}
		else return "payment not update";
	}

}
