package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.AssignmentDto;
import com.example.demo.dto.EnrollmentDto;

@FeignClient(name="Assignment-service")
public interface AssignmentClient {
	@GetMapping("/assignment/{id}")
	public AssignmentDto findAssignmentById(@PathVariable("id")int id);
}
