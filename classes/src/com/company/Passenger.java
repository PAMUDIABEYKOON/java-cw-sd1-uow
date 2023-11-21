package com.company;

public class Passenger {
    String firstName;
    String surName;
    int expenses;

    // No argument constructor
    public Passenger() {
    }

    // All argument constructor
    public Passenger(String firstName, String surName, int expenses) {
        this.firstName = firstName;
        this.surName = surName;
        this.expenses = expenses;
    }
}




