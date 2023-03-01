package com.abdulrahman.final_Project.StartUpDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StartUpDetailsRepo extends JpaRepository<StartUpDetails,Integer> {
    StartUpDetails findStartUpDetailsById(Integer id);

}
