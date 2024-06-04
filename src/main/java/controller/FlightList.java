/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controller;

import Utill.Utill;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BoardingPasses;
import model.Flight;
import model.Passengers;
import model.Seat;

/**
 *
 * @author ADMIN
 */
public class FlightList extends ArrayList<Flight> {

    Scanner sc = new Scanner(System.in);
    Utill u = new Utill();
    SimpleDateFormat sd = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
    Date d = new Date();

    public boolean askToContinue(String welcome) {
        System.out.print(welcome);
        String yn = sc.nextLine().toUpperCase();
        if (yn.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }
    
    public void showAllFlight(){
        if(this.isEmpty()) System.out.println("Empty List");
        for (Flight thi : this) {
            System.out.println(thi.toString());
        }
    }

    public static String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder code = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            code.append(randomChar);
        }
        return code.toString();
    }

    public Date convertDateWithFormat(Date h, String format) throws ParseException {
        SimpleDateFormat nh = new SimpleDateFormat(format);
        return nh.parse(nh.format(h));
    }

    public boolean checkValidTime(Date valid) throws ParseException {
        if (convertDateWithFormat(d, "dd/MM/yyyy").compareTo(convertDateWithFormat(valid, "dd/MM/yyyy")) == 0) {
            LocalTime lt = LocalTime.now();
            if (convertDateWithFormat(d, "HH:mm").compareTo(convertDateWithFormat(valid, "HH:mm")) <= 0) {
                return true;
            } else {
                return false;
            }
        } else if (convertDateWithFormat(d, "dd/MM/yyyy").compareTo(convertDateWithFormat(valid, "dd/MM/yyyy")) < 0) {
            return true;
        }
        return false;
    }

    public ArrayList<Seat> addSeat(int numberSeat) {
        int count = 0;
        ArrayList<Seat> arraySeat = new ArrayList<>();
        String[] s = {"A", "B", "C", "D", "E", "F"};
        for (int i = 1; i < numberSeat; i++) {
            for (String string : s) {
                String id = string + i;
                Seat seat = new Seat(id);
                arraySeat.add(seat);
                count++;
                if (count == numberSeat) {
                    return arraySeat;
                }
            }
        }
        return null;
    }

    public void showSeat(ArrayList<Seat> Seat) {
        int countRow = 0;
        int countColumn = 0;
        for (Seat availableSeat : Seat) {
            countRow++;
            countColumn++;
            System.out.printf("%-5s%-9s", availableSeat.getSeatID() + ": ", (availableSeat.isAvailable() ? "Available" : "Unvalid"));
            System.out.print("  ");
            if (countRow == 6) {
                countRow = 0;
                countColumn = 0;
                System.out.println();
            } else if (countColumn == 3) {
                countColumn = 0;
                System.out.print("              ");
            }
        }
        System.out.println();
    }

    public void addFlight() {
        String flightNumber;
        String departureCity;
        String destinationCity;
        Date departureTime;
        Date arrivalTime;
        int numberSeat;
        String keep;
        boolean check = true;

        //flightNumber
        do {
            check = true;
            flightNumber = u.inputWithMatches("Flight Number: ", "F\\d{4}", "Wrong fotmat!");
            if (flightNumber == null) {
                return;
            }
            for (Flight thi : this) {
                if (flightNumber.equals(thi.getFlightNumber())) {
                    System.out.println("Duplicate ID");
                    if (askToContinue("Do you want to continue! (y/n):")) {
                        check = false;
                    } else {
                        return;
                    }

                }
            }
        } while (!check);
        //departureCity
        departureCity = u.inputNoEmpty("Departure City: ").toUpperCase();
        if (departureCity == null) {
            return;
        }
        //destinationCity
        destinationCity = u.inputNoEmpty("Destination City: ").toUpperCase();
        if (destinationCity == null) {
            return;
        }
        //departureTime
        do {
            departureTime = u.inputDate("Departure Time (HH:mm-dd/MM/yyyy): ");
            if (departureTime == null) {
                return;
            }
            try {
                if (!checkValidTime(departureTime)) {
                    throw new Exception("Not Valid! Please enter after current day.");
                }
                check = true;
            } catch (Exception e) {
                System.err.println(e);
                check = false;
            }
        } while (!check);

        //arrivalTime
        do {
            check = true;
            arrivalTime = u.inputDate("Arrival Time (HH:mm-dd/MM/yyyy): ");
            if (arrivalTime == null) {
                return;
            }
            if (arrivalTime.before(departureTime)) {
                System.out.println("ARRIVAL TIME must be after DEPARTURE TIME");
                check = false;
            }

        } while (!check);
        //seat

        numberSeat = u.inputInt("Number of seat: ", "Number of seat > 0");

        Flight fl = new Flight(flightNumber, departureCity, destinationCity, departureTime, arrivalTime, addSeat(numberSeat), numberSeat);
        this.add(fl);
    }

    public void manageFlightSchedules() {
        String flightNumber;
        String departureCity;
        String destinationCity;
        Date departureTime;
        Date arrivalTime;
        int numberSeat;
        String keep;
        boolean check = false;
        boolean checkExits = false;

        //flightNumber
        check = true;
        flightNumber = u.inputWithMatches("Flight Number: ", "F\\d{4}", "Wrong fotmat!");
        if (flightNumber == null) {
            return;
        }
        for (Flight thi : this) {
            if (flightNumber.equals(thi.getFlightNumber())) {
                checkExits = true;
                //departureCity
                departureCity = u.inputString("Departure City: ").toUpperCase();
                if (departureCity == null) {
                } else {
                    thi.setDepartureCity(departureCity);
                }
                //destinationCity
                destinationCity = u.inputString("Destination City: ").toUpperCase();
                if (destinationCity == null) {
                } else {
                    thi.setDestinationCity(destinationCity);
                }
                //departureTime
                do {
                    departureTime = u.inputDateCanNull2("Departure Time (HH:mm-dd/MM/yyyy): ");
                    if (departureTime == null) {
                        break;
                    }
                    try {
                        if (!checkValidTime(departureTime)) {
                            throw new Exception("Not Valid! Please enter after current day.");
                        }else{
                            thi.setDepartureTime(departureTime);
                        }
                        check = true;
                    } catch (Exception e) {
                        System.err.println(e);
                        check = false;
                    }
                } while (!check);
                //arrivalTime
                do {
                    check = true;
                    arrivalTime = u.inputDateCanNull2("Arrival Time (HH:mm-dd/MM/yyyy): ");
                    if (arrivalTime == null) {
                        break;
                    }
                    if (arrivalTime.before(departureTime)) {
                        System.out.println("ARRIVAL TIME must be after DEPARTURE TIME");
                        check = false;
                    }else{
                        thi.setArrivalTime(arrivalTime);
                    }
                    

                } while (!check);                
            }

        }
        if(!checkExits){
            System.out.println("Not Found");
        }

    }

    public boolean searchFlight() throws ParseException {
        boolean check = false;

        System.out.println("SEARCH FLIGHT");
        String departureCity = u.inputNoEmpty("Departure City: ").toUpperCase();
        if (departureCity == null) {
            return false;
        }
        String destinationCity = u.inputNoEmpty("Destination City: ").toUpperCase();
        if (destinationCity == null) {
            return false;
        }
        Date departureTime = u.inputDateNotHours("Departure Time (dd/MM/yyyy): ");
        if (departureTime == null) {
            return false;
        }
        Date arrivalTime = u.inputDateCanNull("Arrival Time (dd/MM/yyyy): ");

        Date d = new Date();
        for (Flight thi : this) {
            if (departureCity.equals(thi.getDepartureCity())
                    && destinationCity.equals(thi.getDestinationCity())
                    && departureTime.compareTo(convertDateWithFormat(thi.getDepartureTime(), "dd/MM/yyyy")) == 0) {
                if (arrivalTime == null) {
                    if (convertDateWithFormat(d, "HH:mm").compareTo(convertDateWithFormat(thi.getDepartureTime(), "HH:mm")) < 0) {
                        System.out.println(thi.toString());
                        check = true;
                    }
                } else if (arrivalTime.compareTo(convertDateWithFormat(thi.getArrivalTime(), "dd/MM/yyyy")) == 0) {
                    System.out.println(thi.toString());
                    check = true;
                }
            }
        }
        if (!check) {
            System.out.println("Not Found");
            return false;
        }
        return true;
    }

    public BoardingPasses chooseFlight(ArrayList<BoardingPasses> boarding) {
        boolean check = true;
        Passengers p = new Passengers();
        BoardingPasses b;
        String flightNumber = u.inputNoEmpty("Flight Number: ").toUpperCase();
        if (flightNumber.isEmpty()) {
            return null;
        }
        for (Flight thi : this) {
            if (flightNumber.equals(thi.getFlightNumber())) {
                System.out.println("PASSENGER INFO");
                String name = u.inputNoEmpty("Name: ");
                if (name == null) {
                    return null;
                }
                p.setName(name);
                String phone = u.inputWithMatches("Phone: ", "^[0-9]{9,11}$", "9 to 11 digits");
                if (phone == null) {
                    return null;
                }
                p.setPhoneNumber(phone);
                String cccd = u.inputWithMatches("CCCD: ", "^[0-9]{12}$", "12 digits");
                if (cccd == null) {
                    return null;
                }
                p.setCccd(cccd);
                String reservationID = "";
                do {
                    check = true;
                    reservationID = generateRandomCode(6);
                    for (BoardingPasses boardingPasses : boarding) {
                        if (reservationID.equals(boardingPasses.getReservationID())) {
                            check = false;
                        }
                    }
                } while (!check);
                p.setReservationID(reservationID);
                System.out.println("Reservation ID: " + reservationID);
                b = new BoardingPasses(p, thi, reservationID);
                boarding.add(b);
                return b;
            }
        }
        return null;
    }

    public void chooseSeat(ArrayList<BoardingPasses> boaringPasses) {
        boolean check = false;
        boolean checkSeat = false;
        String reservationID;
        do {
            do {
                check = false;
                try {
                    reservationID = u.inputNoEmpty("Reservation ID: ").toUpperCase();
                } catch (Exception e) {
                    return;
                }
                for (Flight thi : this) {
                    for (Seat availableSeat : thi.getAvailableSeats()) {
                        if (availableSeat.getPassenger() != null) {
                            if (availableSeat.getPassenger().getReservationID().equals(reservationID)) {
                                System.err.println("Duplicate Reservation ID");
                                check = true;
                                break;
                            }
                        }
                    }
                    if (check) {
                        break;
                    }
                }
            } while (check);
            for (BoardingPasses b : boaringPasses) {
                if (reservationID.equals(b.getReservationID())) {
                    check = true;
                    boolean k = true;
                    try {
                        k = convertDateWithFormat(d, "HH:mm-dd/MM/yyyy").before(convertDateWithFormat(b.getFlight().getDepartureTime(), "HH:mm-dd/MM/yyyy"));
                    } catch (ParseException ex) {
                        Logger.getLogger(FlightList.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (!k) {
                        System.err.println("Flight has departed");
                    } else {
                        this.showSeat(b.getFlight().getAvailableSeats());
                        do {
                            check = false;
                            checkSeat = false;
                            String seat = u.inputNoEmpty("Choose Seat: ").toUpperCase();
                            if (seat == null) {
                                return;
                            }
                            for (Seat availableSeat : b.getFlight().getAvailableSeats()) {
                                if (seat.equals(availableSeat.getSeatID())) {
                                    check = true;
                                    if (availableSeat.isAvailable()) {
                                        availableSeat.setPassenger(b.getPassenger());
                                        availableSeat.setAvailable(false);
                                        System.out.println("Successfull");
                                        System.out.println(b.getPassenger().toString()+", "+seat);
                                    } else {
                                        System.out.println("Sold");
                                        checkSeat = true;
                                    }
                                }
                            }
                        } while (checkSeat);
                    }
                }
            }
            if (!check) {
                System.out.println("Not found");
                if (askToContinue("Do you want to continue? (y/n): ")) {
                    check = false;
                } else {
                    return;
                }
            }
        } while (!check);
    }
    
    public void deleteAFlight(String fn){
        if(this.isEmpty()) System.out.println("Empty List");
        boolean check=false;
        for (Flight thi : this) {
            if(fn.toUpperCase().equalsIgnoreCase(thi.getFlightNumber())){
                this.remove(thi);
                check=true;
            }
        }
        if(!check) System.out.println("Not found");
    }

    public void delete(){
        Date nd =new Date();
        FlightList a =new FlightList();
        for (Flight thi : this) {
            if(thi.getArrivalTime().before(nd)){
                a.add(thi);
            }
        }
        for (Flight flight : a) {
            this.remove(flight);
        }
    }

}
