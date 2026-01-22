/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phoshoko.utilities;

import com.phoshoko.storage.Connect;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Mpho
 */
public class Style {

    public Style() {
        System.out.println("Styles connected");
    }

    //Table styles
    public static void table(JTable table) {
        // Style the header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(Color.WHITE);
        header.setForeground(Color.BLACK);
        header.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

        // Style table
        table.setBackground(Color.WHITE);
        table.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
        table.setGridColor(Color.LIGHT_GRAY);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        // Style selection
        table.setSelectionBackground(new Color(144, 238, 144));
        table.setSelectionForeground(Color.BLACK);

        //row height and cell margins
        table.setRowHeight(25);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.getParent().setBackground(Color.WHITE);

    }

    public static void dshboardTable(JTable table) {
        // Style the header for elegant look
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setBackground(Color.WHITE);
        header.setForeground(Color.BLACK);
        header.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

        // Style the table itself
        table.setBackground(Color.WHITE);
        table.setBorder(BorderFactory.createEmptyBorder());
        table.setGridColor(new Color(240, 240, 240));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowMargin(0);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);

        // Elegant selection style
        table.setSelectionBackground(new Color(200, 230, 200));  // light green
        table.setSelectionForeground(new Color(40, 40, 40));

        // Row height and visual polish
        table.setRowHeight(32);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFillsViewportHeight(true);
        table.getParent().setBackground(Color.WHITE);

    }

    //method to change the icons on the labels and colours for a hover effect
    public void sideBarOptionSelect(JLabel label, boolean changeColor) {

        String changeIcon = "/com/phoshoko/images/" + label.getIcon().toString().substring(label.getIcon().toString().lastIndexOf("/"));
        if (changeColor) {
            label.setBackground(new Color(50, 50, 50));
            label.setForeground(Color.WHITE);
            if (changeIcon.contains("(1)")) {
                label.setIcon(new javax.swing.ImageIcon(getClass().getResource(changeIcon)));
            } else {
                label.setIcon(new javax.swing.ImageIcon(getClass().getResource(changeIcon.substring(0, changeIcon.lastIndexOf(".")) + "(1)" + changeIcon.substring(changeIcon.lastIndexOf(".")))));

            }

        } else {
            label.setBackground(new Color(240, 235, 225));
            label.setForeground(Color.BLACK);
            if (changeIcon.contains("(1)")) {
                label.setIcon(new javax.swing.ImageIcon(getClass().getResource(changeIcon.substring(0, changeIcon.indexOf("(")) + changeIcon.substring(changeIcon.lastIndexOf(".")))));
            } else {
                label.setIcon(new javax.swing.ImageIcon(getClass().getResource(changeIcon)));
            }

        }
    }
    //-------------------
    //Button Styles
    //===================

    // color palettes for each button
    private static final Color EDIT_DEFAULT = new Color(255, 179, 64);   // Orange
    private static final Color EDIT_HOVER = new Color(255, 160, 20);

    private static final Color DELETE_DEFAULT = new Color(229, 89, 89);   //Red
    private static final Color DELETE_HOVER = new Color(217, 48, 48);

    private static final Color INSERT_DEFAULT = new Color(76, 175, 80);   //Green
    private static final Color INSERT_HOVER = new Color(56, 142, 60);

    private static final Color REPORT_DEFAULT = new Color(33, 150, 243);   //Blue
    private static final Color REPORT_HOVER = new Color(25, 118, 210);

    private static final Border BUTTON_BORDER = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            new EmptyBorder(10, 20, 10, 20)
    );
    private static final Border SHADOW_BORDER = BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 2, new Color(0, 0, 0, 50)), // Subtle drop shadow effect
            BUTTON_BORDER
    );

    public void functionButtons(JLabel label, JTable[] table, String functionType, String className) {

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                //Icons are named with(1) at the end to show bold so this code changes the icon to the one that is bold
                String changeIcon = "/com/phoshoko/images/" + label.getIcon().toString().substring(label.getIcon().toString().lastIndexOf("/"));
                if (changeIcon.contains("(1)")) {
                    label.setIcon(new javax.swing.ImageIcon(getClass().getResource(changeIcon)));
                } else {
                    label.setIcon(new javax.swing.ImageIcon(getClass().getResource(changeIcon.substring(0, changeIcon.lastIndexOf(".")) + "(1)" + changeIcon.substring(changeIcon.lastIndexOf(".")))));

                }
                switch (functionType) {
                    case "edit":

                        applyFunctionStyles(label, EDIT_HOVER, true);
                        label.setToolTipText("edit");
                        break;
                    case "report":
                        label.setToolTipText("report");
                        applyFunctionStyles(label, REPORT_HOVER, true);

                        break;
                    case "insert":
                        label.setToolTipText("insert");
                        applyFunctionStyles(label, INSERT_HOVER, true);
                        break;
                    case "delete":
                        label.setToolTipText("delete");
                        applyFunctionStyles(label, DELETE_HOVER, true);

                        break;
                    default:
                        System.err.println("Style:Misspell in one of the function names.");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //Icons are named with(1) at the end to show bold so this code changes the icon to the one thats not bold
                String changeIcon = "/com/phoshoko/images/" + label.getIcon().toString().substring(label.getIcon().toString().lastIndexOf("/"));
                if (changeIcon.contains("(1)")) {
                    label.setIcon(new javax.swing.ImageIcon(getClass().getResource(changeIcon.substring(0, changeIcon.indexOf("(")) + changeIcon.substring(changeIcon.lastIndexOf(".")))));
                } else {
                    label.setIcon(new javax.swing.ImageIcon(getClass().getResource(changeIcon)));
                }

                //Switch  statement to get/change the colors
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Logic.function = functionType;
                System.out.println(Logic.function);
                //report function is separate
                if (!functionType.equalsIgnoreCase("report")) {
                    switch (className) {
                        case Connect.CLASS_CROPS:
                            Logic.tableFunctions(table[0], Connect.CLASS_CROPS, functionType);
                            break;
                        case Connect.CLASS_EQUIPMENT:
                            Logic.tableFunctions(table[1], Connect.CLASS_EQUIPMENT, functionType);
                            break;

                        case Connect.CLASS_FINANCE:
                            Logic.tableFunctions(table[2], Connect.CLASS_FINANCE, functionType);
                            break;

                        case Connect.CLASS_LIVESTOCK:
                            Logic.tableFunctions(table[3], Connect.CLASS_LIVESTOCK, functionType);
                            break;

                        case Connect.CLASS_MAINTENANCELOGS:
                            Logic.tableFunctions(table[4], Connect.CLASS_MAINTENANCELOGS, functionType);
                            break;
                        case Connect.CLASS_USERS:
                            Logic.tableFunctions(table[5], Connect.CLASS_USERS, functionType);
                            break;
                        default:
                            System.out.println("Class not found");
                    }

                } else {
                     switch (className) {
                        case Connect.CLASS_CROPS:
                         {
                             try {
                                 Logic.report(table[0], className);
                             } catch (PrinterException ex) {
                                 Logger.getLogger(Style.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                            break;

                        case Connect.CLASS_EQUIPMENT:
                         {
                             try {
                                 Logic.report(table[1], className);
                             } catch (PrinterException ex) {
                                 Logger.getLogger(Style.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                            break;


                        case Connect.CLASS_FINANCE:
                         {
                             try {
                                 Logic.report(table[2], className);
                             } catch (PrinterException ex) {
                                 Logger.getLogger(Style.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                            break;


                        case Connect.CLASS_LIVESTOCK:
                         {
                             try {
                                 Logic.report(table[3], className);
                             } catch (PrinterException ex) {
                                 Logger.getLogger(Style.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                            break;


                        case Connect.CLASS_MAINTENANCELOGS:
                         {
                             try {
                                 Logic.report(table[4], className);
                             } catch (PrinterException ex) {
                                 Logger.getLogger(Style.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                            break;

                        case Connect.CLASS_USERS:
                         {
                             try {
                                 Logic.report(table[5], className);
                             } catch (PrinterException ex) {
                                 Logger.getLogger(Style.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                            break;

                        default:
                            System.out.println("Class not found");
                    }
                }

            }

        });
        //To set the labels on sartUp
        switch (functionType) {
            case "edit":

                applyFunctionStyles(label, EDIT_DEFAULT, false);

                break;
            case "report":

                applyFunctionStyles(label, REPORT_DEFAULT, false);

                break;
            case "insert":

                applyFunctionStyles(label, INSERT_DEFAULT, false);

                break;
            case "delete":

                applyFunctionStyles(label, DELETE_DEFAULT, false);

                break;
            default:
                System.err.println("Style:Misspell in one of the function names.");
        }

    }

    private void applyFunctionStyles(JLabel label, Color colour, boolean changeColour) {
        if (changeColour) {
            label.setOpaque(true);
            label.setBackground(colour);
            label.setBorder(SHADOW_BORDER);
            label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
            label.setOpaque(true);
            label.setBackground(colour);
            label.setBorder(BUTTON_BORDER);
            label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

}
