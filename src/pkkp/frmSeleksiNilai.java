/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pkkp;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dimas Ardhana
 */
public class frmSeleksiNilai extends javax.swing.JFrame {

    private Connection con;
    private Statement stat;
    private ResultSet res;
    public String id;
    public String nama;
    final String queryInsert = "INSERT INTO lolos_nilai(id_peserta, nama_peserta, nilai_tes_tulis, nilai_tes_wawancara, total_nilai, hasil_seleksi_nilai) VALUES(?,?,?,?,?,?)";
    final String querySelect = "SELECT * FROM lolos_nilai";
    final String queryDelete = "DELETE FROM lolos_nilai where id_peserta=?";
//
//    private void setId() {
//        txtId_Peserta.setText("joko");
//    }
//
//    private void setNama() {
//        txtNm_Peserta.setText("wawan");
//    }

    /**
     * Creates new form frmSeleksiNilai
     */
    public frmSeleksiNilai() {
        initComponents();
        txtTot.setEditable(false);
        open_db();
        selectDB();
        getContentPane().setBackground(Color.getHSBColor(255, 185, 15));
    }

    //method buka database
    private void open_db() {
        try {
            KoneksiMysql kon = new KoneksiMysql("localhost", "root", "", "dbpkkp");
            con = kon.getConnection();
            System.out.println("Koneksi DB : Succesfully");
        } catch (Exception e) {
            System.out.println("Koneksi DB : Failed " + e);
        }
    }

    public void itemTerpilih() {
        frmSupportSeleksiNilai fDB = new frmSupportSeleksiNilai();
        fDB.fAB = this;

        txtId_Peserta.setText(id);
        txtNm_Peserta.setText(nama);
    }

    //method insert database
    public void insertDB() {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(queryInsert);

            statement.setString(1, txtId_Peserta.getText());
            statement.setString(2, txtNm_Peserta.getText());
            statement.setString(3, txtTulis.getText());
            statement.setString(4, txtWawancara.getText());
            statement.setString(5, txtTot.getText());
            float nilaiFix = Float.parseFloat(txtTot.getText());
            String hasil = "";
            if (nilaiFix >= 85) {
                hasil = "LOLOS";
            } else {
                hasil = "TIDAK LOLOS";
            }
            statement.setString(6, hasil);

            statement.executeUpdate();
            System.out.println("Berhasil Input DB");
        } catch (SQLException e) {
            System.out.println("Gagal Insert DB " + e);
        }
        selectDB();
    }

    //method select database
    public void selectDB() {
        DefaultTableModel dtb = new DefaultTableModel();
        dtb.addColumn("Kd Peserta");
        dtb.addColumn("Nama Peserta");
        dtb.addColumn("Tes Tulis");
        dtb.addColumn("Tes Wawancara");
        dtb.addColumn("Total Nilai");
        dtb.addColumn("Hasil");
        tblSeleksiNilai.setModel(dtb);
        System.out.println("sebelum try");
        try {
            stat = con.createStatement();
            res = stat.executeQuery(querySelect);
            while (res.next()) {
                dtb.addRow(new Object[]{
                    res.getString("id_peserta"),
                    res.getString("nama_peserta"),
                    res.getString("nilai_tes_tulis"),
                    res.getString("nilai_tes_wawancara"),
                    res.getString("total_nilai"),
                    res.getString("hasil_seleksi_nilai")
                });
            }
            System.out.println("Sukses Load Table");
        } catch (SQLException e) {
            System.out.println("Gagal Load Table " + e);
        }
    }

    public void deleteDB() {
        PreparedStatement statement = null;
        int pilih = JOptionPane.showConfirmDialog(null, "Confirm Delete?");
        if (pilih == 0) {
            try {
                statement = con.prepareStatement(queryDelete);
                statement.setString(1, getIdDelete());
                statement.executeUpdate();

            } catch (Exception e) {
                System.out.println("Gagal hapus " + e);
            }
        }
        selectDB();
    }

    private String getIdDelete() {
        int row = tblSeleksiNilai.getSelectedRow();
        int column = 0;
        String finalValue = (String) tblSeleksiNilai.getValueAt(row, column);
        System.out.println(finalValue);
        return finalValue;
    }

    private void clearForm() {
        txtId_Peserta.setText("");
        txtNm_Peserta.setText("");
        txtTulis.setText("");
        txtWawancara.setText("");
        txtTot.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cmdPilih = new javax.swing.JButton();
        txtId_Peserta = new javax.swing.JTextField();
        txtNm_Peserta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTulis = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtWawancara = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTot = new javax.swing.JTextField();
        cmdPilih1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSeleksiNilai = new javax.swing.JTable();
        cmdSimpan = new javax.swing.JButton();
        cmdBatal = new javax.swing.JButton();
        cmdKeluar = new javax.swing.JButton();
        cmdHapus = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Seleksi Nilai");

        cmdPilih.setText("Pilih");
        cmdPilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPilihActionPerformed(evt);
            }
        });

        jLabel2.setText("Nilai Tes Tulis");

        jLabel3.setText("Nilai Tes Wawancara");

        jLabel4.setText("Total Nilai");

        cmdPilih1.setText("Hitung");
        cmdPilih1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPilih1ActionPerformed(evt);
            }
        });

        tblSeleksiNilai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Kode Peserta", "Nama Peserta", "Nilai Tes Tulis", "Nilai Tes Wawancara", "Total Nilai", "Hasil Seleksi Nilai"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
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
        jScrollPane2.setViewportView(tblSeleksiNilai);

        cmdSimpan.setText("Simpan");
        cmdSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSimpanActionPerformed(evt);
            }
        });

        cmdBatal.setText("Clear");
        cmdBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBatalActionPerformed(evt);
            }
        });

        cmdKeluar.setText("Keluar");
        cmdKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdKeluarActionPerformed(evt);
            }
        });

        cmdHapus.setText("Hapus");
        cmdHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdHapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdPilih, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtId_Peserta, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNm_Peserta, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTulis, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtWawancara, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmdPilih1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTot, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmdSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmdHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdPilih)
                    .addComponent(txtId_Peserta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNm_Peserta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdSimpan)
                    .addComponent(cmdHapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtWawancara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdBatal)
                    .addComponent(cmdKeluar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdPilih1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdPilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPilihActionPerformed
        // TODO add your handling code here:
        frmSupportSeleksiNilai fDB = new frmSupportSeleksiNilai();
        fDB.fAB = this;
        fDB.setVisible(true);
        fDB.setResizable(false);
//        isianForm();
    }//GEN-LAST:event_cmdPilihActionPerformed

    private void cmdPilih1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPilih1ActionPerformed
        // TODO add your handling code here:
        int nilaiTulis = Integer.parseInt(txtTulis.getText());
        int nilaiWawancara = Integer.parseInt(txtWawancara.getText());
        float result = (nilaiTulis + nilaiWawancara) / 2;
        txtTot.setText(String.valueOf(result));
    }//GEN-LAST:event_cmdPilih1ActionPerformed

    private void cmdSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSimpanActionPerformed
        // TODO add your handling code here:

        insertDB();
        clearForm();
    }//GEN-LAST:event_cmdSimpanActionPerformed

    private void cmdHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHapusActionPerformed
        // TODO add your handling code here:
        deleteDB();
    }//GEN-LAST:event_cmdHapusActionPerformed

    private void cmdBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBatalActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_cmdBatalActionPerformed

    private void cmdKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdKeluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_cmdKeluarActionPerformed

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
            java.util.logging.Logger.getLogger(frmSeleksiNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSeleksiNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSeleksiNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSeleksiNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSeleksiNilai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdBatal;
    private javax.swing.JButton cmdHapus;
    private javax.swing.JButton cmdKeluar;
    private javax.swing.JButton cmdPilih;
    private javax.swing.JButton cmdPilih1;
    private javax.swing.JButton cmdSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblSeleksiNilai;
    private javax.swing.JTextField txtId_Peserta;
    private javax.swing.JTextField txtNm_Peserta;
    private javax.swing.JTextField txtTot;
    private javax.swing.JTextField txtTulis;
    private javax.swing.JTextField txtWawancara;
    // End of variables declaration//GEN-END:variables
}
