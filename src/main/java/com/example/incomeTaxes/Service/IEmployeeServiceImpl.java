package com.example.incomeTaxes.Service;

import java.util.List;
import java.util.Optional;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
import org.springframework.stereotype.Service;

import com.example.incomeTaxes.Exception.ResourceNotFoundException;
import com.example.incomeTaxes.Model.Employee;
import com.example.incomeTaxes.Repository.IEmployeeRepository;
import com.example.incomeTaxes.Service.IEmployeeService;

@Service
public class IEmployeeServiceImpl implements IEmployeeService {

    private IEmployeeRepository employeeRepository;



    public IEmployeeServiceImpl(IEmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }



    @Override
    public IEmployee saveEmployee(IEmployee employee) {
        return employeeRepository.save(employee);
    }


    @Override
    public IEmployee getEmployeeTaxDed(IEmployee employee, Long empId) {
IEmployee existingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", id));


       existingEmployee.setFirstName(employee.getempDOJ());
       existingEmployee.setFirstName(employee.getempFirstName());
       existingEmployee.setLastName(employee.getempLastName());
       existingEmployee.setLastName(employee.getempSalary());
       existingEmployee.setTaxDedAmount(getTaxDedAmount(IEmployee employee));
       existingEmployee.setCessAmount(getCessAmount(IEmployee employee));
       employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Overrde
    public Employee getTaxDedAmount(Employee employee){

        int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
        int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
        String taxStartDate="";
        String taxEndDate="";
        if (CurrentMonth<4) {
            taxStartDate="01-04-"+(CurrentYear-1);
            taxEndDate="31-03-"+(CurrentYear);
        } else {
            taxStartDte="01-04-"+(CurrentYear);
            taxEndDate="31-03-"+(CurrentYear+1);
        }
       Date joiningDate = IEmployee.getempDOJ();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
        String formattedDate = sdf.format(joiningDate);
       int noTaxDays = Period.between(taxStartDate , formattedDate).getDays();
       int WorkingDays =  Period.between(taxStartDate ,taxEndDate).getDays();
       int taxableDays =    WorkingDays - noTaxDays;

       double payPerDay = Employee.getempSalary() /30;
       double totalTaxableSalary = payPerDay * taxableDays;

       double taxDed =0.0;
       double appPay = 0.0;
       if (totalTaxableSalary <=250000){
            taxDed =0; }
       else if(totalTaxableSalary > 250000 && totalTaxableSalary <= 500000){

           appPay = totalTaxableSalary -250000;
            taxDed = 0.05 * appPay;
       }

    else if (totalTaxableSalary >= 500001 && totalTaxableSalary <= 1000000) {
        appPay = totalTaxableSalary - 500000;
         taxDed = 12500 + (0.20 * appIncome);
    } else {
        appPay = totalTaxableSalary - 1000000;
        taxDed = 112500 + (0.30 * appPay);
    }
      return  taxDed;

    }


    @Overrde
    public Employee getCessAmount(Employee employee) {
        double payPerDay = Employee.getempSalary() /30;
        double totalTaxableSalary = payPerDay * taxableDays;

        double cessAmount = 0.0;
        double appr = 0.0;

        if(totalTaxableSalary > 250000) {
            appr = totalTaxableSalary - 250000;
            cessAmount = 0.02 * appr;

        }
         return cessAmount;

    }


}