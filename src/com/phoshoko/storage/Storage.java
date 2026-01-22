/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phoshoko.storage;

/**
 *
 * @author Mpho
 */
public class Storage {
    private String[] data;

    
    public String getData(int i) {
        return data[i];
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data= data;
    }
    
}
