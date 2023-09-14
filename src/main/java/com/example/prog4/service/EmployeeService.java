package com.example.prog4.service;

import com.example.prog4.model.Employee;
import com.example.prog4.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository repository;
    public List<Employee> getEmployees(){
        return repository.findAll();
    }
    public Employee getById(Integer id){
        return repository.findById(id).get();
    }
    public Employee createEmployee(Employee employee){
        return repository.save(employee);
    }
    public List<Employee> searchByKeyword(String keyword, String startDate, String endDate) {
        return repository.searchByKeywordAndDateRange(keyword, startDate, endDate);
    }
    public List<Employee> sort(String order, String attribute) {
        Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Sort sort = Sort.by(direction, attribute);

        return repository.findAll(sort);
    }
    public List<String> getAllCSP(){
        List<String> CSP = new ArrayList<>();
        CSP.add("AGRICULTURAL_WORKERS");
        CSP.add("CRAFTSMEN_AND_ARTISANS");
        CSP.add("TRADERS_AND_MERCHANTS");
        CSP.add("CIVIL_SERVANTS_AND_PROFESSIONALS");
        CSP.add("UNSKILLED_LABORERS");
        return CSP;
    }

}
