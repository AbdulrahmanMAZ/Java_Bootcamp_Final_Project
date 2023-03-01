package com.abdulrahman.final_Project.DTO;

import com.abdulrahman.final_Project.Advisor.Advisor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ResponseList {

    private List<Advisor> advisors;
    private Integer status;

}
