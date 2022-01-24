/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbzsogr.bankomat.main;

import ch.bbzsogr.bankomat.BankKarte;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author PlayWolfYT
 */
public class Bankomat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        System.out.println("Test");
        
        File file = new File("./cards/test.txt");
        file.createNewFile();
        BankKarte bankKarte = new BankKarte(file);
        bankKarte.writeToFile();
    }
    
}
