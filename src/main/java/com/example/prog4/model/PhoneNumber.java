package com.example.prog4.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "phone_number", uniqueConstraints = @UniqueConstraint(columnNames = { "countryCode", "phoneNumber" }))
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String countryCode;
    private String phoneNumber;
}
