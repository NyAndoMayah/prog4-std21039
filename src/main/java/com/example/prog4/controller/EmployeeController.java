package com.example.prog4.controller;

import com.example.prog4.controller.viewModel.CreateEmployee;
import com.example.prog4.controller.viewModel.ViewEmployee;
import com.example.prog4.mapper.EmployeeMapper;
import com.example.prog4.model.Company;
import com.example.prog4.service.AuthService;
import com.example.prog4.service.CompanyService;
import com.example.prog4.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.ForbiddenException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@Slf4j
public class EmployeeController {
    private EmployeeService service;
    private EmployeeMapper mapper;
    private CompanyService companyService;
    private AuthService authService;


    @GetMapping("/employees")
    public String index(Model model, HttpSession session) {
        try{
            authService.isLogged(session);
        } catch (Exception e){
            throw new ForbiddenException();
        }
        List<ViewEmployee> viewEmployees = service.getEmployees()
                .stream().map(employee -> mapper.toViewEmployee(employee)).toList();
        List<String> categoriesList = service.getAllCSP();
        List<Company> companies = companyService.getCompanies();
        if(companies.size() == 0 ){
            companies.add(Company.builder()
                    .name("NUMER")
                    .build());
        }

        model.addAttribute("company", companies.get(companies.size() - 1));

        model.addAttribute("categoriesList", categoriesList);
        model.addAttribute("employees", viewEmployees);
        model.addAttribute("newEmployee", new CreateEmployee());
        return "index";
    }


    @PostMapping("/addEmployee")
    public String addEmployee(Model model, @ModelAttribute("newEmployee") CreateEmployee newEmployee, HttpSession session) throws IOException {
        try{
            authService.isLogged(session);
        } catch (Exception e){
            throw new ForbiddenException();
        }
        service.createEmployee(mapper.toDomain(newEmployee));
        model.addAttribute("newEmployee", new CreateEmployee());
        return "redirect:/employees";
    }

    @GetMapping("/employees/{id}/edit")
    public String editEmployee(@PathVariable("id") Integer id, Model model, HttpSession session) {
        try{
            authService.isLogged(session);
        } catch (Exception e){
            throw new ForbiddenException();
        }
        List<Company> companies = companyService.getCompanies();
        if(companies.size() > 0) {
            model.addAttribute("company", companies.get(0));
        }
        CreateEmployee employee = mapper.toCreateEmployee(service.getById(id));
        model.addAttribute("employee", employee);
        return "employeeEdit";
    }

    @PostMapping("/employees/{id}/edit")
    public String updateEmployee(Model model, @PathVariable("id") Integer id, @ModelAttribute("employee") CreateEmployee updatedEmployee, HttpSession session) throws IOException {
        try{
            authService.isLogged(session);
        } catch (Exception e){
            throw new ForbiddenException();
        }
        service.createEmployee(mapper.toDomain(updatedEmployee));
        model.addAttribute("newEmployee", new CreateEmployee());
        return "redirect:/employees";
    }


    @GetMapping("/employeesInfo/{id}")
    public String getEmployeeById(Model model, @PathVariable Integer id, HttpSession session){
        try{
            authService.isLogged(session);
        } catch (Exception e){
            throw new ForbiddenException();
        }
        List<Company> companies = companyService.getCompanies();
        if(companies.size() > 0){
            model.addAttribute("company", companies.get(0));
        }
        if(id != null){
        ViewEmployee employee =  mapper.toViewEmployee(service.getById(id));
         model.addAttribute("employee", employee);
        }
        return "employeeCard";
    }

    @PostMapping("/search")
    public String SearchPage(@RequestParam(value = "keyword", required = false) String word,
                             @RequestParam(value = "startDate", required = false) String startDateStr,
                             @RequestParam(value = "endDate", required = false) String endDateStr,
                             Model model, HttpSession session) {
        try{
            authService.isLogged(session);
        } catch (Exception e){
            throw new ForbiddenException();
        }

        log.info("Word to search:{} " , word);

        List<ViewEmployee> employees = service.searchByKeyword(word, startDateStr, endDateStr).stream()
                .map(employee -> mapper.toViewEmployee(employee))
                .collect(Collectors.toList());

        List<Company> companies = companyService.getCompanies();
        if(companies.size() == 0 ){
            companies.add(Company.builder()
                    .name("NUMER")
                    .build());
        }

        model.addAttribute("company", companies.get(companies.size() - 1));

        model.addAttribute("newEmployee", new CreateEmployee());
        model.addAttribute("employees", employees);
        return "index";
    }


    @PostMapping("/sort")
    public String SortPage(@RequestParam(value = "sortAttribute", defaultValue = "lastName") String sortAttribute,
                           @RequestParam(value = "sortOrder", defaultValue = "asc") String sortOrder,
                           Model model, HttpSession session) {

        try{
            authService.isLogged(session);
        } catch (Exception e){
            throw new ForbiddenException();
        }

        List<ViewEmployee> employees = service.sort(sortOrder, sortAttribute).stream().map(employee -> mapper.toViewEmployee(employee)).toList();

        List<Company> companies = companyService.getCompanies();
        if(companies.size() == 0 ){
            companies.add(Company.builder()
                    .name("NUMER")
                    .build());
        }

        model.addAttribute("company", companies.get(companies.size() - 1));

        model.addAttribute("newEmployee", new CreateEmployee());
        model.addAttribute("employees", employees);

        model.addAttribute("sortAttribute", sortAttribute);
        model.addAttribute("sortOrder", sortOrder);
        return "index";
    }
}

