package com.abdulrahman.final_Project.Review;
import com.abdulrahman.final_Project.Advisor.Advisor;
import com.abdulrahman.final_Project.Appointments.Appointments;
import com.abdulrahman.final_Project.Start_up.StartUp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Review {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "title field must not be nul")
    @NotEmpty(message = "title must not be empty")
    @Size(max = 40,message = "you've exceeded the character limit which is 40")
    private String title;
    @NotNull
    @NotEmpty
    @Size(max = 40,message = "you've exceeded the character limit which is 240")
    private String content;
    @NotNull
    @Min(value = 1,message = "Rating must be bigger than 1 and less than five")
    @Max(value = 5,message = "Rating must be bigger than 1 and less than five")
    private Integer rating;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "startUp_id")
    private StartUp startUp;

     @ManyToOne
     @JsonIgnore
     @JoinColumn(name = "advisor_id")
     private Advisor advisor;

     @OneToOne
     @MapsId
     @JsonIgnore
     private Appointments appointment;




}
