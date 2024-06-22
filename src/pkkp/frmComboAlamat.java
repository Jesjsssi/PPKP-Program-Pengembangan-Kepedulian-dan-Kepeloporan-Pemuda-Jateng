/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkkp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 *
 * @author Dimas Ardhana
 */
public class frmComboAlamat extends javax.swing.JFrame {

    private Connection con;

    /**
     * Creates new form frmComboAlamat
     */
    public frmComboAlamat() {
        initComponents();
        open_db();
        retrieveProvinsi();
    }

    private void open_db() {
        try {
            KoneksiMysql kon = new KoneksiMysql("localhost", "root", "", "dbpkkp");
            con = kon.getConnection();
            System.out.println("Koneksi DB : Succesfully");
        } catch (Exception e) {
            System.out.println("Koneksi DB : Failed " + e);
        }
    }

    public void retrieveProvinsi() {
        ResultSet res;
        Statement stat;
        try {
            stat = con.createStatement();
            res = stat.executeQuery("SELECT * FROM provinces");
            while (res.next()) {
                jComboBoxProvinsi.addItem(res.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("gagal load provinsi " + e);
        }
    }

    int getIdProvinsi() {
        ResultSet res;
        Statement stat;
        PreparedStatement ps = null;
        String namaProvinsi = (jComboBoxProvinsi.getSelectedItem().toString());
        int id = 0;
        try {
            ps = con.prepareStatement("SELECT * FROM provinces WHERE name LIKE ?");
            ps.setString(1, "%" + namaProvinsi + "%");
            res = ps.executeQuery();

            while (res.next()) {
                id = res.getInt("id");
            }
            System.out.println("behasil load id provinsi");
        } catch (SQLException e) {
            System.out.println("gagal load id provinsi " + e);
        }
        return id;
    }

    public void retrieveKabKota(int idProvinsi) {
        ResultSet res;
        PreparedStatement ps;
        jComboBoxKabKota.removeAllItems();
        try {
            ps = con.prepareStatement("SELECT name FROM regencies WHERE province_id=?");
            ps.setInt(1, idProvinsi);
            res = ps.executeQuery();
            while (res.next()) {
                jComboBoxKabKota.addItem(res.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("gagal load data kab/kota " + e);
        }
    }

    int getIdKabKota() {
        ResultSet res;
        PreparedStatement ps = null;
        int id = 0;
        System.out.println("sampe sini");
        try {
            String namaKabKota = (jComboBoxKabKota.getSelectedItem().toString());
            ps = con.prepareStatement("SELECT * FROM regencies WHERE name LIKE ?");
            ps.setString(1, "%" + namaKabKota + "%");
            res = ps.executeQuery();

            while (res.next()) {
                id = res.getInt("id");
            }
            System.out.println("behasil load id kab/kota");
        } catch (SQLException | NullPointerException e) {
            System.out.println("gagal load id kab/kota " + e);
        }
        return id;
    }

    public void retrieveKecamatan(int idKabKota) {
        ResultSet res;
        Statement stat;
        PreparedStatement ps;
        jComboBoxKec.removeAllItems();
        try {
            ps = con.prepareStatement("SELECT name FROM districts WHERE regency_id=?");
            ps.setInt(1, idKabKota);
            res = ps.executeQuery();
            while (res.next()) {
                jComboBoxKec.addItem(res.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("gagal load data kecamatan " + e);
        }
    }
    
    int getIdKecamatan() {
        ResultSet res;
        PreparedStatement ps = null;
        int id = 0;
        System.out.println("sampe sini");
        try {
            String namaKecamatan = (jComboBoxKec.getSelectedItem().toString());
            ps = con.prepareStatement("SELECT * FROM districts WHERE name LIKE ?");
            ps.setString(1, "%" + namaKecamatan + "%");
            res = ps.executeQuery();

            while (res.next()) {
                id = res.getInt("id");
            }
            System.out.println("behasil load id kecamatan");
        } catch (SQLException | NullPointerException e) {
            System.out.println("gagal load id kecamatan " + e);
        }
        return id;
    }
    
    public void retrieveKelurahan(int idKecamatan) {
        ResultSet res;
        Statement stat;
        PreparedStatement ps;
        jComboBoxKel.removeAllItems();
        try {
            ps = con.prepareStatement("SELECT name FROM villages WHERE district_id=?");
            ps.setInt(1, idKecamatan);
            res = ps.executeQuery();
            while (res.next()) {
                jComboBoxKel.addItem(res.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("gagal load data kelurahan " + e);
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

        jComboBoxProvinsi = new javax.swing.JComboBox<>();
        jComboBoxKabKota = new javax.swing.JComboBox<>();
        jComboBoxKec = new javax.swing.JComboBox<>();
        jComboBoxKel = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBoxProvinsi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"-- Pilih --"}));
        jComboBoxProvinsi.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jComboBoxProvinsiCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jComboBoxProvinsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxProvinsiActionPerformed(evt);
            }
        });

        jComboBoxKabKota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));
        jComboBoxKabKota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxKabKotaActionPerformed(evt);
            }
        });

        jComboBoxKec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));
        jComboBoxKec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxKecActionPerformed(evt);
            }
        });

        jComboBoxKel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));

        jLabel1.setText("PROVINSI");

        jLabel2.setText("KAB/KOTA");

        jLabel3.setText("KECAMATAN");

        jLabel4.setText("KELURAHAN");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxProvinsi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxKabKota, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxKec, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxKel, 0, 217, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxProvinsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxKabKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxKec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxKel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBoxProvinsiCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jComboBoxProvinsiCaretPositionChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBoxProvinsiCaretPositionChanged

    private void jComboBoxProvinsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxProvinsiActionPerformed
        // TODO add your handling code here:
        retrieveKabKota(getIdProvinsi());
    }//GEN-LAST:event_jComboBoxProvinsiActionPerformed

    private void jComboBoxKabKotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxKabKotaActionPerformed
        // TODO add your handling code here:
        retrieveKecamatan(getIdKabKota());
    }//GEN-LAST:event_jComboBoxKabKotaActionPerformed

    private void jComboBoxKecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxKecActionPerformed
        // TODO add your handling code here:
        retrieveKelurahan(getIdKecamatan());
    }//GEN-LAST:event_jComboBoxKecActionPerformed

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
            java.util.logging.Logger.getLogger(frmComboAlamat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmComboAlamat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmComboAlamat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmComboAlamat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmComboAlamat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxKabKota;
    private javax.swing.JComboBox<String> jComboBoxKec;
    private javax.swing.JComboBox<String> jComboBoxKel;
    private javax.swing.JComboBox<String> jComboBoxProvinsi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
