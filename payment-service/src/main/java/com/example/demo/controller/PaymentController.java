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

import com.example.demo.client.EnrollmentClient;
import com.example.demo.client.StudentClient;
import com.example.demo.dao.PaymentRepository;
import com.example.demo.dto.EnrollmentDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.model.Payment;

@RequestMapping("/payment")
@RestController
public class PaymentController {

	private final PaymentRepository paymentRepository;
	private final StudentClient studentClient;
	private EnrollmentClient enrollmentClient;
	public PaymentController(PaymentRepository paymentRepository,StudentClient studentClient,EnrollmentClient enrollmentClient)
	{
		this.paymentRepository=paymentRepository;
		this.studentClient=studentClient;
		this.enrollmentClient=enrollmentClient;
	}
	
	@PostMapping
	public String add(@RequestBody Payment payment)
	{
		StudentDto s=studentClient.findStudentById(payment.getStudentId());
		if(s==null)return "student not found";
		EnrollmentDto e=enrollmentClient.findEnrollmentById(payment.getEnrollmentId());
		if(e==null)return "enroll not found";
		
		payment.setPaymentDate(LocalDate.now());
	   
		paymentRepository.save(payment);
		return "data insert";
	}
	@GetMapping("/{id}")
	public Payment findPaymentById(@PathVariable("id") int id)
	{
		return paymentRepository.findById(id).orElse(null);
	}
	@GetMapping
	public List<Payment> findAllPayments()
	{
		return paymentRepository.findAll();
	}
	@DeleteMapping("/{id}")
	public int deletePaymentById(@PathVariable("id") int id)
	{
		Payment p=findPaymentById(id);
		if(p!=null)
		{
			paymentRepository.deleteById(id);
			return 1;
		}
		return 0;
	}
	@PutMapping("/{id}")
	public String updatePayment(@PathVariable("id")int id,@RequestBody Payment payment)
	{
		Payment p=findPaymentById(id);
		if(p!=null)
		{
			StudentDto s=studentClient.findStudentById(payment.getStudentId());
			if(s==null)return "student not found";
			EnrollmentDto e=enrollmentClient.findEnrollmentById(payment.getEnrollmentId());
			if(e==null)return "enroll not found";
			payment.setId(id);
		    paymentRepository.save(payment);
		    return "Data update";
		}
		else return "payment not found";
	}
	
	
}
