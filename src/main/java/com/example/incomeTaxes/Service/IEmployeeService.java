
package com.example.incomeTaxes.Service;

import java.util.List;

import com.example.incomeTaxes.Model.IEmployee;

public interface EmployeeService {
    IEmployee saveEmployee(IEmployee employee);

    IEmployee getEmployeeTaxDed(IEmployee employee, Long empId);

}

