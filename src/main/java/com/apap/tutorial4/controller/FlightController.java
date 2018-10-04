package com.apap.tutorial4.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.FlightService;
import com.apap.tutorial4.service.PilotService;

/**
 *  FlightController
 *  @author Priscilla Tiffany
 */
@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		pilot.addPilotFlight(flight);
				
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	@RequestMapping(value = "/flight/delete/{id}", method = RequestMethod.GET)
	private String delete(@PathVariable(value = "id") Long id, Model model) {
		FlightModel flight = flightService.findFlightById(id);
		flightService.deleteFlight(flight);
		return "delete";
	}
	
	@RequestMapping(value = "/flight/update/{id}", method = RequestMethod.GET)
	private String update(@PathVariable(value = "id") Long id, Model model) {
		FlightModel flight = flightService.findFlightById(id);
		model.addAttribute("flight", flight);
		return "updateFlight";
	}
	
	@RequestMapping(value = "/flight/update/{id}", method = RequestMethod.POST)
	private String updateFlightSubmit(@PathVariable(value = "id") Long id,
									  @RequestParam("flightNumber") String flightNumber,
									  @RequestParam("origin") String origin,
									  @RequestParam("destination") String destination,
									  @RequestParam("time") Date time, Model model) {
		FlightModel flight = flightService.findFlightById(id);
		flightService.updateFlight(flight, flightNumber, origin, destination, time);
		return "update";
	}

	@RequestMapping(value = "/flight/view", method = RequestMethod.GET)
	private String viewFlightByName(@RequestParam("flightNumber") String flightNumber, Model model) {
		List<FlightModel> flight = flightService.findFlightByName(flightNumber);
		model.addAttribute("flight", flight);
		return "view-flight";
	}
}
