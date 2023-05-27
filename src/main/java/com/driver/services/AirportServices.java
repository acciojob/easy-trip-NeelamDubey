package com.driver.services;
import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import com.driver.repository.Airportrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class AirportServices {

    private static Airportrepository airportRepository =new Airportrepository();

    public static void addAirport(Airport airport) {
        airportRepository.addAirport(airport);
    }

    public void addFlight(Flight flight) {
        airportRepository.addFlight(flight);
    }

    public void addPassenger(Passenger passenger) {
        airportRepository.addPassenger(passenger);
    }

    public String getLargestAirportName() {
        List<Airport> allAirport=airportRepository.getAllAirport();
        int min=-1;
        String str="";
        for (Airport airport:allAirport){
            if(min<airport.getNoOfTerminals()){
                min=airport.getNoOfTerminals();
                str=airport.getAirportName();
            }
            else if (min==airport.getNoOfTerminals()) {
                int check=str.compareTo(airport.getAirportName());
                if(check>0){
                    str=airport.getAirportName();
                }
            }
        }
        return str;
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
        List<Flight> flightList=airportRepository.getAllFlight();
        double min=Double.MAX_VALUE;
        for(Flight flight:flightList){
            if(flight.getFromCity().equals(fromCity) && flight.getToCity().equals(toCity) && min>flight.getDuration()){
                min=flight.getDuration();
            }
        }
        if(min==Double.MAX_VALUE)return -1;
        return min;
    }

    public String bookTicket(Integer flightId, Integer passengerId) {
        return airportRepository.bookTicket(flightId,passengerId);
    }

    public String cancelATicket(Integer flightId, Integer passengerId) {
        return airportRepository.cancelATicket(flightId,passengerId);
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
        return airportRepository.countOfBookingsDoneByPassengerAllCombined(passengerId);
    }
}
