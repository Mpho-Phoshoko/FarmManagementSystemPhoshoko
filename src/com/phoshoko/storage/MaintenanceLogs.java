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
public class MaintenanceLogs extends Storage {

    private int equipmentID, logID;
    private String description;
    private double cost;
    private LocalDate serviceDate;
    private static final int COLUMNS = 5;
    private String[] data = new String[COLUMNS];

    public MaintenanceLogs(int logID, int equipmentID, LocalDate serviceDate, String description, double cost) {
        this.equipmentID = equipmentID;
        this.logID = logID;
        this.description = description;
        this.cost = cost;
        this.serviceDate = serviceDate;

        //Adding the data to the string array called data
        data[0] = "" + logID;
        data[1] = "" + equipmentID;
        data[2] = "" + serviceDate;
        data[3] = "" + description;
        data[4] = "" + cost;
        super.setData(data);
    }

    public int getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
    }

    public int getMaintanaceLogsID() {
        return logID;
    }

    public void setMaintanaceLogsID(int logID) {
        this.logID = logID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate ServiceDate) {
        this.serviceDate = ServiceDate;
    }

    @Override
    public String toString() {
        return "MaintananceLogs{" + "equipmentID=" + equipmentID + ", logID=" + logID + ", description=" + description + ", cost=" + cost + ", serviceDate=" + serviceDate + ", data=" + data + '}';
    }
    
    
}
