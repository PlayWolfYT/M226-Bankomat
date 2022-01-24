/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbzsogr.bankomat.remote;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author PlayWolfYT
 */
public class BankAccount {
    
    private String accountIBAN;
    private boolean accountLocked;
    private double accountSaldo;
    private double accountWithdrawalLimit;
    
    public BankAccount(String accountIBAN) throws NumberFormatException, IOException {
        this.accountIBAN = accountIBAN;
        loadFromFile();
    }

    public void test() throws IOException {
        this.accountLocked = true;
        this.accountSaldo = 2000d;
        this.accountWithdrawalLimit = 50000d;
        this.writeToFile();
    }

    private void loadFromFile() throws NumberFormatException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(BankSystem.BANK_FILE));

        String line;

        while((line = reader.readLine()) != null) {

            // Bank file is seperated with ; so we split to get our data
            String[] data = line.split(";");

            // if we are not interested in this line we skip it.
            if(!this.accountIBAN.equals(data[0])) continue;

            this.accountLocked = Boolean.parseBoolean(data[1]);
            this.accountSaldo = Double.parseDouble(data[2]);
            this.accountWithdrawalLimit = Double.parseDouble(data[3]);
            break;
        }

        reader.close();
    }

    private void writeToFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(BankSystem.BANK_FILE));
        List<String> data = reader.lines().collect(Collectors.toList());
        reader.close();
        
        int index = -1;
        try {
        index = data.indexOf(
                data.stream()
                        .filter(line -> line.split(";")[0].equals(this.accountIBAN))
                        .collect(Collectors.toList())
                        .get(0)
            );
        } catch(IndexOutOfBoundsException ingored) {}
        
        // Override our data at the line of our account with the new data, If we have an entry for this account
        // Otherwise add to our data
        String newData = this.accountIBAN + ";" + String.valueOf(this.accountLocked) + ";" + this.accountSaldo + ";" + this.accountWithdrawalLimit;
        if(index > -1) {
            data.set(index, newData);
        } else {
            data.add(newData);
        }
        
        // Clear file
        new PrintWriter(BankSystem.BANK_FILE).close();

        // Write new data to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(BankSystem.BANK_FILE));
        writer.write(String.join(System.lineSeparator(), data));
        writer.flush();
        writer.close();
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public double getAccountSaldo() {
        return accountSaldo;
    }

    public String getAccountIBAN() {
        return accountIBAN;
    }

    public double getAccountWithdrawalLimit() {
        return accountWithdrawalLimit;
    }

}
