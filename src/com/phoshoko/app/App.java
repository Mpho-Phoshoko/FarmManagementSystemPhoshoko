/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.phoshoko.app;

import com.phoshoko.utilities.*;
import com.phoshoko.storage.Connect;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;

/**
 *
 *
 * @author Mpho
 */
public class App extends javax.swing.JFrame {

    /**
     * Creates new form App
     */
    private final Logic lg = new Logic();

    private final Style look = new Style();
    private boolean fullScreen = false, selected, maximized = true;
    private int xMouse;
    private int yMouse;

    public App() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

        //Dashboard varibles
        lblCountCrops.setText("Crops: " + Connect.getSizeCrops());
        lblCountEquipment.setText("Equipment: " + Connect.getSizeEquipment());
        lblCountLivestock.setText("Livestock: " + Connect.getSizeLivestock());
        //Table Styling
        Style.dshboardTable(tblDashboard);
        Style.table(tblCrops);
        Style.table(tblUsers);
        Style.table(tblLivestock);
        Style.table(tblMaintenance);
        Style.table(tblFinance);
        Style.table(tblEquipment);

        //button styling
        //table array
        JTable[] tblArr = {tblCrops, tblEquipment, tblFinance, tblLivestock, tblMaintenance, tblUsers};

        //Crops
        look.functionButtons(lblInsertCrops, tblArr, "insert", Connect.CLASS_CROPS);
        look.functionButtons(lblDeleteCrops, tblArr, "delete", Connect.CLASS_CROPS);
        look.functionButtons(lblEditCrops, tblArr, "edit", Connect.CLASS_CROPS);
        look.functionButtons(lblReportCrops, tblArr, "report", Connect.CLASS_CROPS);

        //Users
        look.functionButtons(lblInsertUsers, tblArr, "insert", Connect.CLASS_USERS);
        look.functionButtons(lblDeleteUsers, tblArr, "delete", Connect.CLASS_USERS);
        look.functionButtons(lblEditUsers, tblArr, "edit", Connect.CLASS_USERS);
        look.functionButtons(lblReportUsers, tblArr, "report", Connect.CLASS_USERS);

        //Equipment
        look.functionButtons(lblInsertEquipment, tblArr, "insert", Connect.CLASS_EQUIPMENT);
        look.functionButtons(lblDeleteEquipment, tblArr, "delete", Connect.CLASS_EQUIPMENT);
        look.functionButtons(lblEditEquipment, tblArr, "edit", Connect.CLASS_EQUIPMENT);
        look.functionButtons(lblReportEquipment, tblArr, "report", Connect.CLASS_EQUIPMENT);

        //Livestock
        look.functionButtons(lblInsertLivestock, tblArr, "insert", Connect.CLASS_LIVESTOCK);
        look.functionButtons(lblDeleteLivestock, tblArr, "delete", Connect.CLASS_LIVESTOCK);
        look.functionButtons(lblEditLivestock, tblArr, "edit", Connect.CLASS_LIVESTOCK);
        look.functionButtons(lblReportLivestock, tblArr, "report", Connect.CLASS_LIVESTOCK);

        //Finance
        look.functionButtons(lblInsertFinance, tblArr, "insert", Connect.CLASS_FINANCE);
        look.functionButtons(lblDeleteFinance, tblArr, "delete", Connect.CLASS_FINANCE);
        look.functionButtons(lblEditFinance, tblArr, "edit", Connect.CLASS_FINANCE);
        look.functionButtons(lblReportFinance, tblArr, "report", Connect.CLASS_FINANCE);

        //MaintenanceLogs
        look.functionButtons(lblInsertMaintenanceLogs, tblArr, "insert", Connect.CLASS_MAINTENANCELOGS);
        look.functionButtons(lblDeleteMaintenanceLogs, tblArr, "delete", Connect.CLASS_MAINTENANCELOGS);
        look.functionButtons(lblEditMaintenanceLogs, tblArr, "edit", Connect.CLASS_MAINTENANCELOGS);
        look.functionButtons(lblReportMaintenanceLogs, tblArr, "report", Connect.CLASS_MAINTENANCELOGS);

        populateTables();

        // Path to the image file, relative to the project root or source folder
        String imagePath = "/com/phoshoko/images/fern.png";

        try {
            // Use getClass().getResource() to get the URL of the resource
            // The leading '/' ensures the path is absolute from the classpath root
            ImageIcon icon = new ImageIcon(App.class.getResource(imagePath));

            // Set the JFrame's icon using the loaded image
            this.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.err.println("Error: The image file was not found at " + imagePath);
            e.printStackTrace();
        }

    }

    public void resetTables() {
        //reset values
        lg.resetTable(tblDashboard);
        lg.resetTable(tblUsers);
        lg.resetTable(tblLivestock);
        lg.resetTable(tblCrops);
        lg.resetTable(tblEquipment);
        lg.resetTable(tblMaintenance);
        lg.resetTable(tblFinance);
    }

    public final void populateTables() {
        resetTables();

        //populate tables
        lg.populateTables("dashboard", tblDashboard);
        lg.populateTables(Connect.getCLASS_CROPS(), tblCrops);
        lg.populateTables(Connect.getCLASS_EQUIPMENT(), tblEquipment);
        lg.populateTables(Connect.getCLASS_LIVESTOCK(), tblLivestock);
        lg.populateTables(Connect.getCLASS_MAINTENANCELOGS(), tblMaintenance);
        lg.populateTables(Connect.getCLASS_FINANCE(), tblFinance);
        lg.populateTables(Connect.getCLASS_USERS(), tblUsers);
    }

    public JLabel[] getAdminLabels() {
        // Returning the data gives me an error of empty statement
        JLabel[] data = {lblUsers, lblFinance, lblDeleteCrops, lblDeleteEquipment, lblDeleteFinance, lblDeleteLivestock, lblDeleteMaintenanceLogs, lblDeleteUsers, lblEditCrops, lblEditEquipment, lblEditFinance, lblEditLivestock, lblEditMaintenanceLogs, lblEditUsers};
        return data;
    }

    public void setLblUsername(String username) {
        lblUsername.setText(username);
    }

    private void lblCenterBottomSignOut() {
        // Get panel dimensions
        int panelWidth = pnlSideBar.getWidth();
        int panelHeight = pnlSideBar.getHeight();

        // Get label dimensions
        int labelWidth = lblSignOut.getWidth();
        int labelHeight = lblSignOut.getHeight();

        // Calculate centered X position
        int x = (panelWidth - labelWidth) / 2;

        // Place label 20px from the bottom
        int y = panelHeight - labelHeight - 20;

        // Update label position
        lblSignOut.setBounds(x, y, lblSignOut.getWidth(), lblSignOut.getHeight());
        lblSignOut.setLocation(x, y);

        //redraw
        lblSignOut.revalidate();
        lblSignOut.repaint();
    }

    private void sideBarHoverReset() {
        look.sideBarOptionSelect(lblDashboard, false);
        look.sideBarOptionSelect(lblCrops, false);
        look.sideBarOptionSelect(lblLivestock, false);
        look.sideBarOptionSelect(lblEquipment, false);
        look.sideBarOptionSelect(lblMaintenanceLogs, false);
        look.sideBarOptionSelect(lblUsers, false);
        look.sideBarOptionSelect(lblFinance, false);
    }

    private void setFrame(JPanel panel, JLabel label) {
        selected = true;
        sideBarHoverReset();
        look.sideBarOptionSelect(label, true);
        pnlDisplay.removeAll();
        pnlDisplay.add(panel);
        pnlDisplay.repaint();
        pnlDisplay.revalidate();
    }

    //getting the tables to be used in styles so aplications of function logic can be applied from the begin
    public JTable getTblCrops() {
        return tblCrops;
    }

    public JTable getTblLivestock() {
        return tblLivestock;
    }

    public JTable getTblEquipment() {
        return tblEquipment;
    }

    public JTable getTblFinance() {
        return tblFinance;
    }

    public JTable getTblMaintenanceLogs() {
        return tblMaintenance;
    }

    public JTable getTblUsers() {
        return tblUsers;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        pnlHeader = new javax.swing.JPanel();
        btnMaximize = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnMinus = new javax.swing.JButton();
        pnlSideBar = new javax.swing.JPanel();
        lblUsernamePng = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblMaintenanceLogs = new javax.swing.JLabel();
        lblEquipment = new javax.swing.JLabel();
        lblLivestock = new javax.swing.JLabel();
        lblCrops = new javax.swing.JLabel();
        lblDashboard = new javax.swing.JLabel();
        lblSignOut = new javax.swing.JLabel();
        lblUsers = new javax.swing.JLabel();
        lblFinance = new javax.swing.JLabel();
        pnlDisplay = new javax.swing.JPanel();
        pnlDashboard = new javax.swing.JPanel();
        lblHeadingDashboard = new javax.swing.JLabel();
        lblCountLivestock = new javax.swing.JLabel();
        lblCountCrops = new javax.swing.JLabel();
        lblCountEquipment = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDashboard = new javax.swing.JTable();
        lblRecentActivities = new javax.swing.JLabel();
        pnlLivestock = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLivestock = new javax.swing.JTable();
        lblInsertLivestock = new javax.swing.JLabel();
        lblDeleteLivestock = new javax.swing.JLabel();
        lblEditLivestock = new javax.swing.JLabel();
        lblHeadingLivestock = new javax.swing.JLabel();
        lblReportLivestock = new javax.swing.JLabel();
        txfSearchLivestock = new javax.swing.JTextField();
        pnlCrops = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCrops = new javax.swing.JTable();
        lblEditCrops = new javax.swing.JLabel();
        lblDeleteCrops = new javax.swing.JLabel();
        lblHeadingCrops = new javax.swing.JLabel();
        lblInsertCrops = new javax.swing.JLabel();
        lblReportCrops = new javax.swing.JLabel();
        txfSearchCrops = new javax.swing.JTextField();
        pnlMaintenanceLogs = new javax.swing.JPanel();
        lblHeadingMaintenanceLogs = new javax.swing.JLabel();
        lblEditMaintenanceLogs = new javax.swing.JLabel();
        lblDeleteMaintenanceLogs = new javax.swing.JLabel();
        lblInsertMaintenanceLogs = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMaintenance = new javax.swing.JTable();
        lblReportMaintenanceLogs = new javax.swing.JLabel();
        txfSearchMaintenance = new javax.swing.JTextField();
        pnlFinance = new javax.swing.JPanel();
        lblHeadingFinance = new javax.swing.JLabel();
        lblEditFinance = new javax.swing.JLabel();
        lblDeleteFinance = new javax.swing.JLabel();
        lblInsertFinance = new javax.swing.JLabel();
        lblReportFinance = new javax.swing.JLabel();
        txfSearchFinance = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblFinance = new javax.swing.JTable();
        pnlEquipment = new javax.swing.JPanel();
        lblHeadingEquipment = new javax.swing.JLabel();
        lblEditEquipment = new javax.swing.JLabel();
        lblDeleteEquipment = new javax.swing.JLabel();
        lblInsertEquipment = new javax.swing.JLabel();
        txfSearchEquipment = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblEquipment = new javax.swing.JTable();
        lblReportEquipment = new javax.swing.JLabel();
        pnlUsers = new javax.swing.JPanel();
        lblHeadingUsers = new javax.swing.JLabel();
        lblEditUsers = new javax.swing.JLabel();
        lblDeleteUsers = new javax.swing.JLabel();
        lblInsertUsers = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        lblReportUsers = new javax.swing.JLabel();
        txfSearchUsers = new javax.swing.JTextField();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FarmManenangementSystem");
        setUndecorated(true);
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        pnlHeader.setBackground(new java.awt.Color(240, 235, 225));
        pnlHeader.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        pnlHeader.setPreferredSize(new java.awt.Dimension(800, 30));
        pnlHeader.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlHeaderMouseDragged(evt);
            }
        });
        pnlHeader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlHeaderMousePressed(evt);
            }
        });

        btnMaximize.setBackground(new java.awt.Color(242, 242, 242));
        btnMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/maximize.png"))); // NOI18N
        btnMaximize.setBorder(null);
        btnMaximize.setBorderPainted(false);
        btnMaximize.setContentAreaFilled(false);
        btnMaximize.setFocusable(false);
        btnMaximize.setRequestFocusEnabled(false);
        btnMaximize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMaximizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMaximizeMouseExited(evt);
            }
        });
        btnMaximize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaximizeActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(242, 242, 242));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/Exit.png"))); // NOI18N
        btnExit.setBorder(null);
        btnExit.setBorderPainted(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setFocusable(false);
        btnExit.setRequestFocusEnabled(false);
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
        });
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnMinus.setBackground(new java.awt.Color(242, 242, 242));
        btnMinus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/minus.png"))); // NOI18N
        btnMinus.setBorder(null);
        btnMinus.setBorderPainted(false);
        btnMinus.setContentAreaFilled(false);
        btnMinus.setFocusable(false);
        btnMinus.setRequestFocusEnabled(false);
        btnMinus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMinusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMinusMouseExited(evt);
            }
        });
        btnMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMinus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMaximize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit)
                .addGap(0, 0, 0))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnExit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btnMaximize, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        pnlSideBar.setBackground(new java.awt.Color(240, 235, 225));
        pnlSideBar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 2, new java.awt.Color(0, 0, 0)));
        pnlSideBar.setFocusable(false);
        pnlSideBar.setPreferredSize(new java.awt.Dimension(200, 570));
        pnlSideBar.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnlSideBarComponentResized(evt);
            }
        });
        pnlSideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsernamePng.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsernamePng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/user.png"))); // NOI18N
        lblUsernamePng.setPreferredSize(new java.awt.Dimension(200, 150));
        pnlSideBar.add(lblUsernamePng, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, 144));

        lblUsername.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("Mpho_Phoshoko");
        pnlSideBar.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 200, 31));

        lblMaintenanceLogs.setBackground(pnlSideBar.getBackground());
        lblMaintenanceLogs.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMaintenanceLogs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaintenanceLogs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/log-file.png"))); // NOI18N
        lblMaintenanceLogs.setText("Maintenance");
        lblMaintenanceLogs.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        lblMaintenanceLogs.setOpaque(true);
        lblMaintenanceLogs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMaintenanceLogsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblMaintenanceLogsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblMaintenanceLogsMouseExited(evt);
            }
        });
        pnlSideBar.add(lblMaintenanceLogs, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 370, 188, 40));

        lblEquipment.setBackground(pnlSideBar.getBackground());
        lblEquipment.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblEquipment.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEquipment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/shovel.png"))); // NOI18N
        lblEquipment.setText("Equipment");
        lblEquipment.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        lblEquipment.setOpaque(true);
        lblEquipment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEquipmentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblEquipmentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblEquipmentMouseExited(evt);
            }
        });
        pnlSideBar.add(lblEquipment, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 330, 188, 40));

        lblLivestock.setBackground(pnlSideBar.getBackground());
        lblLivestock.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblLivestock.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblLivestock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/deer.png"))); // NOI18N
        lblLivestock.setText("LiveStock");
        lblLivestock.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        lblLivestock.setOpaque(true);
        lblLivestock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLivestockMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLivestockMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLivestockMouseExited(evt);
            }
        });
        pnlSideBar.add(lblLivestock, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 290, 188, 40));

        lblCrops.setBackground(pnlSideBar.getBackground());
        lblCrops.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCrops.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCrops.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/massage.png"))); // NOI18N
        lblCrops.setText("Crops");
        lblCrops.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        lblCrops.setOpaque(true);
        lblCrops.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCropsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCropsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCropsMouseExited(evt);
            }
        });
        pnlSideBar.add(lblCrops, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 250, 188, 40));

        lblDashboard.setBackground(pnlSideBar.getBackground());
        lblDashboard.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/house-chimney.png"))); // NOI18N
        lblDashboard.setText("DashBoard");
        lblDashboard.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        lblDashboard.setOpaque(true);
        lblDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDashboardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDashboardMouseExited(evt);
            }
        });
        pnlSideBar.add(lblDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 210, 188, 40));

        lblSignOut.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblSignOut.setForeground(new java.awt.Color(255, 102, 102));
        lblSignOut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSignOut.setText("Sign Out");
        lblSignOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSignOutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSignOutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSignOutMouseExited(evt);
            }
        });
        pnlSideBar.add(lblSignOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 113, -1));

        lblUsers.setBackground(pnlSideBar.getBackground());
        lblUsers.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblUsers.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/avatar.png"))); // NOI18N
        lblUsers.setText("Users");
        lblUsers.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        lblUsers.setOpaque(true);
        lblUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblUsersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblUsersMouseExited(evt);
            }
        });
        pnlSideBar.add(lblUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 450, 188, 40));

        lblFinance.setBackground(pnlSideBar.getBackground());
        lblFinance.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblFinance.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFinance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/usd-circle.png"))); // NOI18N
        lblFinance.setText("Finance");
        lblFinance.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        lblFinance.setOpaque(true);
        lblFinance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFinanceMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblFinanceMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblFinanceMouseExited(evt);
            }
        });
        pnlSideBar.add(lblFinance, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 410, 188, 40));

        pnlDisplay.setBackground(new java.awt.Color(255, 255, 255));
        pnlDisplay.setPreferredSize(new java.awt.Dimension(600, 570));
        pnlDisplay.setLayout(new java.awt.CardLayout());

        pnlDashboard.setBackground(new java.awt.Color(255, 255, 255));

        lblHeadingDashboard.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblHeadingDashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeadingDashboard.setText("Dashboard");
        lblHeadingDashboard.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 4, 0, new java.awt.Color(0, 0, 0)));

        lblCountLivestock.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCountLivestock.setText("Livestock: 0");
        lblCountLivestock.setMaximumSize(new java.awt.Dimension(190, 50));
        lblCountLivestock.setPreferredSize(new java.awt.Dimension(190, 22));

        lblCountCrops.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCountCrops.setText("Crops:  0");
        lblCountCrops.setPreferredSize(lblCountLivestock.getPreferredSize());

        lblCountEquipment.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCountEquipment.setText("Equipment:  0");

        tblDashboard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDashboard.setGridColor(new java.awt.Color(0, 0, 0));
        tblDashboard.setOpaque(false);
        tblDashboard.setShowGrid(true);
        tblDashboard.getTableHeader().setResizingAllowed(false);
        tblDashboard.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(tblDashboard);
        if (tblDashboard.getColumnModel().getColumnCount() > 0) {
            tblDashboard.getColumnModel().getColumn(0).setPreferredWidth(22);
        }

        lblRecentActivities.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblRecentActivities.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRecentActivities.setText("Recenct Activities");

        javax.swing.GroupLayout pnlDashboardLayout = new javax.swing.GroupLayout(pnlDashboard);
        pnlDashboard.setLayout(pnlDashboardLayout);
        pnlDashboardLayout.setHorizontalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDashboardLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHeadingDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDashboardLayout.createSequentialGroup()
                        .addComponent(lblCountLivestock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(lblCountCrops, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                        .addGap(42, 42, 42)
                        .addComponent(lblCountEquipment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblRecentActivities, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6)))
        );
        pnlDashboardLayout.setVerticalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDashboardLayout.createSequentialGroup()
                .addComponent(lblHeadingDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCountLivestock, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCountCrops, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCountEquipment, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRecentActivities, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE))
        );

        pnlDisplay.add(pnlDashboard, "card2");

        pnlLivestock.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        tblLivestock.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblLivestock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "LivestockID", "Breed", "TagID", "DateOfBirth", "Gender", "HealthStatus", "VaccinationDate", "BreedingDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLivestock.setGridColor(new java.awt.Color(0, 0, 0));
        tblLivestock.setShowGrid(true);
        tblLivestock.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblLivestock);
        if (tblLivestock.getColumnModel().getColumnCount() > 0) {
            tblLivestock.getColumnModel().getColumn(0).setResizable(false);
        }

        lblInsertLivestock.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblInsertLivestock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInsertLivestock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/add.png"))); // NOI18N
        lblInsertLivestock.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lblDeleteLivestock.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDeleteLivestock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDeleteLivestock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/bin.png"))); // NOI18N
        lblDeleteLivestock.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lblEditLivestock.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEditLivestock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditLivestock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/edit.png"))); // NOI18N
        lblEditLivestock.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lblHeadingLivestock.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblHeadingLivestock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeadingLivestock.setText("LiveStock Manegement");

        lblReportLivestock.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblReportLivestock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReportLivestock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/analytics.png"))); // NOI18N
        lblReportLivestock.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        txfSearchLivestock.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txfSearchLivestock.setText("SEARCH");
        txfSearchLivestock.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        txfSearchLivestock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txfSearchLivestockKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlLivestockLayout = new javax.swing.GroupLayout(pnlLivestock);
        pnlLivestock.setLayout(pnlLivestockLayout);
        pnlLivestockLayout.setHorizontalGroup(
            pnlLivestockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHeadingLivestock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlLivestockLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(txfSearchLivestock, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(lblEditLivestock, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDeleteLivestock, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblInsertLivestock, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblReportLivestock, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLivestockLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2))
        );
        pnlLivestockLayout.setVerticalGroup(
            pnlLivestockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLivestockLayout.createSequentialGroup()
                .addComponent(lblHeadingLivestock, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(pnlLivestockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfSearchLivestock, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEditLivestock)
                    .addComponent(lblReportLivestock)
                    .addComponent(lblDeleteLivestock)
                    .addComponent(lblInsertLivestock))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
        );

        pnlDisplay.add(pnlLivestock, "card4");

        pnlCrops.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tblCrops.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblCrops.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CropID", "CropType", "PlantingDate", "HarvestingDate", "Yield", "PecticideUsage"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCrops.setGridColor(new java.awt.Color(0, 0, 0));
        tblCrops.setShowGrid(true);
        tblCrops.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblCrops);
        if (tblCrops.getColumnModel().getColumnCount() > 0) {
            tblCrops.getColumnModel().getColumn(0).setResizable(false);
            tblCrops.getColumnModel().getColumn(5).setResizable(false);
        }

        lblEditCrops.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEditCrops.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditCrops.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/edit.png"))); // NOI18N
        lblEditCrops.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lblDeleteCrops.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDeleteCrops.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDeleteCrops.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/bin.png"))); // NOI18N
        lblDeleteCrops.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        lblDeleteCrops.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDeleteCropsMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDeleteCropsMouseExited(evt);
            }
        });

        lblHeadingCrops.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblHeadingCrops.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeadingCrops.setText("Crop Manegement");

        lblInsertCrops.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblInsertCrops.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInsertCrops.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/add.png"))); // NOI18N
        lblInsertCrops.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lblReportCrops.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblReportCrops.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReportCrops.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/analytics.png"))); // NOI18N
        lblReportCrops.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        txfSearchCrops.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txfSearchCrops.setText("SEARCH");
        txfSearchCrops.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        txfSearchCrops.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txfSearchCropsKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlCropsLayout = new javax.swing.GroupLayout(pnlCrops);
        pnlCrops.setLayout(pnlCropsLayout);
        pnlCropsLayout.setHorizontalGroup(
            pnlCropsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHeadingCrops, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCropsLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(txfSearchCrops, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(lblEditCrops, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDeleteCrops, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblInsertCrops, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblReportCrops, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCropsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        pnlCropsLayout.setVerticalGroup(
            pnlCropsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCropsLayout.createSequentialGroup()
                .addComponent(lblHeadingCrops, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(pnlCropsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txfSearchCrops, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCropsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblEditCrops)
                        .addComponent(lblDeleteCrops)
                        .addComponent(lblInsertCrops)
                        .addComponent(lblReportCrops)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
        );

        pnlDisplay.add(pnlCrops, "card7");

        pnlMaintenanceLogs.setBackground(new java.awt.Color(255, 255, 255));

        lblHeadingMaintenanceLogs.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblHeadingMaintenanceLogs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeadingMaintenanceLogs.setText("Maintenance Logs");

        lblEditMaintenanceLogs.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEditMaintenanceLogs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditMaintenanceLogs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/edit.png"))); // NOI18N
        lblEditMaintenanceLogs.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lblDeleteMaintenanceLogs.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDeleteMaintenanceLogs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDeleteMaintenanceLogs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/bin.png"))); // NOI18N
        lblDeleteMaintenanceLogs.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lblInsertMaintenanceLogs.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblInsertMaintenanceLogs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInsertMaintenanceLogs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/add.png"))); // NOI18N
        lblInsertMaintenanceLogs.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        tblMaintenance.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblMaintenance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "LogID", "EquipmentID", "ServiceDate", "Description", "Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMaintenance.setGridColor(new java.awt.Color(0, 0, 0));
        tblMaintenance.setShowGrid(true);
        tblMaintenance.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblMaintenance);

        lblReportMaintenanceLogs.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblReportMaintenanceLogs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReportMaintenanceLogs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/analytics.png"))); // NOI18N
        lblReportMaintenanceLogs.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        lblReportMaintenanceLogs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblReportMaintenanceLogsMouseClicked(evt);
            }
        });

        txfSearchMaintenance.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txfSearchMaintenance.setText("SEARCH");
        txfSearchMaintenance.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        txfSearchMaintenance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfSearchMaintenanceActionPerformed(evt);
            }
        });
        txfSearchMaintenance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txfSearchMaintenanceKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlMaintenanceLogsLayout = new javax.swing.GroupLayout(pnlMaintenanceLogs);
        pnlMaintenanceLogs.setLayout(pnlMaintenanceLogsLayout);
        pnlMaintenanceLogsLayout.setHorizontalGroup(
            pnlMaintenanceLogsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHeadingMaintenanceLogs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMaintenanceLogsLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(txfSearchMaintenance, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(lblEditMaintenanceLogs, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDeleteMaintenanceLogs, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblInsertMaintenanceLogs, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblReportMaintenanceLogs, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMaintenanceLogsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4))
        );
        pnlMaintenanceLogsLayout.setVerticalGroup(
            pnlMaintenanceLogsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMaintenanceLogsLayout.createSequentialGroup()
                .addComponent(lblHeadingMaintenanceLogs, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(pnlMaintenanceLogsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblReportMaintenanceLogs)
                    .addGroup(pnlMaintenanceLogsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txfSearchMaintenance, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEditMaintenanceLogs)
                        .addComponent(lblInsertMaintenanceLogs)
                        .addComponent(lblDeleteMaintenanceLogs)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
        );

        pnlDisplay.add(pnlMaintenanceLogs, "card7");

        pnlFinance.setBackground(new java.awt.Color(255, 255, 255));

        lblHeadingFinance.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblHeadingFinance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeadingFinance.setText("Finance");

        lblEditFinance.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEditFinance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditFinance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/edit.png"))); // NOI18N
        lblEditFinance.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lblDeleteFinance.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDeleteFinance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDeleteFinance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/bin.png"))); // NOI18N
        lblDeleteFinance.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lblInsertFinance.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblInsertFinance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInsertFinance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/add.png"))); // NOI18N
        lblInsertFinance.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lblReportFinance.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblReportFinance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReportFinance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/analytics.png"))); // NOI18N
        lblReportFinance.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        txfSearchFinance.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txfSearchFinance.setText("SEARCH");
        txfSearchFinance.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        txfSearchFinance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txfSearchFinanceKeyReleased(evt);
            }
        });

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));

        tblFinance.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblFinance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TransactionDate", "IncomeOrExpense", "HarvestingDate", "Description", "FinanceID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFinance.setGridColor(new java.awt.Color(0, 0, 0));
        tblFinance.setShowGrid(true);
        tblFinance.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tblFinance);

        javax.swing.GroupLayout pnlFinanceLayout = new javax.swing.GroupLayout(pnlFinance);
        pnlFinance.setLayout(pnlFinanceLayout);
        pnlFinanceLayout.setHorizontalGroup(
            pnlFinanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHeadingFinance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlFinanceLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(txfSearchFinance, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(lblEditFinance, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblInsertFinance, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDeleteFinance, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblReportFinance, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFinanceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5))
        );
        pnlFinanceLayout.setVerticalGroup(
            pnlFinanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFinanceLayout.createSequentialGroup()
                .addComponent(lblHeadingFinance, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(pnlFinanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEditFinance)
                    .addComponent(lblInsertFinance)
                    .addComponent(lblDeleteFinance)
                    .addComponent(lblReportFinance)
                    .addComponent(txfSearchFinance, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane5))
        );

        pnlDisplay.add(pnlFinance, "card8");

        pnlEquipment.setBackground(new java.awt.Color(255, 255, 255));
        pnlEquipment.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnlEquipmentComponentResized(evt);
            }
        });

        lblHeadingEquipment.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblHeadingEquipment.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeadingEquipment.setText("Equipment Manegement");

        lblEditEquipment.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEditEquipment.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditEquipment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/edit.png"))); // NOI18N
        lblEditEquipment.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lblDeleteEquipment.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDeleteEquipment.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDeleteEquipment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/bin.png"))); // NOI18N
        lblDeleteEquipment.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lblInsertEquipment.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblInsertEquipment.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInsertEquipment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/add.png"))); // NOI18N
        lblInsertEquipment.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        txfSearchEquipment.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txfSearchEquipment.setText("SEARCH");
        txfSearchEquipment.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        txfSearchEquipment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfSearchEquipmentActionPerformed(evt);
            }
        });
        txfSearchEquipment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txfSearchEquipmentKeyReleased(evt);
            }
        });

        jScrollPane7.setBackground(new java.awt.Color(255, 255, 255));

        tblEquipment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblEquipment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EquipmentID", "EquipmentType", "PurchaseDate", "Condition", "LastServiceDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEquipment.setGridColor(new java.awt.Color(0, 0, 0));
        tblEquipment.setOpaque(false);
        tblEquipment.setShowGrid(true);
        tblEquipment.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(tblEquipment);

        lblReportEquipment.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblReportEquipment.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReportEquipment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/analytics.png"))); // NOI18N
        lblReportEquipment.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        javax.swing.GroupLayout pnlEquipmentLayout = new javax.swing.GroupLayout(pnlEquipment);
        pnlEquipment.setLayout(pnlEquipmentLayout);
        pnlEquipmentLayout.setHorizontalGroup(
            pnlEquipmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHeadingEquipment, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addGroup(pnlEquipmentLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(txfSearchEquipment, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEditEquipment, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDeleteEquipment, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblInsertEquipment, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblReportEquipment, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEquipmentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7))
        );
        pnlEquipmentLayout.setVerticalGroup(
            pnlEquipmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEquipmentLayout.createSequentialGroup()
                .addComponent(lblHeadingEquipment, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(pnlEquipmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfSearchEquipment)
                    .addComponent(lblReportEquipment)
                    .addComponent(lblDeleteEquipment)
                    .addComponent(lblInsertEquipment)
                    .addComponent(lblEditEquipment))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane7)
                .addGap(0, 0, 0))
        );

        pnlDisplay.add(pnlEquipment, "card5");

        pnlUsers.setBackground(new java.awt.Color(255, 255, 255));
        pnlUsers.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnlUsersComponentResized(evt);
            }
        });

        lblHeadingUsers.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblHeadingUsers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeadingUsers.setText("Users");

        lblEditUsers.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEditUsers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/edit.png"))); // NOI18N
        lblEditUsers.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lblDeleteUsers.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDeleteUsers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDeleteUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/bin.png"))); // NOI18N
        lblDeleteUsers.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lblInsertUsers.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblInsertUsers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInsertUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/add.png"))); // NOI18N
        lblInsertUsers.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        tblUsers.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UserID", "Username", "Passwored", "Role", "Gender", "DateOfBirth"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsers.setGridColor(new java.awt.Color(0, 0, 0));
        tblUsers.setOpaque(false);
        tblUsers.setShowGrid(true);
        tblUsers.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblUsers);

        lblReportUsers.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblReportUsers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReportUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/analytics.png"))); // NOI18N
        lblReportUsers.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        txfSearchUsers.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txfSearchUsers.setText("SEARCH");
        txfSearchUsers.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        txfSearchUsers.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txfSearchUsersKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlUsersLayout = new javax.swing.GroupLayout(pnlUsers);
        pnlUsers.setLayout(pnlUsersLayout);
        pnlUsersLayout.setHorizontalGroup(
            pnlUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHeadingUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlUsersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3))
            .addGroup(pnlUsersLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(txfSearchUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(lblEditUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblInsertUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDeleteUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblReportUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlUsersLayout.setVerticalGroup(
            pnlUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsersLayout.createSequentialGroup()
                .addComponent(lblHeadingUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(pnlUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfSearchUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblReportUsers)
                    .addComponent(lblEditUsers)
                    .addComponent(lblDeleteUsers)
                    .addComponent(lblInsertUsers))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
        );

        pnlDisplay.add(pnlUsers, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlSideBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSideBar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(pnlDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblDashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDashboardMouseEntered
        if (!selected) {
            look.sideBarOptionSelect(lblDashboard, true);
        }

    }//GEN-LAST:event_lblDashboardMouseEntered

    private void lblDashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDashboardMouseExited
        if (!selected) {
            look.sideBarOptionSelect(lblDashboard, false);
        }
    }//GEN-LAST:event_lblDashboardMouseExited

    private void lblCropsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCropsMouseEntered
        if (!selected) {
            look.sideBarOptionSelect(lblCrops, true);
        }
    }//GEN-LAST:event_lblCropsMouseEntered

    private void lblCropsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCropsMouseExited
        if (!selected) {
            look.sideBarOptionSelect(lblCrops, false);
        }
    }//GEN-LAST:event_lblCropsMouseExited

    private void lblLivestockMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLivestockMouseEntered
        if (!selected) {
            look.sideBarOptionSelect(lblLivestock, true);
        }
    }//GEN-LAST:event_lblLivestockMouseEntered

    private void lblLivestockMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLivestockMouseExited
        if (!selected) {
            look.sideBarOptionSelect(lblLivestock, false);
        }
    }//GEN-LAST:event_lblLivestockMouseExited

    private void lblEquipmentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEquipmentMouseEntered
        if (!selected) {
            look.sideBarOptionSelect(lblEquipment, true);
        }
    }//GEN-LAST:event_lblEquipmentMouseEntered

    private void lblEquipmentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEquipmentMouseExited
        if (!selected) {
            look.sideBarOptionSelect(lblEquipment, false);
        }
    }//GEN-LAST:event_lblEquipmentMouseExited

    private void lblMaintenanceLogsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMaintenanceLogsMouseEntered
        if (!selected) {
            look.sideBarOptionSelect(lblMaintenanceLogs, true);
        }
    }//GEN-LAST:event_lblMaintenanceLogsMouseEntered

    private void lblMaintenanceLogsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMaintenanceLogsMouseExited
        if (!selected) {
            look.sideBarOptionSelect(lblMaintenanceLogs, false);
        }
    }//GEN-LAST:event_lblMaintenanceLogsMouseExited

    private void lblDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDashboardMouseClicked
        setFrame(pnlDashboard, lblDashboard);

    }//GEN-LAST:event_lblDashboardMouseClicked

    private void lblCropsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCropsMouseClicked
        setFrame(pnlCrops, lblCrops);
    }//GEN-LAST:event_lblCropsMouseClicked

    private void lblLivestockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLivestockMouseClicked
        setFrame(pnlLivestock, lblLivestock);
    }//GEN-LAST:event_lblLivestockMouseClicked

    private void lblEquipmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEquipmentMouseClicked
        setFrame(pnlEquipment, lblEquipment);
    }//GEN-LAST:event_lblEquipmentMouseClicked

    private void lblMaintenanceLogsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMaintenanceLogsMouseClicked
        setFrame(pnlMaintenanceLogs, lblMaintenanceLogs);
    }//GEN-LAST:event_lblMaintenanceLogsMouseClicked

    private void lblUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsersMouseClicked
        setFrame(pnlUsers, lblUsers);
    }//GEN-LAST:event_lblUsersMouseClicked

    private void lblUsersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsersMouseEntered
        if (!selected) {
            look.sideBarOptionSelect(lblUsers, true);
        }
    }//GEN-LAST:event_lblUsersMouseEntered

    private void lblUsersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsersMouseExited
        if (!selected) {
            look.sideBarOptionSelect(lblUsers, false);
        }
    }//GEN-LAST:event_lblUsersMouseExited

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized

    }//GEN-LAST:event_formComponentResized

    private void btnMaximizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaximizeActionPerformed
        if (maximized) {
            //handle fullscreen - taskbar
            App.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            App.this.setMaximizedBounds(env.getMaximumWindowBounds());
            maximized = false;

            btnMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/maximizeFilled.png")));
            fullScreen = true;
        } else {
            setExtendedState(JFrame.NORMAL);
            maximized = true;

            btnMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/maximize.png")));
            fullScreen = false;
        }
        lblCenterBottomSignOut();
    }//GEN-LAST:event_btnMaximizeActionPerformed

    public void setUserName(String text){
        lblUsers.setText(text);
    }
    private void btnMaximizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaximizeMouseEntered

        if (fullScreen) {
            btnMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/maximize.png")));
        } else {
            btnMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/maximizeFilled.png")));
        }
    }//GEN-LAST:event_btnMaximizeMouseEntered

    private void btnMaximizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaximizeMouseExited
        if (fullScreen) {
            btnMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/maximizeFilled.png")));

        } else {
            btnMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/maximize.png")));
        }
    }//GEN-LAST:event_btnMaximizeMouseExited

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/ExitFilled.png")));
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/Exit.png")));
    }//GEN-LAST:event_btnExitMouseExited

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnMinusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinusMouseEntered
        btnMinus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/minusBold.png")));
    }//GEN-LAST:event_btnMinusMouseEntered

    private void btnMinusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinusMouseExited
        btnMinus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phoshoko/images/minus.png")));
    }//GEN-LAST:event_btnMinusMouseExited

    private void btnMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusActionPerformed
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_btnMinusActionPerformed

    private void pnlHeaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHeaderMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_pnlHeaderMousePressed

    private void pnlHeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHeaderMouseDragged
        if (maximized) {
            int x = evt.getXOnScreen();
            int y = evt.getYOnScreen();
            this.setLocation(x - xMouse, y - yMouse);
        }
    }//GEN-LAST:event_pnlHeaderMouseDragged

    private void pnlUsersComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlUsersComponentResized

    }//GEN-LAST:event_pnlUsersComponentResized

    private void lblFinanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFinanceMouseClicked
        setFrame(pnlFinance, lblFinance);
    }//GEN-LAST:event_lblFinanceMouseClicked

    private void lblFinanceMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFinanceMouseEntered
        if (!selected) {
            look.sideBarOptionSelect(lblFinance, true);
        }
    }//GEN-LAST:event_lblFinanceMouseEntered

    private void lblFinanceMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFinanceMouseExited
        if (!selected) {
            look.sideBarOptionSelect(lblFinance, false);
        }
    }//GEN-LAST:event_lblFinanceMouseExited

    private void pnlEquipmentComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlEquipmentComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlEquipmentComponentResized

    private void txfSearchMaintenanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfSearchMaintenanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfSearchMaintenanceActionPerformed

    private void pnlSideBarComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlSideBarComponentResized

    }//GEN-LAST:event_pnlSideBarComponentResized

    private void txfSearchEquipmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfSearchEquipmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfSearchEquipmentActionPerformed

    private void txfSearchCropsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfSearchCropsKeyReleased
        lg.searchTable(tblCrops, txfSearchCrops);
    }//GEN-LAST:event_txfSearchCropsKeyReleased

    private void lblReportMaintenanceLogsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReportMaintenanceLogsMouseClicked
       
    }//GEN-LAST:event_lblReportMaintenanceLogsMouseClicked

    private void lblDeleteCropsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDeleteCropsMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lblDeleteCropsMouseExited

    private void lblDeleteCropsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDeleteCropsMouseClicked

    }//GEN-LAST:event_lblDeleteCropsMouseClicked

    private void lblSignOutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSignOutMouseEntered
        lblSignOut.setForeground(Color.red);
    }//GEN-LAST:event_lblSignOutMouseEntered

    private void lblSignOutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSignOutMouseExited
        lblSignOut.setForeground(new Color(255, 102, 102));
    }//GEN-LAST:event_lblSignOutMouseExited

    private void txfSearchUsersKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfSearchUsersKeyReleased
        lg.searchTable(tblUsers, txfSearchUsers);
    }//GEN-LAST:event_txfSearchUsersKeyReleased

    private void txfSearchEquipmentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfSearchEquipmentKeyReleased
        lg.searchTable(tblEquipment, txfSearchEquipment);

    }//GEN-LAST:event_txfSearchEquipmentKeyReleased

    private void txfSearchFinanceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfSearchFinanceKeyReleased
        lg.searchTable(tblFinance, txfSearchFinance);

    }//GEN-LAST:event_txfSearchFinanceKeyReleased

    private void txfSearchMaintenanceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfSearchMaintenanceKeyReleased
        lg.searchTable(tblMaintenance, txfSearchMaintenance);

    }//GEN-LAST:event_txfSearchMaintenanceKeyReleased

    private void txfSearchLivestockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfSearchLivestockKeyReleased
        lg.searchTable(tblLivestock, txfSearchLivestock);

    }//GEN-LAST:event_txfSearchLivestockKeyReleased

    private void lblSignOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSignOutMouseClicked
        this.setVisible(false);
        Main.getAuth().resetAuthLabels();
        Main.getAuth().setVisible(true);
        
        
    }//GEN-LAST:event_lblSignOutMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnMaximize;
    private javax.swing.JButton btnMinus;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblCountCrops;
    private javax.swing.JLabel lblCountEquipment;
    private javax.swing.JLabel lblCountLivestock;
    private javax.swing.JLabel lblCrops;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblDeleteCrops;
    private javax.swing.JLabel lblDeleteEquipment;
    private javax.swing.JLabel lblDeleteFinance;
    private javax.swing.JLabel lblDeleteLivestock;
    private javax.swing.JLabel lblDeleteMaintenanceLogs;
    private javax.swing.JLabel lblDeleteUsers;
    private javax.swing.JLabel lblEditCrops;
    private javax.swing.JLabel lblEditEquipment;
    private javax.swing.JLabel lblEditFinance;
    private javax.swing.JLabel lblEditLivestock;
    private javax.swing.JLabel lblEditMaintenanceLogs;
    private javax.swing.JLabel lblEditUsers;
    private javax.swing.JLabel lblEquipment;
    private javax.swing.JLabel lblFinance;
    private javax.swing.JLabel lblHeadingCrops;
    private javax.swing.JLabel lblHeadingDashboard;
    private javax.swing.JLabel lblHeadingEquipment;
    private javax.swing.JLabel lblHeadingFinance;
    private javax.swing.JLabel lblHeadingLivestock;
    private javax.swing.JLabel lblHeadingMaintenanceLogs;
    private javax.swing.JLabel lblHeadingUsers;
    private javax.swing.JLabel lblInsertCrops;
    private javax.swing.JLabel lblInsertEquipment;
    private javax.swing.JLabel lblInsertFinance;
    private javax.swing.JLabel lblInsertLivestock;
    private javax.swing.JLabel lblInsertMaintenanceLogs;
    private javax.swing.JLabel lblInsertUsers;
    private javax.swing.JLabel lblLivestock;
    private javax.swing.JLabel lblMaintenanceLogs;
    private javax.swing.JLabel lblRecentActivities;
    private javax.swing.JLabel lblReportCrops;
    private javax.swing.JLabel lblReportEquipment;
    private javax.swing.JLabel lblReportFinance;
    private javax.swing.JLabel lblReportLivestock;
    private javax.swing.JLabel lblReportMaintenanceLogs;
    private javax.swing.JLabel lblReportUsers;
    private javax.swing.JLabel lblSignOut;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsernamePng;
    private javax.swing.JLabel lblUsers;
    private javax.swing.JPanel pnlCrops;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JPanel pnlDisplay;
    private javax.swing.JPanel pnlEquipment;
    private javax.swing.JPanel pnlFinance;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlLivestock;
    private javax.swing.JPanel pnlMaintenanceLogs;
    private javax.swing.JPanel pnlSideBar;
    private javax.swing.JPanel pnlUsers;
    private javax.swing.JTable tblCrops;
    private javax.swing.JTable tblDashboard;
    private javax.swing.JTable tblEquipment;
    private javax.swing.JTable tblFinance;
    private javax.swing.JTable tblLivestock;
    private javax.swing.JTable tblMaintenance;
    private javax.swing.JTable tblUsers;
    private javax.swing.JTextField txfSearchCrops;
    private javax.swing.JTextField txfSearchEquipment;
    private javax.swing.JTextField txfSearchFinance;
    private javax.swing.JTextField txfSearchLivestock;
    private javax.swing.JTextField txfSearchMaintenance;
    private javax.swing.JTextField txfSearchUsers;
    // End of variables declaration//GEN-END:variables
}
