/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Utill.Utill;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Crew;
import model.Flight;

/**
 *
 * @author ADMIN
 */
public class CrewList extends ArrayList<Crew> {

    Utill u = new Utill();
//    public boolean checkPilot(){
//        int count =0;
//        for (Crew thi : this) {
//            if(thi.getRole()=="Pilot"){
//                count ++;
//                if(count>2)return false;
//            }
//        }
//        return true;
//    }

    public void delete(FlightList a) {
        for (Crew thi : this) {
            for (Flight flight : a) {
                if (thi.getFlightNumber().equalsIgnoreCase(flight.getFlightNumber())) {
                    thi.setStatus(true);
                    thi.setFlightNumber("null");
                }
            }

        }
    }

    public void addCrew() {

        String name;
        String role = null;
        boolean status;
        String flightNumber;
        String id;
        boolean check = true;
        boolean checkID = true;

        do {
            id = u.inputNoEmpty("Crew ID: ");
            for (Crew thi : this) {
                if (id.equalsIgnoreCase(thi.getId())) {
                    System.err.println("Duplicate ID");
                    checkID = false;
                }
            }
        } while (!checkID);
        name = u.inputNoEmpty("Name: ");
        if (name == null) {
            return;
        }
        do {
            check = true;
            System.out.println("Role: ");
            System.out.println("+-----------------------+");
            System.out.println("| 1. Pilots.            |");
            System.out.println("| 2. Flight attendants. |");
            System.out.println("| 3. Ground staff.      |");
            System.out.println("+-----------------------+");
            int choice = Integer.parseInt(u.inputString("Choice Role: "));
            switch (choice) {
                case 1:
                    role = "Pilot";
                    break;
                case 2:
                    role = "Flight attendant";
                    break;
                case 3:
                    role = "Ground staff";
                    break;
                default:
                    check = false;
                    break;
            }
        } while (!check);
        flightNumber = u.inputString("Flight Number: ");
        if (flightNumber != null) {
            status = false;
        } else {
            status = true;
        }
        if (flightNumber.isEmpty()) {
            Crew cr = new Crew(id, name, role, "NULL", status);
            this.add(cr);
        } else {
            Crew cr = new Crew(id, name, role, flightNumber, status);
            this.add(cr);
        }

    }

    public void crewAssignments(FlightList a) {
        Date d = new Date();
        boolean check = false;
        SimpleDateFormat sd = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
        String id;

        id = u.inputNoEmpty("ID: ");
        if (id == null) {
            return;
        }
        for (Crew thi : this) {
            if (id.equalsIgnoreCase(thi.getId())) {
                String flightNumber = u.inputNoEmpty("Flight Number: ").toUpperCase();
                if (flightNumber == null) {
                    return;
                }
                for (Flight flight : a) {
                    try {
                        if (flightNumber.equalsIgnoreCase(flight.getFlightNumber())&&thi.isStatus() && sd.parse(sd.format(d)).before(sd.parse(sd.format(flight.getDepartureTime())))) {
                            check = true;
                            thi.setFlightNumber(flightNumber);
                            thi.setStatus(false);
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(CrewList.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (!check) {
                    System.out.println("Not Found");
                    return;
                }
            }
        }
    }

    public void show() {
        if (this.isEmpty()) {
            System.out.println("Empty List");
        }
        for (Crew thi : this) {
            System.out.println(thi.toString());
        }
    }

}
