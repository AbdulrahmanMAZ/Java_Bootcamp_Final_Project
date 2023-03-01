package com.abdulrahman.final_Project.Advisor;

import com.abdulrahman.final_Project.AdvisorDetails.AdvisorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvisorRepo extends JpaRepository<Advisor,Integer> {


    Advisor findAdvisorById(Integer advisorID);
    List<Advisor> findAllBySpeciality(String speciality);
    List<Advisor> findByOrderByRatingDesc();
}
