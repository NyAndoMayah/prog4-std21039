package com.example.prog4.repository;

import com.example.prog4.model.PhoneNumber;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {
    PhoneNumber getPhoneNumberByPhoneNumber(String PhoneNumber);
}
