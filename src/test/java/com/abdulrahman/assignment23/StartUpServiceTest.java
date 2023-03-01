package com.abdulrahman.assignment23;


import com.abdulrahman.final_Project.Advisor.Advisor;
import com.abdulrahman.final_Project.Advisor.AdvisorRepo;
import com.abdulrahman.final_Project.AdvisorDetails.AdvisorDetails;
import com.abdulrahman.final_Project.AdvisorDetails.AdvisorDetailsRepo;
import com.abdulrahman.final_Project.Appointments.Appointments;
import com.abdulrahman.final_Project.Appointments.AppointmentsRepo;
import com.abdulrahman.final_Project.Appointments.AppointmentsService;
import com.abdulrahman.final_Project.Auth.AuthRepo;
import com.abdulrahman.final_Project.Auth.AuthService;
import com.abdulrahman.final_Project.DTO.AdvisorRegisterFormDTO;
import com.abdulrahman.final_Project.DTO.StartUpRegisterFormDTO;
import com.abdulrahman.final_Project.MyUser.MyUser;
import com.abdulrahman.final_Project.Review.Review;
import com.abdulrahman.final_Project.Review.ReviewRepo;
import com.abdulrahman.final_Project.Review.ReviewService;
import com.abdulrahman.final_Project.StartUpDetails.StartUpDetails;
import com.abdulrahman.final_Project.StartUpDetails.StartUpDetailsRepo;
import com.abdulrahman.final_Project.Start_up.StartUp;
import com.abdulrahman.final_Project.Start_up.StartUpRepo;
import com.abdulrahman.final_Project.Start_up.StartUpService;
import com.abdulrahman.final_Project.exception.ApiException;
import com.abdulrahman.final_Project.helper.MyTimeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StartUpServiceTest {

    @InjectMocks
    StartUpService startUpService;
    @Mock
    StartUpRepo startUpRepo;
    @Mock
    AuthRepo authRepository;

    @Mock
    AppointmentsRepo appointmentsRepo;
    @Mock
    AppointmentsService appointmentsService;
    @InjectMocks
    AuthService authService;

    @Mock
    AdvisorRepo advisorRepo;

    @Mock
    AdvisorDetailsRepo advisorDetailsRepo;



    @Mock
    StartUpDetailsRepo startUpDetailsRepo;

    @Mock
    MyTimeService myTimeService;
    @Mock
    ReviewService reviewService;

    @Mock
    ReviewRepo reviewRepo;

    StartUpRegisterFormDTO startUp;

    AdvisorRegisterFormDTO advisor;
    MyUser myUser1;
    Advisor advisor1,advisor2,advisor3;
    StartUp startUp1;
    AdvisorDetails advisorDetails;
    StartUpDetails startUpDetails;
    Appointments appointment;
    List<Appointments> appointments;
    List<Advisor> advisors;
    MyUser user;
    Review review;
    List<Review> reviews;
    StartUp todo1,todo2,todo3;
    String time;
    LocalDateTime dateTime;
//    List<StartUp> todos;

    @BeforeEach
    void setUp() {
        appointments=new ArrayList<>();
        appointments.add(appointment);
        reviews=new ArrayList<>();
        review = new Review();
//        review.setAdvisor(advisor1);
//        review.setStartUp(startUp1);
//        review.setAppointment(appointment);
        review.setRating(3);
        review.setTitle("Great");
        review.setContent("Great");
        reviews.add(review);
        startUp1 = new StartUp(null, "startUp.name()", "startUp.industry()",appointments, 0,null,null,null,null);
        advisor1 = new Advisor(null,"advisor.firstName()","advisor.speciality()",50,0,new BigDecimal(1),null,appointments,null,null,null);
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
    public void bookAppointmentTest(){
        when(startUpRepo.findStartUpById(startUp1.getId())).thenReturn(startUp1);
        when(advisorRepo.findAdvisorById(advisor1.getId())).thenReturn(advisor1);
        when(appointmentsRepo.findAppointmentByDateTimeAndAdvisor_IdAndStatus(dateTime,advisor1.getId(),"Accepted")).thenReturn(null);
        when(appointmentsRepo.findAppointmentByDateTimeAndStartUp_IdAndStatusNot(dateTime,startUp1.getId(),"Completed")).thenReturn(null);
        int hours = dateTime.getHour();
        int minutes = dateTime.getMinute();
        String hours_minutes = hours+":"+minutes;
        when(myTimeService.validTime(hours_minutes)).thenReturn(true);

        startUpService.bookAppointment(startUp1.getId(),advisor1.getId(),time);


       verify(startUpRepo,times(1)).findStartUpById(startUp1.getId());

    }
    @Test
    public void pendingAppointmentsTest(){
        when(startUpRepo.findStartUpById(startUp1.getId())).thenReturn(startUp1);
        when(appointmentsRepo.findAllByStartUpAndStatus(startUp1,"Pending")).thenReturn(appointments);

        List<Appointments> appointments1 = startUpService.pendingAppointments(startUp1.getId());
        Assertions.assertEquals(appointments1,appointments);
        verify(startUpRepo,times(1)).findStartUpById(startUp1.getId());
        verify(appointmentsRepo,times(1)).findAllByStartUpAndStatus(startUp1,"Pending");

    }
//
    @Test
    public void postReviewTest(){
        when(appointmentsRepo.findAppointmentsByIdAndAdvisor_IdAndStartUp_Id(appointment.getId(),advisor1.getId(),startUp1.getId())).thenReturn(appointment);
        when(advisorRepo.findAdvisorById(advisor1.getId())).thenReturn(advisor1);
        when(startUpRepo.findStartUpById(startUp1.getId())).thenReturn(startUp1);
        when(reviewRepo.findAllByAdvisor_Id(advisor1.getId())).thenReturn(reviews);
        startUpService.postReview(appointment.getId(),startUp1.getId(),advisor1.getId(),review);
        verify(startUpRepo,times(1)).findStartUpById(startUp1.getId());
        verify(appointmentsRepo,times(1)).findAppointmentsByIdAndAdvisor_IdAndStartUp_Id(appointment.getId(),advisor1.getId(),startUp1.getId());
    }
//
//    @Test
//    public void updateStartUpTest(){
//
//        when(todoRepository.findStartUpById(todo1.getId())).thenReturn(todo1);
//        todoService.updateStartUp(user.getId(),todo1.getId(),todo2);
//        verify(todoRepository,times(1)).findStartUpById(todo1.getId());
//        verify(todoRepository,times(1)).save(todo1);
//
//    }
//
//    @Test
//    public void deleteStartUpTest(){
//
//        when(todoRepository.findStartUpById(todo1.getId())).thenReturn(todo1);
//        todoService.removeStartUp(user.getId(),todo1.getId());
//        verify(todoRepository,times(1)).findStartUpById(todo1.getId());
//        verify(todoRepository,times(1)).delete(todo1);
//
//    }





}