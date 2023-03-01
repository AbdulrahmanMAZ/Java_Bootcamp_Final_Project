package com.abdulrahman.final_Project.Appointments;

import com.abdulrahman.final_Project.Advisor.Advisor;
import com.abdulrahman.final_Project.Feedback.Feedback;
import com.abdulrahman.final_Project.Start_up.StartUp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.After;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Appointments {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Future
    private LocalDateTime dateTime;

    @NotNull
    @Pattern(regexp = "^Pending||Accepted||Unpaid||Paid||Rejected||Completed$")
    private String status;


    @PositiveOrZero
    private Integer Fee=0;

    private boolean reviewed;
    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Advisor advisor;
    @ManyToOne

    @JoinColumn(name = "startUp_id")
    private StartUp startUp;

     @OneToOne(cascade = CascadeType.ALL,mappedBy = "appointment")
     @PrimaryKeyJoinColumn
     private Feedback feedback;


}
