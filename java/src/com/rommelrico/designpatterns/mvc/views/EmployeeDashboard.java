package com.rommelrico.designpatterns.mvc.views;

import com.rommelrico.designpatterns.mvc.models.*;

public class EmployeeDashboard {

    private Employee employee;

    public EmployeeDashboard(Employee employee) {
        this.employee = employee;
    }

    public void printEmployeeInformation() {
        System.out.println("Name: " + employee.getFirstName());
        System.out.println("Last Name: " + employee.getLastName());
        System.out.println("SSN: " + employee.getSsn());
        System.out.println("Salary: " + employee.getSalary());
    }

}
