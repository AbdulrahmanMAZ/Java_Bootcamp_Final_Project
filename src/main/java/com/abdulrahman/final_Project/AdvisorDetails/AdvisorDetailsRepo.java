package com.abdulrahman.final_Project.AdvisorDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvisorDetailsRepo extends JpaRepository<AdvisorDetails,Integer> {
    AdvisorDetails findAdvisorDetailsById(Integer id);
    List<AdvisorDetails> findAllByFirstNameContainsIgnoreCaseAndLastNameContainsIgnoreCase(String firstName,String lastName);

}
