package com.phoshoko.storage;

import java.time.LocalDate;

public class Users extends Storage {

    private int userID;
    private String username, password, role, gender;
    private LocalDate dateOfBirth;
    private static final int COLUMNS = 6;
    private final String[] data = new String[COLUMNS];

    public Users(int userID, String username, String password, String role, String gender, LocalDate dateOfBirth) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        
        data[0] = "" + userID;
        data[1] = username;
        data[2] = password;
        data[3] = role;
        data[4] = gender;
        data[5] = "" + dateOfBirth;
        super.setData(data);
    }
    
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "UserID\t" + userID + "\nUsername\t" + username + "\nPassword\t" + password + "\nRole\t" + role + "\nGender\t" + gender + "\nDateOfBirth\t" + dateOfBirth;
    }

}