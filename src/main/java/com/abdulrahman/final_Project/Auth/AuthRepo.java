package com.abdulrahman.final_Project.Auth;

import com.abdulrahman.final_Project.MyUser.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepo extends JpaRepository<MyUser,Integer> {
    MyUser findMyUserByUsername(String username);
}
