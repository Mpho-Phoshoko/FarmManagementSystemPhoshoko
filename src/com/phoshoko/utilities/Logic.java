/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phoshoko.utilities;

import com.phoshoko.app.Authentication;
import com.phoshoko.app.Forms;
import com.phoshoko.app.Main;
import com.phoshoko.storage.Connect;
import com.phoshoko.storage.Storage;
import com.phoshoko.storage.Users;
import static com.phoshoko.utilities.Style.table;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Mpho
 */
public class Logic {

    private static Connect db;
    String[][] arrActivityData = new String[1000][2];
    int arrActitySize = 0;
    ArrayList<String[]> data = new ArrayList<>();

    public Logic() {
        //Loading screen

        //Connects Storage
        db = new Connect();
    }

    public void resetTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }

    public static void resetTabels() {

        resetArrays();

        Main.getAuth().getApp().populateTables();
        System.out.println("populate");

    }

    private static void resetArrays() {
        // Crops list
        Connect.setCropsList(new ArrayList<>());
        Connect.setMaintenanceLogsList(new ArrayList<>());
        Connect.setFinanceList(new ArrayList<>());
        Connect.setUsersList(new ArrayList<>());
        Connect.setEquipmentList(new ArrayList<>());
        Connect.setLivestockList(new ArrayList<>());

        //reset size
        Connect.setSizeCrops(0);
        Connect.setSizeMaintenanceLogs(0);
        Connect.setSizeFinance(0);
        Connect.setSizeUsers(0);
        Connect.setSizeEquipment(0);
        Connect.setSizeLivestock(0);

        //Load Data
        db.storeData();

    }

    public static Connect getDb() {
        return db;
    }

    public static boolean login(String username, String password) {
        ResultSet rs = db.query(Sql.login(username, password));
        try {
            while (rs.next()) {
                if (rs.getString("username").equalsIgnoreCase(username) && rs.getString("password").equals(password)) {
                    String name =rs.getString("username");
                    writeToActivityLog(name+": Logged in");
                    return true;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void register(String username, String password, String role, String gender, String dateOfBirth) {
        db.update(Sql.insertStatementUsers(username, password, role, gender, dateOfBirth));
        ResultSet rs = db.query(Sql.login(username, password));
        
        try {
            while (rs.next()) {

                
                      Authentication.setClient(new Users(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getString("Gender"),
                        rs.getDate("DateOfBirth").toLocalDate()
                ));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static void writeToActivityLog(String message) {
        // Define the file name and the timestamp format
        String fileName = "activity_log.txt";

        try {

            String logLine = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " - " + message;

            FileWriter fileWriter = new FileWriter(fileName, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write the log line and a new line character
            bufferedWriter.write(logLine);
            bufferedWriter.newLine();

            // Close the writer to flush and save the data
            bufferedWriter.close();
        } catch (IOException ex) {
            // Log the exception in case of a file writing error
            System.err.println("Error writing to the activity log file: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public static void writeToErrorLog(String message) {
        // Define the file name and the timestamp format
        String fileName = "error_log.txt";

        try {

            String logLine = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " - " + message;

            FileWriter fileWriter = new FileWriter(fileName, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write the log line and a new line character
            bufferedWriter.write(logLine);
            bufferedWriter.newLine();

            // Close the writer to flush and save the data
            bufferedWriter.close();
        } catch (IOException ex) {
            // Log the exception in case of a file writing error
            System.err.println("Error writing to the activity log file: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void populateTables(String className, JTable table) {

        //Using if statements to set nessesary varibles(Java 17 does support switch statements with cases that can change)
        if (className.equalsIgnoreCase("dashboard")) {
            addActivityData(table);
        } else if (className.equals(Connect.getCLASS_CROPS())) {
            addData(Connect.getCropsList(), Connect.getSizeCrops(), table);
        } else if (className.equals(Connect.getCLASS_EQUIPMENT())) {
            addData(Connect.getEquipmentList(), Connect.getSizeEquipment(), table);
        } else if (className.equals(Connect.getCLASS_FINANCE())) {
            addData(Connect.getFinanceList(), Connect.getSizeFinance(), table);
        } else if (className.equals(Connect.getCLASS_MAINTENANCELOGS())) {
            addData(Connect.getMaintenanceLogsList(), Connect.getSizeMaintenanceLogs(), table);
        } else if (className.equals(Connect.getCLASS_LIVESTOCK())) {
            addData(Connect.getLivestockList(), Connect.getSizeLivestock(), table);
        } else if (className.equals(Connect.getCLASS_USERS())) {
            addData(Connect.getUsersList(), Connect.getSizeUsers(), table);
        }

    }

    public static void report(JTable table, String className) throws PrinterException {
        MessageFormat header;
        MessageFormat footer;
        switch (className) {
            case Connect.CLASS_CROPS:
                header = new MessageFormat(Connect.CLASS_CROPS);
                footer = new MessageFormat("Page{0,number,integer}");
                table.print(JTable.PrintMode.NORMAL, header, footer);
                break;
            case Connect.CLASS_EQUIPMENT:
                header = new MessageFormat(Connect.CLASS_EQUIPMENT);
                footer = new MessageFormat("Page{0,number,integer}");
                table.print(JTable.PrintMode.NORMAL, header, footer);
                break;

            case Connect.CLASS_FINANCE:
                header = new MessageFormat(Connect.CLASS_FINANCE);
                footer = new MessageFormat("Page{0,number,integer}");
                table.print(JTable.PrintMode.NORMAL, header, footer);
                break;

            case Connect.CLASS_LIVESTOCK:
                header = new MessageFormat(Connect.CLASS_LIVESTOCK);
                footer = new MessageFormat("Page{0,number,integer}");
                table.print(JTable.PrintMode.NORMAL, header, footer);
                break;

            case Connect.CLASS_MAINTENANCELOGS:
                header = new MessageFormat(Connect.CLASS_MAINTENANCELOGS);
                footer = new MessageFormat("Page{0,number,integer}");
                table.print(JTable.PrintMode.NORMAL, header, footer);
                break;
            case Connect.CLASS_USERS:
                header = new MessageFormat(Connect.CLASS_USERS);
                footer = new MessageFormat("Page{0,number,integer}");
                table.print(JTable.PrintMode.NORMAL, header, footer);
                break;
            default:
                System.out.println("Class not found");
        }

    }

    private void addData(ArrayList<? extends Storage> list, int size, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < size; i++) {

            model.addRow(list.get(i).getData());
        }
    }

    //sourced from: https://www.youtube.com/watch?v=haQIw3YDTPg
    public void searchTable(JTable tabel, JTextField txtField) {
        DefaultTableModel ob = (DefaultTableModel) tabel.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        tabel.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter("(i?)" + txtField.getText()));

    }

    private void addActivityData(JTable table) {
        readActivityData();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        for (int i = 0; i < arrActitySize; i++) {
            data.add(new String[]{arrActivityData[i][0], arrActivityData[i][1]});
        }
        for (int i = arrActitySize; i > 0; i--) {
            model.addRow(data.get(i - 1));

        }
    }

    private void readActivityData() {

        try {

            // Create a Scanner object to read from the file.
            Scanner scanner = new Scanner(new File("activity_log.txt"));

            // Loop through the file line by line as long as there is more content.
            while (scanner.hasNextLine()) {
                // Read the current line.
                String line = scanner.nextLine();
                Scanner scLine = new Scanner(line).useDelimiter(" - ");
                arrActivityData[arrActitySize][0] = scLine.next();
                arrActivityData[arrActitySize][1] = scLine.next();
                arrActitySize++;
            }

            // Close the scanner to release file resources.
            scanner.close();

        } catch (FileNotFoundException e) {
            // Handle the case where the file does not exist.
            System.err.println("Error: The file activity_log.txt was not found.");
            e.printStackTrace();
        }
    }

//Gets the position of the array for futher handeling in the forms class
    public static int positionInArray = -1;
    public static String cell = "-10", function = "";

    public static void tableFunctions(JTable table, String className, String action) {
        function = action;
        int response = -10;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        switch (action) {
            case "edit":
                response = JOptionPane.showConfirmDialog(null, "Do you want edit selected row?",
                        "Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                break;
            case "insert":
                response = JOptionPane.showConfirmDialog(null, "Do you want insert data?",
                        "Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                break;
            case "delete":
                response = JOptionPane.showConfirmDialog(null, "Do you want delete selected row/s?",
                        "Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                break;
            default:
                System.out.println("");
        } //gets the int positions of the rows
        int[] row = table.getSelectedRows();
        
        if (response == JOptionPane.YES_OPTION) {

            if (row.length == 1 || action.equalsIgnoreCase("insert") || (row.length >= 1 && action.equalsIgnoreCase("delete"))) {
                //Admin is allowed to delete multiple feilds but not edit or update multiple feilds
                switch (action) {
                    case "delete":
                        for (int i = 0; i < row.length; i++) {
                            cell = table.getModel().getValueAt(row[i], 0).toString();
                            System.out.println(cell);
                            db.update(Sql.deleteTableData(className, cell));
                            model.removeRow(row[i]);

                        }
                        JOptionPane.showMessageDialog(null, "Deleteted!");
                        break;
                    case "edit":
                        cell = table.getModel().getValueAt(row[0], 0).toString();
                        System.out.println(cell);

                        break;

                    default:
                        System.out.println("Action not found");
                }

                switch (className) {
                    case Connect.CLASS_CROPS:
                        for (int i = 0; i < Connect.getSizeCrops(); i++) {
                            if (Connect.getCropsList().get(i).getCropsID() == Integer.parseInt(cell)) {
                                switch (action) {
                                    case "delete":
                                        Connect.getCropsList().remove(i);
                                        Connect.setSizeCrops(Connect.getSizeCrops() - 1);
                                        break;
                                    case "edit":
                                        form.getFrmCrops().setVisible(true);
                                        positionInArray = i;
                                        break;

                                    default:
                                        System.out.println("");
                                }
                            }
                        }

                        if (action.equalsIgnoreCase("insert")) {
                            form.getFrmCrops().setVisible(true);
                        }
                        break;
                    case Connect.CLASS_EQUIPMENT:
                        for (int i = 0; i < Connect.getSizeEquipment(); i++) {
                            if (Connect.getEquipmentList().get(i).getEquipmentID() == Integer.parseInt(cell)) {
                                switch (action) {
                                    case "delete":
                                        Connect.getEquipmentList().remove(i);
                                        Connect.setSizeEquipment(Connect.getSizeEquipment() - 1);
                                        break;
                                    case "edit":
                                        form.getFrmEquipment().setVisible(true);
                                        positionInArray = i;
                                        break;

                                    default:
                                        System.out.println("");
                                }
                            }
                        }
                        if (action.equalsIgnoreCase("insert")) {
                            form.getFrmEquipment().setVisible(true);
                        }
                        break;

                    case Connect.CLASS_FINANCE:
                        for (int i = 0; i < Connect.getSizeFinance(); i++) {
                            if (Connect.getFinanceList().get(i).getTransactionID() == Integer.parseInt(cell)) {
                                switch (action) {
                                    case "delete":
                                        Connect.getFinanceList().remove(i);
                                        Connect.setSizeFinance(Connect.getSizeFinance() - 1);
                                        break;
                                    case "edit":
                                        form.getFrmFinance().setVisible(true);
                                        positionInArray = i;
                                        break;

                                    default:
                                        System.out.println("");
                                }
                            }
                        }
                        if (action.equalsIgnoreCase("insert")) {
                            form.getFrmFinance().setVisible(true);
                        }
                        break;

                    case Connect.CLASS_LIVESTOCK:
                        for (int i = 0; i < Connect.getSizeLivestock(); i++) {
                            if (Connect.getLivestockList().get(i).getLivestockID() == Integer.parseInt(cell)) {
                                switch (action) {
                                    case "delete":
                                        Connect.getLivestockList().remove(i);
                                        Connect.setSizeLivestock(Connect.getSizeLivestock() - 1);
                                        break;
                                    case "edit":
                                        form.getFrmLivestock().setVisible(true);
                                        positionInArray = i;
                                        break;

                                    default:
                                        System.out.println("");
                                }
                            }
                        }
                        if (action.equalsIgnoreCase("insert")) {
                            form.getFrmLivestock().setVisible(true);
                        }
                        break;

                    case Connect.CLASS_MAINTENANCELOGS:
                        for (int i = 0; i < Connect.getSizeMaintenanceLogs(); i++) {
                            if (Connect.getMaintenanceLogsList().get(i).getMaintanaceLogsID() == Integer.parseInt(cell)) {
                                switch (action) {
                                    case "delete":
                                        Connect.getMaintenanceLogsList().remove(i);
                                        Connect.setSizeMaintenanceLogs(Connect.getSizeMaintenanceLogs() - 1);
                                        break;
                                    case "edit":
                                        form.getFrmMaintenanceLogs().setVisible(true);
                                        positionInArray = i;
                                        break;

                                    default:
                                        System.out.println("");
                                }
                            }
                        }
                        if (action.equalsIgnoreCase("insert")) {
                            form.getFrmMaintenanceLogs().setVisible(true);
                        }
                        break;
                    case Connect.CLASS_USERS:
                        for (int i = 0; i < Connect.getSizeUsers(); i++) {
                            if (Connect.getUsersList().get(i).getUserID() == Integer.parseInt(cell)) {
                                switch (action) {
                                    case "delete":
                                        Connect.getUsersList().remove(i);
                                        Connect.setSizeUsers(Connect.getSizeUsers() - 1);
                                        break;
                                    case "edit":
                                        form.getFrmUsers().setVisible(true);
                                        positionInArray = i;
                                        break;

                                    default:
                                        System.out.println("action not avaliblile");
                                }
                            }
                        }
                        if (action.equalsIgnoreCase("insert")) {
                            form.getFrmUsers().setVisible(true);
                        }
                        break;
                    default:
                        System.out.println("Class not found");
                }
            } else if (action.equalsIgnoreCase("edit")) {

                JOptionPane.showMessageDialog(null, "Please select an option or select only one option to edit.");

            } else {
                JOptionPane.showMessageDialog(null, "Please select an option or options to delete");
            }
        }
    }

    //ADD
    static Forms form = new Forms();

    //Mouseclicked
}
