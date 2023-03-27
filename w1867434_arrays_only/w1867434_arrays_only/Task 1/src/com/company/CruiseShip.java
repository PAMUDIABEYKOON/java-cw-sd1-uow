package com.company;
import java.io.*;
import java.util.*;

public class CruiseShip {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String[] cabins = new String[13];
        initialise(cabins);

        boolean run = true;
        while (run) {
            System.out.println("\n Choose One Of The Options Below");
            System.out.println("A: Add Passenger");
            System.out.println("V: View All Cabins");
            System.out.println("E: Display Empty Cabins");
            System.out.println("D: Delete Passenger From Cabin");
            System.out.println("F: Find Cabin From Passenger's Name");
            System.out.println("S: Store Program Data Into File");
            System.out.println("L: Load Program Data From File");
            System.out.println("O: View Passengers By Alphabetical Order");
            System.out.println("Q: Quit Program");

            String Selection = input.next().toUpperCase();
            switch (Selection) {

                case "A":
                    addPassengerToCabin(cabins);
                    break;
                case "V":
                    viewAllCabins(cabins);
                    break;
                case "E":
                    viewEmptyCabins(cabins);
                    break;
                case "D":
                    removePassenger(cabins);
                    break;
                case "F":
                    findCabinByPassengerName(cabins);
                    break;
                case "S":
                    storeData(cabins);
                    break;
                case "L":
                    loadData(cabins);
                    break;
                case "O":
                    viewByAlphabeticalOrder(cabins);
                    break;
                case "Q":
                    run = false;
                    break;
                default:
                    System.out.println("Invalid Selection");
                    break;
            }
        }
    }


    // initialise
    private static void initialise(String cabins[]) {
        for (int x = 1; x < 13; x++) cabins[x] = "e";
        System.out.println("initialised");
    }


    // add a passenger to a selected cabin
    private static void addPassengerToCabin(String cabins[]) {

        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter cabin number (1-12) : ");
            int cabinNum = input.nextInt();

            if (cabinNum < 13 && cabinNum > 0) {
                System.out.println("Enter name for cabin " + cabinNum + " :");
                String passengerName = input.next();
                passengerName = passengerName.toLowerCase();
                cabins[cabinNum] = passengerName;

                System.out.println("Passenger Added Successfully....!");

            } else {
                System.out.println("Invalid Selection");
            }
        } catch (InputMismatchException e) {
            System.out.println("Integers only");
        }
    }


    // view all cabins
    private static void viewAllCabins(String cabins[]) {
        for (int x = 1; x < 13; x++) {
            System.out.println("Cabin " + x);
            System.out.println(cabins[x] + "\n");
        }
    }


    // display empty cabins
    private static void viewEmptyCabins(String cabins[]) {
        System.out.println("Empty Cabins");
        for (int x = 1; x < 13; x++) {
            if (cabins[x].equals("e")) {
                System.out.println("    Cabin " + x);
            }
        }
    }


    // remove a passenger
    private static void removePassenger(String cabins[]) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter cabin number to delete the passenger(1-12)");
            int cabinNum = input.nextInt();

            if (cabinNum < 13 && cabinNum >0) {
                cabins[cabinNum] = "e";
                System.out.println("Passenger Deleted Successfully....!");
            } else {
                System.out.println("Invalid Selection");
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Integers only");
        }
    }


    // find cabin from passenger's name
    private static void findCabinByPassengerName(String cabins[]) {
        String customerName;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name to find the Cabin: ");
        customerName = input.next();
        customerName = customerName.toLowerCase();
        boolean Find = false;
        for (int x = 1; x < 13; x++) {
            if (customerName.equals(cabins[x])) {
                System.out.println(customerName + " is in cabin " + x);
                Find = true;
            }
        }
        if (Find == false) {
            System.out.println("Nobody is booked with that name.");
        }
    }


    // store data into a file
    private static void storeData(String cabins[]) {
        String data = "";
        for (int x = 1; x < 13; x++) {
            data = data + "Cabin " + x + "\n";

            if (cabins[x].equals("e")) {
                data = data + "No passengers";

            } else {
                data = data + cabins[x];
            }
            data = data + "\n\n";
        }

        try {
            FileWriter file = new FileWriter("DataFile.txt");
            file.write(data);
            file.close();
        } catch (IOException e) {
            System.out.println("Error Occurred");
            e.printStackTrace();
        }
        System.out.println("File Saved Successfully....!");
    }


    // load data from the file
    private static void loadData(String cabins[]) {
        try {
            File fileObj = new File("DataFile.txt");
            Scanner myReader = new Scanner(fileObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println("Error Occurred");
            e.printStackTrace();
        }
        System.out.println("File Loaded Successfully....!");
    }


    // view passenger list by alphabetical order
    private static void viewByAlphabeticalOrder(String cabins[]) {
        String[] names = new String[cabins.length];
        for (int x = 1; x < 13; x++) {
            names[x] = cabins[x];
        }

        for (int i = 1; i < 13; i++) {
            for (int j = i + 1; j < 13; j++) {
                String temp;
                if (names[i].compareTo(names[j]) > 0) {
                    temp = names[i];
                    names[i] = names[j];
                    names[j] = temp;
                }
            }
        }
        System.out.print("Sorted by Albhabetical Order\n");
        for (int x = 1; x < 13; x++) {
            System.out.print(names[x] + "\n");
        }
    }
}