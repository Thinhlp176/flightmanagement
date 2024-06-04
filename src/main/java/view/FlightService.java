/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.BoardingPassesList;
import controller.CrewList;
import controller.FlightList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import model.AccessControl;
import model.BoardingPasses;
import model.Crew;
import model.Flight;
import model.User;

/**
 *
 * @author ADMIN
 */
public class FlightService {

    User us = new User();
    AccessControl ac = new AccessControl();
    BoardingPassesList b = new BoardingPassesList();
    FlightList fl = new FlightList();
    CrewList cl = new CrewList();

    public void flightScheduleManagement() {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        do {
            System.out.println("+------------------------------+");
            System.out.println("| 1. Add new flight.           |");
            System.out.println("| 2. Show all flihgt.          |");
            System.out.println("| 3. Delete flihgt.            |");
            System.out.println("| Orders-Go back to main menu. |");
            System.out.println("+------------------------------+");
            System.out.print("Choice: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.err.println(e);
            }
            switch (choice) {
                case 1:
                    fl.addFlight();
                    break;
                case 2:
                    fl.showAllFlight();
                    break;
                case 3:
                    fl.deleteAFlight(sc.nextLine());
                    break;
                default:
                    check = false;
            }
        } while (check);
    }

    public void passengerReservationAndBooking() {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        try {
            do {
                System.out.println("+------------------------------+");
                System.out.println("| 1. Search flight.            |");
                System.out.println("| 2. Booking.                  |");
                System.out.println("| Orders-Go back to main menu. |");
                System.out.println("+------------------------------+");
                System.out.print("Choice: ");
                int choice = 0;
                try {
                    choice = Integer.parseInt(sc.nextLine());
                } catch (Exception e) {
                    System.err.println(e);
                }
                switch (choice) {
                    case 1:
                        fl.searchFlight();
                        break;
                    case 2:
                        fl.chooseFlight(b);
                        break;
                    default:
                        check = false;
                }
            } while (check);
        } catch (Exception e) {
            System.err.println("Errol: " + e.getMessage());
        }
    }

    public void passengerCheckInAndSeatAllocation() {
        fl.chooseSeat(b);
    }

    public void crewManagementMndAssignments() {
        Scanner sc = new Scanner(System.in);
        boolean check = true;

        do {
            System.out.println("+------------------------------+");
            System.out.println("| 1. Add Crew.                 |");
            System.out.println("| 2. Crew assignments.         |");
            System.out.println("| 3. Show crew.                |");
            System.out.println("| Orders-Go back to main menu. |");
            System.out.println("+------------------------------+");
            System.out.print("Choice: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.err.println(e);
            }
            switch (choice) {
                case 1:
                    cl.addCrew();
                    break;
                case 2:
                    cl.crewAssignments(fl);
                    break;
                case 3:
                    cl.show();
                    break;
                default:
                    check = false;
            }
        } while (check);
    }

    public void adminRights() {
        boolean check = true;
        Scanner sc = new Scanner(System.in);

        System.out.println("+------------------------------+");
        System.out.println("| 1. Grant Admin Right.        |");
        System.out.println("| 2. Revoke Admin Rights.      |");
        System.out.println("| Orders-Go back to main menu. |");
        System.out.println("+------------------------------+");
        System.out.print("Choice: ");
        int choice = 0;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.err.println(e);
        }

        switch (choice) {
            case 1:
                if (us.isAdmin()) {
                    System.out.println("You are already an ADMIN!");
                } else {
                    ac.grantAdminRights(us);
                }
                break;
            case 2:
                if (us.isAdmin()) {
                    ac.revokeAdminRights(us);
                } else {
                    System.out.println("You are not an ADMIN!");
                }
                break;
            default:
                check = false;
                break;

        }

    }

    public boolean checkFileExists(String fileName) {
        File f = new File(fileName);
        try {
            if (f.exists() && f.isFile()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void saveToFile(String path, int type) {
        try {
            File f = new File(path);
            // Create a file with name and the link have been declared
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream o = new ObjectOutputStream(fos);
            switch (type) {
                case 1:
                    for (Flight thi : fl) {
                        o.writeObject(thi);
                    }

                    break;
                case 2:
                    for (BoardingPasses bo : b) {
                        o.writeObject(bo);
                    }
                    break;

                default:
                    for (Crew cr : cl) {
                        o.writeObject(cr);
                    }
                    break;
            }

            o.close();    // Close ObjectOutputStream after completing saving
            fos.close();  // Close file before end  
            System.out.println("Successfull");
        } catch (FileNotFoundException e) {
            System.out.println("=>> File not found");
        } catch (IOException e) {
            System.out.println("=>> Error initializing stream");
        }
    }

    public void loadFromFile(String path, int type) {
        if (checkFileExists(path)) {
            try {
                InputStream fi = new FileInputStream(new File(path));
                ObjectInputStream oi = new ObjectInputStream(fi);

                boolean more = true;
                switch (type) {
                    case 1:

                        while (more && (fi.available() > 0)) {
                            Flight fll = (Flight) oi.readObject();
                            if (fll != null) {
                                fl.add(fll);
                            } else {
                                more = false;
                            }
                        }
                        break;
                    case 2:
                        while (more && (fi.available() > 0)) {
                            BoardingPasses bo = (BoardingPasses) oi.readObject();
                            if (bo != null) {
                                b.add(bo);
                            } else {
                                more = false;
                            }
                        }
                        break;

                    default:
                        while (more && (fi.available() > 0)) {
                            Crew cr = (Crew) oi.readObject();
                            if (cr != null) {
                                cl.add(cr);
                            } else {
                                more = false;
                            }
                        }

                        break;
                }

                oi.close();
                fi.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("There was an error while reading data : " + e.getMessage());
            }

        } else {
            System.out.println("The Products data file could not be fould !");
        }

    }

}
