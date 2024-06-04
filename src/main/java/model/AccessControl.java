/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class AccessControl {
    final String PASSWORD = "123"; 
    public void grantAdminRights(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Password: ");
        String pw= sc.nextLine();
        if(pw.equalsIgnoreCase(PASSWORD)){
            user.setAdmin(true);
            System.out.println("Grant Admin right");
        }else{
            System.err.println("Wrong!");
        }
    }

    public void revokeAdminRights(User user) {
        System.out.println("Revoke Admin Right");
        user.setAdmin(false);
    }

}
