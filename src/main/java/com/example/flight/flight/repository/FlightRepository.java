package com.example.flight.flight.repository;

import com.example.flight.flight.enums.StatusEnum;
import com.example.flight.flight.model.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flights, Long> {
    List<Flights> getFlightsByArrivalTimeLessThanAndStatus(LocalDateTime time, StatusEnum statusEnum);
    List<Flights> getFlightsByDepartureTimeLessThanAndStatus(LocalDateTime time, StatusEnum statusEnum);

}
