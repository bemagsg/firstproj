package com.react.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.react.model.Employee;
import com.react.respository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class HomeController {
	
	
	private EmployeeRepository repository;
	
	@Autowired
	private HomeController(EmployeeRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/employees")
	public Collection<Employee> emp(){
		return repository.findAll();
	}
	
	@GetMapping("/employee/{id}")
	ResponseEntity<?> getEmployee(@PathVariable int id) {
		Optional<Employee> emp= repository.findById(id);
		return emp.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/employee")
	ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee emp) throws URISyntaxException{
		Employee result = repository.save(emp);
		return ResponseEntity.created(new URI("/api/employee/" + result.getId()))
				.body(result);
	}
	
	@DeleteMapping("/employee/{id}")
	void deleteEmployee(@PathVariable int id){
		repository.deleteById(id);
	}
	
	@PutMapping("/employee")
	ResponseEntity<Employee> editEmployee(@Valid @RequestBody Employee newEmployee) {
		Employee result = repository.save(newEmployee);
        return ResponseEntity.ok().body(result);
	}
}
