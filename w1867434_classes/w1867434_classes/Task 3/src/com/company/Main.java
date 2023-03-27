package com.company;
import java.io.*;
import java.util.*;

public class Main {
    static List<Cabin> cabins = new ArrayList<>();
    static List<Passenger> waitingList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        initialise();

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
            System.out.println("T: Print Expenses");
            System.out.println("Q: Quit Program");

            String Selection = input.next().toUpperCase();
            switch (Selection) {

                case "A":
                    addPassengerToCabin();
                    break;
                case "V":
                    viewAllCabins();
                    break;
                case "E":
                    viewEmptyCabins();
                    break;
                case "D":
                    removePassenger();
                    break;
                case "F":
                    findCabinByPassengerName();
                    break;
                case "S":
                    storeData();
                    break;
                case "L":
                    loadData();
                    break;
                case "O":
                    viewByAlphabeticalOrder();
                    break;
                case "T":
                    printExpenses();
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
    private static void initialise() {
        for (int x = 0; x < 13; x++) {
            Cabin cabin = new Cabin();
            cabins.add(cabin);
        }
        System.out.println("initialised");
    }


    // add a passenger to a selected cabin
    private static void addPassengerToCabin() {

        try {
            System.out.println("Enter cabin number (1-12) : ");
            Scanner input = new Scanner(System.in);
            int cabinNum = input.nextInt();

            if (cabinNum > 0 && cabinNum < 13) {
                Cabin cabin = cabins.get(cabinNum - 1);
                List<Passenger> passengers = cabin.getPassengers();

                if (passengers.size() == 3) {
                    checkAvailability();

                } else {
                    System.out.println("Enter the firstname : ");
                    String firstname = input.next();
                    firstname = firstname.toLowerCase();

                    System.out.println("Enter the lastname : ");
                    String lastname = input.next();
                    lastname = lastname.toLowerCase();

                    System.out.println("Enter expenses : $ ");
                    int expense = input.nextInt();

                    Passenger passenger = new Passenger(firstname, lastname, expense);
                    passengers.add(passenger);
                    cabin.setPassengers(passengers);
                    System.out.println("Passenger added");
                }
            } else {
                System.out.println("Invalid cabin number");
            }
        } catch (InputMismatchException e) {
            System.out.println("Integers only");
        }
    }


     // checks cabin availability
    private static void checkAvailability() {
        boolean isCruiseShipFull = true;
        for (int i = 0; i < 12; i++) {
            Cabin cabin = cabins.get(i);
            List<Passenger> passengers = cabin.getPassengers();

            if (passengers.size() != 3) {
                isCruiseShipFull = false;
            }
        }

        if (!isCruiseShipFull) {
            System.out.println("Cabin is full");
        } else {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the firstname : ");
            String firstname = input.next();
            firstname = firstname.toLowerCase();

            System.out.println("Enter the lastname : ");
            String lastname = input.next();
            lastname = lastname.toLowerCase();

            System.out.println("Enter expenses : $ ");
            int expense = input.nextInt();

            Passenger passenger = new Passenger(firstname, lastname, expense);
            waitingList.add(passenger);
            System.out.println("Cruise ship is full. Passenger is added to waiting list");
        }
    }


    // view all cabins
    private static void viewAllCabins() {
        for (int i = 0; i < 12; i++) {
            Cabin cabin = cabins.get(i);
            System.out.println("\nCabin " + (i + 1));
            List<Passenger> passengers = cabin.getPassengers();

            if (passengers.size() != 0) {
                for (int j = 0; j < passengers.size(); j++) {
                    System.out.println("    Passenger " + (j + 1));
                    System.out.println("    First name: " + passengers.get(j).firstName);
                    System.out.println("    Last name: " + passengers.get(j).surName);
                    System.out.println("    Expenses: $" + passengers.get(j).expenses + "\n");
                }
            } else {
                System.out.println("No passengers");
            }
        }
    }


    // display empty cabins
    private static void viewEmptyCabins() {
        System.out.println("Empty Cabins");
        for (int i = 0; i < 12; i++) {
            Cabin cabin = cabins.get(i);
            List<Passenger> passengers = cabin.getPassengers();

            if (passengers.size() == 0) {
                System.out.println("    Cabin " + (i + 1));
            }
        }
    }


    // remove a passenger
    private static void removePassenger() {

        try {
            boolean isCruiseShipFull = true;
            for (int i = 0; i < 12; i++) {
                Cabin cabin = cabins.get(i);
                List<Passenger> passengers = cabin.getPassengers();

                if (passengers.size() != 3) {
                    isCruiseShipFull = false;
                }
            }

            Scanner input = new Scanner(System.in);
            System.out.println("Enter cabin number to delete passenger (1-12)");
            int cabinNum = input.nextInt();

            if (cabinNum < 13 && cabinNum > 0) {
                System.out.println("Enter passenger's first name");
                String firstName = input.next();
                firstName = firstName.toLowerCase();

                Cabin cabin = cabins.get(cabinNum - 1);
                List<Passenger> passengers = cabin.getPassengers();
                for (int j = 0; j < passengers.size(); j++) {

                    if (passengers.get(j).firstName.equals(firstName)) {
                        if (isCruiseShipFull) {
                            passengers.set(j, waitingList.get(0));
                            waitingList.remove(0);
                        } else {
                            passengers.remove(j);
                        }
                        cabin.setPassengers(passengers);
                        cabins.set(cabinNum, cabin);
                        System.out.println("Passenger deleted");
                    }
                }
            } else {
                System.out.println("Invalid cabin");
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Integers only");
        }
    }


    // find cabin from passenger's name
    private static void findCabinByPassengerName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter passenger's first name");
        String firstName = input.next();
        firstName = firstName.toLowerCase();

        for (int i = 0; i < 12; i++) {
            Cabin cabin = cabins.get(i);
            List<Passenger> passengers = cabin.getPassengers();
            for (int j = 0; j < passengers.size(); j++) {
                if (passengers.get(j).firstName.equals(firstName)) {
                    System.out.println("Cabin " + (i + 1));
                }
            }
        }
    }


    // store data into a file
    private static void storeData() {
        String data = "";
        for (int i = 0; i < 12; i++) {
            Cabin cabin = cabins.get(i);
            data = data + "Cabin " + Integer.toString(i + 1) + "\n";
            List<Passenger> passengers = cabin.getPassengers();

            if (passengers.size() != 0) {
                for (int j = 0; j < passengers.size(); j++) {
                    data = data + "\n";
                    data = data + "     Passenger " + Integer.toString(j + 1) + "\n";
                    data = data + "     First name: " + passengers.get(j).firstName + "\n";
                    data = data + "     Surname: " + passengers.get(j).surName + "\n";
                    data = data + "     Expenses: $" + Integer.toString(passengers.get(j).expenses) + "\n";
                }
                data = data + "\n";
            } else {
                data = data + "No passengers\n\n";
            }
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
    private static void loadData() {
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
    private static void viewByAlphabeticalOrder() {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Cabin cabin = cabins.get(i);
            List<Passenger> passengers = cabin.getPassengers();

            if (passengers.size() != 0) {
                for (int j = 0; j < passengers.size(); j++) {
                    names.add(passengers.get(j).firstName + " - Cabin " + Integer.toString(i + 1));
                }
            }
        }

        for (int i = 0; i < names.size(); i++) {
            for (int j = i + 1; j < names.size(); j++) {
                String temp;
                if (names.get(i).compareTo(names.get(j)) > 0) {
                    temp = names.get(i);
                    names.set(i, names.get(j));
                    names.set(j, temp);
                }
            }
        }
        System.out.print("Sorted by Albhabetical Order\n\n");
        for (int x = 0; x < names.size(); x++) {
            System.out.print(names.get(x) + "\n");
        }
    }


    // print total expenses
    private static void printExpenses() {
        int total = 0;
        for (int i = 0; i < 12; i++) {
            Cabin cabin = cabins.get(i);
            List<Passenger> passengers = cabin.getPassengers();
            if (passengers.size() != 0) {
                for (int j = 0; j < passengers.size(); j++) {
                    total += passengers.get(j).expenses;
                }
            }
        }
        System.out.print("Total expenses: $" + total + "\n");
    }
}