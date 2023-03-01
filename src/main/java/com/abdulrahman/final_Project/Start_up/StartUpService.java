package com.abdulrahman.final_Project.Start_up;

import com.abdulrahman.final_Project.Advisor.Advisor;
import com.abdulrahman.final_Project.Advisor.AdvisorRepo;
import com.abdulrahman.final_Project.AdvisorDetails.AdvisorDetails;
import com.abdulrahman.final_Project.AdvisorDetails.AdvisorDetailsRepo;
import com.abdulrahman.final_Project.Appointments.Appointments;
import com.abdulrahman.final_Project.Appointments.AppointmentsRepo;
import com.abdulrahman.final_Project.Appointments.AppointmentsService;
import com.abdulrahman.final_Project.Feedback.Feedback;
import com.abdulrahman.final_Project.MyUser.MyUser;
import com.abdulrahman.final_Project.Review.Review;
import com.abdulrahman.final_Project.Review.ReviewRepo;
import com.abdulrahman.final_Project.Review.ReviewService;
import com.abdulrahman.final_Project.exception.ApiException;
import com.abdulrahman.final_Project.helper.MyTimeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StartUpService {
    final private StartUpRepo startUpRepo;
    final private AdvisorRepo advisorRepo;
    final private MyTimeService myTimeService;

    Logger logger = LoggerFactory.getLogger(StartUpService.class);
    final private AppointmentsRepo appointmentsRepo;
    final private AppointmentsService appointmentsService;

    final private ReviewRepo reviewRepo;
    final private ReviewService reviewService;

    final private AdvisorDetailsRepo advisorDetailsRepo;
    public List<StartUp> getStartUps(){
        return startUpRepo.findAll();
    }
    public void addStartUp(StartUp startUp){
        startUp.setUser(new MyUser(null,"dommyUser","1234","ADMIN",null,null));
        startUpRepo.save(startUp);

    }
    public void editStartUp(Integer id, StartUp startUp){
        StartUp temp = startUpRepo.findStartUpById(id);
        if (temp == null) {
            throw new ApiException("Not found");
        }

        temp.setNameOfStartUp(startUp.getNameOfStartUp());
        temp.setIndustry(startUp.getIndustry());
//        temp.set(customer.getStore());
        startUpRepo.save(temp);

    }
    public void deleteStartUp(Integer id){
        StartUp temp = startUpRepo.findStartUpById(id);
        if (temp == null) {
            throw new ApiException("Not found");
        }
        startUpRepo.delete(temp);

    }
    public void bookAppointment(Integer startUp_id, Integer advisor_id,String time){
        StartUp startUp = startUpRepo.findStartUpById(startUp_id);
        if (startUp == null) {
            throw new ApiException("Not found");
        }
        Advisor advisor = advisorRepo.findAdvisorById(advisor_id);
        if (advisor == null) {
            throw new ApiException("Not found");
        }


        // Store the date in a variable
        logger.info(time);
        LocalDateTime appointmentStartTime = LocalDateTime.parse(time);
        logger.info("passed time parsing" + appointmentStartTime.getMinute());
        int hours = appointmentStartTime.getHour();
        int minutes = appointmentStartTime.getMinute();
        String hours_minutes = hours+":"+minutes;
        logger.info("passed time parsing" + hours_minutes);
        boolean Advisor_available = false;
        boolean startUp_available = false;
        Appointments appointment;
        // check if the appointment is available by checking if any advisor / start up has an accepted appointment at the same time
        Appointments check_advisor_availability = appointmentsRepo.findAppointmentByDateTimeAndAdvisor_IdAndStatus(appointmentStartTime,advisor_id,"Accepted");
        Appointments check_startUp_availability = appointmentsRepo.findAppointmentByDateTimeAndStartUp_IdAndStatusNotIn(appointmentStartTime,startUp_id,new ArrayList<>(
                Arrays.asList("Completed","Paid")));
//        Appointments check_startUp_availability_same = appointmentsRepo.findAppointmentByDateTimeAndStartUp_IdAndStatus(appointmentStartTime,startUp_id,"Accepted");

        if (check_advisor_availability == null && check_startUp_availability == null) {
            appointment = new Appointments(null,appointmentStartTime,"Pending",0,false,null,null,null);
            Advisor_available = true;
        } else if (check_advisor_availability != null) {
            throw new ApiException("This advisor is not available at this time, please try another date");
        } else if (check_startUp_availability != null&&!check_startUp_availability.getStatus().equals("Completed")){
            throw new ApiException("You already has a date at this specific time, please try another date");
        }else {
            throw new ApiException("This time is not valid, or in the past,  please try another date");
        }
        // Before booking make sure the time is a full hour
        if (!myTimeService.validTime(hours_minutes)){
            throw new ApiException("This is not a valid time, please choose a correct time (9:00,10:00,11:00,12:00,13:00,14:00,15:00,16:00)");
        }

        appointment.setAdvisor(advisor);
        appointment.setStartUp(startUp);
        appointment.setStatus("Pending");

//        List<Appointments>  startUpAppointments = startUp.getAppointments();
//        startUpAppointments.add(appointment);
//        List<Appointments>  advisorAppointments = advisor.getAppointments();
//        advisorAppointments.add(appointment);
//        startUp.setAppointments(startUpAppointments);
//        advisor.setAppointments(advisorAppointments);
//        startUpRepo.save(startUp);
//        advisorRepo.save(advisor);

        startUp.addAppointmentToMyAppointments(appointment);
        advisor.addAppointmentToMyAppointments(appointment);
//        advisorRepo.save(advisor);
//        startUpRepo.save(startUp);
        appointmentsService.addAppointment(appointment);

    }
    public void rescheduleAppointment(Integer appointment_id, Integer startUp_id, Integer advisor_id,String newTime){
        Appointments appointments = appointmentsRepo.findAppointmentsByIdAndAdvisor_IdAndStartUp_Id(appointment_id,advisor_id,startUp_id);
        if (appointments == null) {
            throw new ApiException("Appointment Not found");
        }
        if (!appointments.equals("Pending") || !appointments.equals("Accepted")) {
            throw new ApiException("Appointment date can not be reschedule");
        }

        // Parse the string into a valid date format then store the date in a variable
        LocalDateTime appointmentNewTime = LocalDateTime.parse(newTime);
        // Create a string containing the time format 00:00
        int hours = appointmentNewTime.getHour();
        int minutes = appointmentNewTime.getMinute();
        String hours_minutes = hours+":"+minutes;


        Appointments appointment;
        // check if the new appointment is available
        Appointments check_advisor_availability = appointmentsRepo.findAppointmentByDateTimeAndAdvisor_Id(appointmentNewTime,advisor_id);
        Appointments check_startUp_availability = appointmentsRepo.findAppointmentByDateTimeAndStartUp_IdAndStatusNotIn(appointmentNewTime,startUp_id,new ArrayList<>(
                Arrays.asList("Completed","Paid")));
        if (check_advisor_availability == null && check_startUp_availability == null) {
            appointment = new Appointments(null,appointmentNewTime,appointments.getStatus(),appointments.getFee() == null ?0 : appointments.getFee(),false,appointments.getAdvisor(),appointments.getStartUp(),null);
        } else if (check_advisor_availability != null) {
            throw new ApiException("This advisor is not available at this time, please try another date");
        } else if (check_startUp_availability != null){
            throw new ApiException("You already has a date at this specific time, please try another date");
        }else {
            throw new ApiException("This time is not valid, please try another date");
        }
        // Before booking make sure the time is a full hour
        if (!myTimeService.validTime(hours_minutes)){
            throw new ApiException("This is not a valid time, please choose a correct time (9:00,10:00,11:00,12:00,13:00,14:00,15:00,16:00)");
        }


        appointment.setDateTime(appointmentNewTime);
        appointmentsRepo.save(appointment);
    }
    public void cancelAppointment(Integer appointment_id, Integer startUp_id, Integer advisor_id){
        Appointments appointment = appointmentsRepo.findAppointmentsByIdAndAdvisor_IdAndStartUp_Id(appointment_id,advisor_id,startUp_id);
        if (appointment == null) {
            throw new ApiException("Appointment Not found");
        }

        Advisor advisor = advisorRepo.findAdvisorById(advisor_id);
        if (advisor == null) {
            throw new ApiException("Advisor Not found");
        }
        StartUp startUp = startUpRepo.findStartUpById(startUp_id);
        if (advisor == null) {
            throw new ApiException("Start up Not found");
        }

        // check if the current time is before the appointment date by two days.
        LocalDateTime current =  LocalDateTime.now();
        if (!current.plusDays(2).isBefore(appointment.getDateTime())){
            throw new ApiException("You can not cancel this appointment ,you cna only cancel appointments before two days (48 hours)");
        }


        else if (appointment.getStatus().equals("Paid") && current.plusDays(2).isBefore(appointment.getDateTime())) {
            advisor.refund(appointment.getFee());
            startUp.addMoneyToWallet(appointment.getFee());
            advisorRepo.save(advisor);
            startUpRepo.save(startUp);
            appointmentsRepo.delete(appointment);

        }
        else if ((appointment.getStatus().equals("Pending")||appointment.getStatus().equals("Accepted")) && current.plusDays(2).isBefore(appointment.getDateTime())) {
            appointmentsRepo.delete(appointment);
        }


    }
    public List<Appointments> pendingAppointments( Integer startUp_id){
        StartUp startUp = startUpRepo.findStartUpById(startUp_id);
        if (startUp == null) {
            throw new ApiException("Start up Not found");
        }
        List<Appointments> appointments = appointmentsRepo.findAllByStartUpAndStatus(startUp,"Pending");
        if (appointments.isEmpty()) {
            throw new ApiException("You have no upcoming appointments");
        }
        return appointments;

    }
    public List<Appointments> upComingAppointments( Integer startUp_id){
        StartUp startUp = startUpRepo.findStartUpById(startUp_id);
        if (startUp == null) {
            throw new ApiException("Start up Not found");
        }
        List<Appointments> appointments = appointmentsRepo.findAllByStartUpAndStatus(startUp,"Accepted");
        if (appointments.isEmpty()) {
            throw new ApiException("You have no upcoming appointments");
        }
    return appointments;

    }
    public List<Appointments> CompletedAppointments( Integer startUp_id){
        StartUp startUp = startUpRepo.findStartUpById(startUp_id);
        if (startUp == null) {
            throw new ApiException("Start up Not found");
        }
        List<Appointments> appointments = appointmentsRepo.findAllByStartUpAndStatus(startUp,"Completed");
        if (appointments.isEmpty()) {
            throw new ApiException("You have no completed appointments yet!");
        }
        return appointments;

    }
    public void payAppointmentFee(Integer appointment_id, Integer startUp_id, Integer advisor_id){
        Appointments appointment = appointmentsRepo.findAppointmentsByIdAndAdvisor_IdAndStartUp_Id(appointment_id,advisor_id,startUp_id);
        if (appointment == null) {
            throw new ApiException("Appointment Not found");
        }
        // check if the appointment is Accepted

        if (!appointment.getStatus().equals("Accepted")) {
            throw new ApiException("Appointment Not Accepted Yet");
        }
        // check if the start-up has the money
        StartUp startUp = startUpRepo.findStartUpById(startUp_id);
        if (startUp.getWallet() < appointment.getFee()) {
            throw new ApiException("Start up Does not have the money");
        }
        // Transfer the advisor fees.
        Advisor advisor = advisorRepo.findAdvisorById(advisor_id);
        advisor.addMoneyToWallet(appointment.getFee());
        startUp.withdrawMoneyFromWallet(appointment.getFee());
        appointment.setStatus("Paid");
        advisorRepo.save(advisor);
        startUpRepo.save(startUp);
        appointmentsRepo.save(appointment);
    }

    public void CreditMoneyToWallet(Integer startUp_id, Integer money){
        if (money <= 0) {
            throw new ApiException("You must credit more than zero");
        }
        StartUp startUp = startUpRepo.findStartUpById(startUp_id);
        if (startUp == null) {
            throw new ApiException("Start up Does not exist");
        }

        startUp.addMoneyToWallet(money);
        startUpRepo.save(startUp);
    }
    public void postReview(Integer appointment_id, Integer startUp_id, Integer advisor_id, Review review){
        Appointments appointment = appointmentsRepo.findAppointmentsByIdAndAdvisor_IdAndStartUp_Id(appointment_id,advisor_id,startUp_id);
        if (appointment == null) {
            throw new ApiException("Appointment Not found");
        }

        // Check if the appointment is completed
        if (!appointment.getStatus().equals("Completed")) {
            throw new ApiException("This appointment need to be completed before review");
        }
        // Check if the appointment has been already reviewed.
        if (appointment.isReviewed()) {
            throw new ApiException("This appointment is already been reviewed by you");
        }
        Advisor advisor = advisorRepo.findAdvisorById(advisor_id);
        if (advisor == null) {
            throw new ApiException("Advisor Not found");
        }
        StartUp startUp = startUpRepo.findStartUpById(startUp_id);
        if (startUp == null) {
            throw new ApiException("Start up Not found");
        }


        // Post review
        review.setAppointment(appointment);
        review.setStartUp(startUp);
        review.setAdvisor(advisor);

        reviewService.addReview(review);
        reviewRepo.save(review);
        appointment.setReviewed(true);
        appointmentsRepo.save(appointment);
        // calculate rating

        double totalRating = 0;
        Integer numberOfReviews = 0;
        List<Review> reviews = reviewRepo.findAllByAdvisor_Id(advisor_id);
        for (Review review1: reviews) {
            totalRating += review1.getRating();
            numberOfReviews++;
        }
//        logger.info(String.valueOf(numberOfReviews));
        advisor.setRating(new BigDecimal(totalRating /numberOfReviews) );

        advisorRepo.save(advisor);
    }

    public  List<Advisor> ListAllAdvisors(){
        List<Advisor> found_advisors = advisorRepo.findAll();
        if (found_advisors.isEmpty()) {
            throw new ApiException("There is no available advisors");
        }
        return  found_advisors;

    }

    public  List<Advisor> SearchForSpeciality(String speciality){
       List<Advisor> found_advisors = advisorRepo.findAllBySpeciality(speciality);
        if (found_advisors.isEmpty()) {
            throw new ApiException("There is no available advisor with that speciality");
        }
        return  found_advisors;

    }
    public  List<Advisor> SortAdvisorsByRating(){
        List<Advisor> found_advisors = advisorRepo.findByOrderByRatingDesc();
        if (found_advisors.isEmpty()) {
            throw new ApiException("There is no available advisor with that speciality");
        }
        return  found_advisors;

    }

    public  List<AdvisorDetails> findAdvisorByName(String first, String last){
        List<AdvisorDetails> found_advisors = advisorDetailsRepo.findAllByFirstNameContainsIgnoreCaseAndLastNameContainsIgnoreCase(first,last);
        if (found_advisors.isEmpty()) {
            throw new ApiException("There is no available advisors");
        }
        return  found_advisors;

    }

}
