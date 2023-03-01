package com.abdulrahman.final_Project.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public record AdvisorRegisterFormDTO(
//        @NotEmpty
//                                 @NotNull String username,
         @NotEmpty
         @NotNull
         @Pattern(regexp =  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
        //1. Password must contain at least one digit [0-9].
        //2. Password must contain at least one lowercase Latin character [a-z].
        //3. Password must contain at least one uppercase Latin character [A-Z].
        //4  Password must contain at least one special character like ! @ # & ( ).
        //5. Password must contain a length of at least 8 characters and a maximum of 20 characters.
         String password,
         @Pattern(regexp = "^.+@.+\\.\\w{2,}$" ,message = "must be in this form XXX@XX.XX")
         String email,
         @Pattern(regexp = "^FINANCE||MANAGEMENT||MARKETING$",message = "Speciality must be one of the following: 1. FINANCE. 2. MANAGEMENT 3. MARKETING")
         @Column(columnDefinition = "varchar(15) not null check (speciality='FINANCE' || speciality='MANAGEMENT'|| speciality='MARKETING'  )")

         String speciality,
         @PositiveOrZero @NotNull Integer feePerHour,

         @Pattern(regexp = "^MALE||FEMALE",message = "Gender must be either a MALE or FEMALE")
         @Column(columnDefinition = "varchar(15) not null check (gender='MALE' || gender='FEMALE'  )")
         String gender,
         @NotEmpty
         @NotNull
         String firstName,
         @NotEmpty
         @NotNull
         String lastName,

         @NotEmpty
         @Pattern(regexp = "^0\\d{9}$",message = "Must be 0XXXXXXXXX")
         String phoneNumber
                                ){}