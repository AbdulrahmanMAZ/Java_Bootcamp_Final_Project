package com.abdulrahman.final_Project.Advisor;

import com.abdulrahman.final_Project.Appointments.Appointments;
import com.abdulrahman.final_Project.DTO.Response;
import com.abdulrahman.final_Project.Feedback.Feedback;
import com.abdulrahman.final_Project.MyUser.MyUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/advisor")
@RequiredArgsConstructor
public class AdvisorController {

    // ServiceInjection
    private final AdvisorService advisorService;

    // getter
    @GetMapping("/admin/get_advisor")
    public List<Advisor> getAdvisors(){
        return advisorService.getAdvisors();
    }
    @PostMapping("/admin/add_advisor")

    public ResponseEntity addAdvisor(@Valid @RequestBody Advisor _advisor){
                advisorService.addAdvisor(_advisor);
                return ResponseEntity.status(200).body("Success");

    }
    @PutMapping("/edit_advisor")
    public ResponseEntity updateAdvisors(@AuthenticationPrincipal MyUser myUser,@Valid  @RequestBody Advisor _advisor){
        advisorService.editAdvisor(myUser.getId(),_advisor);
        return ResponseEntity.status(200).body("Updated");

    }
    @DeleteMapping("/admin/delete_advisor/{id}")
    public ResponseEntity deleteAdvisors(@PathVariable Integer id){
        advisorService.deleteAdvisor(id);
        return ResponseEntity.status(200).body("Updated");

    }

    @PutMapping("/reschedule_appointment/{appointmentID}/{startUp_ID}/{dateTime}")
    public ResponseEntity<Response> rescheduleAppointment(@PathVariable Integer appointmentID, @PathVariable Integer startUp_ID, @AuthenticationPrincipal MyUser myUser, @PathVariable String dateTime){
        advisorService.rescheduleAppointment(appointmentID,startUp_ID,myUser.getId(),dateTime);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Your appointment has been Updated",200));

    }
    @DeleteMapping("/cancel_appointment/{appointmentID}/{startUp_ID}")
    public ResponseEntity cancelAppointment(@PathVariable Integer appointmentID,@PathVariable Integer startUp_ID, @AuthenticationPrincipal MyUser myUser){
        advisorService.cancelAppointment(appointmentID,startUp_ID,myUser.getId());
        return ResponseEntity.status(200).body("Your appointment has been canceled");

    }
    @GetMapping("/pending_appointment")
    public ResponseEntity getPendingAppointment(@AuthenticationPrincipal MyUser myUser){
        List<Appointments> appointments = advisorService.pendingAppointments(myUser.getId());
        return ResponseEntity.status(200).body(appointments);

    }
    @GetMapping("/upcoming_appointment")
    public ResponseEntity getUpcomingAppointment(@AuthenticationPrincipal MyUser myUser){
        List<Appointments> appointments = advisorService.upComingAppointments(myUser.getId());
        return ResponseEntity.status(200).body(appointments);

    }
    @PutMapping("/accept_appointment/{appointmentID}/{startUp_ID}")
    public ResponseEntity<Response> AcceptAppointment(@PathVariable Integer appointmentID,@PathVariable Integer startUp_ID,@AuthenticationPrincipal MyUser myUser){
        advisorService.AcceptAppointment(appointmentID,startUp_ID,myUser.getId());;
        return ResponseEntity.status(200).body(new Response("This appointment has been Accepted and the bill has been sent to the start-up",200));

    }
    @PutMapping("/reject_appointment/{appointmentID}/{startUp_ID}")
    public ResponseEntity CancelAppointment(@PathVariable Integer appointmentID,@PathVariable Integer startUp_ID,@AuthenticationPrincipal MyUser myUser){
        advisorService.cancelAppointment(appointmentID,startUp_ID,myUser.getId());;
        return ResponseEntity.status(200).body("This appointment has been Accepted and the bill has been sent to the start-up");

    }
    @PutMapping("/complete_appointment_feedback/{appointmentID}/{startUp_ID}")
    public ResponseEntity<Response> completeAppointment(@PathVariable Integer appointmentID,@PathVariable Integer startUp_ID,@AuthenticationPrincipal MyUser myUser, @RequestBody Feedback feedback){
         advisorService.CompleteAppointmentAndWriteFeedback(appointmentID,startUp_ID,myUser.getId(),feedback);;
        return ResponseEntity.status(200).body(new Response("This appointment has been marked complete and feed back has been sent",200));


    }


}
