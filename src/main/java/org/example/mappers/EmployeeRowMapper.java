package org.example.mappers;

import org.example.entity.Employee;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EmployeeRowMapper  implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Employee().setId(rs.getLong("id"))
                .setName(rs.getString("name"))
                .setOccupation(rs.getString("occupation"))
                .setSalary(rs.getInt("salary"))
                .setAge(rs.getInt("age"))
                .setJoinDate(rs.getDate("join_date"));
    }

    // mostly like as List of employees

    /*
     * got rid of ----- List<Employee> employees = new ArrayList<>();
     *
     * got rid of ----- while(rs.next()){
     *                      employees.add(new Employee().setId(rs.getLong("id"))
     *                      .setName(rs.getString("name"))
     *                      .setOccupation(rs.getString("occupation"))
     *                      .setSalary(rs.getInt("salary"))
     *                      .setAge(rs.getInt("age"))
     *                      .setJoinDate(rs.getDate("join_date")));
     * }
     * got rid of -----return employees;
     *
     *
     * */
}
