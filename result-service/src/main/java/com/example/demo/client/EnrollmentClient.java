package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.EnrollmentDto;

@FeignClient(name="Enrollment-service")
public interface EnrollmentClient {

	@GetMapping("/enrollment/{id}")
	public EnrollmentDto findEnrollmentById(@PathVariable("id")int id);
}
