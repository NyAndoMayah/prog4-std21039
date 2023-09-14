package com.example.prog4.service;

import com.example.prog4.model.PhoneNumber;
import com.example.prog4.repository.PhoneNumberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PhoneNumberService {
    private PhoneNumberRepository phoneNumberRepository;

    public PhoneNumber getByPhoneNumber(String phone){
        return phoneNumberRepository.getPhoneNumberByPhoneNumber(phone) != null ? phoneNumberRepository.getPhoneNumberByPhoneNumber(phone) : null;
    }

}
