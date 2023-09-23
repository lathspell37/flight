package com.example.flight.flight.controller;

import com.example.flight.flight.exception.ResourceNotFoundException;
import com.example.flight.flight.model.Airports;
import com.example.flight.flight.model.Flights;
import com.example.flight.flight.model.Routes;
import com.example.flight.flight.repository.FlightRepository;
import com.example.flight.flight.repository.RouteRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class FlightController {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private RouteRepository routeRepository;

    //get flights
    @GetMapping("flights")
    public List<Flights> getAllFlights(){
        return this.flightRepository.findAll();
    }

    //get flight by id
    @GetMapping("/flights/{id}")
    public ResponseEntity<Flights> getFlightsById(@PathVariable(value = "id") Long flightId)
            throws ResourceNotFoundException {
        Flights flights = flightRepository.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found for this id :" + flightId));
        return ResponseEntity.ok().body(flights);
    }
    //save flight
    @PostMapping("flights/{route_id}")
    public Flights createFlights(@RequestBody Flights flights, @PathVariable(value = "route_id") Long routeId) throws ResourceNotFoundException {
        Routes routes = routeRepository.findById(routeId)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found for this id :" + routeId));
        flights.setRoute(routes);
        return this.flightRepository.save(flights);
    }

    //update flight
    @PutMapping("flight/{id}")
    public ResponseEntity<Flights> updateFlights(@PathVariable(value = "id") Long flightId, @PathVariable(value = "route_id") Long routeId,
                                                   @Validated @RequestBody Flights flightDetails) throws ResourceNotFoundException {

        Flights flights = flightRepository.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found for this id :" + flightId));
        Routes routes = routeRepository.findById(routeId)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found for this id :" + routeId));

        flights.setFlightNumber(flightDetails.getFlightNumber());
        flights.setCapacity(flightDetails.getCapacity());
        flights.setPrice(flightDetails.getPrice());
        flights.setDepartureTime(flightDetails.getDepartureTime());
        flights.setArrivalTime(flightDetails.getArrivalTime());
        flights.setStatus(flightDetails.getStatus());
        flights.setRoute(routes);


        return ResponseEntity.ok(this.flightRepository.save(flights));
    }

    //delete flight
    @DeleteMapping("flight/{id}")
    public Map<String, Boolean> deleteFlight(@PathVariable(value = "id") Long flightId) throws ResourceNotFoundException {

        Flights flights = flightRepository.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found for this id :" + flightId));

        this.flightRepository.delete(flights);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }


}
