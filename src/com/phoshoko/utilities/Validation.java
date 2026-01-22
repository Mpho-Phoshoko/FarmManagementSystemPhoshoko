package com.phoshoko.utilities;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class Validation {

    public boolean text(String text, JLabel lblValidation, int numberOfCharacters,
            boolean symbolsAllowed, boolean numbersAllowed, boolean spacesAllowed) {

        // Length Check
        if (text.length() < numberOfCharacters) {
            lblValidation.setText("Minimum " + numberOfCharacters + " letters required");
            lblValidation.setForeground(Color.red);
            return false;
        }

        // Space Check
        if (!spacesAllowed) {
            for (int i = 0; i < text.length(); i++) {
                if (Character.isWhitespace(text.charAt(i))) {
                    lblValidation.setText("No spaces allowed");
                    lblValidation.setForeground(Color.red);
                    return false;
                }
            }
        }

        // Number Check
        if (!numbersAllowed) {
            for (int i = 0; i < text.length(); i++) {
                if (Character.isDigit(text.charAt(i))) {
                    lblValidation.setText("No numbers allowed");
                    lblValidation.setForeground(Color.red);
                    return false;
                }
            }
        }

        // Symbol Check 
        if (!symbolsAllowed) {
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                // Check if character is not a letter, not a digit, and not a space (if spaces are allowed)
                boolean isSymbol = !Character.isLetter(c) && !Character.isDigit(c);
                if (isSymbol && !(spacesAllowed && Character.isWhitespace(c))) {
                    lblValidation.setText("No symbols allowed");
                    lblValidation.setForeground(Color.red);
                    return false;
                }
            }
        }

        // All checks passed
        lblValidation.setText("Valid input");
        lblValidation.setForeground(new Color(0, 128, 0)); // Dark green
        return true;
    }

    public boolean number(String text, JLabel lblValidation, boolean realNumber) {
        //Number check

        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                lblValidation.setText("Enter a number allwed");
                lblValidation.setForeground(Color.red);
                return false;
            }
        }

        if (!realNumber) {
            if (text.contains(".")) {
                lblValidation.setText("Enter an integer.");
                lblValidation.setForeground(Color.red);
            }
        }
        return true;
    }

    public boolean validtionGender(JRadioButton radFemale, JRadioButton radMale, JLabel lblValidation) {
        if (radFemale.isSelected()) {

            lblValidation.setText("Female Selected");
            lblValidation.setForeground(Color.green);
            return true;
        } else if (radMale.isSelected()) {

            lblValidation.setText("Male Selected");
            lblValidation.setForeground(Color.green);
            return true;
        } else {
            lblValidation.setText("Click on a gender");
            lblValidation.setForeground(Color.red);
            return false;
        }
    }

    public boolean validtionDate(String date, JLabel lblValidation) {

        if (date.length() == 9) {
            if (number(date.substring(0, 4), lblValidation, false) && date.substring(4, 5).equals("-") && number(date.substring(5, 7), lblValidation, false) && date.substring(7, 8).equals("-") && number(date.substring(8, 10), lblValidation, false)) {
                if (Integer.parseInt(date.substring(0, 4)) >= 1950 && Integer.parseInt(date.substring(0, 4)) <= 2020) {

                    if (Integer.parseInt(date.substring(5, 7)) >= 1 && Integer.parseInt(date.substring(5, 7)) <= 12) {
                        if (Integer.parseInt(date.substring(8, 10)) >= 1 && Integer.parseInt(date.substring(8, 10)) <= 31) {
                            lblValidation.setText("Date selected");
                            lblValidation.setForeground(Color.green);
                            return true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Day must be between 1 and 30");
                            lblValidation.setForeground(Color.RED);
                            return false;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Month must be between 1 and 12");
                    lblValidation.setForeground(Color.RED);
                    return false;
                }

            } else {

                lblValidation.setForeground(Color.red);
                return false;
            }
        } else {
            return false;
        }

        return true;

    }

    //method to convert date
    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        if (dateToConvert != null) {
            return dateToConvert.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        } else {
            return null;
        }

    }

    public boolean password(char[] password, JLabel validationText) {
        /* Requirements:
    Req 1: Minimum 8 characters
    Req 2: At least 1 uppercase letter
    Req 3: At least 1 lowercase letter  
    Req 4: At least 1 number
    Req 5: At least 1 special character
    Req 6: No spaces
         */

        boolean req1 = true, req2 = true, req3 = true, req4 = true, req5 = true;

        if (password.length >= 8) {
            
            StringBuilder output = new StringBuilder("Your password is missing:");

            for (int i = 0; i < password.length; i++) {
                char chr = password[i];

               
               

                // Check for uppercase letters (Req 2)
                if (Character.isUpperCase(chr)) {
                    req2 = false; 
                }

                // Check for lowercase letters (Req 3)
                if (Character.isLowerCase(chr)) {
                    req3 = false; 
                }

                // Check for numbers (Req 4)
                if (Character.isDigit(chr)) {
                    req4 = false; 
                }

                // Check for special characters (Req 5)
                if (!Character.isLetterOrDigit(chr) && !Character.isSpaceChar(chr)) {
                    req5 = false; 
                }
            }

            // After checking all characters, update requirements that weren't met
            if (req2) {
                output.append("\n✗ At least 1 uppercase letter (A-Z)");
            }
            if (req3) {
                output.append("\n✗ At least 1 lowercase letter (a-z)");
            }
            if (req4) {
                output.append("\n✗ At least 1 number (0-9)");
            }
            if (req5) {
                output.append("\n✗ At least 1 special character (!@#$%^&*)");
            }

            // Check if all requirements are met
            if (req2 || req3 || req4 || req5) {
                validationText.setText("Eg. Password123!");
                validationText.setForeground(Color.RED);

                JOptionPane.showMessageDialog(
                        null,
                        "Password must meet the following requirements:\n"
                        + "✓ Minimum 8 characters\n"
                        + "✓ At least 1 uppercase letter (A-Z)\n"
                        + "✓ At least 1 lowercase letter (a-z)\n"
                        + "✓ At least 1 number (0-9)\n"
                        + "✓ At least 1 special character (!@#$%^&*)\n"
                        + "✗ \n\n"
                        + output.toString(),
                        "Password Requirements",
                        JOptionPane.ERROR_MESSAGE
                );

                return false;
            } else {
                validationText.setText("✓ Password accepted");
                validationText.setForeground(new Color(0, 128, 0)); // Dark green
                return true;
            }

        } else {
            validationText.setText("Eg. Password123!");
            validationText.setForeground(Color.RED);

            JOptionPane.showMessageDialog(
                    null,
                    "Password must be at least 8 characters long\n\n"
                    + "Your password is missing:\n"
                    + "✗ Minimum 8 characters (you have " + password.length + ")",
                    "Password Too Short",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }
    }

}
