package com.abdulrahman.final_Project.MyUser;


import com.abdulrahman.final_Project.Auth.AuthRepo;
import com.abdulrahman.final_Project.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailedService implements UserDetailsService {
    private final AuthRepo authRepo;
    private final Logger logger = LoggerFactory.getLogger(MyUserDetailedService.class);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info(username);

        MyUser myUser = authRepo.findMyUserByUsername(username);
        logger.info( String.valueOf (myUser == null));
        if(myUser == null){
            throw new ApiException("Wrong username or password");
        }

        return myUser;

    }
}
