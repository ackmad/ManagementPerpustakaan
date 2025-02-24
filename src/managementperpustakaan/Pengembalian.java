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
public class Pengembalian extends javax.swing.JFrame {
  
Connection conn;
    public Pengembalian() {
     initComponents();
        connection();
     tampilDataPengembalian();
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
  public void tampilDataPengembalian() {
    String sql = "SELECT p.id_pengembalian, pm.id_peminjaman, a.nama, p.tanggal_kembali_aktual, " +
                 "p.denda, p.status_denda, p.created_at, p.updated_at, p.deleted_at " +
                 "FROM pengembalian p " +
                 "JOIN peminjaman pm ON p.id_peminjaman = pm.id_peminjaman " +
                 "JOIN anggota a ON pm.id_anggota = a.id_anggota";

    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID Pengembalian");
    model.addColumn("ID Peminjaman");
    model.addColumn("Nama Anggota");
    model.addColumn("Tanggal Kembali Aktual");
    model.addColumn("Denda");
    model.addColumn("Status Denda");
    model.addColumn("Created At");
    model.addColumn("Updated At");
    model.addColumn("Deleted At");

    try {
        PreparedStatement pre = conn.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("id_pengembalian"),
                rs.getString("id_peminjaman"),
                rs.getString("nama"),
                rs.getString("tanggal_kembali_aktual"),
                rs.getString("denda"),
                rs.getString("status_denda"),
                rs.getString("created_at"),
                rs.getString("updated_at"),
                rs.getString("deleted_at")
            });
        }

        table_data.setModel(model);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengambil data!", "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem3 = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_data = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txt_idpengembalian = new javax.swing.JTextField();
        btn_bukuDikembalikan = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();

        jMenuItem3.setText("jMenuItem3");

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

        jLabel5.setFont(new java.awt.Font("Beirut", 0, 24)); // NOI18N
        jLabel5.setText("Mengembalikan Buku");

        btn_bukuDikembalikan.setText("Buku Dikembalikan");
        btn_bukuDikembalikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bukuDikembalikanActionPerformed(evt);
            }
        });

        btn_edit.setText("Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_idpengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_bukuDikembalikan, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_idpengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_bukuDikembalikan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
          new Pengembalian().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
          new Pinjaman().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btn_bukuDikembalikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bukuDikembalikanActionPerformed
  String idPeminjaman = txt_idpengembalian.getText().trim();
    if (idPeminjaman.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Masukkan ID Peminjaman!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int denda = 0;
    String statusDenda = "Tidak Ada";

    try {
        String sqlSelect = "SELECT tanggal_kembali FROM peminjaman WHERE id_peminjaman = ?";
        PreparedStatement preSelect = conn.prepareStatement(sqlSelect);
        preSelect.setString(1, idPeminjaman);
        ResultSet rs = preSelect.executeQuery();

        if (rs.next()) {
            Date tglKembali = rs.getDate("tanggal_kembali");
            Date tglAktual = new Date(); // Current Date

            if (tglAktual.after(tglKembali)) {
                long selisihHari = (tglAktual.getTime() - tglKembali.getTime()) / (1000 * 60 * 60 * 24);
                denda = (int) selisihHari * 5000;
                statusDenda = "Terlambat";
            }
        } else {
            JOptionPane.showMessageDialog(null, "ID Peminjaman tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sqlInsert = "INSERT INTO pengembalian (id_peminjaman, tanggal_kembali_aktual, denda, status_denda, created_at, updated_at, deleted_at) " +
                           "VALUES (?, CURRENT_DATE, ?, ?, NOW(), NOW(), NULL)";
        PreparedStatement preInsert = conn.prepareStatement(sqlInsert);
        preInsert.setString(1, idPeminjaman);
        preInsert.setInt(2, denda);
        preInsert.setString(3, statusDenda);

        int rowInserted = preInsert.executeUpdate();
        if (rowInserted > 0) {
            JOptionPane.showMessageDialog(null, "Buku berhasil dikembalikan!");
            tampilDataPengembalian(); // Update tabel pengembalian
        } else {
            JOptionPane.showMessageDialog(null, "Gagal mengembalikan buku!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan!", "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }     
    }//GEN-LAST:event_btn_bukuDikembalikanActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
     int selectedRow = table_data.getSelectedRow();
    if (selectedRow != -1) {
        String idPengembalian = table_data.getValueAt(selectedRow, 0).toString();
        new UpdatePengembalian(idPengembalian, conn, this).setVisible(true);
    } else {
        JOptionPane.showMessageDialog(null, "Pilih data terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_btn_editActionPerformed

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
            java.util.logging.Logger.getLogger(Pengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pengembalian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_bukuDikembalikan;
    private javax.swing.JButton btn_edit;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_data;
    private javax.swing.JTextField txt_idpengembalian;
    // End of variables declaration//GEN-END:variables
}
