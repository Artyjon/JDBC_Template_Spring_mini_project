package org.example.repositories;

import org.example.entity.Employee;
import org.example.repositories.statements.SaveEmployeePreparedStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import static org.example.repositories.queries.EmployeeQueries.*;


@Repository
public class EmployeeRepository implements CrudRepository<Employee> {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // public int update (String query)


    public int saveUsingUpdate(Employee e) {
        String query = "insert into employee (id, name, salary) values ('%d','%s','%d')".formatted(e.getId(), e.getName(), e.getSalary());
        return jdbcTemplate.update(query);

    }


    public int updateUsingUpdate(Employee e) {
        String query = "update employee set name = '%s', salary = '%d' where id = '%d'".formatted(e.getName(), e.getSalary(), e.getId());
        return jdbcTemplate.update(query);
    }


    public int deleteUsingUpdate(Employee e) {
        String query = "delete from employee where id = '%d'".formatted(e.getId());
        return jdbcTemplate.update(query);

    }


    // public T execute(String sql, PreparedStatementCallback<T> psc);


    @Override
    public Integer saveByPreparedStatement(Employee e) {
        return jdbcTemplate.execute(SAVE_EMPLOYEE, new SaveEmployeePreparedStatementCallback(e)); // object was created!!!!!!!
    }

    @Override
    public Integer updateByPreparedStatement(Employee e) {
        return jdbcTemplate.execute(UPDATE_EMPLOYEE, (PreparedStatementCallback<Integer>)  ps -> {
            ps.setString(1, e.getName());
            ps.setString(2, e.getOccupation());
            ps.setFloat(3, e.getSalary());
            ps.setInt(4, e.getAge());
            ps.setDate(5, e.getJoinDate());
            ps.setLong(6, e.getId());

            ps.execute();

            return ps.getUpdateCount();
        });
    }

    @Override
    public Integer deleteByPreparedStatement(Employee e) {
        return 0;
    }
}
