package com.abdulrahman.final_Project.Start_up;

import com.abdulrahman.final_Project.Advisor.Advisor;
import com.abdulrahman.final_Project.Appointments.Appointments;
import com.abdulrahman.final_Project.Feedback.Feedback;
import com.abdulrahman.final_Project.MyUser.MyUser;
import com.abdulrahman.final_Project.Review.Review;
import com.abdulrahman.final_Project.StartUpDetails.StartUpDetails;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class StartUp
{
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @NotNull
    private String nameOfStartUp;


    @NotEmpty
    @NotNull
    private String industry;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "startUp")
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private List<Appointments> appointments;
    @PositiveOrZero
    private Integer Wallet=0;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "startUp")
    @PrimaryKeyJoinColumn
    private List<Review> review;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "startUp")
    @PrimaryKeyJoinColumn
    private StartUpDetails details;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "startUp")
    @PrimaryKeyJoinColumn
    private List<Feedback> feedbacks;
//    @ManyToMany(mappedBy = "customers")
//    @JsonIgnore
//    private List<Store> stores;


    @OneToOne
    @MapsId
    @JsonIgnore
    @JoinColumn(name = "id")
    private MyUser user;

    public void addAppointmentToMyAppointments(Appointments appointment){
        appointments.add(appointment);
    }

    public void addMoneyToWallet(Integer fee){
        this.Wallet += fee;
    }
    public void withdrawMoneyFromWallet(Integer fee){
        this.Wallet -= fee;
    }
}
