package com.abdulrahman.final_Project.StartUpDetails;

import com.abdulrahman.final_Project.Appointments.Appointments;
import com.abdulrahman.final_Project.DTO.LocationDTO;
import com.abdulrahman.final_Project.MyUser.MyUser;
import com.abdulrahman.final_Project.Start_up.StartUp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3")
@RequiredArgsConstructor
public class StartUpDetailsController {

    // ServiceInjection
    private final StartUpDetailsService startUpDetailsService;

    // getter
    @GetMapping("/admin/get_startup_details")
    public List<StartUpDetails> getStartUpDetailss(){
        return startUpDetailsService.getStartUpDetails();
    }
    @PostMapping("/admin/addstartUpDetails")

    public ResponseEntity addStartUpDetails( @RequestBody StartUpDetails startUpDetails){
                startUpDetailsService.addStartUpDetails(startUpDetails);
                return ResponseEntity.status(200).body("Success");

    }
    @PutMapping("/edit_startup_details")
    public ResponseEntity updateStartUpDetails(@AuthenticationPrincipal MyUser myUser ,@RequestBody StartUpDetails startUpDetails){
        startUpDetailsService.editStartUpDetails(myUser.getId(), startUpDetails);
        return ResponseEntity.status(200).body("Updated");

    }
    @DeleteMapping("/admin/delete_startup_details/{id}")
    public ResponseEntity updateStartUpDetails(@PathVariable Integer id){
        startUpDetailsService.deleteStartUpDetails(id);
        return ResponseEntity.status(200).body("Updated");

    }


}
