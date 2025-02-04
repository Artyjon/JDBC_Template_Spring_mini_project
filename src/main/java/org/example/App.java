package org.example;


import org.example.config.AppConfig;
import org.example.entity.Employee;
import org.example.services.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class App {

    public static void main(String[] args) {

        // config

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");

        // data
        Long id = Math.round(Math.random() * 100);
        Employee employee = createEmployee(id);// for demo>

        // demo
        //updateDemo(employeeService, employee); - turned off

        //executeWithPreparedStatement(employeeService, employee);

        printFirstEmployee(employeeService);

        printAllEmployees(employeeService);

        printEmployeeById(employeeService, 1L);

        simpleSaveAndDeleteDemo(employeeService, employee);


    }


    private static void updateDemo(EmployeeService employeeService, Employee employee) {
        System.out.println("updateDemo");
        System.out.println();

        int status = employeeService.saveUsingUpdate(employee);
        System.out.println("Employees were saved by update method: " + status);

        status = employeeService.updateUsingUpdate(employee);
        System.out.println("Employees were updated by update method: " + status);

        status = employeeService.deleteUsingUpdate(employee);
        System.out.println("Employees were deleted by update method: " + status);

        System.out.println();
    }

    private static void executeWithPreparedStatement(EmployeeService employeeService, Employee employee) {
        System.out.println("executeWithPreparedStatement");

        Integer status = employeeService.saveByPreparedStatement(employee);
        System.out.println("Employees were saved by execute with prepared statement: " + status);

        status = employeeService.updateByPreparedStatement(employee);
        System.out.println("Employees were updated by execute with prepared statement: " + status);

        //status = employeeService.deleteByPreparedStatement(employee);
        //System.out.println("Employees were deleted by execute with prepared statement: " + status);
    }

    private static Employee createEmployee(Long id) {
        return new Employee()
                .setId(id)
                .setName("Аркадий Паровозов")
                .setOccupation("Помогатор")
                .setSalary(10000)
                .setAge(25)
                .setJoinDate(Date.valueOf(LocalDate.now()));
    }

    private static void printFirstEmployee(EmployeeService employeeService) {
        Employee employee = employeeService.getFirstWithResultSetExtractor();
        System.out.println("First employee: " + employee);
    }

    private static void printAllEmployees(EmployeeService employeeService) {
        employeeService.findAll()
                .forEach(System.out::println);
        System.out.println();
    }

    private static void printEmployeeById(EmployeeService employeeService, Long id) {
        System.out.println("Employee with id " + id + ": " + employeeService.findById(id));
        System.out.println();
    }


    private static void simpleSaveAndDeleteDemo(EmployeeService employeeService, Employee employee) {
        System.out.println("simpleSaveAndDeleteDemo");
        employeeService.save(employee);
        employeeService.deleteById(employee.getId());
    }


}

