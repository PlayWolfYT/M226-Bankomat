/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbzsogr.bankomat.remote;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author PlayWolfYT
 */
public class BankSystem {
    
    public static final File BANK_FILE = new File("./resources/bank.csv");

    public static BankAccount getAccount(String iban) {
        setup();

        try {
            return new BankAccount(iban);
        } catch(IOException e) {
            System.err.println("Bank could not be loaded.");
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private static void setup() {
        // Check if bank file actually exists, otherwise create the folders and the file itself.
        if(!BANK_FILE.exists()) {
            try {
                new File("./resources").mkdir();
                BANK_FILE.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

}