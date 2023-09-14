package com.example.prog4.mapper;

import com.example.prog4.controller.viewModel.ViewCompany;
import com.example.prog4.model.Company;
import com.example.prog4.model.PhoneNumber;
import com.example.prog4.repository.PhoneNumberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

@AllArgsConstructor
@Component
public class CompanyMapper {
    private PhoneNumberRepository phoneNumberRepository;

    public Company toCompany(ViewCompany view) throws IOException {
        byte[] imageBytes = view.getLogo().getBytes();
        String encodedImage = Base64.getEncoder().encodeToString(imageBytes);
        ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
        PhoneNumber phoneNumber = PhoneNumber.builder().phoneNumber(view.getPhoneNumber()).build();
        if(phoneNumber != null){
            phoneNumberRepository.save(phoneNumber);
        }
        phoneNumbers.add(phoneNumber);
        return Company.builder()
                .id(view.getId())
                .name(view.getName())
                .logo(encodedImage)
                .rcs(view.getRcs())
                .nif(view.getNif())
                .stat(view.getStat())
                .email(view.getEmail())
                .phoneNumbers(phoneNumbers)
                .slogan(view.getSlogan())
                .address(view.getAddress())
                .build();
    }
}
