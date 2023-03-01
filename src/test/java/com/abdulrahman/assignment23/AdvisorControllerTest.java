package com.abdulrahman.assignment23;


import com.abdulrahman.final_Project.Advisor.Advisor;
import com.abdulrahman.final_Project.Advisor.AdvisorController;
import com.abdulrahman.final_Project.Advisor.AdvisorRepo;
import com.abdulrahman.final_Project.Advisor.AdvisorService;
import com.abdulrahman.final_Project.Appointments.Appointments;
import com.abdulrahman.final_Project.Auth.AuthController;
import com.abdulrahman.final_Project.Auth.AuthService;
import com.abdulrahman.final_Project.DTO.AdvisorRegisterFormDTO;
import com.abdulrahman.final_Project.DTO.Response;
import com.abdulrahman.final_Project.DTO.StartUpRegisterFormDTO;
import com.abdulrahman.final_Project.Feedback.Feedback;
import com.abdulrahman.final_Project.MyUser.MyUser;
import com.abdulrahman.final_Project.Review.Review;
import com.abdulrahman.final_Project.Start_up.StartUp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class AdvisorControllerTest {

    @InjectMocks
    AdvisorController advisorController;
    @Mock
    AuthService authService;
    @Mock
    AdvisorRepo advisorRepo;
    @Mock
    AdvisorService advisorService;
    MyUser myUser;
    Advisor advisor1,advisor2,advisor3;
    StartUp startUp1;
    Appointments appointment;
    String time;
    LocalDateTime dateTime;

    @BeforeEach
    void setUp() {
//        appointments=new ArrayList<>();
//        appointments.add(appointment);
//        reviews=new ArrayList<>();
//        review = new Review();
//        review.setAdvisor(advisor1);
//        review.setStartUp(startUp1);
//        review.setAppointment(appointment);
//        review.setRating(3);
//        review.setTitle("Great");
//        review.setContent("Great");
//        reviews.add(review);
        myUser=new MyUser(null,"Abdulrahman","12345","ADMIN", null,null);
        startUp1 = new StartUp(null, "startUp.name()", "startUp.industry()",null, 0,null,null,null,null);
        advisor1 = new Advisor(null,"advisor.firstName()","advisor.speciality()",50,0,new BigDecimal(1),null,null,null,null,null);
        time = "2017-01-13T17:09:42.411";
        dateTime = LocalDateTime.parse(time);
        appointment = new Appointments(null,dateTime,"Completed",0,false,null,null,null);


//        user=new MyUser(null,"Abdulrahman","12345","USER", null);


//        todo1=new StartUp(null,"todo1",user);
//        todo2=new StartUp(null,"todo2",user);
//        todo3=new StartUp(null,"todo3",null);

///
    }
    @Test
    public void rescheduleAppointment(){

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


//        AdvisorRegisterFormDTO advisor =new AdvisorRegisterFormDTO("Abdulrahman","12345","Abdulrahman@gmail.com", "Phsyco",40,"Male","Abdulrahman","Salem","0525136537");
        ResponseEntity<Response> responseEntity = advisorController.rescheduleAppointment(appointment.getId(),startUp1.getId(),myUser,time);
        Assertions.assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
        Response response = new Response("Your appointment has been Updated",200);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(response);

    }
//
    @Test
    public void acceptAppointmentTest(){

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


//        StartUpRegisterFormDTO startUp =new StartUpRegisterFormDTO("Abdulrahman","12345","Abdulrahman@gmail.com", "Phsyco","Owner","Ryiadh","IT","0525136537");
        ResponseEntity<Response> responseEntity = advisorController.AcceptAppointment(appointment.getId(),startUp1.getId(),myUser);
        Assertions.assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
        Response response = new Response("This appointment has been Accepted and the bill has been sent to the start-up",200);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(response);

    }
@Test
    public void completeAppointmentTest(){

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    ResponseEntity<Response> responseEntity = advisorController.completeAppointment(appointment.getId(),startUp1.getId(),myUser,new Feedback());
        Assertions.assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
        Response response = new Response("This appointment has been marked complete and feed back has been sent",200);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(response);

    }
//






}