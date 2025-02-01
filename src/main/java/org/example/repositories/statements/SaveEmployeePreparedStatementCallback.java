package org.example.repositories.statements;

import org.example.entity.Employee;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveEmployeePreparedStatementCallback implements PreparedStatementCallback<Integer> {

    private final Employee e;

    public SaveEmployeePreparedStatementCallback(Employee e) {
        this.e = e;
    }

    @Override
    public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
        ps.setLong(1, e.getId());
        ps.setString(2, e.getName());
        ps.setString(3, e.getOccupation());
        ps.setFloat(4, e.getSalary());
        ps.setInt(5, e.getAge());
        ps.setDate(6, e.getJoinDate());


        ps.execute();
        return ps.getUpdateCount();
    }


}
