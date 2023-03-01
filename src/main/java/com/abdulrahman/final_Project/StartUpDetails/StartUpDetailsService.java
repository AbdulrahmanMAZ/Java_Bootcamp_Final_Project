package com.abdulrahman.final_Project.StartUpDetails;

import com.abdulrahman.final_Project.Appointments.Appointments;
import com.abdulrahman.final_Project.Appointments.AppointmentsRepo;
import com.abdulrahman.final_Project.DTO.LocationDTO;
import com.abdulrahman.final_Project.Start_up.StartUp;
import com.abdulrahman.final_Project.Start_up.StartUpRepo;
import com.abdulrahman.final_Project.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StartUpDetailsService {
    final private StartUpDetailsRepo startUpDetailsRepo;
    final private AppointmentsRepo appointmentsRepo;


    final private StartUpRepo startUpRepo;



    public List<StartUpDetails> getStartUpDetails(){
        return startUpDetailsRepo.findAll();
    }
    public void addStartUpDetails(StartUpDetails store){
        startUpDetailsRepo.save(store);

    }
    public void editStartUpDetails(Integer id, StartUpDetails store){
        StartUpDetails temp = startUpDetailsRepo.findStartUpDetailsById(id);
        if (temp == null) {
            throw new ApiException("Not found");
        }
//        temp.setName(store.getName());
        temp.setEmail(store.getEmail());
        temp.setBasedIn(store.getBasedIn());
        temp.setOwnerName(store.getOwnerName());
        temp.setPhoneNumber(store.getPhoneNumber());
        startUpDetailsRepo.save(temp);

    }
    public void deleteStartUpDetails(Integer id){
        StartUpDetails temp = startUpDetailsRepo.findStartUpDetailsById(id);
        if (temp == null) {
            throw new ApiException("Not found");
        }
        startUpDetailsRepo.delete(temp);

    }

}
