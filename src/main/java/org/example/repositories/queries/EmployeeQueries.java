package org.example.repositories.queries;

public class EmployeeQueries {


    private EmployeeQueries() {
    }

    public static final String SAVE_EMPLOYEE = """
            INSERT INTO employee (id, name, occupation, salary, age, join_date)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    public static final String UPDATE_EMPLOYEE = """
            UPDATE employee
            SET name = ?, occupation = ?, salary = ?, age = ?, join_date = ?
            WHERE id = ?
            """;

    public static final String DELETE_EMPLOYEE = """
            DELETE FROM employee AS e
            WHERE e.id = ?
            """;

    public static final String FIND_FIRST_EMPLOYEE = """
            SELECT * 
            FROM employee AS e
            WHERE e.id = 1
            """;

    public static final String FIND_ALL_EMPLOYEES = """
            SELECT * 
            FROM employee AS e
            """;

    public static final String FIND_EMPLOYEE_BY_ID = """
            SELECT * 
            FROM employee AS e
            WHERE e.id = ?
            """;

}
