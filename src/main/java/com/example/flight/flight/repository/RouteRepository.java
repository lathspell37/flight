package com.example.flight.flight.repository;

import com.example.flight.flight.model.Routes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Routes, Long> { //type id long
}
