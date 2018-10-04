package com.apap.tutorial4.service;

import java.sql.Date;
import java.util.List;

import com.apap.tutorial4.model.FlightModel;

/**
 *  FlightService
 *  @author Priscilla Tiffany
 */
public interface FlightService {
	void addFlight(FlightModel flight);
	void deleteFlight(FlightModel flight);
	void updateFlight(FlightModel flight, String flightNumber, String origin, String destination, Date time);
	FlightModel findFlightById(Long id);
	List<FlightModel> findFlightByName(String flightNumber);
}