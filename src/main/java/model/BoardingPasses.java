/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class BoardingPasses  implements Serializable {
    private Passengers passenger;
    private Flight flight;
    private String reservationID;

    public BoardingPasses() {
    }

    public BoardingPasses(Passengers passenger, Flight flight, String reservationID) {
        this.passenger = passenger;
        this.flight = flight;
        this.reservationID = reservationID;
    }

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public Passengers getPassenger() {
        return passenger;
    }

    public void setPassenger(Passengers passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "BoardingPasses{" + "passenger=" + passenger + ", flight=" + flight + ", reservationID=" + reservationID + '}';
    }

    
    
    
}
