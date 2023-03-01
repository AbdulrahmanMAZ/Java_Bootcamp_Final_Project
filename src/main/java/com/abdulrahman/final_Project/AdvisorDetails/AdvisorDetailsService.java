package com.abdulrahman.final_Project.AdvisorDetails;

import com.abdulrahman.final_Project.Appointments.AppointmentsRepo;

import com.abdulrahman.final_Project.Start_up.StartUpRepo;
import com.abdulrahman.final_Project.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvisorDetailsService {
    final private AdvisorDetailsRepo advisorDetailsRepo;
    final private AppointmentsRepo appointmentsRepo;


    final private StartUpRepo startUpRepo;



    public List<AdvisorDetails> getAdvisorDetails(){
        return advisorDetailsRepo.findAll();
    }
    public void addAdvisorDetails(AdvisorDetails store){
        advisorDetailsRepo.save(store);

    }
    public void editAdvisorDetails(Integer id, AdvisorDetails store){
        AdvisorDetails temp = advisorDetailsRepo.findAdvisorDetailsById(id);
        if (temp == null) {
            throw new ApiException("Not found");
        }
        temp.setFirstName(store.getFirstName());
        temp.setLastName(store.getLastName());
        temp.setLivesIn(store.getLivesIn());
        temp.setPhoneNumber(store.getPhoneNumber());
        temp.setOverview(store.getOverview());
        temp.setGender(store.getGender());
        temp.setEmail(store.getEmail());
        advisorDetailsRepo.save(temp);

    }
    public void deleteAdvisorDetails(Integer id){
        AdvisorDetails temp = advisorDetailsRepo.findAdvisorDetailsById(id);
        if (temp == null) {
            throw new ApiException("Not found");
        }
        advisorDetailsRepo.delete(temp);

    }

}
