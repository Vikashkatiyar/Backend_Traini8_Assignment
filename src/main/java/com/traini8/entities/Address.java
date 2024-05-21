package com.traini8.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    //id column will be stored in Address table as PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String detailedAddress;
    private String city;
    private String state;
    private String pincode;


}
