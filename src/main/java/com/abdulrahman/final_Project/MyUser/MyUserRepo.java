package com.abdulrahman.final_Project.MyUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepo extends JpaRepository<MyUser, Integer> {
     MyUser findMyUserById(Integer id);

}
