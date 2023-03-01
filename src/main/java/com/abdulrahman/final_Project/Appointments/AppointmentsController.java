package com.abdulrahman.final_Project.Appointments;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/")
@RequiredArgsConstructor
public class AppointmentsController {

    // ServiceInjection
    private final AppointmentsService appointmentsService;

    // getter
    @GetMapping("/get_appointments")
    public List<Appointments> getAppointments(){
        return appointmentsService.getAppointments();
    }
    @PostMapping("/add_appointments")

    public ResponseEntity addAppointments( @RequestBody Appointments appointments){
                appointmentsService.addAppointment(appointments);
                return ResponseEntity.status(200).body("Success");

    }
    @PutMapping("/edit_appointments")
    public ResponseEntity updateAppointments(@PathVariable Integer id, @RequestBody Appointments appointments){
        appointmentsService.editAppointment(id, appointments);
        return ResponseEntity.status(200).body("Updated");

    }
    @DeleteMapping("/delete_appointments")
    public ResponseEntity updateAppointments(@PathVariable Integer id){
        appointmentsService.deleteAppointment(id);
        return ResponseEntity.status(200).body("Updated");

    }

//    // Create endpoint that takes a _appointments name and return all _appointments information
//    @GetMapping("/get__appointments_info/{name}")
//    public ResponseEntity updateAppointments(@PathVariable String name){
//       Appointments appointments = appointmentsService.returnAppointmentInfoByName(name);
//        return ResponseEntity.status(200).body(appointments);
//
//    }
//    // Create endpoint that takes _appointmentsId and return number of _appointmentss available
//    @GetMapping("/get__appointments_count/{id}")
//    public ResponseEntity getAppointmentsCount(@PathVariable Integer id){
//        Integer _appointments_count = appointmentsService.returnCount(id);
//        return ResponseEntity.status(200).body(_appointments_count);
//
//    }
//    // Create endpoint that return all _appointmentss under a specific genre
//    @GetMapping("/get__appointments_by_genre/{genre}")
//    public ResponseEntity getAppointmentsByGenre(@PathVariable String genre){
//        List<Appointments> appointments = appointmentsService.findByGenre(genre);
//        return ResponseEntity.status(200).body(appointments);
//
//    }
}
