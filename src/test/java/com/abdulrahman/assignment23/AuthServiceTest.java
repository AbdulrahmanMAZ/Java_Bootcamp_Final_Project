package com.abdulrahman.assignment23;



import com.abdulrahman.final_Project.Advisor.Advisor;
import com.abdulrahman.final_Project.Advisor.AdvisorRepo;
import com.abdulrahman.final_Project.AdvisorDetails.AdvisorDetails;
import com.abdulrahman.final_Project.AdvisorDetails.AdvisorDetailsRepo;
import com.abdulrahman.final_Project.Auth.AuthRepo;
import com.abdulrahman.final_Project.Auth.AuthService;
import com.abdulrahman.final_Project.DTO.AdvisorRegisterFormDTO;
import com.abdulrahman.final_Project.DTO.StartUpRegisterFormDTO;
import com.abdulrahman.final_Project.MyUser.MyUser;
import com.abdulrahman.final_Project.StartUpDetails.StartUpDetails;
import com.abdulrahman.final_Project.StartUpDetails.StartUpDetailsRepo;
import com.abdulrahman.final_Project.Start_up.StartUp;
import com.abdulrahman.final_Project.Start_up.StartUpRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    AuthService authService;

    @Mock
    AuthRepo authRepository;
//    @Mock
//     authRepository;
    @Mock
    AdvisorRepo advisorRepo;

    @Mock
    AdvisorDetailsRepo advisorDetailsRepo;

    @Mock
    StartUpRepo startUpRepo;

    @Mock
    StartUpDetailsRepo startUpDetailsRepo;
    StartUpRegisterFormDTO startUp;

    AdvisorRegisterFormDTO advisor;
    MyUser myUser1;
    Advisor advisor1,advisor2,advisor3;
    StartUp startUp1;
    AdvisorDetails advisorDetails;
    StartUpDetails startUpDetails;

    List<Advisor> advisors;

    @BeforeEach
    void setUp() {
        advisor =new AdvisorRegisterFormDTO("Abdulrahman","12345","Abdulrahman@gmail.com", "Phsyco",40,"Male","Abdulrahman","Salem","0525136537");
        startUp =new StartUpRegisterFormDTO("Abdulrahman","12345","Abdulrahman@gmail.com", "Phsyco","Owner","Riyadh","IT","0525136537");

        //        String hashedPassword = new BCryptPasswordEncoder().encode(myUserData.password());
//        myUser.setPassword(hashedPassword);
        myUser1 = new MyUser(null,advisor.username(),advisor.password(),"ADVISOR",null,null);

//        MyUser myUser2 = new MyUser(1,advisor.username(),advisor.password(),"ADVISOR",null,null);
//        MyUser myUser3 = new MyUser(1,advisor.username(),advisor.password(),"ADVISOR",null,null);

        advisor1 = new Advisor(null,advisor.firstName(),advisor.speciality(),advisor.feePerHour(),0,new BigDecimal(1),null,null,null,null,null);
        startUp1 = new StartUp(null, startUp.name(), startUp.industry(),null, 0,null,null,null,null);

        //        advisor2=new Advisor(null,advisor.firstName(),advisor.speciality(),advisor.feePerHour(),0,new BigDecimal(1),myUser2,null,null,null,null);
//        advisor3=new Advisor(null,advisor.firstName(),advisor.speciality(),advisor.feePerHour(),0,new BigDecimal(1),myUser3,null,null,null,null);

        advisorDetails = new AdvisorDetails(null,advisor.firstName(),advisor.lastName(),advisor.phoneNumber(),advisor.email(),0,advisor1,null,"Male",null);
        startUpDetails = new StartUpDetails(null,startUp.ownerName(),startUp.name(),startUp.phoneNumber(),startUp.email(), startUp1,"Riyadh");

//        advisors=new ArrayList<>();
//        advisors.add(advisor1);
//        advisors.add(advisor2);
//        advisors.add(advisor3);
    }


    @Test
    public void registerAdvisorTest(){
        when(advisorRepo.save(advisor1)).thenReturn(advisor1);
        when(advisorDetailsRepo.save(advisorDetails)).thenReturn(advisorDetails);

        authService.registerAdvisor(advisor);
        verify(advisorDetailsRepo,times(1)).save(advisorDetails);

    }
    @Test
    public void registerStartUpTest(){
        when(startUpRepo.save(startUp1)).thenReturn(startUp1);
        when(startUpDetailsRepo.save(startUpDetails)).thenReturn(startUpDetails);

        authService.registerStartUp(startUp);
        verify(startUpDetailsRepo,times(1)).save(startUpDetails);

    }







}