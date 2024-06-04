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
public class Passengers {
    private String name;
    private String phoneNumber;
    private String cccd;
    private String reservationID;


    public Passengers() {
    }

    public Passengers(String name, String phoneNumber, String cccd, String reservationID, Flight flight) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.cccd = cccd;
        this.reservationID = reservationID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    

    @Override
    public String toString() {
        return name + ", " + phoneNumber + ", " + cccd + ", " + reservationID;
    }


    
    
}
