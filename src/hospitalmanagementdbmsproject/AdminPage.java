/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementdbmsproject;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Faysal Ahmad
 */
public class AdminPage extends javax.swing.JFrame {

    Connection con;
    PreparedStatement insert, select, delete, update, search, shortBy;
    String user = "ADMIN";
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/hospital_management", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void addData(String name, String building, String gender, int age, String address, String Phn_no, String doctor_of,String password,String room_no) {
        try {

            con = getConnection();
            if (!doctor_of.equals("")) {
                insert = con.prepareStatement("insert into doctor(name,age,gender,address,building,phn_no,password,room_no,doctor_of) values(?,?,?,?,?,?,?,?,?)");
                insert.setString(8, room_no);
                insert.setString(9, doctor_of);
            } else {
                System.out.println("StaffAdd");
                insert = con.prepareStatement("insert into staff(name,age,gender,address,building,phn_no,password) values(?,?,?,?,?,?,?)");
            }

            insert.setString(1, name);
            insert.setInt(2, age);
            insert.setString(3, gender);
            insert.setString(4, address);
            insert.setString(5, building);
            insert.setString(6, Phn_no);
            insert.setString(7, password);
            insert.executeUpdate();

            JOptionPane.showMessageDialog(null, "Record Added Successfully", "Success", 1);
            adminNameTf.setText("");
            adminAgeTf.setText("");
            genderCB.setSelectedIndex(0);
            adminAddressTf.setText("");
            dctrOfCB.setSelectedIndex(0);
            adminPhnNoTf.setText("");
            buildingCB.setSelectedIndex(0);
            passTf.setText("");

            if (tableNameLabel.getText().equals("Doctors")) {
                getDoctorData();
            } else {
                getStaffData();
            }

        } catch (SQLException ex) {
            Logger.getLogger(HospitalManagementDBMSProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getDoctorData() {
        con = getConnection();
        try {
            select = con.prepareStatement("select * from doctor");
            ResultSet result = select.executeQuery();
            ResultSetMetaData Rss = result.getMetaData();
            int c = Rss.getColumnCount();
            updateTable(result, c, 1);
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void getStaffData() {
        con = getConnection();
        try {
            select = con.prepareStatement("select * from staff");
            ResultSet result = select.executeQuery();
            ResultSetMetaData Rss = result.getMetaData();
            int c = Rss.getColumnCount();
            updateTable(result, c, 2);

        } catch (SQLException ex) {
            Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTable(ResultSet result, int c, int wh) {
        try {
            DefaultTableModel table = (DefaultTableModel) dctrstfTable.getModel();
            table.setRowCount(0);

            while (result.next()) {
                Vector v = new Vector();

                for (int i = 1; i <= c; i++) {
                    if (wh == 1) {
                        v.add(result.getString("doctor_id"));
                    } else {
                        v.add(result.getString("staff_id"));
                    }
                    v.add(result.getString("name"));
                    v.add(result.getString("building"));
                    v.add("0" + result.getString("phn_no"));
                }
                table.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public AdminPage() {
        initComponents();
        dltBtn.setVisible(false);
        updateBtn.setVisible(false);
        getDoctorData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        userlabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dctrstfTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        adminAgeTf = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        adminNameTf = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        dltBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        staffBtn = new javax.swing.JButton();
        dctrBtn = new javax.swing.JButton();
        tableNameLabel = new javax.swing.JLabel();
        doctorOFlabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        adminPhnNoTf = new javax.swing.JTextField();
        adminAddressTf = new javax.swing.JTextField();
        genderCB = new javax.swing.JComboBox<>();
        buildingLabel = new javax.swing.JLabel();
        buildingCB = new javax.swing.JComboBox<>();
        passlabel = new javax.swing.JLabel();
        passTf = new javax.swing.JPasswordField();
        idSearchTf = new javax.swing.JTextField();
        nameSearchTf = new javax.swing.JTextField();
        nameSearchBtn = new javax.swing.JButton();
        shortByCB = new javax.swing.JComboBox<>();
        buildingSearchCB = new javax.swing.JComboBox<>();
        idSearchBtn = new javax.swing.JButton();
        buildingSearchBtn = new javax.swing.JButton();
        dctrSearchCB = new javax.swing.JComboBox<>();
        roomLabel = new javax.swing.JLabel();
        roomNoCB = new javax.swing.JComboBox<>();
        dctrOfCB = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel1.setText("EWU HOSPITAL");

        jPanel1.setBackground(new java.awt.Color(204, 51, 0));

        userlabel.setBackground(new java.awt.Color(255, 51, 0));
        userlabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        userlabel.setForeground(new java.awt.Color(255, 255, 255));
        userlabel.setText("ADMIN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(userlabel)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(userlabel)
                .addContainerGap())
        );

        dctrstfTable.setBorder(new javax.swing.border.MatteBorder(null));
        dctrstfTable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dctrstfTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Name", "Building", "Phone No"
            }
        ));
        dctrstfTable.setRowHeight(25);
        dctrstfTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dctrstfTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dctrstfTable);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Name:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Age:");

        adminAgeTf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Gender:");

        adminNameTf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        addBtn.setBackground(new java.awt.Color(51, 153, 0));
        addBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        dltBtn.setBackground(new java.awt.Color(255, 0, 51));
        dltBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dltBtn.setForeground(new java.awt.Color(255, 255, 255));
        dltBtn.setText("Delete");
        dltBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dltBtnActionPerformed(evt);
            }
        });

        updateBtn.setBackground(new java.awt.Color(255, 153, 0));
        updateBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        updateBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        staffBtn.setText("Staffs");
        staffBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffBtnActionPerformed(evt);
            }
        });

        dctrBtn.setText("Doctors");
        dctrBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dctrBtnActionPerformed(evt);
            }
        });

        tableNameLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tableNameLabel.setText("Doctors");

        doctorOFlabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doctorOFlabel.setText("Doctor Of:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Address:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Phone No:");

        adminPhnNoTf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        adminAddressTf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        genderCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        buildingLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buildingLabel.setText("Building:");

        buildingCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "B1", "B2", "B3", "B4" }));

        passlabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        passlabel.setText("Password:");

        passTf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        idSearchTf.setText("Enter ID");
        idSearchTf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                idSearchTfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                idSearchTfFocusLost(evt);
            }
        });

        nameSearchTf.setText("Enter Name");
        nameSearchTf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nameSearchTfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameSearchTfFocusLost(evt);
            }
        });

        nameSearchBtn.setText("Search");
        nameSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameSearchBtnActionPerformed(evt);
            }
        });

        shortByCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Short By", "Name", "Building" }));
        shortByCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortByCBActionPerformed(evt);
            }
        });

        buildingSearchCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Building", "B1", "B2", "B3", "B4" }));
        buildingSearchCB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                buildingSearchCBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                buildingSearchCBFocusLost(evt);
            }
        });

        idSearchBtn.setText("Search");
        idSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idSearchBtnActionPerformed(evt);
            }
        });

        buildingSearchBtn.setText("Search");
        buildingSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildingSearchBtnActionPerformed(evt);
            }
        });

        dctrSearchCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Doctor", "Nephrologists", "Urologist", "Psychiatrists", "General surgeons", "Skin" }));
        dctrSearchCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dctrSearchCBActionPerformed(evt);
            }
        });

        roomLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        roomLabel.setText("Room No:");

        roomNoCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "201", "202", "203", "204", "205", "206", "207", "208", "209", "210", "211" }));

        dctrOfCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Doctor", "Nephrologists", "Urologist", "Psychiatrists", "General surgeons", "Skin" }));
        dctrOfCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dctrOfCBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(142, 142, 142))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 36, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(adminNameTf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(adminAgeTf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(genderCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addBtn)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(updateBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(dctrBtn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(staffBtn))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel7))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(doctorOFlabel)
                                                    .addGap(18, 18, 18))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(buildingLabel)
                                                    .addGap(31, 31, 31))))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(adminAddressTf, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                            .addComponent(adminPhnNoTf, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                            .addComponent(buildingCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(dctrOfCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(91, 91, 91)
                                        .addComponent(dltBtn)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passlabel)
                                    .addComponent(roomLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passTf)
                                    .addComponent(roomNoCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(idSearchTf, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idSearchBtn)
                                .addGap(64, 64, 64)
                                .addComponent(buildingSearchCB, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buildingSearchBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nameSearchTf, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameSearchBtn))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(dctrSearchCB, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(149, 149, 149)
                                .addComponent(tableNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(shortByCB, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tableNameLabel)
                        .addComponent(shortByCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dctrBtn)
                        .addComponent(staffBtn)
                        .addComponent(dctrSearchCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(adminNameTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(adminAgeTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(genderCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(adminAddressTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(buildingLabel)
                                    .addComponent(buildingCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(adminPhnNoTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(doctorOFlabel)
                            .addComponent(dctrOfCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(roomLabel)
                            .addComponent(roomNoCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passlabel)
                            .addComponent(passTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addBtn)
                            .addComponent(dltBtn)
                            .addComponent(updateBtn)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameSearchBtn)
                    .addComponent(nameSearchTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idSearchTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buildingSearchCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idSearchBtn)
                    .addComponent(buildingSearchBtn))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void staffBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffBtnActionPerformed
        dctrOfCB.setVisible(false);
        doctorOFlabel.setVisible(false);
        tableNameLabel.setText("Staffs");
        dctrSearchCB.setVisible(false);
        passTf.setVisible(true);
        passlabel.setVisible(true);
        setText("", "", "", 0, "", "", "");
        addBtn.setVisible(true);
        dltBtn.setVisible(false);
        updateBtn.setVisible(false);
        roomLabel.setVisible(false);
        roomNoCB.setVisible(false);
        getStaffData();
    }//GEN-LAST:event_staffBtnActionPerformed

    private void dctrBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dctrBtnActionPerformed
        
        dctrOfCB.setVisible(true);
        doctorOFlabel.setVisible(true);
        tableNameLabel.setText("Doctors");
        addBtn.setVisible(true);
        dltBtn.setVisible(false);
        dctrSearchCB.setVisible(true);
        updateBtn.setVisible(false);
        passTf.setVisible(true);
        passlabel.setVisible(true);
        roomLabel.setVisible(true);
        roomNoCB.setVisible(true);
        dctrOfCB.setSelectedIndex(0);
        setText("", "", "", 0, "", "", "");
        getDoctorData();
    }//GEN-LAST:event_dctrBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed

        if (tableNameLabel.getText().equals("Doctors"))
            addData(adminNameTf.getText(), buildingCB.getSelectedItem().toString(), genderCB.getSelectedItem().toString(), Integer.parseInt(adminAgeTf.getText()), 
                    adminAddressTf.getText(), adminPhnNoTf.getText(),passTf.getPassword().toString(),dctrOfCB.getSelectedItem().toString(),roomNoCB.getSelectedItem().toString());
        else
            addData(adminNameTf.getText(), buildingCB.getSelectedItem().toString(), genderCB.getSelectedItem().toString(), 
                    Integer.parseInt(adminAgeTf.getText()), adminAddressTf.getText(),adminPhnNoTf.getText(), "",passTf.getPassword().toString(), "");
    }//GEN-LAST:event_addBtnActionPerformed

    public void setText(String name, String address, String phn_no, int age, String gender, String building, String doctor_of) {
        adminNameTf.setText(name);
        adminAddressTf.setText(address);
        adminPhnNoTf.setText("0" + phn_no);
        if(age!=0) adminAgeTf.setText(String.valueOf(age));
        else adminAgeTf.setText("");
        genderCB.setSelectedItem(gender);
        buildingCB.setSelectedItem(building);
        if (tableNameLabel.getText().equals("Doctors")) {
            dctrOfCB.setSelectedItem(doctor_of);
        }
    }

    private void dctrstfTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dctrstfTableMouseClicked

        dltBtn.setVisible(true);
        updateBtn.setVisible(true);
        addBtn.setVisible(false);
        passTf.setVisible(false);
        passlabel.setVisible(false);
        DefaultTableModel table = (DefaultTableModel) dctrstfTable.getModel();
        int index = dctrstfTable.getSelectedRow();
        String id = table.getValueAt(index, 0).toString();
        con = getConnection();

        try {
            if (tableNameLabel.getText().equals("Doctors")) {
                select = con.prepareStatement("select * from doctor where doctor_id = ?");
            } else {
                select = con.prepareStatement("select * from staff where staff_id = ?");
            }
            select.setInt(1, Integer.parseInt(id));

            ResultSet result = select.executeQuery();
            String doctor_of = null;
            while (result.next()) {
                String name = result.getString("name");
                int age = result.getInt("age");
                String gender = result.getString("gender");
                String address = result.getString("address");
                String building = result.getString("building");
                if (tableNameLabel.getText().equals("Doctors")) {
                    doctor_of = result.getString("doctor_of");
                }
                String phn_no = result.getString("phn_no");
                setText(name, address, phn_no, age, gender, building, doctor_of);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_dctrstfTableMouseClicked

    public void deleteData(String id)
    {
        con = getConnection();
        try {
            if (tableNameLabel.getText().equals("Doctors")) {
                delete = con.prepareStatement("delete from doctor where doctor_id = ?");
            } else {
                delete = con.prepareStatement("delete from staff where staff_id = ?");
            }
            delete.setInt(1, Integer.parseInt(id));
            delete.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Delete Successfully", "Success", 1);

        } catch (SQLException ex) {
            Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void dltBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dltBtnActionPerformed
        DefaultTableModel table = (DefaultTableModel) dctrstfTable.getModel();
        int index = dctrstfTable.getSelectedRow();
        String id = table.getValueAt(index, 0).toString();
        deleteData(id);
        if(tableNameLabel.getText().equals("Doctors")) getDoctorData();
        else getStaffData();
        
    }//GEN-LAST:event_dltBtnActionPerformed

    public void search(String name,String building,int id,String doctor)
    {
        System.out.println(doctor);
        con = getConnection();
        int wh = 1;
        try {
            if (tableNameLabel.getText().equals("Doctors")) {
                if(id!=-1) {
                    search  = con.prepareStatement("select * from doctor where doctor_id = ?");
                    search.setInt(1, id);
                }
                else if(!name.equals("")) {
                    search  = con.prepareStatement("select * from doctor where name like ?");
                    search.setString(1,"%" + name + "%");
                }
                else if(!building.equals("Select Building")){
                    search  = con.prepareStatement("select * from doctor where building = ?");
                    search.setString(1, building);
                }
                else
                {
                    search  = con.prepareStatement("select * from doctor where doctor_of like ?");
                    search.setString(1, "%" + doctor + "%");
                }
            } else {
                wh = 2;
                if(id!=-1) {
                    search  = con.prepareStatement("select * from staff where staff_id = ?");
                    search.setInt(1, id);
                }
                else if(!name.equals("")) {
                    search  = con.prepareStatement("select * from staff where name = ?");
                    search.setString(1,  name);
                }
                else if(!building.equals("Select Building")){
                    search  = con.prepareStatement("select * from staff where building = ?");
                    search.setString(1, building);
                }
            }
            
            ResultSet result = search.executeQuery();
            ResultSetMetaData rss = result.getMetaData();
            int c = rss.getColumnCount();
            updateTable(result, c, wh);
            

        } catch (SQLException ex) {
            Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void nameSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameSearchBtnActionPerformed
        buildingSearchCB.setSelectedIndex(0);
        idSearchTf.setText("Enter ID");
        if(!nameSearchTf.getText().isEmpty())
        {
            search(nameSearchTf.getText(), "Select Building", -1,"");
        }
    }//GEN-LAST:event_nameSearchBtnActionPerformed

    private void idSearchTfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idSearchTfFocusGained
        buildingSearchCB.setSelectedIndex(0);
        nameSearchTf.setText("Enter Name");
        idSearchTf.setText("");
    }//GEN-LAST:event_idSearchTfFocusGained

    private void idSearchTfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idSearchTfFocusLost
        if(!idSearchBtn.getModel().isPressed()) nameSearchTf.setText("Enter ID");
    }//GEN-LAST:event_idSearchTfFocusLost

    private void nameSearchTfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameSearchTfFocusGained
        buildingSearchCB.setSelectedIndex(0);
        idSearchTf.setText("Enter ID");
        nameSearchTf.setText("");
    }//GEN-LAST:event_nameSearchTfFocusGained

    private void nameSearchTfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameSearchTfFocusLost
           if(!nameSearchBtn.getModel().isPressed()) nameSearchTf.setText("Enter Name");
    }//GEN-LAST:event_nameSearchTfFocusLost

    private void idSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idSearchBtnActionPerformed
        buildingSearchCB.setSelectedIndex(0);
        nameSearchTf.setText("Enter Name");
        if(!idSearchTf.getText().isEmpty())
        {
            search("", "Select Building", Integer.parseInt(idSearchTf.getText()),"");
        }
    }//GEN-LAST:event_idSearchBtnActionPerformed

    private void buildingSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buildingSearchBtnActionPerformed
        idSearchTf.setText("Enter ID");
        nameSearchTf.setText("Enter Name");
        if(buildingSearchCB.getSelectedIndex() != 0)
        {
            search("", buildingSearchCB.getSelectedItem().toString(), -1,"");
        }
    }//GEN-LAST:event_buildingSearchBtnActionPerformed

    private void buildingSearchCBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_buildingSearchCBFocusLost
        if(!buildingSearchBtn.getModel().isPressed()) buildingCB.setSelectedIndex(0);
    }//GEN-LAST:event_buildingSearchCBFocusLost

    private void buildingSearchCBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_buildingSearchCBFocusGained
        idSearchTf.setText("Enter ID");
        nameSearchTf.setText("Enter Name");
    }//GEN-LAST:event_buildingSearchCBFocusGained

    public void shortBy(int index)
    {
        con = getConnection();
        int wh = 1;
        try {
            if (tableNameLabel.getText().equals("Doctors")) {
                if(index == 1) shortBy = con.prepareStatement("select * from doctor group by name");
                else shortBy = con.prepareStatement("select * from doctor order by building asc");
            } else {
                wh = 2;
                if(index == 1) shortBy = con.prepareStatement("select * from staff group by name");
                else shortBy = con.prepareStatement("select * from staff order by building asc");
            }
            ResultSet result = shortBy.executeQuery();
            ResultSetMetaData rss = result.getMetaData();
            int c = rss.getColumnCount();
            updateTable(result, c, wh);

        } catch (SQLException ex) {
            Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void shortByCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortByCBActionPerformed
        if(shortByCB.getSelectedIndex() == 1) shortBy(shortByCB.getSelectedIndex());
        if(shortByCB.getSelectedIndex() == 2) shortBy(shortByCB.getSelectedIndex());
    }//GEN-LAST:event_shortByCBActionPerformed

    private void dctrSearchCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dctrSearchCBActionPerformed
        search("", "Select Building", -1,dctrSearchCB.getSelectedItem().toString());
    }//GEN-LAST:event_dctrSearchCBActionPerformed

    public void updateData(String name, String building, String gender, int age, String address, String Phn_no, String doctor_of,int id)
    {
        try {

            con = getConnection();
            if (!doctor_of.equals("")) {
                update = con.prepareStatement("update doctor set name=?,age=?,gender=?,address=?,building=?,phn_no=?,doctor_of=? where doctor_id = ?");
                update.setString(7, doctor_of);
                update.setInt(8, id);
            } else {
                update = con.prepareStatement("update staff set name=?,age=?,gender=?,address=?,building=?,phn_no=? where staff_id = ?");
                update.setInt(7, id);
            }

            update.setString(1, name);
            update.setInt(2, age);
            update.setString(3, gender);
            update.setString(4, address);
            update.setString(5, building);
            update.setString(6, Phn_no);
            update.executeUpdate();

            JOptionPane.showMessageDialog(null, "Record Updated Successfully", "Success", 1);
            adminNameTf.setText("");
            adminAgeTf.setText("");
            genderCB.setSelectedIndex(0);
            adminAddressTf.setText("");
            dctrOfCB.setSelectedIndex(0);
            adminPhnNoTf.setText("");
            buildingCB.setSelectedIndex(0);

            if (tableNameLabel.getText().equals("Doctors")) {
                getDoctorData();
            } else {
                getStaffData();
            }

        } catch (SQLException ex) {
            Logger.getLogger(HospitalManagementDBMSProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        
        DefaultTableModel table = (DefaultTableModel) dctrstfTable.getModel();
        int index = dctrstfTable.getSelectedRow();
        String id = table.getValueAt(index, 0).toString(); 
        
        if (tableNameLabel.getText().equals("Doctors"))
        {
            updateData(adminNameTf.getText(), buildingCB.getSelectedItem().toString(), genderCB.getSelectedItem().toString(), 
                    Integer.parseInt(adminAgeTf.getText()), adminAddressTf.getText(), adminPhnNoTf.getText(), dctrOfCB.getSelectedItem().toString(),Integer.parseInt(id));
            getDoctorData();
        }
        else
        {
            updateData(adminNameTf.getText(), buildingCB.getSelectedItem().toString(), genderCB.getSelectedItem().toString(), Integer.parseInt(adminAgeTf.getText()), adminAddressTf.getText(), adminPhnNoTf.getText(), "",Integer.parseInt(id));
            getStaffData();
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void dctrOfCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dctrOfCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dctrOfCBActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JTextField adminAddressTf;
    private javax.swing.JTextField adminAgeTf;
    private javax.swing.JTextField adminNameTf;
    private javax.swing.JTextField adminPhnNoTf;
    private javax.swing.JComboBox<String> buildingCB;
    private javax.swing.JLabel buildingLabel;
    private javax.swing.JButton buildingSearchBtn;
    private javax.swing.JComboBox<String> buildingSearchCB;
    private javax.swing.JButton dctrBtn;
    private javax.swing.JComboBox<String> dctrOfCB;
    private javax.swing.JComboBox<String> dctrSearchCB;
    private javax.swing.JTable dctrstfTable;
    private javax.swing.JButton dltBtn;
    private javax.swing.JLabel doctorOFlabel;
    private javax.swing.JComboBox<String> genderCB;
    private javax.swing.JButton idSearchBtn;
    private javax.swing.JTextField idSearchTf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nameSearchBtn;
    private javax.swing.JTextField nameSearchTf;
    private javax.swing.JPasswordField passTf;
    private javax.swing.JLabel passlabel;
    private javax.swing.JLabel roomLabel;
    private javax.swing.JComboBox<String> roomNoCB;
    private javax.swing.JComboBox<String> shortByCB;
    private javax.swing.JButton staffBtn;
    private javax.swing.JLabel tableNameLabel;
    private javax.swing.JButton updateBtn;
    private javax.swing.JLabel userlabel;
    // End of variables declaration//GEN-END:variables

}
