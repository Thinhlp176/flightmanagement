/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import java.util.ArrayList;
import model.BoardingPasses;
import model.Flight;

/**
 *
 * @author ADMIN
 */
public class BoardingPassesList extends ArrayList<BoardingPasses>{
    
    
    public void delete(FlightList a){
        BoardingPassesList b = new BoardingPassesList();
        for (BoardingPasses thi : this) {
            for (Flight flight : a) {
                if(thi.getFlight().equals(flight.getFlightNumber())){
                    b.add(thi);
                }
            }
        }
        for (BoardingPasses boardingPasses : b) {
            this.remove(boardingPasses);
        }
    }
}
