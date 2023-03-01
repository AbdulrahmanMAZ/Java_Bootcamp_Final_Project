package com.abdulrahman.final_Project.Auth;

import com.abdulrahman.final_Project.DTO.Response;
import com.abdulrahman.final_Project.MyUser.MyUser;
import com.abdulrahman.final_Project.DTO.AdvisorRegisterFormDTO;
import com.abdulrahman.final_Project.DTO.StartUpRegisterFormDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register/advisor")
    public ResponseEntity<Response> registerAdvisor(@Valid @RequestBody AdvisorRegisterFormDTO myUser){
        authService.registerAdvisor(myUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Advisor has Registered successfully",201));
    }
    @PostMapping("/register/start-up")
    public ResponseEntity<Response> registerStartUp(@Valid @RequestBody StartUpRegisterFormDTO myUser){
        authService.registerStartUp(myUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Start up has Registered successfully",201));
    }
    @PostMapping("/login")
    public ResponseEntity login(@AuthenticationPrincipal MyUser myUser){

        if (myUser.getRole().equals("ADMIN")) {
            return ResponseEntity.status(HttpStatus.OK).body("Welcome Admin");
        } else if (myUser.getRole().equals("STARTUP")) {
            return ResponseEntity.status(HttpStatus.OK).body("Welcome Start Up");
        } else if (myUser.getRole().equals("ADVISOR")) {
            return ResponseEntity.status(HttpStatus.OK).body("Welcome Advisor");
        }else if(myUser == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong credential");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body("Welcome You");
        }

        }

}

