package com.react;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.react.model.Employee;
import com.react.respository.EmployeeRepository;

@Component
public class DatabaseLoader implements CommandLineRunner{

	
	private final EmployeeRepository repository;
	
	@Autowired
	public DatabaseLoader(EmployeeRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void run(String...strings) throws Exception {
		this.repository.save(new Employee("Gelo","Magalona","Gwapo"));
	}
}
