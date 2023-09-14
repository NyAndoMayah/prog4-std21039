package com.example.prog4.controller;
import com.example.prog4.controller.viewModel.ViewEmployee;
import com.example.prog4.mapper.EmployeeMapper;
import com.example.prog4.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CsvExportController {

    private EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;

    public CsvExportController(EmployeeMapper employeeMapper, EmployeeService employeeService) {
        this.employeeMapper = employeeMapper;
        this.employeeService = employeeService;
    }

    @GetMapping("/export-to-csv")
    public void exportToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"employees.csv\"");

        List<ViewEmployee> employees = employeeService.getEmployees().stream()
                .map(employee -> employeeMapper.toViewEmployee(employee)).toList();

        PrintWriter writer = response.getWriter();

        for (ViewEmployee employee : employees) {
            writer.println(employeeToCsvString(employee));
        }
        writer.flush();
        writer.close();
    }

    private List<String> employeeToCsvString(ViewEmployee employee) {
        List<String> employees = new ArrayList<>();
        employees.add("Matricule: " + employee.getMatricule());
        employees.add("Prenoms: " + employee.getFirstName());
        employees.add("Nom: " + employee.getLastName());
        employees.add("Numero de telephone: " + employee.getPhoneNumber());
        employees.add("Date de naissance: " + employee.getBirthDate());
        employees.add("Addresse: " + employee.getAddress());
        employees.add("Position: " + employee.getPosition());
        employees.add("Date d'embauche: " + employee.getEntranceDate());
        employees.add("Date de sortie: " + employee.getDepartureDate());
        employees.add("Mail personnel: " + employee.getEmailPerso());
        employees.add("Mail professionnnel " + employee.getEmailPro());


        return employees;

    }
}
