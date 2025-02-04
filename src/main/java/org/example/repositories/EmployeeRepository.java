package org.example.repositories;

import org.example.entity.Employee;
import org.example.mappers.EmployeeResultSetExtractor;
import org.example.repositories.statements.SaveEmployeePreparedStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.example.repositories.queries.EmployeeQueries.*;


@Repository
public class EmployeeRepository implements CrudRepository<Employee> {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Employee> employeeRowMapper;

    private final ResultSetExtractor<Employee> employeeResultSetExtractor;

    public EmployeeRepository(JdbcTemplate jdbcTemplate, ResultSetExtractor<Employee> employeeResultSetExtractor, RowMapper<Employee> employeeRowMapper) {
        this.employeeRowMapper = employeeRowMapper;
        this.jdbcTemplate = jdbcTemplate;
        this.employeeResultSetExtractor = employeeResultSetExtractor;
    }

    // public int update (String query)

    public int saveUsingUpdate(Employee e) {
        String query = "insert into employee (id, name, occupation, salary, age, join_date) " +
                       "values ('" + e.getId() + "', '" + e.getName() + "', '" + e.getOccupation() + "', '" + e.getSalary() + "', '" + e.getAge() + "', '" + e.getJoinDate() + "')";
        return jdbcTemplate.update(query);

    }


    public int updateUsingUpdate(Employee e) {
        String query = "update employee set name='" + e.getName() + "',salary='" + e.getSalary() + "' where id='" + e.getId() + "'";
        return jdbcTemplate.update(query);
    }


    public int deleteUsingUpdate(Employee e) {
        String query = "delete from employee where id='" + e.getId() + "'";
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

    // public T query(String sql, ResultSetExtractor<T> rse)


    @Override
    public Employee getFirstWithResultSetExtractor() {
        return jdbcTemplate.query(FIND_FIRST_EMPLOYEE, employeeResultSetExtractor);
    }

    //public List<T> query(String sql, RowMapper<T> rm)

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(FIND_ALL_EMPLOYEES, employeeRowMapper);
    }



    @Override
    public Employee findById(Long id) {
        List<Employee> resultSet = jdbcTemplate.query(FIND_EMPLOYEE_BY_ID, employeeRowMapper, id);
        return resultSet.get(0);
    }

    // public int update(String sql, @Nullable Object... args);

    @Override
    public int save(Employee employee) {
        return jdbcTemplate.update(SAVE_EMPLOYEE,
                employee.getId(),
                employee.getName(),
                employee.getOccupation(),
                employee.getSalary(),
                employee.getAge(),
                employee.getJoinDate());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(DELETE_EMPLOYEE, id);
    }
}
