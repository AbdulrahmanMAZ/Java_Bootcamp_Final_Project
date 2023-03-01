package com.abdulrahman.final_Project.AdvisorDetails;

import com.abdulrahman.final_Project.Advisor.Advisor;
import com.abdulrahman.final_Project.Start_up.StartUp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor


public class AdvisorDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "First name should not be empty")
    @NotNull(message = "firstName field should not be null")
    private String firstName;

    @NotEmpty(message = "Last name should not be empty")
    @NotNull(message = "Last name field should not be null")
    private String lastName;

    @NotEmpty
    @Pattern(regexp = "^0\\d{9}$",message = "Must be 0XXXXXXXXX")
    @Column(columnDefinition = "varchar(10) unique not null")
    private String phoneNumber;
    @Pattern(regexp = "^.+@.+\\.\\w{2,}$" , message = "must be in this form XXX@XX.XX")
    @Column(columnDefinition = "varchar(40) unique not null")
    private String email;



    @PositiveOrZero
    private Integer Wallet=0;
    @OneToOne
    @MapsId
    @JsonIgnore
    private Advisor advisor;


    private String livesIn;

    @Pattern(regexp = "^MALE||FEMALE",message = "Gender must be either a MALE or FEMALE")
    @Column(columnDefinition = "varchar(15) not null check (gender='MALE' || gender='FEMALE'  )")
    private String gender;

    private String Overview;





}
