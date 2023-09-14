package com.example.prog4.mapper;

import com.example.prog4.controller.viewModel.CreateEmployee;
import com.example.prog4.controller.viewModel.ViewEmployee;
import com.example.prog4.model.CIN;
import com.example.prog4.model.Employee;
import com.example.prog4.model.PhoneNumber;
import com.example.prog4.repository.CINRepository;
import com.example.prog4.repository.PhoneNumberRepository;
import com.example.prog4.service.PhoneNumberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class EmployeeMapper {

    private PhoneNumberRepository phoneNumberRepository;
    private CINRepository cinRepository;
    private PhoneNumberService phoneNumberService;


    public CreateEmployee toCreateEmployee(Employee employee){
        return CreateEmployee.builder()
                .id(employee.getId())
                .matricule(employee.getMatricule())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .birthDate(employee.getBirthDate())
//                .picture(employee.getPicture())
                .phoneNumber(employee.getPhoneNumber().get(0).getPhoneNumber())
                .address(employee.getAddress())
                .emailPerso(employee.getPersonnalEmail())
                .emailPro(employee.getProfessionnalEmail())
                .CINDate(employee.getCIN().getCINDate())
                .CINDate(employee.getCIN() != null ? employee.getCIN().getCINDate() : null)
                .CINNUmber(employee.getCIN() != null ? Optional.ofNullable(employee.getCIN().getNumber()).orElse(null) : null)
                .CINPLace(employee.getCIN() != null ? employee.getCIN().getCINplace() : null)
                .position(employee.getPosition())
                .childrenNumber(employee.getChildrenNumber())
                .categorieSocioProfessionnelle(String.valueOf(employee.getCategorieSocioProfesional()))
                .cnapsNumber(employee.getCnaps())
                .entranceDate(employee.getEntranceDate())
                .departureDate(employee.getDepartureDate())
                .sexe(String.valueOf(employee.getSex()))
                .username(employee.getUsername())
                .password(employee.getPassword())
                .build();
    }
    public ViewEmployee toViewEmployee(Employee employee){
        return ViewEmployee.builder()
                .id(employee.getId())
                .CSP(String.valueOf(employee.getCategorieSocioProfesional()))
                .picture(employee.getPicture())
                .address(employee.getAddress())
                .sexe(String.valueOf(employee.getSex()))
                .emailPerso(employee.getPersonnalEmail())
                .emailPro(employee.getProfessionnalEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .birthDate(employee.getBirthDate())
                .CINDate(employee.getCIN() != null ? employee.getCIN().getCINDate() : null)
                .CINNumber(employee.getCIN() != null ? Optional.ofNullable(employee.getCIN().getNumber()).orElse(null) : null)
                .CINPLace(employee.getCIN() != null ? employee.getCIN().getCINplace() : null)
                .matricule(employee.getMatricule() != null ? employee.getMatricule() : "REF-" + UUID.randomUUID().toString())
                .position(employee.getPosition())
                .countryCode(employee.getPhoneNumber().get(0).getCountryCode() == null ? "" : employee.getPhoneNumber().get(0).getCountryCode())
                .phoneNumber(employee.getPhoneNumber().get(0).getPhoneNumber())
                .cnapsNumber(employee.getCnaps())
                .departureDate(employee.getDepartureDate())
                .entranceDate(employee.getEntranceDate())
                .matricule(employee.getMatricule())
                .username(employee.getUsername())
//                .password(employee.getPassword())
                .build();
    }

    public Employee toDomain(CreateEmployee rest) throws IOException {
        byte[] imageBytes = rest.getPicture().getBytes();
        String encodedImage = Base64.getEncoder().encodeToString(imageBytes);
        ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
        PhoneNumber phoneNumber = PhoneNumber.builder()
                .phoneNumber(rest.getPhoneNumber())
                .countryCode(rest.getCountryCode())
                .build();
        PhoneNumber actualPhone = phoneNumberService.getByPhoneNumber(rest.getPhoneNumber());
        if(phoneNumber != null || actualPhone == null && phoneNumber != actualPhone) {
            phoneNumberRepository.save(phoneNumber);
        }
        phoneNumbers.add(phoneNumber);
        CIN cin = CIN.builder()
                .number(rest.getCINNUmber())
                .CINDate(rest.getCINDate())
                .CINplace(rest.getCINPLace())
                .build();
        if(cin != null){
            cinRepository.save(cin);
        }
        log.info("CSP: " , rest.getCategorieSocioProfessionnelle());
        return Employee.builder()
                .id(rest.getId())
                .picture(encodedImage)
                .lastName(rest.getLastName())
                .firstName(rest.getFirstName())
                .birthDate(rest.getBirthDate())
                .phoneNumber(phoneNumbers)
                .CIN(cin)
                .address(rest.getAddress())
                .professionnalEmail(rest.getEmailPro())
                .personnalEmail(rest.getEmailPerso())
                .position(rest.getPosition())
                .cnaps(rest.getCnapsNumber())
                .sex(Employee.Sex.valueOf(rest.getSexe()))
                .departureDate(rest.getDepartureDate())
                .entranceDate(rest.getEntranceDate())
                .categorieSocioProfesional(Employee.CSP.valueOf(rest.getCategorieSocioProfessionnelle()))
                .username(rest.getUsername())
                .password(rest.getPassword())
                .build();
    }
}
