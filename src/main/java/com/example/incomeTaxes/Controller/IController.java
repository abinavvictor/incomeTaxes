package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private IEmployeeService	employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    //rest Api to store Employee Details
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee)
    {
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee),HttpStatus.CREATED);
    }

    //restApi for taxDeductions
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeTaxDed(@PathVariable("id") Long empId ,@RequestBody IEmployee employee){

        return new ResponseEntity<Employee>(employeeService.getEmployeeTaxDed(employee,empId),HttpStatus.OK);

    }








}