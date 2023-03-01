package com.abdulrahman.final_Project.AdvisorDetails;

import com.abdulrahman.final_Project.MyUser.MyUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/")
@RequiredArgsConstructor
public class AdvisorDetailsController {

    // ServiceInjection
    private final AdvisorDetailsService advisorDetailsService;

    // getter
    @GetMapping("/admin/get_advisor_details")
    public List<AdvisorDetails> getAdvisorDetails(){
        return advisorDetailsService.getAdvisorDetails();
    }
    @PostMapping("/admin/add_advisor_details")

    public ResponseEntity addAdvisorDetails( @RequestBody AdvisorDetails _advisor_details){
                advisorDetailsService.addAdvisorDetails(_advisor_details);
                return ResponseEntity.status(200).body("Success");

    }
    @PutMapping("/edit_advisor_details")
    public ResponseEntity updateAdvisorDetails(@AuthenticationPrincipal MyUser myUser, @RequestBody AdvisorDetails _advisor_details){
        advisorDetailsService.editAdvisorDetails(myUser.getId(), _advisor_details);
        return ResponseEntity.status(200).body("Updated");

    }
    @DeleteMapping("/admin/delete_advisor_details/{id}")
    public ResponseEntity updateAdvisorDetails(@PathVariable Integer id){
        advisorDetailsService.deleteAdvisorDetails(id);
        return ResponseEntity.status(200).body("Updated");

    }


}
