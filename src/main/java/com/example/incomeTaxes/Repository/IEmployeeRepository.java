package com.example.incomeTaxes.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Employee;

public interface Repository extends JpaRepository<Employee, Long>{

}