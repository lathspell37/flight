package com.example.flight.flight.cron;

import com.example.flight.flight.enums.StatusEnum;
import com.example.flight.flight.model.Flights;
import com.example.flight.flight.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class Cronjobs {
    private FlightRepository flightRepository;

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        List<Flights> departedFlights = flightRepository.getFlightsByArrivalTimeLessThanAndStatus(currentLocalDateTime, StatusEnum.Departed);
        List<Flights> scheduledFlights = flightRepository.getFlightsByDepartureTimeLessThanAndStatus(currentLocalDateTime, StatusEnum.Scheduled);

        for (Flights departedFlight : departedFlights) {
            departedFlight.setStatus(StatusEnum.Arrived);
            flightRepository.save(departedFlight);
        }

        for (Flights scheduledFlight : scheduledFlights) {
            scheduledFlight.setStatus(StatusEnum.Departed);
            flightRepository.save(scheduledFlight);
        }


    }


}
