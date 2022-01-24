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
    private int cardExpirationMonth;
    private int cardExpirationYear;
    private int cardPinCode;
    
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
        
        // TODO: Fix this
        
        char[] surnameBuffer = new char[18];
        reader.read(surnameBuffer, 0, surnameBuffer.length);
        
        char[] nameBuffer = new char[12];
        reader.read(nameBuffer, 18, nameBuffer.length);
        
        char[] accountDesignationBuffer = new char[24];
        reader.read(accountDesignationBuffer, 30, accountDesignationBuffer.length);
        
        char[] ibanBuffer = new char[26];
        reader.read(ibanBuffer, 54, ibanBuffer.length);
        
        char[] bankNameBuffer = new char[25];
        reader.read(bankNameBuffer, 80, bankNameBuffer.length);
        
        char[] cardNumberBuffer = new char[7];
        reader.read(cardNumberBuffer, 105, cardNumberBuffer.length);
        
        char[] expirationMonthBuffer = new char[2];
        reader.read(expirationMonthBuffer, 112, expirationMonthBuffer.length);
        
        char[] expirationYearBuffer = new char[2];
        reader.read(expirationYearBuffer, 114, expirationYearBuffer.length);
        
        char[] pinCodeBuffer = new char[6];
        reader.read(pinCodeBuffer, 116, pinCodeBuffer.length);
        
        this.cardSurname = String.valueOf(surnameBuffer).trim();
        this.cardName = String.valueOf(nameBuffer).trim();
        this.cardAccountDesignation = String.valueOf(accountDesignationBuffer).trim();
        this.cardIBAN = String.valueOf(ibanBuffer).trim();
        this.cardBankName = String.valueOf(bankNameBuffer).trim();
        this.cardNumber = String.valueOf(cardNumberBuffer).trim();
        this.cardExpirationMonth = Integer.parseInt(String.valueOf(expirationMonthBuffer));
        this.cardExpirationYear = Integer.parseInt(String.valueOf(expirationYearBuffer));
        this.cardPinCode = Integer.parseInt(String.valueOf(pinCodeBuffer));
    }
    
    public void writeToFile() throws IOException {
        // Create the writer
        try (FileWriter writer = new FileWriter(this.cardFile)) {
            // Fill data with spaces
            char[] data = new char[122];
            Arrays.fill(data, ' ');
            
            // Now override the spaces with the actual data
            System.arraycopy(this.cardSurname.toCharArray(),                        0, data, 0, 18);
            System.arraycopy(this.cardName.toCharArray(),                           0, data, 18, 12);
            System.arraycopy(this.cardAccountDesignation.toCharArray(),             0, data, 30, 24);
            System.arraycopy(this.cardIBAN.toCharArray(),                           0, data, 54, 26);
            System.arraycopy(this.cardBankName.toCharArray(),                       0, data, 80, 25);
            System.arraycopy(this.cardNumber.toCharArray(),                         0, data, 105, 7);
            System.arraycopy((""+this.cardExpirationMonth).toCharArray(),           0, data, 112, 2);
            System.arraycopy((""+this.cardExpirationYear).toCharArray(),            0, data, 114, 2);
            System.arraycopy((""+this.cardPinCode).toCharArray(),                   0, data, 116, 6);
            
            // write the data to the file
            writer.write(data);
            writer.flush();
        }
    }
}
