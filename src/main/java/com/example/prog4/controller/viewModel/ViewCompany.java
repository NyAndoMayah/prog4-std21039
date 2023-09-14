package com.example.prog4.controller.viewModel;

import com.example.prog4.model.PhoneNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ViewCompany {
    private Long id;
    private String name;
    private String description;
    private String email;
    private String slogan;
    private String address;
    private String phoneNumber;
    private String nif;
    private String stat;
    private String rcs;
    private MultipartFile logo;
}
