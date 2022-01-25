/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbzsogr.bankomat.main;

import ch.bbzsogr.bankomat.remote.BankSystem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author PlayWolfYT
 */
public class Bankomat {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        /*Scanner scanner = new Scanner(System.in);
        System.out.print("Enter card number: ");
        String cardNumber = scanner.nextLine();
        System.out.println("card number: " + cardNumber);
        
        BankCard card = new BankCard(new File("./resources/cards/" + cardNumber));
        
        int errorCount = 0;
        while(true) {
            System.out.print("Please enter card pin: ");
            String pin = scanner.nextLine();
            if(card.validatePin(pin)) {
                System.out.println("Pin is valid");
                break;
            } else {
                errorCount++;
                if(errorCount >= 3) {
                    // TODO: Consume card
                    System.err.println("Incorrect pin 3 times. Consuming card");
                    System.exit(0);
                }
                System.err.println("Invalid pin. Try again");
            }
        }
        
        System.out.println("READING FROM REMOTE BANK");
        // TODO: Read data from remote

        scanner.close();*/
    }



    
}
