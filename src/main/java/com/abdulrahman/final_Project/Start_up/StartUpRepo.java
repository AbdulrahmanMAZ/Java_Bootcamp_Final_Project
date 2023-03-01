package com.abdulrahman.final_Project.Start_up;

import com.abdulrahman.final_Project.Appointments.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StartUpRepo extends JpaRepository<StartUp,Integer> {
    StartUp findStartUpById(Integer id);


}
