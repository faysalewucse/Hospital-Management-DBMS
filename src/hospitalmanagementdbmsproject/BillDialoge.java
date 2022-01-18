/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementdbmsproject;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Faysal Ahmad
 */
public class BillDialoge extends javax.swing.JFrame {

    String patient_id;
    String name;
    String building;
    String gender;
    int age;
    String address;
    String Phn_no;
    int doctor_id;
    String disease;
    int room_no;
    String dctr_fee = "0", room_fee = "0", total = "0", due = "0", paid = "0";

    Connection con;
    PreparedStatement select, insert;

    public void addData() {
        new StaffPage().addData(name, building, gender, age, address, Phn_no, doctor_id, disease, room_no);
        addBill();
        this.setVisible(false);
        new StaffPage().getPatientData();
    }

    public BillDialoge() {
        initComponents();
    }

    public BillDialoge(String patient_id) throws HeadlessException {

        this.patient_id = patient_id;
        initComponents();
        addPatientBtn.setText("Update");
        dctrChargeTf.setVisible(false);
        room_chargeLbl.setVisible(false);
        dctrChargeLbl.setVisible(false);
        room_chargeTf.setVisible(false);

        con = new StaffPage().getConnection();

        try {

            select = con.prepareStatement("select * from bill where patient_id = ?");
            select.setInt(1, Integer.parseInt(patient_id));

            ResultSet result = select.executeQuery();

            while (result.next()) {
                total = result.getString("total_amount");
                paid = result.getString("paid");
                due = result.getString("due");

                totalText.setText(total);
                dctrChargePaidTf.setText(paid);
                dueText.setText(due);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BillDialoge(String name, String building, String gender, int age, String address, String Phn_no, int doctor_id, String disease, int room_no) throws HeadlessException {
        this.name = name;
        this.building = building;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.Phn_no = Phn_no;
        this.doctor_id = doctor_id;
        this.disease = disease;
        this.room_no = room_no;

        initComponents();
        setFee();
    }

    public void addBill() {
        try {

            con = new StaffPage().getConnection();

            insert = con.prepareStatement("insert into bill(doctor_charge,room_charge,total_amount,paid,due) values(?,?,?,?,?)");

            insert.setInt(1, Integer.parseInt(dctr_fee));
            insert.setInt(2, Integer.parseInt(room_fee));
            insert.setInt(3, Integer.parseInt(totalText.getText()));
            insert.setInt(4, Integer.parseInt(dctrChargePaidTf.getText()));
            insert.setInt(5, Integer.parseInt(totalText.getText()) - Integer.parseInt(dctrChargePaidTf.getText()));

            insert.executeUpdate();
            this.setVisible(false);

        } catch (SQLException ex) {
            Logger.getLogger(HospitalManagementDBMSProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateBill() {
        try {

            con = new StaffPage().getConnection();

            insert = con.prepareStatement("update bill set due = ?,paid =? where patient_id = ?");
            String paidbefore = paid;
            insert.setInt(3, Integer.parseInt(patient_id));
            insert.setInt(2, Integer.parseInt(paidbefore) + Integer.parseInt(dctrChargePaidTf.getText()));
            insert.setInt(1, Integer.parseInt(dueText.getText()) - Integer.parseInt(dctrChargePaidTf.getText()));

            insert.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Updated Successfully", "Success", 1);
            this.setVisible(false);

        } catch (SQLException ex) {
            Logger.getLogger(HospitalManagementDBMSProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setFee() {
        con = new StaffPage().getConnection();

        try {

            select = con.prepareStatement("select * from doctor_charge where doctor_id = ?");
            select.setInt(1, doctor_id);

            ResultSet result = select.executeQuery();

            while (result.next()) {
                dctr_fee = result.getString("fee");
                dctrChargeTf.setText(dctr_fee);
            }

            select = con.prepareStatement("select * from room where room_no = ?");
            select.setInt(1, room_no);

            ResultSet result2 = select.executeQuery();

            while (result2.next()) {
                room_fee = result2.getString("charge");
                room_chargeTf.setText(room_fee);
            }

            total = String.valueOf(Integer.parseInt(dctr_fee) + Integer.parseInt(room_fee));

            totalText.setText(total);

        } catch (SQLException ex) {
            Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        dctrChargeLbl = new javax.swing.JLabel();
        dctrChargePaidTf = new javax.swing.JTextField();
        room_chargeTf = new javax.swing.JTextField();
        room_chargeLbl = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        totalText = new javax.swing.JLabel();
        dctrChargeTf = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        dueText = new javax.swing.JLabel();
        addPatientBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Patinet Bill");

        dctrChargeLbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dctrChargeLbl.setText("Doctor Charge:");

        room_chargeLbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        room_chargeLbl.setText("Room Charge: ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Total:");

        totalText.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Paid:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Due:");

        dueText.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        addPatientBtn.setBackground(new java.awt.Color(0, 204, 0));
        addPatientBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addPatientBtn.setText("Add Patient");
        addPatientBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPatientBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(room_chargeLbl)
                        .addComponent(jLabel10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)))
                    .addComponent(dctrChargeLbl, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dctrChargeTf, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(room_chargeTf)
                    .addComponent(dctrChargePaidTf)
                    .addComponent(dueText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jLabel5)
                .addGap(0, 106, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addPatientBtn)
                .addGap(111, 111, 111))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dctrChargeLbl)
                    .addComponent(dctrChargeTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(room_chargeLbl)
                    .addComponent(room_chargeTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(dctrChargePaidTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dueText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(addPatientBtn)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addPatientBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPatientBtnActionPerformed
        if (!addPatientBtn.getText().equals("Update")) {
            addData();
        } else {
            updateBill();
        }
    }//GEN-LAST:event_addPatientBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BillDialoge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BillDialoge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BillDialoge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BillDialoge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BillDialoge().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPatientBtn;
    private javax.swing.JLabel dctrChargeLbl;
    private javax.swing.JTextField dctrChargePaidTf;
    private javax.swing.JTextField dctrChargeTf;
    private javax.swing.JLabel dueText;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel room_chargeLbl;
    private javax.swing.JTextField room_chargeTf;
    private javax.swing.JLabel totalText;
    // End of variables declaration//GEN-END:variables
}
