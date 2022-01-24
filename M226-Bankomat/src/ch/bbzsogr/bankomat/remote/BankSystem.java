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

    public BankSystem() {
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