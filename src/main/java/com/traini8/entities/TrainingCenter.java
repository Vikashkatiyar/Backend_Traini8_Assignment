package com.traini8.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingCenter {

    //id column will be stored in TrainingCenter table as PRIMARY KEY
    @Id
    private String id;

    private String centerName;
    private String centerCode;

    //This is to setup one to one relationship between TrainingCenter and Address table
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    private int studentCapacity;

    //By using @ElementCollection , the SpringDataJpa will create a table that will store the data from field coursesOffered  and create
    //a mapping between TrainingCenter and table that will store the coursesOffered data .
    @ElementCollection
    private List<String> coursesOffered;
    
    private LocalDateTime createdOn;
    private String email;
    private String contactPhone;
}
