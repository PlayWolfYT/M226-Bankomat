/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbzsogr.bankomat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author PlayWolfYT
 */
public class BankKarte {
    
    private final File cardFile;
    
    private String cardSurname;
    private String cardName;
    private String cardAccountDesignation;
    private String cardIBAN;
    private String cardBankName;
    private String cardNumber;
    private String cardExpirationMonth;
    private String cardExpirationYear;
    private String cardPinCode;
    
    /**
     * Creates a new BankKarte instance. This instance then contains all the information about the card that it needs.
     * @param cardFile The file which contains the information about the card.
     * @throws FileNotFoundException If the specified file does not exist.
     * @throws IOException If the specified file could not get read for some reason.
     */
    public BankKarte(File cardFile) throws FileNotFoundException, IOException {
        this.cardFile = cardFile;
        loadFromFile();
    }
    
    
    /**
     * Reads all the card information from the file that was specified in the constructor
     * Should also only be called by the constructor, since this overrides all the local variables with the ones from the file.
     * 
     * @throws FileNotFoundException if the specified file does not exist
     * @throws IOException if the file could not get read
     */
    private void loadFromFile() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.cardFile));
        String data = reader.readLine();
        
        this.cardSurname = data.substring(0, 18).trim();
        this.cardName = data.substring(18, 30).trim();
        this.cardAccountDesignation = data.substring(30, 54).trim();
        this.cardIBAN = data.substring(54, 80).trim();
        this.cardBankName = data.substring(80, 105).trim();
        this.cardNumber = data.substring(105, 112).trim();
        this.cardExpirationMonth = data.substring(112, 114).trim();
        this.cardExpirationYear = data.substring(114, 116).trim();
        this.cardPinCode = data.substring(116, 122).trim();
    }
    
    public void writeToFile() throws IOException {
        // Create the writer
        try (FileWriter writer = new FileWriter(this.cardFile)) {
            // Fill data with spaces
            char[] data = new char[122];
            Arrays.fill(data, ' ');
            
            // Now override the spaces with the actual data
            System.arraycopy(this.cardSurname.toCharArray(),                        0, data, 0, Math.min(this.cardSurname.length(), 18));
            System.arraycopy(this.cardName.toCharArray(),                           0, data, 18, Math.min(this.cardName.length(), 12));
            System.arraycopy(this.cardAccountDesignation.toCharArray(),             0, data, 30, Math.min(this.cardAccountDesignation.length(), 24));
            System.arraycopy(this.cardIBAN.toCharArray(),                           0, data, 54, Math.min(this.cardIBAN.length(), 26));
            System.arraycopy(this.cardBankName.toCharArray(),                       0, data, 80, Math.min(this.cardBankName.length(), 25));
            System.arraycopy(this.cardNumber.toCharArray(),                         0, data, 105, Math.min(this.cardNumber.length(), 7));
            System.arraycopy(this.cardExpirationMonth.toCharArray(),                0, data, 112, Math.min(this.cardExpirationMonth.length(), 2));
            System.arraycopy(this.cardExpirationYear.toCharArray(),                 0, data, 114, Math.min(this.cardExpirationYear.length(), 2));
            System.arraycopy(this.cardPinCode.toCharArray(),                        0, data, 116, Math.min(this.cardPinCode.length(), 6));
            
            // write the data to the file
            writer.write(data);
            writer.flush();
        }
    }
}
