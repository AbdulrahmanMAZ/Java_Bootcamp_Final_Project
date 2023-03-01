package com.abdulrahman.final_Project.Appointments;

import com.abdulrahman.final_Project.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentsService {
    final private AppointmentsRepo appointmentsRepo;

    public List<Appointments> getAppointments(){
        return appointmentsRepo.findAll();
    }
    public void addAppointment(Appointments appointments){
        appointmentsRepo.save(appointments);

    }
    public void editAppointment(Integer id, Appointments appointments){
        Appointments temp = appointmentsRepo.findAppointmentById(id);
        if (temp == null) {
            throw new ApiException("Not found");
        }
        temp.setDateTime(appointments.getDateTime());
        temp.setStatus(appointments.getStatus());
        appointmentsRepo.save(temp);

    }
    public void deleteAppointment(Integer id){
        Appointments temp = appointmentsRepo.findAppointmentById(id);
        if (temp == null) {
            throw new ApiException("Not found");
        }
        appointmentsRepo.delete(temp);

    }

//    public Integer returnCount(Integer id){
//        Appointments temp = appointmentsRepo.findAppointmentById(id);
//        if (temp == null) {
//            throw new ApiException("Not found");
//        }
//        return temp.getAppointmentCount();
//
//    }
//    public Appointments returnAppointmentInfoByName(String name){
//        Appointments temp = appointmentsRepo.findAppointmentByDateTime(name);
//        if (temp == null) {
//            throw new ApiException("Not found");
//        }
//        return temp;
//
//    }
//    public List<Appointments> findByGenre(String genre){
//        List<Appointments> appointments = appointmentsRepo.findAppointmentById(genre);
//        if (appointments.isEmpty()) {
//            throw new ApiException("Not found");
//        }
//        return appointments;
//
//    }
}
