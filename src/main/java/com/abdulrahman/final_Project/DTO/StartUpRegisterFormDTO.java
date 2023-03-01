package com.abdulrahman.final_Project.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record StartUpRegisterFormDTO(
//        @NotEmpty
//        @NotNull String username,
        @NotEmpty
        @Pattern(regexp =  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
        //1. Password must contain at least one digit [0-9].
        //2. Password must contain at least one lowercase Latin character [a-z].
        //3. Password must contain at least one uppercase Latin character [A-Z].
        // 4  Password must contain at least one special character like ! @ # & ( ).
        // 5. Password must contain a length of at least 8 characters and a maximum of 20 characters.
        String password,
        @Pattern(regexp = "^.+@.+\\.\\w{2,}$" ,message = "must be in this form XXX@XX.XX")
        String email,
        @NotEmpty
        @NotNull String nameOfStartUp,
        @NotEmpty
        @NotNull String ownerName,
        @NotEmpty
        @NotNull String basedIn,
        String industry,
        @NotEmpty
        @Pattern(regexp = "^0\\d{9}$",message = "Must be 0XXXXXXXXX")
        String phoneNumber){}