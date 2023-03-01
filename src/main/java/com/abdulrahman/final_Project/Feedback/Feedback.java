package com.abdulrahman.final_Project.Feedback;

import com.abdulrahman.final_Project.Advisor.Advisor;
import com.abdulrahman.final_Project.Appointments.Appointments;
import com.abdulrahman.final_Project.Start_up.StartUp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String instructions;
//    @NotNull
//    @PositiveOrZero
//    private Integer bookCount;
//    @NotNull
//    @Pattern(regexp = "^Action||Drama||Comedy||Horror||Romance||Mystery||Thriller$")
//    private String genre;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "advisor_id")
    private Advisor advisor;

     @ManyToOne
     @JsonIgnore
     @JoinColumn(name = "startUp_id")
     private StartUp startUp;

     @OneToOne
     @MapsId
     @JsonIgnore
     private Appointments appointment;






}
