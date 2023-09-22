package com.example.flight.flight.controller;

import com.example.flight.flight.exception.ResourceNotFoundException;
import com.example.flight.flight.model.Airports;
import com.example.flight.flight.repository.AirportsRepository;
import org.hibernate.sql.ast.tree.predicate.BooleanExpressionPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class AirportsController {

    @Autowired
    private AirportsRepository airportsRepository;

    //get airports
    @GetMapping("airports")
    public List<Airports> getAllAirports(){
        return this.airportsRepository.findAll();
    }

    //get airport by id
    @GetMapping("/airports/{id}")
    public ResponseEntity<Airports> getAirportsById(@PathVariable(value = "id") Long airportId)
        throws ResourceNotFoundException {
        Airports airports = airportsRepository.findById(airportId)
                .orElseThrow(() -> new ResourceNotFoundException("Airport not found for this id :" + airportId));
        return ResponseEntity.ok().body(airports);
    }
    //save airport
    @PostMapping("airports")
    public Airports createAirports(@RequestBody Airports airports){
        return this.airportsRepository.save(airports);
    }
    //update airport

    @PutMapping("airports/{id}")
    public ResponseEntity<Airports> updateAirports(@PathVariable(value = "id") Long airportId,
                                                   @Validated @RequestBody Airports airportsDetails) throws ResourceNotFoundException {

        Airports airports = airportsRepository.findById(airportId)
                .orElseThrow(() -> new ResourceNotFoundException("Airport not found for this id :" + airportId));

        airports.setCode(airportsDetails.getCode());
        airports.setCity(airportsDetails.getCity());
        airports.setCountry(airportsDetails.getCountry());

        return ResponseEntity.ok(this.airportsRepository.save(airports));
    }
    //delete airport

    @DeleteMapping("airports/{id}")
    public Map<String, Boolean> deleteAirports(@PathVariable(value = "id") Long airportId) throws ResourceNotFoundException {

        Airports airports = airportsRepository.findById(airportId)
                .orElseThrow(() -> new ResourceNotFoundException("Airport not found for this id :" + airportId));

        this.airportsRepository.delete(airports);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

}
