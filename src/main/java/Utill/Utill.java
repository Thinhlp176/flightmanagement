/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utill;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class Utill {

    
    SimpleDateFormat sd = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
    SimpleDateFormat nh = new SimpleDateFormat("dd/MM/yyyy");
    Date d = new Date();
    
    public boolean confirmYesNo(String welcome){
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String input = sc.nextLine();
        if(input.equalsIgnoreCase("y")) return true;
        else return false;
    }

    public String inputWithMatches(String welcome, String matches, String errorMatches) {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        do {
            try {
                System.out.print(welcome);
                String result = sc.nextLine().toUpperCase();
                if (result.isEmpty()) {
                    System.out.print("Do you want to input again? (y/n): ");
                    String yn = sc.nextLine().toUpperCase();
                    if (yn.equals("Y")) {
                        throw new Exception("Input again!");
                    } else {
                        return null;
                    }
                }
                if (!result.matches(matches)) {
                    throw new Exception(errorMatches);
                }
                return result;
            } catch (Exception e) {
                System.err.println(e);
                check = false;
            }
        } while (!check);
        return null;
    }

    public String inputNoEmpty(String welcome) {
        
        String input;
        Scanner sc =new Scanner(System.in);
        boolean check = true;
        do {
            System.out.print(welcome);
            try {
                input = sc.nextLine();
                if (input.isEmpty()) {
                    System.out.print("Do you want to input again? (y/n): ");
                    String yn = sc.nextLine().toUpperCase();
                    if (yn.equals("Y")) {
                        throw new Exception("Input again!");
                    } else {
                        return null;
                    }
                }
                return input;
            } catch (Exception e) {
                System.err.println(e);
                check = false;
            }
        } while (!check);
        return null;
    }

    public Date inputDate(String welcome) {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean check = true;
        do {
            System.out.print(welcome);
            try {
                input = sc.nextLine();
                if (input.isEmpty()) {
                    System.out.print("Do you want to input again? (y/n): ");
                    String yn = sc.nextLine().toUpperCase();
                    if (yn.equals("Y")) {
                        throw new Exception("Input again!");
                    } else {
                        return null;
                    }
                }
                Date rt = sd.parse(input);
                return rt;
            } catch (Exception e) {
                System.err.println(e);
                check = false;
            }
        } while (!check);
        return null;
    }
    
    public Date inputDateNotHours(String welcome) {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean check = true;
        do {
            System.out.print(welcome);
            try {
                input = sc.nextLine();
                if (input.isEmpty()) {
                    System.out.print("Do you want to input again? (y/n): ");
                    String yn = sc.nextLine().toUpperCase();
                    if (yn.equals("Y")) {
                        throw new Exception("Input again!");
                    } else {
                        return null;
                    }
                }
                
                Date rt = nh.parse(input);
                return rt;
            } catch (Exception e) {
                System.err.println(e);
                check = false;
            }
        } while (!check);
        return null;
    }
    
    public Date inputDateCanNull(String welcome) {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean check = true;
        do {
            System.out.print(welcome);
            try {
                input = sc.nextLine();
                if (input.isEmpty()) return null;
                Date rt = nh.parse(input);
                return rt;
            } catch (Exception e) {
                System.err.println(e);
                check = false;
            }
        } while (!check);
        return null;
    }
public Date inputDateCanNull2(String welcome) {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean check = true;
        do {
            System.out.print(welcome);
            try {
                input = sc.nextLine();
                if (input.isEmpty()) return null;
                Date rt = sd.parse(input);
                return rt;
            } catch (Exception e) {
                System.err.println(e);
                check = false;
            }
        } while (!check);
        return null;
    }
    
    public int inputInt(String welcome, String error) {
        Scanner sc = new Scanner(System.in);
        int input;
        String keep;
        boolean check = true;
        do {
            System.out.print(welcome);
            try {
                keep=sc.nextLine();
                if(keep.isEmpty()){
                    throw new Exception("Not null");
                }
                input = Integer.parseInt(keep);
                if (input <= 0) {
                    throw new Exception(error);
                }
                return input;
            } catch (Exception e) {
                System.err.println(e);
                check = false;
            }
        } while (!check);
        return 0;
    }
    
    public String inputString(String welcome){
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        return sc.nextLine();
    }
}
