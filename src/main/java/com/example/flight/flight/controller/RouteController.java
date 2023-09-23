package com.example.flight.flight.controller;

import com.example.flight.flight.exception.ResourceNotFoundException;
import com.example.flight.flight.model.Airports;
import com.example.flight.flight.model.Routes;
import com.example.flight.flight.repository.AirportsRepository;
import com.example.flight.flight.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class RouteController {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private AirportsRepository airportsRepository;
    //get routes
    @GetMapping("routes")
    public List<Routes> getAllRoutes() {return this.routeRepository.findAll();}

    //get route by id
    @GetMapping("/routes/{id}")
    public ResponseEntity<Routes> getRoutesById(@PathVariable(value ="id") Long routesId)
        throws ResourceNotFoundException {
        Routes routes = routeRepository.findById(routesId)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found for this id : " + routesId));
        return ResponseEntity.ok().body(routes);
    }


    //save routes
    @PostMapping("routes")
    public Routes createRoutes(@RequestBody Routes routes) {
        return this.routeRepository.save(routes);
    }

    //update routes
    @PutMapping("routes/{id}")
    public ResponseEntity<Routes> updateRoutes(@PathVariable(value= "id") Long routesId,
                                               @Validated @RequestBody Routes routesDetails) throws ResourceNotFoundException {
        Routes routes = routeRepository.findById(routesId)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found for this id : " + routesId));
        return ResponseEntity.ok().body(routes);
    }

    //delete route
    @DeleteMapping("routes/{id}")
    public Map<String, Boolean> deleteRoutes(@PathVariable(value = "id") Long routesId) throws ResourceNotFoundException {

        Routes routes = routeRepository.findById(routesId)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found for this id :" + routesId));

        this.routeRepository.delete(routes);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    @PostMapping("routes/{source_id}/{destination_id}")
    public ResponseEntity<Routes> updateRoutes(@PathVariable(value= "source_id") Long sourceId, @PathVariable(value = "destination_id") Long destinationId,
                                               @Validated @RequestBody Routes routesDetails) throws ResourceNotFoundException {
        Airports sourceAirport = airportsRepository.findById(sourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Source Route not found for this id :" + sourceId));
        Airports destinationAirport = airportsRepository.findById(destinationId)
                .orElseThrow(() -> new ResourceNotFoundException("Destination Route not found for this id :" + destinationId));
        Routes routes = new Routes();
        routes.setDestination(destinationAirport);
        routes.setSource(sourceAirport);
        routes.setDistance(routesDetails.getDistance());
        routes = routeRepository.save(routes);
        return ResponseEntity.ok().body(routes);
    }

}
