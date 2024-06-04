/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package view;

import Utill.Utill;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlightService fs = new FlightService();
        Scanner sc = new Scanner(System.in);
        Utill u = new Utill();


        final String FLIGHT = "C:\\Java\\FlightMangement\\src\\main\\java\\txt\\Flight.dat";
        final String BOARDING = "C:\\Java\\FlightMangement\\src\\main\\java\\txt\\BoardingPasses.dat";
        final String CREW = "C:\\Java\\FlightMangement\\src\\main\\java\\txt\\CrewList.dat";

        boolean out =false;
        int choice = 0;
        boolean check = true;
        boolean checkInput = true;
        fs.loadFromFile(FLIGHT, 1);
        fs.loadFromFile(BOARDING, 2);
        fs.loadFromFile(CREW, 3);
        do {
            System.out.println("+-------------------------------MENU--------------------------------+");
            System.out.println("| 1. Flight schedule management.                                    |");
            System.out.println("| 2. Passenger reservation and booking.                             |");
            System.out.println("| 3. Passenger check-in and seat allocation.                        |");
            System.out.println("| 4. Crew management and assignments.                               |");
            System.out.println("| 5. Administrator access for system management.                    |");
            System.out.println("| 6. Data storage for flight details, reservations, and assignments.|");
            System.out.println("+-------------------------------------------------------------------+");
            do {
                System.out.print("(1...6): ");
                try {
                    choice = Integer.parseInt(sc.nextLine());
                    checkInput = true;
                } catch (Exception e) {
                    System.err.println(e);
                    checkInput = false;
                }
            } while (!checkInput);
            boolean checkCTN = false;
            switch (choice) {
                case 1:
                    if (fs.us.isAdmin()) {
                        checkCTN = false;
                        fs.flightScheduleManagement();
                    } else {
                        System.err.println("You need ADMIN rights!");
                    }
                    break;
                case 2:
                    fs.passengerReservationAndBooking();
                    break;
                case 3:
                    fs.passengerCheckInAndSeatAllocation();
                    break;
                case 4:
                    if (fs.us.isAdmin()) {
                        fs.crewManagementMndAssignments();
                    } else {
                        System.err.println("You need ADMIN rights!");
                    }
                    break;
                case 5:
                    fs.adminRights();
                    break;
                case 6:
                    fs.saveToFile(FLIGHT, 1);
                    fs.saveToFile(BOARDING, 2);
                    fs.saveToFile(CREW, 3);
                    break;
                default:
                    out=u.confirmYesNo("Do you want to quit? (y/n): ");
                    break;

            }

        } while (!out);
    }
}
