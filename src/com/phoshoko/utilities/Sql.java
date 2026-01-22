package com.phoshoko.utilities;

import com.phoshoko.storage.Connect;
import javax.swing.JTable;

/**
 *
 * @author Mpho
 */
public class Sql {
    //All sql used in the project gets stored here

    //Sql for Storage
    public static String selectStorage(String className) {
        String statement = "select * from " + className;
        System.out.println(statement);
        return statement;
    }

    //Sql for Storage
    public static String login(String username, String password) {
        String statement = "select username,password from users";
        System.out.println(statement);
        return statement;
    }

    //Sql to delete 
    public static String deleteTableData(String tableName, String cell) {
        String id = "";
        switch (tableName) {
            case "":

                break;
            case Connect.CLASS_CROPS:
                id = "CropID";
                break;
            case Connect.CLASS_USERS:
                id = "UserID";
                break;
            case Connect.CLASS_EQUIPMENT:
                id = "EquipmentID";
                break;
            case Connect.CLASS_FINANCE:
                id = "TransactionID";
                break;
            case Connect.CLASS_LIVESTOCK:
                id = "LivestockID";
                break;
            case Connect.CLASS_MAINTENANCELOGS:
                id = "LogID";
                break;
            default:
                throw new AssertionError();
        }
        String statement = "DELETE FROM " + tableName + " WHERE " + id + " = " + cell;

        System.out.println(statement);
        return statement;
    }

    public static String insertStatementCrops(String cropType, String plantingDate, String harvestDate, String yield, String pesticideUsage) {

        String statement = "INSERT INTO " + Connect.CLASS_CROPS + " (CropType, PlantingDate, HarvestDate, Yield, PesticideUsage) "
                + "VALUES ('" + cropType + "', #" + plantingDate + "#, #" + harvestDate + "#, "
                + "'" + yield + "', " + pesticideUsage + ")";
        System.out.println(statement);
        return statement;
    }

    public static String insertStatementLivestock(String breed, String tagID, String dateOfBirth, String gender, String healthStatus, String vaccinationDate, String breedingDate) {

        String statement = "INSERT INTO " + Connect.CLASS_LIVESTOCK + " (Breed, TagID, DateOfBirth, Gender, HealthStatus, VaccinationDate, BreedingDate) "
                + "VALUES ('" + breed + "', '" + tagID + "', #" + dateOfBirth + "#, '" + gender + "', '"
                + healthStatus + "', #" + vaccinationDate + "#, #" + breedingDate + "#)";
        System.out.println(statement);
        return statement;
    }
        public static String insertStatementLivestock(String breed, String tagID, String dateOfBirth, String gender, String healthStatus, String vaccinationDate) {

        String statement = "INSERT INTO " + Connect.CLASS_LIVESTOCK + " (Breed, TagID, DateOfBirth, Gender, HealthStatus, VaccinationDate, BreedingDate) "
                + "VALUES ('" + breed + "', '" + tagID + "', #" + dateOfBirth + "#, '" + gender + "', '"
                + healthStatus + "', #" + vaccinationDate + "#)";
        System.out.println(statement);
        return statement;
    }

    public static String insertStatementMaintenanceLogs(String equipmentID, String serviceDate, String description, String cost) {

        String statement = "INSERT INTO " + Connect.CLASS_MAINTENANCELOGS + " (EquipmentID, ServiceDate, Description, Cost) "
                + "VALUES ('" + equipmentID + "', #" + serviceDate + "#, '" + description + "', '" + cost + "')";
        System.out.println(statement);
        return statement;
    }

    public static String insertStatementFinance(String transactionDate, String incomeOrExpense, String description, String amount) {

        String statement = "INSERT INTO " + Connect.CLASS_FINANCE + " (TransactionDate, IncomeOrExpense, Description, Amount) "
                + "VALUES (#" + transactionDate + "#, '" + incomeOrExpense + "', '" + description + "', '" + amount + "')";
        System.out.println(statement);
        return statement;
    }

    public static String insertStatementEquipment(String equipmentType, String purchaseDate, String condition, String lastServiceDate) {

        String statement = "INSERT INTO " + Connect.CLASS_EQUIPMENT + " (EquipmentType, PurchaseDate, Condition, LastServiceDate) "
                + "VALUES ('" + equipmentType + "', #" + purchaseDate + "#, '" + condition + "', #" + lastServiceDate + "#)";
        System.out.println(statement);
        return statement;
    }

    public static String insertStatementUsers(String username, String password, String role, String gender, String dateOfBirth) {

        String statement = "INSERT INTO " + Connect.CLASS_USERS + " (Username, Password, Role, Gender, DateOfBirth) "
                + "VALUES ('" + username + "', '" + password + "', '" + role + "', '" + gender + "', #" + dateOfBirth + "#)";
        System.out.println(statement);
        return statement;
    }
    public static String updateStatementCrops(String cropID, String cropType, String plantingDate, String harvestDate, String yield, String pesticideUsage) {
    String statement = "UPDATE " + Connect.CLASS_CROPS + " SET "
            + "CropType = '" + cropType + "', "
            + "PlantingDate = #" + plantingDate + "#, "
            + "HarvestDate = #" + harvestDate + "#, "
            + "Yield = '" + yield + "', "
            + "PesticideUsage = " + pesticideUsage + " "
            + "WHERE CropID = " + cropID;
    System.out.println(statement);
    return statement;
}

public static String updateStatementLivestock(String livestockID, String breed, String tagID, String dateOfBirth, String gender, String healthStatus, String vaccinationDate, String breedingDate) {
    String statement = "UPDATE " + Connect.CLASS_LIVESTOCK + " SET "
            + "Breed = '" + breed + "', "
            + "TagID = " + tagID + ", "
            + "DateOfBirth = #" + dateOfBirth + "#, "
            + "Gender = '" + gender + "', "
            + "HealthStatus = '" + healthStatus + "', "
            + "VaccinationDate = #" + vaccinationDate + "#, "
            + "BreedingDate = #" + breedingDate + "# "
            + "WHERE LivestockID = " + livestockID;
    System.out.println(statement);
    return statement;
}
public static String updateStatementLivestock(String livestockID, String breed, String tagID, String dateOfBirth, String gender, String healthStatus, String vaccinationDate) {
    String statement = "UPDATE " + Connect.CLASS_LIVESTOCK + " SET "
            + "Breed = '" + breed + "', "
            + "TagID = " + tagID + ", "
            + "DateOfBirth = #" + dateOfBirth + "#, "
            + "Gender = '" + gender + "', "
            + "HealthStatus = '" + healthStatus + "', "
            + "VaccinationDate = #" + vaccinationDate + "# "
            + "WHERE LivestockID = " + livestockID;
    System.out.println(statement);
    return statement;
}

public static String updateStatementMaintenanceLogs(String logID, String equipmentID, String serviceDate, String description, String cost) {
    String statement = "UPDATE " + Connect.CLASS_MAINTENANCELOGS + " SET "
            + "EquipmentID = '" + equipmentID + "', "
            + "ServiceDate = #" + serviceDate + "#, "
            + "Description = '" + description + "', "
            + "Cost = '" + cost + "' "
            + "WHERE LogID = " + logID;
    System.out.println(statement);
    return statement;
}

public static String updateStatementFinance(String transactionID, String transactionDate, String incomeOrExpense, String description, String amount) {
    String statement = "UPDATE " + Connect.CLASS_FINANCE + " SET "
            + "TransactionDate = #" + transactionDate + "#, "
            + "IncomeOrExpense = '" + incomeOrExpense + "', "
            + "Description = '" + description + "', "
            + "Amount = '" + amount + "' "
            + "WHERE TransactionID = " + transactionID;
    System.out.println(statement);
    return statement;
}

public static String updateStatementEquipment(String equipmentID, String equipmentType, String purchaseDate, String condition, String lastServiceDate) {
    String statement = "UPDATE " + Connect.CLASS_EQUIPMENT + " SET "
            + "EquipmentType = '" + equipmentType + "', "
            + "PurchaseDate = #" + purchaseDate + "#, "
            + "Condition = '" + condition + "', "
            + "LastServiceDate = #" + lastServiceDate + "# "
            + "WHERE EquipmentID = " + equipmentID;
    System.out.println(statement);
    return statement;
}

public static String updateStatementUsers(String userid,String username, String password, String role, String gender, String dateOfBirth) {
    String statement = "UPDATE " + Connect.CLASS_USERS + " SET "
            + "Username = '" + username + "',"
            + "Password = '" + password + "', "
            + "Role = '" + role + "', "
            + "Gender = '" + gender + "', "
            + "DateOfBirth = #" + dateOfBirth + "# "
            + "WHERE UserID = "+userid;
    System.out.println(statement);
    return statement;
}



}
