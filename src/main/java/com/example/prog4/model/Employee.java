package com.example.prog4.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String matricule;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String entranceDate;
    private String departureDate;
    @Column(columnDefinition = "text")
    private String picture;
    @OneToMany
    private List<PhoneNumber> phoneNumber;
    private String address;
    private String personnalEmail;
    private String professionnalEmail;
    @OneToOne
    private CIN CIN;
    private String position;
    private int childrenNumber;
    private CSP categorieSocioProfesional;
    private int cnaps;
    private Sex sex;
    private String username;
    private String password;
    public enum Sex {
        H,F;
    }
    public enum CSP {
        AGRICULTURAL_WORKERS,
        CRAFTSMEN_AND_ARTISANS,
        TRADERS_AND_MERCHANTS,
        CIVIL_SERVANTS_AND_PROFESSIONALS,
        UNSKILLED_LABORERS;
    }
}
