package com.company;
import java.io.*;
import java.util.*;

public class Cabin{

    int cabinNumber;
    String cabinName;
    List<Passenger> passengers = new ArrayList<>();

    // No argument constructor
    public Cabin() {

    }

    // All argument constructor
    public Cabin(int cabinNumber, String cabinName, List<Passenger> passengers) {
        this.cabinNumber = cabinNumber;
        this.cabinName = cabinName;
        this.passengers = passengers;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
