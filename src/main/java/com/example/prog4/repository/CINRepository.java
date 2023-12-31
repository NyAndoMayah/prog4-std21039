package com.example.prog4.repository;

import com.example.prog4.model.CIN;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CINRepository extends JpaRepository<CIN, Long> {
}
