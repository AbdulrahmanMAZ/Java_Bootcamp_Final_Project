package com.abdulrahman.assignment23;


import com.abdulrahman.final_Project.Advisor.Advisor;
import com.abdulrahman.final_Project.Advisor.AdvisorController;
import com.abdulrahman.final_Project.Advisor.AdvisorRepo;
import com.abdulrahman.final_Project.Advisor.AdvisorService;
import com.abdulrahman.final_Project.Appointments.Appointments;
import com.abdulrahman.final_Project.Appointments.AppointmentsRepo;
import com.abdulrahman.final_Project.Auth.AuthRepo;
import com.abdulrahman.final_Project.Auth.AuthService;
import com.abdulrahman.final_Project.DTO.Response;
import com.abdulrahman.final_Project.Feedback.Feedback;
import com.abdulrahman.final_Project.MyUser.MyUser;
import com.abdulrahman.final_Project.Project_5Application;
import com.abdulrahman.final_Project.Start_up.StartUp;
import com.abdulrahman.final_Project.Start_up.StartUpRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
@DataJpaTest
@ContextConfiguration(classes = Project_5Application.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AppointmentsRepoTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
            @Mock
    AppointmentsRepo appointmentsRepo;
    @Mock
    AuthService authService;
    @Mock
    AdvisorRepo advisorRepo;

    @Mock
    StartUpRepo startUpRepo;
    @Mock
    AdvisorService advisorService;
    MyUser myUser;
    Advisor advisor1,advisor2,advisor3;
    StartUp startUp1;
    Appointments appointment;
    List<Appointments> appointments;
    String time;
    LocalDateTime dateTime;

    @BeforeEach
    void setUp() {

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

         //        advisorRepo.save(advisor1);
        time = "2025-01-13T17:09:42.411";
        dateTime = LocalDateTime.parse(time);
        appointment = new Appointments(null,dateTime,"Completed",0,false,null,null,null);
        appointments=new ArrayList<>();
        appointments.add(appointment);


    }
    @Test
    public void findAppointmentByIdTest(){
//      MyUser user = authRepo.save(new  MyUser(null,"Abdulrahman","12345","ADMIN", null,null));
//        Assertions.assertThat(authRepo.findMyUserByUsername("Abdulrahman")).isEqualTo(user);
        Appointments saved_appointment = appointmentsRepo.save(appointment);
        Assertions.assertThat(appointmentsRepo.findAppointmentById(appointment.getId())).isEqualTo(saved_appointment);
    }
    @Test
    public void findAppointmentByDateTimeAndAdvisor_IdAndStatusTest(){

        Advisor advisor = advisorRepo.save(advisor1);
        appointment.setAdvisor(advisor);
        Appointments saved_appointment = appointmentsRepo.save(appointment);
        Assertions.assertThat(appointmentsRepo.findAppointmentByDateTimeAndAdvisor_IdAndStatus(dateTime,advisor1.getId(),"Completed")).isEqualTo(saved_appointment);
    }

    @Test
    public void findAppointmentsByIdAndAdvisor_IdAndStartUp_IdTest(){

        Advisor saved_advisor = advisorRepo.save(advisor1);
        appointment.setAdvisor(saved_advisor);

        StartUp saved_startUp = startUpRepo.save(startUp1);
        appointment.setStartUp(saved_startUp);

        Appointments saved_appointment = appointmentsRepo.save(appointment);
        Assertions.assertThat(appointmentsRepo.findAppointmentsByIdAndAdvisor_IdAndStartUp_Id(saved_appointment.getId(),advisor1.getId(),startUp1.getId())).isEqualTo(saved_appointment);
    }
    @Test
    public void findAllByDateTimeAndStatusTest(){


         appointmentsRepo.save(appointment);
      appointmentsRepo.findAllByDateTimeAndStatus(dateTime,"Completed");
        Assertions.assertThat(appointmentsRepo.findAllByDateTimeAndStatus(dateTime,"Completed")).isEqualTo(appointments);
   }

    @Test
    public void findAllByStartUpAndStatusTest(){


        StartUp saved_startUp = startUpRepo.save(startUp1);
        appointment.setStartUp(saved_startUp);
        appointmentsRepo.save(appointment);

        Assertions.assertThat(appointmentsRepo.findAllByStartUpAndStatus(saved_startUp,"Completed")).isEqualTo(appointments);
    }


}