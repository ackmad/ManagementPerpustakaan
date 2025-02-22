/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package managementperpustakaan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
public class Pinjaman extends javax.swing.JFrame {

    Connection conn;
    public Pinjaman() {
        initComponents();
        connection();
        loadAnggotaComboBox();
        loadBukuComboBox();
       tampilDataPeminjaman();
    }

   private void connection(){
   try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/perpus?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            JOptionPane.showMessageDialog(null, "koneksi berhasil");
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal");
        }
   }
  
  private void cariDataPeminjaman(String keyword) {
    String sql = "SELECT peminjaman.id_peminjaman, anggota.nama AS nama_anggota, buku.judul AS judul_buku, "
               + "peminjaman.tanggal_pinjam, peminjaman.tanggal_kembali, peminjaman.status "
               + "FROM peminjaman "
               + "JOIN anggota ON peminjaman.id_anggota = anggota.id_anggota "
               + "JOIN buku ON peminjaman.id_buku = buku.id_buku "
               + "WHERE anggota.nama LIKE ? OR buku.judul LIKE ? OR peminjaman.id_peminjaman LIKE ?";

    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID Peminjaman");
    model.addColumn("Nama Anggota");
    model.addColumn("Judul Buku");
    model.addColumn("Tanggal Pinjam");
    model.addColumn("Tanggal Kembali");
    model.addColumn("Status");

    try {
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1, "%" + keyword + "%");
        pre.setString(2, "%" + keyword + "%");
        pre.setString(3, "%" + keyword + "%");
        ResultSet rs = pre.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("id_peminjaman"),
                rs.getString("nama_anggota"),
                rs.getString("judul_buku"),
                rs.getString("tanggal_pinjam"),
                rs.getString("tanggal_kembali"),
                rs.getString("status")
            });
        }

        table_data.setModel(model);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mencari data!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

   
  private void tampilDataPeminjaman() {
    String sql = "SELECT peminjaman.id_peminjaman, anggota.nama AS nama_anggota, buku.judul AS judul_buku, "
            + "peminjaman.tanggal_pinjam, peminjaman.tanggal_kembali, peminjaman.status "
            + "FROM peminjaman "
            + "JOIN anggota ON peminjaman.id_anggota = anggota.id_anggota "
            + "JOIN buku ON peminjaman.id_buku = buku.id_buku";

    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID Peminjaman");
    model.addColumn("Nama Anggota");
    model.addColumn("Judul Buku");
    model.addColumn("Tanggal Pinjam");
    model.addColumn("Tanggal Kembali");
    model.addColumn("Status");

    try {
        PreparedStatement pre = conn.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("id_peminjaman"),
                rs.getString("nama_anggota"),
                rs.getString("judul_buku"),
                rs.getString("tanggal_pinjam"),
                rs.getString("tanggal_kembali"),
                rs.getString("status")
            });
        }

        table_data.setModel(model);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengambil data!", "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}


private void loadAnggotaComboBox() {
    String sql = "SELECT nama FROM anggota"; 

    try {
        PreparedStatement pre = conn.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();

        cmb_anggota.removeAllItems(); 
        cmb_anggota.addItem("-- Pilih Anggota --"); 

        while (rs.next()) {
            String namaAnggota = rs.getString("nama"); 
            cmb_anggota.addItem(namaAnggota); 
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
private void loadBukuComboBox() {
    String sql = "SELECT id_buku, judul FROM buku";
    
    try {
        PreparedStatement pre = conn.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        
        cmb_buku.removeAllItems(); 
        cmb_buku.addItem("-- Pilih Buku --"); 

        while (rs.next()) {
            String idBuku = rs.getString("id_buku");
            String judulBuku = rs.getString("judul");
            cmb_buku.addItem(judulBuku); 
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_data = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        input_search = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmb_anggota = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cmb_buku = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_tglPinjam = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_tglKembali = new javax.swing.JTextField();
        peringatan_1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btn_pinjam = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(206, 242, 230));

        table_data.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table_data);

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel5.setText("Data Peminjaman Buku");

        input_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                input_searchKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel6.setText("Tambah Pinjaman Buku");

        cmb_anggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_anggotaActionPerformed(evt);
            }
        });

        jLabel7.setText("Nama Peminjam (Anggota)");

        cmb_buku.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tsukihime", "Uber", "Gojek", "raja", "shopee", "tokopedia", "tahu bulat", "gosong", "urang", "tahu kotak" }));

        jLabel8.setText("Buku");

        jLabel9.setText("Tanggal Peminjaman dan Pengembalian");

        txt_tglPinjam.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tglPinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tglPinjamActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("-");

        txt_tglKembali.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tglKembali.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tglKembaliKeyReleased(evt);
            }
        });

        peringatan_1.setFont(new java.awt.Font("Helvetica Neue", 2, 10)); // NOI18N
        peringatan_1.setForeground(new java.awt.Color(255, 102, 102));
        peringatan_1.setText("*Batas peminjaman sampai 14hari");

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 2, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 102, 102));
        jLabel11.setText("*Telat pemgembalian denda Rp, 5.000");

        btn_pinjam.setText("Pinjam");
        btn_pinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pinjamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(peringatan_1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_anggota, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb_buku, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txt_tglPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_tglKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btn_pinjam))
                                .addGap(0, 62, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(input_search, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(input_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_anggota, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_buku, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tglPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(txt_tglKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(peringatan_1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_pinjam)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jMenu1.setText("Dashboard");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Management Buku");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Management Anggota");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Pinjaman dan Pengembalian");

        jMenuItem1.setText("Pinjaman");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuItem2.setText("Pengembalian");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Laporan");
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
          new Dashboard().setVisible(true);
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        // TODO add your handling code here:
          new ManagementBuku().setVisible(true);
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        // TODO add your handling code here:
          new ManagementAnggota().setVisible(true);
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
          new Pinjaman().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
          new Pengembalian().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void txt_tglPinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tglPinjamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tglPinjamActionPerformed

    private void cmb_anggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_anggotaActionPerformed
   

    }//GEN-LAST:event_cmb_anggotaActionPerformed

    private void txt_tglKembaliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tglKembaliKeyReleased
   
    }//GEN-LAST:event_txt_tglKembaliKeyReleased

    private void btn_pinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pinjamActionPerformed
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

try {
    String pinjamStr = txt_tglPinjam.getText().trim();
    String kembaliStr = txt_tglKembali.getText().trim();
    String namaAnggota = (String) cmb_anggota.getSelectedItem();
    String judulBuku = (String) cmb_buku.getSelectedItem();

    if (pinjamStr.isEmpty() || kembaliStr.isEmpty() || "-- Pilih Anggota --".equals(namaAnggota) || "-- Pilih Buku --".equals(judulBuku)) {
        JOptionPane.showMessageDialog(null, "Harap isi semua data!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Date tglPinjam = sdf.parse(pinjamStr);
    Date tglKembali = sdf.parse(kembaliStr);

    if (tglKembali.before(tglPinjam)) {
        JOptionPane.showMessageDialog(null, "Tanggal kembali tidak boleh lebih awal dari tanggal pinjam!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    long selisihHari = ChronoUnit.DAYS.between(tglPinjam.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
                                           tglKembali.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());


   if (selisihHari > 14) {
    JOptionPane.showMessageDialog(null, "Peminjaman tidak boleh lebih dari 14 hari!", "Error", JOptionPane.ERROR_MESSAGE);
    return; // Hentikan proses jika lebih dari 14 hari
}


    String idAnggota = null, idBuku = null;

    String sqlAnggota = "SELECT id_anggota FROM anggota WHERE nama = ?";
    PreparedStatement preAnggota = conn.prepareStatement(sqlAnggota);
    preAnggota.setString(1, namaAnggota);
    ResultSet rsAnggota = preAnggota.executeQuery();
    if (rsAnggota.next()) {
        idAnggota = rsAnggota.getString("id_anggota");
    }

    String sqlBuku = "SELECT id_buku FROM buku WHERE judul = ?";
    PreparedStatement preBuku = conn.prepareStatement(sqlBuku);
    preBuku.setString(1, judulBuku);
    ResultSet rsBuku = preBuku.executeQuery();
    if (rsBuku.next()) {
        idBuku = rsBuku.getString("id_buku");
    }

    if (idAnggota == null || idBuku == null) {
        JOptionPane.showMessageDialog(null, "Data anggota atau buku tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String sqlInsert = "INSERT INTO peminjaman (id_anggota, id_buku, tanggal_pinjam, tanggal_kembali, status) " +
                       "VALUES (?, ?, ?, ?, 'Dipinjam')";
    PreparedStatement pre = conn.prepareStatement(sqlInsert);
    pre.setString(1, idAnggota);
    pre.setString(2, idBuku);
    pre.setString(3, pinjamStr);
    pre.setString(4, kembaliStr);

    int rowInserted = pre.executeUpdate();
   if (rowInserted > 0) {
    JOptionPane.showMessageDialog(null, "Peminjaman berhasil ditambahkan!");
    tampilDataPeminjaman(); // Panggil kembali metode untuk menampilkan data terbaru
}
 else {
        JOptionPane.showMessageDialog(null, "Peminjaman gagal ditambahkan!", "Error", JOptionPane.ERROR_MESSAGE);
    }

} catch (ParseException e) {
    JOptionPane.showMessageDialog(null, "Format tanggal salah! Gunakan format yyyy-MM-dd", "Error", JOptionPane.ERROR_MESSAGE);
} catch (SQLException e) {
    JOptionPane.showMessageDialog(null, "Terjadi kesalahan database!", "Error", JOptionPane.ERROR_MESSAGE);
    e.printStackTrace();
}

    }//GEN-LAST:event_btn_pinjamActionPerformed

    private void input_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_input_searchKeyReleased
         String keyword = input_search.getText().trim();
        cariDataPeminjaman(keyword);
    }//GEN-LAST:event_input_searchKeyReleased

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
            java.util.logging.Logger.getLogger(Pinjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pinjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pinjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pinjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pinjaman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_pinjam;
    private javax.swing.JComboBox<String> cmb_anggota;
    private javax.swing.JComboBox<String> cmb_buku;
    private javax.swing.JTextField input_search;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel peringatan_1;
    private javax.swing.JTable table_data;
    private javax.swing.JTextField txt_tglKembali;
    private javax.swing.JTextField txt_tglPinjam;
    // End of variables declaration//GEN-END:variables
}
