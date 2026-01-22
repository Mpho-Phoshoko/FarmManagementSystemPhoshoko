package com.phoshoko.storage;

import com.phoshoko.utilities.Sql;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connect {

    private Connection conn;
    private boolean storageResult = false;

    //Class Names
    public static final String CLASS_CROPS = "Crops";
    public static final String CLASS_EQUIPMENT = "Equipment";
    public static final String CLASS_FINANCE = "FinancialTransactions";
    public static final String CLASS_LIVESTOCK = "Livestock";
    public static final String CLASS_MAINTENANCELOGS = "MaintenanceLogs";
    public static final String CLASS_USERS = "Users";

    //Lists to store data
    private static ArrayList<Crops> cropsList = new ArrayList<>();
    private static ArrayList<Equipment> equipmentList = new ArrayList<>();
    private static ArrayList<Livestock> livestockList = new ArrayList<>();
    private static ArrayList<Finance> financeList = new ArrayList<>();
    private static ArrayList<MaintenanceLogs> maintenanceLogsList = new ArrayList<>();
    private static ArrayList<Users> usersList = new ArrayList<>();

    //Array sizes
    private static int sizeCrops = 0;
    private static int sizeEquipment = 0;
    private static int sizeLivestock = 0;
    private static int sizeFinance = 0;
    private static int sizeMaintenanceLogs = 0;
    private static int sizeUsers = 0;

    public Connect() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String filename = (new File("FMS_Storage.accdb")).getAbsolutePath();
            conn = DriverManager.getConnection("jdbc:ucanaccess://" + filename);
            System.out.println("Connection successful");
            storageResult = true;
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            e.printStackTrace();
        }
        if (storageResult) {
            //get data from the database
            storeData(CLASS_CROPS);
            storeData(CLASS_EQUIPMENT);
            storeData(CLASS_LIVESTOCK);
            storeData(CLASS_FINANCE);
            storeData(CLASS_MAINTENANCELOGS);
            storeData(CLASS_USERS);

        } else {
            System.out.println("Connect: Falied retriving data from FMS_Storage.accdb");
        }
    }

    public ResultSet query(String sql) {
        ResultSet rs = null;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public void storeData() {
        storeData(CLASS_CROPS);
        storeData(CLASS_EQUIPMENT);
        storeData(CLASS_LIVESTOCK);
        storeData(CLASS_FINANCE);
        storeData(CLASS_MAINTENANCELOGS);
        storeData(CLASS_USERS);

    }

    public int update(String sql) {
        int result = 0;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            result = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error");
        }
        return result;
    }

    private void storeData(String className) {

        ResultSet rs = query(Sql.selectStorage(className));
        if (storageResult) {
            try {

                while (rs.next()) {
                    switch (className) {
                        case CLASS_CROPS:
                            cropsList.add(new Crops(
                                    rs.getInt("CropID"),
                                    rs.getString("CropType"),
                                    rs.getDate("PlantingDate").toLocalDate(),
                                    rs.getDate("HarvestDate").toLocalDate(),
                                    rs.getDouble("Yield"),
                                    rs.getBoolean("PesticideUsage")
                            ));
                            sizeCrops++;
                            break;
                        case CLASS_EQUIPMENT:
                            equipmentList.add(new Equipment(
                                    rs.getInt("EquipmentID"),
                                    rs.getString("EquipmentType"),
                                    rs.getDate("PurchaseDate").toLocalDate(),
                                    rs.getString("Condition"),
                                    rs.getDate("LastServiceDate").toLocalDate()
                            ));
                            sizeEquipment++;
                            break;

                        case CLASS_FINANCE:
                            financeList.add(new Finance(
                                    rs.getInt("TransactionID"),
                                    rs.getDate("TransactionDate").toLocalDate(),
                                    rs.getString("IncomeOrExpense"),
                                    rs.getString("Description"),
                                    rs.getDouble("Amount")
                            ));
                            sizeFinance++;
                            break;

                        case CLASS_LIVESTOCK:
                            if (rs.getString("BreedingDate") == null) {
                                livestockList.add(new Livestock(
                                        rs.getInt("LivestockID"),
                                        rs.getString("Breed"),
                                        rs.getInt("TagID"),
                                        rs.getDate("DateOfBirth").toLocalDate(),
                                        rs.getString("Gender"),
                                        rs.getString("HealthStatus"),
                                        rs.getDate("VaccinationDate").toLocalDate()
                                ));
                            } else {
                                livestockList.add(new LivestockBreading(
                                        rs.getInt("LivestockID"),
                                        rs.getString("Breed"),
                                        rs.getInt("TagID"),
                                        rs.getDate("DateOfBirth").toLocalDate(),
                                        rs.getString("Gender"),
                                        rs.getString("HealthStatus"),
                                        rs.getDate("VaccinationDate").toLocalDate(),
                                        rs.getDate("BreedingDate").toLocalDate()
                                ));
                            }

                            sizeLivestock++;
                            break;

                        case CLASS_MAINTENANCELOGS:
                            maintenanceLogsList.add(new MaintenanceLogs(
                                    rs.getInt("LogID"),
                                    rs.getInt("EquipmentID"),
                                    rs.getDate("ServiceDate").toLocalDate(),
                                    rs.getString("Description"),
                                    rs.getDouble("Cost")
                            ));
                            sizeMaintenanceLogs++;
                            break;
                        case CLASS_USERS:
                            usersList.add(new Users(
                                    rs.getInt("UserID"),
                                    rs.getString("Username"),
                                    rs.getString("Password"),
                                    rs.getString("Role"),
                                    rs.getString("Gender"),
                                    rs.getDate("DateOfBirth").toLocalDate()
                            ));
                            sizeUsers++;
                            break;
                        default:
                            System.out.println("Class not found");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static ArrayList<Crops> getCropsList() {
        return cropsList;
    }

    public static ArrayList<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public static ArrayList<Livestock> getLivestockList() {
        return livestockList;
    }

    public static ArrayList<Finance> getFinanceList() {
        return financeList;
    }

    public static ArrayList<MaintenanceLogs> getMaintenanceLogsList() {
        return maintenanceLogsList;
    }

    public static void setCropsList(ArrayList<Crops> cropsList) {
        Connect.cropsList = cropsList;
    }

    public static void setEquipmentList(ArrayList<Equipment> equipmentList) {
        Connect.equipmentList = equipmentList;
    }

    public static void setLivestockList(ArrayList<Livestock> livestockList) {
        Connect.livestockList = livestockList;
    }

    public static void setFinanceList(ArrayList<Finance> financeList) {
        Connect.financeList = financeList;
    }

    public static void setMaintenanceLogsList(ArrayList<MaintenanceLogs> maintenanceLogsList) {
        Connect.maintenanceLogsList = maintenanceLogsList;
    }

    public static void setUsersList(ArrayList<Users> usersList) {
        Connect.usersList = usersList;
    }

    public static ArrayList<Users> getUsersList() {
        return usersList;
    }

    public static int getSizeCrops() {
        return sizeCrops;
    }

    public static int getSizeEquipment() {
        return sizeEquipment;
    }

    public static int getSizeLivestock() {
        return sizeLivestock;
    }

    public static int getSizeFinance() {
        return sizeFinance;
    }

    public static int getSizeMaintenanceLogs() {
        return sizeMaintenanceLogs;
    }

    public static int getSizeUsers() {
        return sizeUsers;
    }

    public static void setSizeCrops(int sizeCrops) {
        Connect.sizeCrops = sizeCrops;
    }

    public static void setSizeEquipment(int sizeEquipment) {
        Connect.sizeEquipment = sizeEquipment;
    }

    public static void setSizeLivestock(int sizeLivestock) {
        Connect.sizeLivestock = sizeLivestock;
    }

    public static void setSizeFinance(int sizeFinance) {
        Connect.sizeFinance = sizeFinance;
    }

    public static void setSizeMaintenanceLogs(int sizeMaintenanceLogs) {
        Connect.sizeMaintenanceLogs = sizeMaintenanceLogs;
    }

    public static void setSizeUsers(int sizeUsers) {
        Connect.sizeUsers = sizeUsers;
    }

    public static String getCLASS_CROPS() {
        return CLASS_CROPS;
    }

    public static String getCLASS_EQUIPMENT() {
        return CLASS_EQUIPMENT;
    }

    public static String getCLASS_FINANCE() {
        return CLASS_FINANCE;
    }

    public static String getCLASS_LIVESTOCK() {
        return CLASS_LIVESTOCK;
    }

    public static String getCLASS_MAINTENANCELOGS() {
        return CLASS_MAINTENANCELOGS;
    }

    public static String getCLASS_USERS() {
        return CLASS_USERS;
    }

}
