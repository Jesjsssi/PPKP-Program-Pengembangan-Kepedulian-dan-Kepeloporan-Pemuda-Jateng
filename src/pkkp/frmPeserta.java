/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pkkp;

import java.awt.Color;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dimas Ardhana
 */
public class frmPeserta extends javax.swing.JFrame {

    /**
     * @return the idPeserta
     */
    public String getIdPeserta() {
        return txtIdPeserta.getText();
    }

    /**
     * @param idPeserta the idPeserta to set
     */
    public void setIdPeserta(String idPeserta) {
        this.txtIdPeserta.setText(idPeserta);
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return txtNm_Peserta.getText();
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.txtNm_Peserta.setText(nama);
    }

    /**
     * @return the kelamin
     */
    public String getKelamin() {
        return cmbJns_Kel.getSelectedItem().toString();
    }

    /**
     * @param kelamin the kelamin to set
     */
    public void setKelamin(String kelamin) {
        this.cmbJns_Kel.setSelectedItem(kelamin);
    }

    /**
     * @return the usia
     */
    public String getUsia() {
        return txtUsia.getText();
    }

    /**
     * @param usia the usia to set
     */
    public void setUsia(String usia) {
        this.txtUsia.setText(usia);
    }

    /**
     * @return the alamat
     */
    public String getAlamat() {
        return txtAlamat.getText();
    }

    /**
     * @param alamat the alamat to set
     */
    public void setAlamat(String alamat) {
        this.txtAlamat.setText(alamat);
    }

    /**
     * @return the provinsi
     */
    public String getProvinsi() {
        return jComboBoxProvinsi.getSelectedItem().toString();
    }

    /**
     * @param provinsi the provinsi to set
     */
    public void setProvinsi(String provinsi) {
        this.jComboBoxProvinsi.setSelectedItem(provinsi);
    }

    /**
     * @return the kelurahan
     */
    public String getKelurahan() {
        return jComboBoxKel.getSelectedItem().toString();
    }

    /**
     * @param kelurahan the kelurahan to set
     */
    public void setKelurahan(String kelurahan) {
        this.jComboBoxKel.setSelectedItem(kelurahan);
    }

    /**
     * @return the kecamatan
     */
    public String getKecamatan() {
        return jComboBoxKec.getSelectedItem().toString();
    }

    /**
     * @param kecamatan the kecamatan to set
     */
    public void setKecamatan(String kecamatan) {
        this.jComboBoxKec.setSelectedItem(kecamatan);
    }

    /**
     * @return the kabkota
     */
    public String getKabkota() {
        return jComboBoxKabKota.getSelectedItem().toString();
    }

    /**
     * @param kabkota the kabkota to set
     */
    public void setKabkota(String kabkota) {
        this.jComboBoxKabKota.setSelectedItem(kabkota);
    }

    /**
     * @return the suratDokter
     */
    public String getSuratDokter() {
        return cmbSrt_Dok.getSelectedItem().toString();
    }

    /**
     * @param suratDokter the suratDokter to set
     */
    public void setSuratDokter(String suratDokter) {
        this.cmbSrt_Dok.setSelectedItem(suratDokter);
    }

    /**
     * @return the skck
     */
    public String getSkck() {
        return cmbSkck.getSelectedItem().toString();
    }

    /**
     * @param skck the skck to set
     */
    public void setSkck(String skck) {
        this.cmbSkck.setSelectedItem(skck);
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return cmbStatus.getSelectedItem().toString();
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.cmbStatus.setSelectedItem(status);
    }

    /**
     * @return the ipk
     */
    public String getIpk() {
        return txtIpk.getText();
    }

    /**
     * @param ipk the ipk to set
     */
    public void setIpk(String ipk) {
        this.txtIpk.setText(ipk);
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return txtEmail.getText();
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.txtEmail.setText(email);
    }

    /**
     * @return the telp
     */
    public String getTelp() {
        return txtNo_Wa.getText();
    }

    /**
     * @param telp the telp to set
     */
    public void setTelp(String telp) {
        this.txtNo_Wa.setText(telp);
    }

    private Connection con;
    private Statement stat;
    private ResultSet res;
    final String queryInsert = "INSERT INTO data_peserta (peserta_id, peserta_nama, peserta_kelamin, peserta_usia, peserta_alamat, peserta_provinsi, peserta_kabkota, peserta_kecamatan, peserta_kelurahan,  peserta_surat_dokter, peserta_skck, peserta_status, peserta_ipk, peserta_email, peserta_telp) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    final String querySelect = "SELECT * FROM data_peserta";
    final String queryUpdate = "update data_peserta set peserta_nama=?, peserta_kelamin=?, peserta_usia=?, peserta_alamat=?, peserta_provinsi=?, peserta_kabkota=?, peserta_kecamatan=?, peserta_kelurahan=?, peserta_surat_dokter=?, peserta_skck=?, peserta_status=?, peserta_ipk=?, peserta_email=?, peserta_telp=? where peserta_id=?;";
    final String queryDelete = "DELETE FROM data_peserta where peserta_id=?";
    final String queryInsertKab = "INSERT INTO kabupaten (kabupaten_nama) VALUES (?)";
    static boolean isUpdate = false;

    /**
     * Creates new form frmPeserta
     */
    public frmPeserta() {
        initComponents();
        setExtendedState(frmPeserta.MAXIMIZED_BOTH);
        open_db();
        selectDB();
        setButtonEdit(false);
        setTextField(false);
        retrieveProvinsi();
        getContentPane().setBackground(Color.getHSBColor(139, 58, 58));
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

    //method select database
    public void selectDB() {
        DefaultTableModel dtb = new DefaultTableModel();
        dtb.addColumn("id");
        dtb.addColumn("nama");
        dtb.addColumn("kelamin");
        dtb.addColumn("usia");
        dtb.addColumn("alamat");
        dtb.addColumn("provinsi");
        dtb.addColumn("kabkota");
        dtb.addColumn("kecamatan");
        dtb.addColumn("kelurahan");
        dtb.addColumn("surat_dokter");
        dtb.addColumn("skck");
        dtb.addColumn("status");
        dtb.addColumn("ipk");
        dtb.addColumn("email");
        dtb.addColumn("telp");
        tblPeserta.setModel(dtb);
        System.out.println("sebelum try");
        try {
            stat = con.createStatement();
            res = stat.executeQuery(querySelect);
            while (res.next()) {
                dtb.addRow(new Object[]{
                    res.getString("peserta_id"),
                    res.getString("peserta_nama"),
                    res.getString("peserta_kelamin"),
                    res.getInt("peserta_usia"),
                    res.getString("peserta_alamat"),
                    res.getString("peserta_provinsi"),
                    res.getString("peserta_kabkota"),
                    res.getString("peserta_kecamatan"),
                    res.getString("peserta_kelurahan"),
                    res.getString("peserta_surat_dokter"),
                    res.getString("peserta_skck"),
                    res.getString("peserta_status"),
                    res.getString("peserta_ipk"),
                    res.getString("peserta_email"),
                    res.getInt("peserta_telp")
                });
            }
            System.out.println("Sukses Load Table");
        } catch (SQLException e) {
            System.out.println("Gagal Load Table " + e);
        }
    }

    //method insert database
    public void insertDB() {
        PreparedStatement statement;
        try {
            statement = con.prepareStatement(queryInsert);
            statement.setString(1, getIdPeserta());
            statement.setString(2, getNama());
            statement.setString(3, getKelamin());
            statement.setInt(4, Integer.parseInt(getUsia()));
            statement.setString(5, getAlamat());
            statement.setString(6, getProvinsi());
            statement.setString(7, getKabkota());
            statement.setString(8, getKecamatan());
            statement.setString(9, getKelurahan());
            statement.setString(10, getSuratDokter());
            statement.setString(11, getSkck());
            statement.setString(12, getStatus());
            statement.setString(13, getIpk());
            statement.setString(14, getEmail());
            statement.setInt(15, Integer.parseInt(getTelp()));
            statement.executeUpdate();
            System.out.println("Berhasil Input DB");
        } catch (SQLException e) {
            System.out.println("Gagal Insert DB " + e);
        }
        insertDBKab();
    }

    public void insertDBKab() {
        PreparedStatement statement;
        try {
            statement = con.prepareStatement(queryInsertKab);
            statement.setString(1, getKabkota());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("gagal input kab " + e);
        }
    }

    public void updateDB() {
        try {
            PreparedStatement statement;
            statement = con.prepareStatement(queryUpdate);
            statement.setString(1, getNama());
            statement.setString(2, getKelamin());
            statement.setInt(3, Integer.parseInt(getUsia()));
            statement.setString(4, getAlamat());
            statement.setString(5, getProvinsi());
            statement.setString(6, getKabkota());
            statement.setString(7, getKecamatan());
            statement.setString(8, getKelurahan());
            statement.setString(9, getSuratDokter());
            statement.setString(10, getSkck());
            statement.setString(11, getStatus());
            statement.setString(12, getIpk());
            statement.setString(13, getEmail());
            statement.setInt(14, Integer.parseInt(getTelp()));
            statement.setString(15, getIdPeserta());
            statement.executeUpdate();
            System.out.println("Berhasil update");
        } catch (SQLException e) {
            System.out.println("Gagal update " + e);
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
                JOptionPane.showMessageDialog(null, "Gagal hapus");
            }
        }
    }

    public String getIdDelete() {
        int row = tblPeserta.getSelectedRow();
        int column = 0;
        String finalValue = (String) tblPeserta.getValueAt(row, column);
        System.out.println(finalValue);
        return finalValue;
    }

    public void fillForm(int index) {
        txtIdPeserta.setText(tblPeserta.getValueAt(index, 0).toString());
        txtNm_Peserta.setText(tblPeserta.getValueAt(index, 1).toString());
        cmbJns_Kel.setSelectedItem(tblPeserta.getValueAt(index, 2).toString());
        txtUsia.setText(tblPeserta.getValueAt(index, 3).toString());
        txtAlamat.setText(tblPeserta.getValueAt(index, 4).toString());
        jComboBoxProvinsi.setSelectedItem(tblPeserta.getValueAt(index, 5).toString());
        jComboBoxKabKota.setSelectedItem(tblPeserta.getValueAt(index, 6).toString());
        jComboBoxKec.setSelectedItem(tblPeserta.getValueAt(index, 7).toString());
        jComboBoxKel.setSelectedItem(tblPeserta.getValueAt(index, 8).toString());
        cmbSrt_Dok.setSelectedItem(tblPeserta.getValueAt(index, 9).toString());
        cmbSkck.setSelectedItem(tblPeserta.getValueAt(index, 10).toString());
        cmbStatus.setSelectedItem(tblPeserta.getValueAt(index, 11).toString());
        txtIpk.setText(tblPeserta.getValueAt(index, 12).toString());
        txtEmail.setText(tblPeserta.getValueAt(index, 13).toString());
        txtNo_Wa.setText(tblPeserta.getValueAt(index, 14).toString());
    }

    public void clearForm() {
        txtIdPeserta.setText("");
        txtNm_Peserta.setText("");
        cmbJns_Kel.setSelectedItem(cmbJns_Kel.getItemAt(0));
        txtUsia.setText("");
        txtAlamat.setText("");
        jComboBoxProvinsi.setSelectedItem(jComboBoxProvinsi.getItemAt(0));
        jComboBoxKabKota.setSelectedItem(jComboBoxKabKota.getItemAt(0));
        jComboBoxKec.setSelectedItem(jComboBoxKec.getItemAt(0));
        jComboBoxKel.setSelectedItem(jComboBoxKel.getItemAt(0));
        cmbSrt_Dok.setSelectedItem(cmbSrt_Dok.getItemAt(0));
        cmbSkck.setSelectedItem(cmbSkck.getItemAt(0));
        cmbStatus.setSelectedItem(cmbStatus.getItemAt(0));
        txtIpk.setText("");
        txtEmail.setText("");
        txtNo_Wa.setText("");
    }

    public void setButtonEdit(boolean value) {
        btnSimpan.setEnabled(value);
        btnBatal.setEnabled(value);
    }

    public void setButtonTambah(boolean value) {
        btnEdit.setEnabled(value);
        btnHapus.setEnabled(value);
        btnTambah.setEnabled(value);
        setButtonEdit(!value);
    }

    public void setTextField(boolean value) {
        txtIdPeserta.setEnabled(value);
        txtNm_Peserta.setEnabled(value);
        cmbJns_Kel.setEnabled(value);
        txtUsia.setEnabled(value);
        txtAlamat.setEnabled(value);
        jComboBoxProvinsi.setEnabled(value);
        jComboBoxKabKota.setEnabled(value);
        jComboBoxKec.setEnabled(value);
        jComboBoxKel.setEnabled(value);
        cmbSrt_Dok.setEnabled(value);
        cmbSkck.setEnabled(value);
        cmbStatus.setEnabled(value);
        txtIpk.setEnabled(value);
        txtEmail.setEnabled(value);
        txtNo_Wa.setEnabled(value);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIdPeserta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNm_Peserta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbJns_Kel = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtUsia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtAlamat = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmbSkck = new javax.swing.JComboBox<>();
        cmbSrt_Dok = new javax.swing.JComboBox<>();
        cmbStatus = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtIpk = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtNo_Wa = new javax.swing.JTextField();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeserta = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxProvinsi = new javax.swing.JComboBox<>();
        jComboBoxKabKota = new javax.swing.JComboBox<>();
        jComboBoxKec = new javax.swing.JComboBox<>();
        jComboBoxKel = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Peserta");

        jLabel1.setBackground(new java.awt.Color(51, 204, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Input Data Peserta");

        jLabel2.setText("Id Peserta");

        jLabel3.setText("Nama Lengkap");

        jLabel4.setText("Jenis Kelamin");

        cmbJns_Kel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --", "Laki-Laki", "Perempuan" }));
        cmbJns_Kel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel5.setText("Usia");

        jLabel6.setText("Alamat");

        jLabel7.setText("Kelurahan");

        jLabel8.setText("Kecamatan");

        jLabel9.setText("Kabupaten/Kota");

        jLabel11.setText("Surat Dokter");

        jLabel12.setText("SKCK");

        jLabel13.setText("Status");

        cmbSkck.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --", "Terlampir", "Tidak Terlampir" }));
        cmbSkck.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cmbSrt_Dok.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --", "Terlampir", "Tidak Terlampir" }));

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --", "Menikah", "Belum Menikah" }));
        cmbStatus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel14.setText("IPK");

        jLabel15.setText("Email");

        jLabel16.setText("No WhatsApp");

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        tblPeserta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id Peserta", "Nama Lengkap", "Jenis Kelamin", "Usia", "Alamat", "Kelurahan", "Kecamatan", "Kabupaten/Kota", "Surat Dokter", "SKCK", "Status", "IPK", "Email", "No WhatsApp"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPeserta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPesertaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPeserta);

        jLabel10.setText("Provinsi");

        jComboBoxProvinsi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));
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

        jButton1.setText("Keluar");
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35))
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(txtNm_Peserta, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(txtIdPeserta)
                            .addComponent(cmbJns_Kel, 0, 1, Short.MAX_VALUE)
                            .addComponent(txtUsia, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxProvinsi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxKabKota, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxKec, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxKel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel16))
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIpk, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNo_Wa, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(52, 52, 52)
                                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(52, 52, 52)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cmbSrt_Dok, 0, 1, Short.MAX_VALUE)
                                        .addComponent(cmbSkck, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(42, 42, 42))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnTambah)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtIdPeserta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNm_Peserta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cmbJns_Kel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtUsia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBoxProvinsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxKabKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxKec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxKel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(cmbSrt_Dok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(cmbSkck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIpk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(5, 5, 5))
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtNo_Wa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnEdit)
                    .addComponent(btnSimpan)
                    .addComponent(btnHapus)
                    .addComponent(btnBatal)
                    .addComponent(jButton1))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        setButtonTambah(false);
        setTextField(true);
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if (isUpdate == false) {
            insertDB();
        } else {
            updateDB();
        }
        selectDB();
        clearForm();
        setButtonTambah(true);
        setTextField(false);
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        int row = tblPeserta.getSelectedRow();
        fillForm(row);
        setButtonTambah(false);
        setTextField(true);
        txtIdPeserta.setEnabled(false);
        isUpdate = true;
    }//GEN-LAST:event_btnEditActionPerformed

    private void tblPesertaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPesertaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPesertaMouseClicked

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        deleteDB();
        selectDB();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        setButtonTambah(true);
        clearForm();
        setTextField(false);
        isUpdate = false;
    }//GEN-LAST:event_btnBatalActionPerformed

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(frmPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPeserta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cmbJns_Kel;
    private javax.swing.JComboBox<String> cmbSkck;
    private javax.swing.JComboBox<String> cmbSrt_Dok;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxKabKota;
    private javax.swing.JComboBox<String> jComboBoxKec;
    private javax.swing.JComboBox<String> jComboBoxKel;
    private javax.swing.JComboBox<String> jComboBoxProvinsi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPeserta;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIdPeserta;
    private javax.swing.JTextField txtIpk;
    private javax.swing.JTextField txtNm_Peserta;
    private javax.swing.JTextField txtNo_Wa;
    private javax.swing.JTextField txtUsia;
    // End of variables declaration//GEN-END:variables
}
