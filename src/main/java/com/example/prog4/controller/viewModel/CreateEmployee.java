package com.example.prog4.controller.viewModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployee {
    private int id;
    private String matricule;
    private String firstName;
    private String lastName;
    private String birthDate;
    private MultipartFile picture;
    private String countryCode;
    private String phoneNumber;
    private String address;
    private String emailPerso;
    private String emailPro;
    private int CINNUmber;
    private String CINPLace;
    private String CINDate;
    private String position;
    private int childrenNumber;
    private String categorieSocioProfessionnelle;
    private int cnapsNumber;
    private String entranceDate;
    private String departureDate;
    private String sexe;
    private String username;
    private String password;
}
