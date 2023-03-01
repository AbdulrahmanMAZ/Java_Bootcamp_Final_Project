package com.abdulrahman.final_Project.MyUser;

import com.abdulrahman.final_Project.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserService {
    private final MyUserRepo myUserRepo;




    public List<MyUser> getMyUsers() {
        return myUserRepo.findAll();
    }

    public void addMyUser( MyUser user) {
        myUserRepo.save(user);
    }
    public void updateMyUser(MyUser newUser, Integer user_id) {
        MyUser user = myUserRepo.findMyUserById(user_id);

        if (user == null) {
            throw new ApiException("not found");
        }
        user.setId(user_id);
        user.setRole(newUser.getRole());
        user.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
        user.setUsername(newUser.getUsername());
        myUserRepo.save(user);


    }

    public void deleteMyUser( Integer user_id) {
        MyUser user = myUserRepo.findMyUserById(user_id);
        System.out.println("blog.getBody()");



        myUserRepo.delete(user);
    }

    // Get customer by ID
    public MyUser getMyUserById( Integer userId) {

            MyUser user_temp = myUserRepo.findMyUserById(userId);
            if (user_temp == null) {
                throw new ApiException("User not found");
            }

            return user_temp;

    }


}
