package com.abdulrahman.assignment23;


import com.abdulrahman.final_Project.Auth.AuthController;
import com.abdulrahman.final_Project.Auth.AuthService;
import com.abdulrahman.final_Project.DTO.AdvisorRegisterFormDTO;
import com.abdulrahman.final_Project.DTO.Response;
import com.abdulrahman.final_Project.DTO.StartUpRegisterFormDTO;
import com.abdulrahman.final_Project.MyUser.MyUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    AuthController authController;
    @Mock
    AuthService authService;



    @Test
    public void registerAdvisorControllerTest(){

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        AdvisorRegisterFormDTO advisor =new AdvisorRegisterFormDTO("Abdulrahman","12345","Abdulrahman@gmail.com", "Phsyco",40,"Male","Abdulrahman","Salem","0525136537");
        ResponseEntity<Response> responseEntity = authController.registerAdvisor(advisor);
        Assertions.assertThat(responseEntity.getStatusCode().value()).isEqualTo(201);
        Response response = new Response("Advisor has Registered successfully",201);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(response);

}
@Test
    public void registerStartUpControllerTest(){

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        StartUpRegisterFormDTO startUp =new StartUpRegisterFormDTO("Abdulrahman","12345","Abdulrahman@gmail.com", "Phsyco","Owner","Ryiadh","IT","0525136537");
        ResponseEntity<Response> responseEntity = authController.registerStartUp(startUp);
        Assertions.assertThat(responseEntity.getStatusCode().value()).isEqualTo(201);
        Response response = new Response("Start up has Registered successfully",201);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(response);

    }







}