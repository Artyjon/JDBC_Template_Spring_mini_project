package org.example.services;


import org.example.entity.Employee;
import org.example.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //public int update (String query)


    public int saveUsingUpdate(Employee e) {
        return employeeRepository.saveUsingUpdate(e);
    }

    public int updateUsingUpdate(Employee e) {
        return employeeRepository.updateUsingUpdate(e);
    }

    public int deleteUsingUpdate(Employee e) {
        return employeeRepository.deleteUsingUpdate(e);
    }


    // public T execute(String sql, PreparedStatementCallback<T> psc);


    public Integer saveByPreparedStatement(Employee e) {
        return employeeRepository.saveByPreparedStatement(e);
    }

    public Integer updateByPreparedStatement(Employee e) {
        return employeeRepository.updateByPreparedStatement(e);
    }

    public Integer deleteByPreparedStatement(Employee e) {
        return employeeRepository.deleteByPreparedStatement(e);
    }

    // public T query(String sql, ResultSetExtractor<T> rse)

    public Employee getFirstWithResultSetExtractor() {
        return employeeRepository.getFirstWithResultSetExtractor();

    }

    // public List<T> query(String sql, RowMapper<T> rowMapper)

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id);
    }

    // public int update(String sql, @Nullable Object... args);

    public void deleteById(Long id) {
        int modified = employeeRepository.deleteById(id);
        if (modified != 0) {
            System.out.println("Employee was deleted by id: " + id);
        } else {
            throw new RuntimeException("Delete failed by id: " + id);
        }
    }

    public void save(Employee employee) {
        int modified = employeeRepository.save(employee);
        if (modified != 0) {
            System.out.println("Employee was saved: " + employee);
        } else {
            throw new RuntimeException("Save failed: " + employee);
        }
    }

}
