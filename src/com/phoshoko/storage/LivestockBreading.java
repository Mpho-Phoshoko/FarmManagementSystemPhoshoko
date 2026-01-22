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
public class LivestockBreading extends Livestock {

    private LocalDate breedingDate;

    public LivestockBreading(int livestockID, String breed, int tagID, LocalDate dateOfBirth, String gender, String healthStatus, LocalDate vaccinationDate, LocalDate breedingDate) {
        super(livestockID, breed, tagID, dateOfBirth, gender, healthStatus, vaccinationDate);
        this.breedingDate = breedingDate;
        super.getData()[7] = "" + breedingDate;
        
    }

    public LocalDate getBreedingDate() {
        return breedingDate;
    }

    public void setBreedingDate(LocalDate breedingDate) {
        this.breedingDate = breedingDate;
    }

    @Override
    public String toString() {
        return super.toString()+ "\nbreedingDate\t" + breedingDate;
    }
}
