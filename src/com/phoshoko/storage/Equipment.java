/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phoshoko.storage;

import java.time.LocalDate;

/**
 *
 * @author Mpho
 */
public class Equipment extends Storage {

    private int equipmentID;
    private String type, condition;
    private LocalDate purchaseDate, lastServiceDate;
    private static final int COLUMNS = 5;
    private String[] data = new String[COLUMNS];

    public Equipment(int equipmentID, String type,  LocalDate purchaseDate,String condition, LocalDate lastServiceDate) {
        this.equipmentID = equipmentID;
        this.type = type;
        this.condition = condition;
        this.purchaseDate = purchaseDate;
        this.lastServiceDate = lastServiceDate;

        //Adding the data to the string array called data
        data[0] = "" + equipmentID;
        data[1] = type;
        data[2] = "" + purchaseDate;
        data[3] = condition;
        data[4] = "" + lastServiceDate;
        super.setData(data);
    }

    public int getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(LocalDate lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

}
