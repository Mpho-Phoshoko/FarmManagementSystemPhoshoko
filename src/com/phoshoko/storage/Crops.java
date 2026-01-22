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
public class Crops extends Storage {

    private int cropsID;
    private String type;
    private LocalDate plantingDate, harvestingDate;
    private boolean pesticideUsage;
    private double yield;
    private String[] data = new String[COLUMNS];

    //implement for others
    private static final int COLUMNS = 6;

    public Crops(int cropsID, String type, LocalDate plantingDate, LocalDate harvestingDate, double yield, boolean pesticideUsage) {
        this.cropsID = cropsID;
        this.type = type;
        this.plantingDate = plantingDate;
        this.harvestingDate = harvestingDate;
        this.pesticideUsage = pesticideUsage;
        this.yield = yield;

        //Adding the data to the string array called data
        data[0] = "" + cropsID;
        data[1] = type;
        data[2] = "" + plantingDate;
        data[3] = "" + harvestingDate;
        data[4] = "" + yield;
        if (pesticideUsage) {
            data[5] = "Yes";
        } else {
            data[5] = "No";
        }
        super.setData(data);
    }

    public int getCropsID() {
        return cropsID;
    }

    public void setCropsID(int cropsID) {
        this.cropsID = cropsID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(LocalDate plantingDate) {
        this.plantingDate = plantingDate;
    }

    public LocalDate getHarvestingDate() {
        return harvestingDate;
    }

    public void setHarvestingDate(LocalDate harvestingDate) {
        this.harvestingDate = harvestingDate;
    }

    public boolean isPesticideUsage() {
        return pesticideUsage;
    }

    public void setPecticideUsage(boolean pesticideUsage) {
        this.pesticideUsage = pesticideUsage;
    }

    public double getYield() {
        return yield;
    }

    public void setYield(double yield) {
        this.yield = yield;
    }

    public static int getCOLUMNS() {
        return COLUMNS;
    }

    @Override
    public String toString() {
        return "Crops{" + "type=" + type + ", plantingDate=" + plantingDate + ", harvestingDate=" + harvestingDate + ", pesticideUsage=" + pesticideUsage + ", yield=" + yield + '}';
    }

}
