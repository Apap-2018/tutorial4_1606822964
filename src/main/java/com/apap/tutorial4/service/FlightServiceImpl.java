package com.apap.tutorial4.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.repository.FlightDB;

/**
 *  FlightServiceImpl
 *  @author Priscilla Tiffany
 *
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDB flightDb;
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}

	@Override
	public void deleteFlight(FlightModel flight) {
		flightDb.delete(flight);
	}

	@Override
	public FlightModel findFlightById(Long id) {
		return flightDb.getOne(id);
	}

	@Override
	public void updateFlight(FlightModel flight, String flightNumber, String origin, String destination, Date time) {
		flight.setFlightNumber(flightNumber);
		flight.setOrigin(origin);
		flight.setDestination(destination);
		flight.setTime(time);
		flightDb.save(flight);		
	}

	@Override
	public List<FlightModel> findFlightByName(String flightNumber) {
		return flightDb.findFlightByName(flightNumber);
	}

}