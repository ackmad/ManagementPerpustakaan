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
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ManagementAnggota extends javax.swing.JFrame {
    private Connection conn;
    private DefaultTableModel model;
    private List<String> columnOrder = new ArrayList<>();
    public ManagementAnggota() {
        initComponents();
        connection();
        tampil_anggota();
    }

    private void connection() {
        try {
            String URL = "jdbc:mysql://localhost:3306/perpus";
            String USERNAME = "root";
            String PASSWORD = "";
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Connection Failed: " + e.getMessage());
        }
    }

     private static class DatabaseConnection {
       private static final String URL = "jdbc:mysql://localhost:3306/perpus";
       private static final String USER = "root";
       private static final String PASSWORD = "";

       public static Connection getConnection() throws SQLException {
           return DriverManager.getConnection(URL, USER, PASSWORD);
       }
   }
    
    private void updateTableColumnOrder(String selectedColumn) {
    DefaultTableModel model = new DefaultTableModel();
    
    String[] columns = {
        "id_anggota", 
        "nomor_anggota", 
        "nama", 
        "alamat", 
        "telepon", 
        "email", 
        "created_at",
        "updated_at",
        "deleted_at"
    };
    
    for (String column : columns) {
        model.addColumn(column);
    }

    try {
        String sql = "SELECT * FROM anggota WHERE deleted_at IS NULL";
        try (PreparedStatement pre = conn.prepareStatement(sql);
             ResultSet rs = pre.executeQuery()) {
            
            while (rs.next()) {
                Object[] row = new Object[columns.length];
                for (int i = 0; i < columns.length; i++) {
                    row[i] = rs.getString(columns[i]);
                }
                model.addRow(row);
            }
        }
        table_data.setModel(model);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error mengupdate tabel: " + e.getMessage());
    }
}

    private void tampil_anggota() {
    model = new DefaultTableModel();
    
    String[] columns = {
        "id_anggota", 
        "nomor_anggota", 
        "nama", 
        "alamat", 
        "telepon", 
        "email", 
        "created_at",
        "updated_at",
        "deleted_at"
    };
    
    for (String column : columns) {
        model.addColumn(column);
    }
    
    try {
        String sql = "SELECT * FROM anggota WHERE deleted_at IS NULL ORDER BY created_at DESC";
        try (PreparedStatement pre = conn.prepareStatement(sql);
             ResultSet res = pre.executeQuery()) {
            
            while (res.next()) {
                Object[] row = new Object[columns.length];
                for (int i = 0; i < columns.length; i++) {
                    row[i] = res.getString(columns[i]);
                }
                model.addRow(row);
            }
            table_data.setModel(model);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error menampilkan data: " + e.getMessage());
    }
}

    
    private void cariAnggota(String keyword) {
        model.setRowCount(0);
        
        try {
            String sql = "SELECT * FROM anggota WHERE deleted_at IS NULL AND (nomor_anggota LIKE ? OR nama LIKE ? OR email LIKE ?)";
            PreparedStatement pre = conn.prepareStatement(sql);
            String searchPattern = "%" + keyword + "%";
            pre.setString(1, searchPattern);
            pre.setString(2, searchPattern);
            pre.setString(3, searchPattern);
            
            ResultSet res = pre.executeQuery();
            while (res.next()) {
                Object[] row = new Object[]{
                    res.getString("id_anggota"),
                    res.getString("nomor_anggota"),
                    res.getString("nama"),
                    res.getString("alamat"),
                    res.getString("email"),
                    res.getString("telepon"),
                    res.getString("created_at"),
                    res.getString("updated_at"),
                    res.getString("deleted_at")
                };
                model.addRow(row);
            }
            table_data.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error searching data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_data = new javax.swing.JTable();
        input_search = new javax.swing.JTextField();
        btn_edit = new javax.swing.JButton();
        btn_tambah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        cetak = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        searchBy = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        pinjam = new javax.swing.JMenu();
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

        input_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_searchActionPerformed(evt);
            }
        });

        btn_edit.setText("Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_tambah.setText("Tambah +");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        cetak.setText("Cetak");
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Phosphate", 0, 18)); // NOI18N
        jLabel1.setText("MANAGEMENT ANGGOTA");

        searchBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id_anggota", "nomor_anggota", "nama", "status", "email", "alamat" }));
        searchBy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                searchByItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cetak))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_tambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_hapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_edit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchBy, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(input_search, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cetak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(input_search, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchBy, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_edit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_tambah))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        pinjam.setText("Pinjaman dan Pengembalian");
        pinjam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pinjamMouseClicked(evt);
            }
        });

        jMenuItem1.setText("Pinjaman");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        pinjam.add(jMenuItem1);

        jMenuItem2.setText("Pengembalian");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        pinjam.add(jMenuItem2);

        jMenuBar1.add(pinjam);

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

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cetakActionPerformed

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

    private void pinjamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pinjamMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_pinjamMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
          new Pinjaman().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
          new Pengembalian().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void input_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_input_searchActionPerformed
   
    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
       TambahAnggota formBuku = new TambahAnggota();
       formBuku.addWindowListener(new java.awt.event.WindowAdapter() {
           @Override
           public void windowClosed(java.awt.event.WindowEvent e) {
               // Refresh tabel saat form input ditutup
               tampil_anggota();
           }
       });
       formBuku.setVisible(true);
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
                                                 
    int selectedRow = table_data.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih anggota yang ingin dihapus.");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus anggota ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        String idAnggota = table_data.getValueAt(selectedRow, 0).toString();
        try {
            String sql = "UPDATE anggota SET deleted_at = NOW() WHERE id_anggota = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, idAnggota);
            pre.executeUpdate();
            JOptionPane.showMessageDialog(this, "Anggota berhasil dihapus.");
            tampil_anggota();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus anggota: " + e.getMessage());
        }
    }


    }//GEN-LAST:event_btn_hapusActionPerformed

    private void searchByItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_searchByItemStateChanged
       if (evt.getStateChange() == ItemEvent.SELECTED) {
           String selectedColumn = searchBy.getSelectedItem().toString().trim();
           System.out.println("Selected column: '" + selectedColumn + "'");
           updateTableColumnOrder(selectedColumn);
       }
    }//GEN-LAST:event_searchByItemStateChanged

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
    int selectedRow = table_data.getSelectedRow();
if (selectedRow == -1) {
    JOptionPane.showMessageDialog(this, "Pilih anggota yang akan diedit!", "Peringatan", JOptionPane.WARNING_MESSAGE);
    return;
}

String idAnggota = table_data.getValueAt(selectedRow, 0).toString();
String nomorAnggota = table_data.getValueAt(selectedRow, 1).toString(); 
String nama = table_data.getValueAt(selectedRow, 2).toString();
String alamat = table_data.getValueAt(selectedRow, 3).toString();
String email = table_data.getValueAt(selectedRow, 4).toString();
String telepon = table_data.getValueAt(selectedRow, 5).toString();

new UpdateAnggota(idAnggota, nomorAnggota, nama, alamat, email, telepon).setVisible(true);

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
            java.util.logging.Logger.getLogger(ManagementAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagementAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagementAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagementAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagementAnggota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton cetak;
    private javax.swing.JTextField input_search;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu pinjam;
    private javax.swing.JComboBox<String> searchBy;
    private javax.swing.JTable table_data;
    // End of variables declaration//GEN-END:variables
}
