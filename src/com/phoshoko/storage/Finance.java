package com.phoshoko.storage;

import java.time.LocalDate;

/**
 *
 * @author Mpho
 */
public class Finance extends Storage {

    private int transactionID;
    private LocalDate transactionDate;
    private String incomeOrExpense, description;
    private static final int COLUMNS = 5;
    private String[] data = new String[COLUMNS];
    private double amount;

    public Finance(int transactionID, LocalDate transactionDate, String incomeOrExpense, String description,double amount) {
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        this.incomeOrExpense = incomeOrExpense;
        this.description = description;
        this.amount = amount;
        //Adding the data to the string array called data
        data[0] = "" + transactionID;
        data[1] = "" + transactionDate;
        data[2] = incomeOrExpense;
        data[3] = description;
        data[4] = ""+amount;
        super.setData(data);
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getIncomeOrExpense() {
        return incomeOrExpense;
    }

    public void setIncomeOrExpense(String incomeOrExpense) {
        this.incomeOrExpense = incomeOrExpense;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Finiance{" + "transactionID=" + transactionID + ", transactionDate=" + transactionDate + ", incomeOrExpense=" + incomeOrExpense + ", description=" + description + '}';
    }

}
