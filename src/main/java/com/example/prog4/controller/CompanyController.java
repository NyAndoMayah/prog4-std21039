package com.example.prog4.controller;

import com.example.prog4.controller.viewModel.ViewCompany;
import com.example.prog4.mapper.CompanyMapper;
import com.example.prog4.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class CompanyController {

        private final CompanyService companyService;

        private CompanyMapper mapper;


        @GetMapping("/company")
        public String showCompanyForm(Model model) {
            model.addAttribute("company", new ViewCompany());
            return "companyForm";
        }

        @PostMapping("/company")
        public String saveCompany(@ModelAttribute ViewCompany company) throws IOException {
            companyService.saveCompany(mapper.toCompany(company));
            return "redirect:/";
        }
}