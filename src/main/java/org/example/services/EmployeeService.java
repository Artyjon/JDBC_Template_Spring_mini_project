package org.example.services;


import org.example.entity.Employee;
import org.example.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



}
