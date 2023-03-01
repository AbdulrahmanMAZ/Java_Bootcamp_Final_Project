package com.abdulrahman.final_Project.StartUpDetails;

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

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class StartUpDetails {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @NotEmpty
    @NotNull
    private String OwnerName;

//    @NotEmpty
//    @NotNull
//    private String name;

    @NotEmpty
    @Pattern(regexp = "^0\\d{9}$",message = "Must be 0XXXXXXXXX")
    @Column(columnDefinition = "varchar(10) unique not null")
    private String phoneNumber;
    @Pattern(regexp = "^.+@.+\\.\\w{2,}$" , message = "must be in this form XXX@XX.XX")
    @Column(columnDefinition = "varchar(40) unique not null")
    private String email;


    @OneToOne
    @MapsId
    @JsonIgnore
    @JoinColumn(name = "id")
    private StartUp startUp;


    private String basedIn;

//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "store")
//    @PrimaryKeyJoinColumn
//    private List<Appointments> appointments;

//    @ManyToMany(mappedBy = "")
//    @JoinTable(name = "customers_stores",
//            joinColumns = @JoinColumn(name = "store_id"),
//            inverseJoinColumns = @JoinColumn(name = "customer_id")
//    )
//    private List<StartUp> startUps;
//    public void addBookToStore(Appointments appointments){
//        this.appointments.add(appointments);
//    }
//    public void registerCustomerToStore(StartUp startUp){
//        startUps.add(startUp);
//    }
}
