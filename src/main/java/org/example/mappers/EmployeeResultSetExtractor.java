package org.example.mappers;

import org.example.entity.Employee;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EmployeeResultSetExtractor implements ResultSetExtractor<Employee> {

    @Override
    public Employee extractData(ResultSet rs) throws SQLException, DataAccessException {

        // you need to check if the result set is empty, if so return null, if not, return the first row

        rs.next();

        return new Employee().setId(rs.getLong("id"))
                .setName(rs.getString("name"))
                .setOccupation(rs.getString("occupation"))
                .setSalary(rs.getInt("salary"))
                .setAge(rs.getInt("age"))
                .setJoinDate(rs.getDate("join_date"));
    }


    // mostly like as List of employees

    /*
    * List<Employee> employees = new ArrayList<>();
    *
    * while(rs.next()){
    *     employees.add(new Employee().setId(rs.getLong("id"))
    *             .setName(rs.getString("name"))
    *             .setOccupation(rs.getString("occupation"))
    *             .setSalary(rs.getInt("salary"))
    *             .setAge(rs.getInt("age"))
    *             .setJoinDate(rs.getDate("join_date")));
    * }
    * return employees;
    *
    *
    * */
}
