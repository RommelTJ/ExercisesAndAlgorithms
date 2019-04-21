package com.rommelrico.designpatterns.mvc.controllers;

import com.rommelrico.designpatterns.mvc.models.*;
import com.rommelrico.designpatterns.mvc.views.*;

public class EmployeeController {

    private Employee employee;
    private EmployeeDashboard employeeDashboard;

    public EmployeeController(Employee employee, EmployeeDashboard employeeDashboard) {
        this.employee = employee;
        this.employeeDashboard = employeeDashboard;
    }

    public void setEmployeeName(String name, String lastName) {
        employee.setFirstName(name);
        employee.setLastName(lastName);
    }

    public String getEmployeeName() {
        return employee.getFirstName() + " " + employee.getLastName();
    }

    public void setSSN(String ssn) {
        employee.setSsn(ssn);
    }

    public String getEmployeeSSN() {
        return employee.getSsn();
    }

    // Update the view

    public void updateDashboard() {
        employeeDashboard.printEmployeeInformation();
    }

}
