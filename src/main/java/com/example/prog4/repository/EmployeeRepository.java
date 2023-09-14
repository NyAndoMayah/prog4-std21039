package com.example.prog4.repository;

import com.example.prog4.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "SELECT e.* FROM employee e " +
            "INNER JOIN employee_phone_number epn ON e.id = epn.employee_id " +
            "INNER JOIN phone_number pn ON epn.phone_number_id = pn.id " +
            "WHERE (LOWER(e.last_name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(e.first_name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR CAST(e.sexe AS VARCHAR) = :keyword " +
            "OR LOWER(CONCAT(pn.country_code, ' ', pn.phone_number)) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "OR ((:startDate IS NULL AND :endDate IS NULL) OR (e.hiring_date BETWEEN :startDate AND :endDate))",
            nativeQuery = true)
    List<Employee> searchByKeywordAndDateRange(@Param("keyword") String keyword,
                                               @Param("startDate") String startDate,
                                               @Param("endDate") String endDate);

    Employee findByUsernameAndPassword(String username, String password);
}
