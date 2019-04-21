package com.rommelrico.designpatterns.mvc;

import com.rommelrico.designpatterns.mvc.controllers.*;
import com.rommelrico.designpatterns.mvc.models.*;
import com.rommelrico.designpatterns.mvc.views.*;

public class Main {

    public static void main(String[] args) {

        Employee employee = new Employee();
        employee.setSalary(123);
        EmployeeDashboard dashboard = new EmployeeDashboard(employee);
        EmployeeController controller = new EmployeeController(employee, dashboard);

        controller.setEmployeeName("Rommel", "Rico");
        controller.setSSN("111111");
    }

}
