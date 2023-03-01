package com.abdulrahman.final_Project.MyUser;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/users")
@RequiredArgsConstructor
public class MyUserController {
    private final MyUserService myUserService;

    @GetMapping
    public List<MyUser> getMyUsers(){
        return myUserService.getMyUsers();
    }
    @PostMapping
    public ResponseEntity addMyUser(@Valid @RequestBody MyUser user){
        myUserService.addMyUser(user);

        return ResponseEntity.status(HttpStatus.OK).body("Done! ");
    }
    @PutMapping("/{user_id}")
    public ResponseEntity updateMyUser(@Valid @RequestBody MyUser user, @PathVariable Integer user_id){
        myUserService.updateMyUser(user,user_id);
        return ResponseEntity.status(HttpStatus.OK).body("Updated! ");
    }
    @DeleteMapping("/{user_id}")
    public ResponseEntity deleteMyUser(@Valid @PathVariable Integer user_id){
        myUserService.deleteMyUser(user_id);

        return ResponseEntity.status(HttpStatus.OK).body("Deleted! ");
    }
    // Get customer by ID
    @GetMapping("/{user_id}")
    public MyUser getMyUserById(@PathVariable Integer user_id){
        return myUserService.getMyUserById(user_id);
    }

}
