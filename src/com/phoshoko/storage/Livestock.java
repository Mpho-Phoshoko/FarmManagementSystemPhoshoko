/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phoshoko.storage;

import java.time.LocalDate;

public class Livestock extends Storage {

    private String breed,  gender, healthStatus;
    private int livestockID,tagID;
    private LocalDate dateOfBirth, vaccinationDate;
    private static final int COLUMNS = 8;
    private final String[] data= new String[COLUMNS];

    public Livestock(int livestockID,String breed,int tagID,LocalDate dateOfBirth, String gender, String healthStatus,   LocalDate vaccinationDate) {
        this.breed = breed;
        this.tagID = tagID;
        this.gender = gender;
        this.healthStatus = healthStatus;
        this.livestockID = livestockID;
        this.dateOfBirth = dateOfBirth;
        this.vaccinationDate = vaccinationDate;
        
        
        //Adding the data to the string array called data
        data[0] =""+livestockID;
        data[1] = breed;
        data[2] =""+ tagID;
        data[3] = "" + dateOfBirth;
        data[4] = gender;
        data[5] = "" + healthStatus;
        data[6] = "" + vaccinationDate;     
         
        super.setData(data);
    }

    public int getLivestockID() {
        return livestockID;
    }

    public void setLivestockID(int livestockID) {
        this.livestockID = livestockID;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getTagID() {
        return tagID;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(LocalDate vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    

    @Override
    public String toString() {
        return "Breed\t" + breed + "\nTagID\t" + tagID + "\nGender\t" + gender + "\nHealthStatus\t" + healthStatus + "\ndateOfBirth\t" + dateOfBirth + "\nvaccinationDate\t" + vaccinationDate ;
    }

}
