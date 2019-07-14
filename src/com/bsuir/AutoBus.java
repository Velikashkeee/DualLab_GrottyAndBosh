package com.bsuir;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AutoBus implements Comparable<AutoBus> {

    //60 minutes is maximum travel time
    private static final int MAXIMUM_TRAVEL_TIME = 60;
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    private String company;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    AutoBus(String input){
        String[] parsedString = input.split(" ");
        this.company = parsedString[0];
        this.departureTime = LocalTime.parse(parsedString[1], FORMAT);
        this.arrivalTime = LocalTime.parse(parsedString[2], FORMAT);
    }

    public AutoBus(String company, LocalTime departureTime, LocalTime arrivalTime){
        this.company = company;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    //Comparator for sort AutoBuses by company

    static Comparator<AutoBus> companyComparator = (autoBus1, autoBus2) -> (autoBus1.company.compareTo(autoBus2.company)) * (-1);

    //get correct TimeTable

    static List<AutoBus> getEfficientAutoBusList(List<AutoBus> busList){
        List<AutoBus> resultList = new ArrayList<>();
        for (AutoBus bus:busList) {
            boolean addToList = true;
            for (AutoBus otherBus:busList) {
                if(!bus.isEfficient(otherBus)){
                    addToList = false;
                }
            }
            if (addToList)
                resultList.add(bus);
        }
        return resultList;
    }

    //check if this bus is more efficient than other

    private boolean isEfficient(AutoBus otherBus){
        boolean result = true;

        //if travel time > MAXIMUM_TRAVEL_TIME. default: 60

        if (this.calcTravelTimeInMinutes() > MAXIMUM_TRAVEL_TIME)
            result = false;

        //if it starts at the same time and reaches later

        else if ((this.departureTime.equals(otherBus.departureTime)) && (this.arrivalTime.isAfter(otherBus.arrivalTime)))
            result = false;

        //if it reaches at the same time and starts earlier

        else if ((this.arrivalTime.equals(otherBus.arrivalTime)) && (this.departureTime.isBefore(otherBus.departureTime)))
            result = false;

        //if it starts earlier and reaches later

        else if ((this.arrivalTime.isAfter(otherBus.arrivalTime)) && (this.departureTime.isBefore(otherBus.departureTime)))
            result = false;

        //if same departure and arrival time choose more comfortable: Posh

        else if ((this.arrivalTime.equals(otherBus.arrivalTime)) && (this.departureTime.equals(otherBus.departureTime)) && (!this.equals(otherBus)))
                if (this.company.equals("Grotty"))
                    result = false;

        return result;
    }

    @Override
    public String toString(){
        return this.company + " " + departureTime.format(FORMAT) + " " + arrivalTime.format(FORMAT);
    }

    //to get travel time

    private int calcTravelTimeInMinutes() {
        return (this.arrivalTime.getHour() - this.departureTime.getHour()) * 60 + (this.arrivalTime.getMinute()-this.departureTime.getMinute());
    }

    //to sort AutoBuses by arrivalTime

    @Override
    public int compareTo(AutoBus bus) {
        int res;
        if (this.arrivalTime.isBefore(bus.arrivalTime))
            res = -1;
        else if (this.arrivalTime.isAfter(bus.arrivalTime))
            res = 1;
        else
            res = 0;
        return res;
    }
}

