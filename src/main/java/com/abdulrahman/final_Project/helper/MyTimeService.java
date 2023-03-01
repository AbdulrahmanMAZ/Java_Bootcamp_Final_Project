package com.abdulrahman.final_Project.helper;

import org.springframework.stereotype.Service;

@Service
public class MyTimeService {
    public boolean validTime(String time){
        if (time.equals( "9:0") ||time .equals( "10:0") ||
                time .equals( "11:0")|| time .equals( "12:0")  ||
                time .equals( "13:0") || time .equals( "14:0")|| time .equals( "15:0")|| time .equals( "16:0")) {
            return true;
        }
        else{
            return false;
        }

    }
}
