/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.DatabaseConnection;


/**
 *
 * @author Putra
 */
public class FormLaporan extends javax.swing.JFrame {

    /**
     * Creates new form FormLaporan
     */
    public FormLaporan() {
        initComponents();
    tampilkanLaporanPegawai(); // Panggil method untuk memuat data laporan pegawai saat form dibuka
    tambahkanListenerTabbedPane(); // Tambahkan listener untuk tabbed pane

    // Tambahkan listener untuk tombol Export
    exportPegawaiButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            exportLaporanPegawaiToPDF(); // Panggil method export laporan pegawai
        }
    });

    exportGajiButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            exportRekapGajiToPDF(); // Panggil method export rekap gaji
        }
    });
    }
    
    private void tambahkanListenerTabbedPane() {
        tabbedPaneLaporan.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                int selectedTab = tabbedPaneLaporan.getSelectedIndex();
                if (selectedTab == 0) { // Tab "Laporan Pegawai"
                    tampilkanLaporanPegawai();
                } else if (selectedTab == 1) { // Tab "Rekap Gaji"
                    tampilkanRekapGaji();
                }
            }
        });
    }


    
    private void tampilkanLaporanPegawai() {
    try {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT j.nama_jabatan AS Jabatan, COUNT(p.id_pegawai) AS Jumlah_Pegawai " +
                     "FROM pegawai p " +
                     "JOIN jabatan j ON p.id_jabatan = j.id_jabatan " +
                     "GROUP BY j.nama_jabatan";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) laporanPegawaiTable.getModel();
        model.setRowCount(0);
        while (rs.next()) {
            Object[] row = {
                rs.getString("Jabatan"),
                rs.getInt("Jumlah_Pegawai")
            };
            model.addRow(row);

            
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}



    
    private void tampilkanRekapGaji() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT p.nama AS Nama_Pegawai, j.nama_jabatan AS Jabatan, g.tanggal_pembayaran AS Tanggal, g.jumlah AS Jumlah_Gaji " +
                         "FROM gaji g " +
                         "JOIN pegawai p ON g.id_pegawai = p.id_pegawai " +
                         "JOIN jabatan j ON p.id_jabatan = j.id_jabatan";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            DefaultTableModel model = (DefaultTableModel) rekapGajiTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                Object[] row = {
                    rs.getString("Nama_Pegawai"),
                    rs.getString("Jabatan"),
                    rs.getDate("Tanggal"),
                    rs.getInt("Jumlah_Gaji")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

   private void exportLaporanPegawaiToPDF() {
        if (laporanPegawaiTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada data untuk diekspor!");
            return;
        }

        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Pilih lokasi untuk menyimpan laporan PDF");
            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();

                if (!filePath.endsWith(".pdf")) {
                    filePath += ".pdf";
                }

                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                document.add(new Paragraph("Laporan Pegawai Berdasarkan Jabatan"));
                document.add(new Paragraph("====================================="));
                document.add(new Paragraph(" "));

                PdfPTable table = new PdfPTable(2);
                table.addCell("Jabatan");
                table.addCell("Jumlah Pegawai");

                for (int i = 0; i < laporanPegawaiTable.getRowCount(); i++) {
                    String jabatan = laporanPegawaiTable.getValueAt(i, 0) != null
                            ? laporanPegawaiTable.getValueAt(i, 0).toString()
                            : "N/A";
                    String jumlahPegawai = laporanPegawaiTable.getValueAt(i, 1) != null
                            ? laporanPegawaiTable.getValueAt(i, 1).toString()
                            : "N/A";

                    table.addCell(jabatan);
                    table.addCell(jumlahPegawai);
                }

                document.add(table);
                document.close();

                JOptionPane.showMessageDialog(this, "Laporan berhasil disimpan di " + filePath);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saat mengekspor laporan: " + e.getMessage());
        }
    }




    
    private void exportRekapGajiToPDF() {
        if (rekapGajiTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada data untuk diekspor!");
            return;
        }

        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Pilih lokasi untuk menyimpan rekap gaji PDF");
            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();

                if (!filePath.endsWith(".pdf")) {
                    filePath += ".pdf";
                }

                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                document.add(new Paragraph("Rekapitulasi Pembayaran Gaji"));
                document.add(new Paragraph("====================================="));
                document.add(new Paragraph(" "));

                PdfPTable table = new PdfPTable(4);
                table.addCell("Nama Pegawai");
                table.addCell("Jabatan");
                table.addCell("Tanggal Pembayaran");
                table.addCell("Jumlah Gaji");

                for (int i = 0; i < rekapGajiTable.getRowCount(); i++) {
                    String namaPegawai = rekapGajiTable.getValueAt(i, 0) != null
                            ? rekapGajiTable.getValueAt(i, 0).toString()
                            : "N/A";
                    String jabatan = rekapGajiTable.getValueAt(i, 1) != null
                            ? rekapGajiTable.getValueAt(i, 1).toString()
                            : "N/A";
                    String tanggal = rekapGajiTable.getValueAt(i, 2) != null
                            ? rekapGajiTable.getValueAt(i, 2).toString()
                            : "N/A";
                    String jumlahGaji = rekapGajiTable.getValueAt(i, 3) != null
                            ? rekapGajiTable.getValueAt(i, 3).toString()
                            : "N/A";

                    table.addCell(namaPegawai);
                    table.addCell(jabatan);
                    table.addCell(tanggal);
                    table.addCell(jumlahGaji);
                }

                document.add(table);
                document.close();

                JOptionPane.showMessageDialog(this, "Rekap Gaji berhasil disimpan di " + filePath);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saat mengekspor rekap gaji: " + e.getMessage());
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

        tabbedPaneLaporan = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        laporanPegawaiTable = new javax.swing.JTable();
        exportPegawaiButton = new javax.swing.JButton();
        kembalibuttonlaporan = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        rekapGajiTable = new javax.swing.JTable();
        exportGajiButton = new javax.swing.JButton();
        kembalibuttonrekap = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        laporanPegawaiTable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        laporanPegawaiTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Jabatan", "Jumlah Pegawai"
            }
        ));
        jScrollPane1.setViewportView(laporanPegawaiTable);

        exportPegawaiButton.setText("Export");
        exportPegawaiButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        kembalibuttonlaporan.setText("Kembali");
        kembalibuttonlaporan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        kembalibuttonlaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembalibuttonlaporanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(344, 344, 344)
                .addComponent(exportPegawaiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(kembalibuttonlaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportPegawaiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kembalibuttonlaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        tabbedPaneLaporan.addTab("Laporan Pegawai", jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        rekapGajiTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nama Pegawai", "Jabatan", "Tanggal Pembayaran", "Jumlah Gaji"
            }
        ));
        jScrollPane2.setViewportView(rekapGajiTable);

        exportGajiButton.setText("Export");
        exportGajiButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        kembalibuttonrekap.setText("Kembali");
        kembalibuttonrekap.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        kembalibuttonrekap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembalibuttonrekapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exportGajiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(kembalibuttonrekap, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kembalibuttonrekap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportGajiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(313, 313, 313))
        );

        tabbedPaneLaporan.addTab("Rekap Gaji", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPaneLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPaneLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kembalibuttonlaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembalibuttonlaporanActionPerformed
       this.dispose(); // Menutup PelangganForm
    MainMenuForm mainMenuForm = MainMenuForm.getInstance(); // Gunakan instance MainMenu yang sudah ada
    mainMenuForm.setVisible(true); // Tampilkan MainMenu 
    }//GEN-LAST:event_kembalibuttonlaporanActionPerformed

    private void kembalibuttonrekapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembalibuttonrekapActionPerformed
        this.dispose(); // Menutup PelangganForm
    MainMenuForm mainMenuForm = MainMenuForm.getInstance(); // Gunakan instance MainMenu yang sudah ada
    mainMenuForm.setVisible(true); // Tampilkan MainMenu 
    }//GEN-LAST:event_kembalibuttonrekapActionPerformed

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
            java.util.logging.Logger.getLogger(FormLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLaporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exportGajiButton;
    private javax.swing.JButton exportPegawaiButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton kembalibuttonlaporan;
    private javax.swing.JButton kembalibuttonrekap;
    private javax.swing.JTable laporanPegawaiTable;
    private javax.swing.JTable rekapGajiTable;
    private javax.swing.JTabbedPane tabbedPaneLaporan;
    // End of variables declaration//GEN-END:variables
}
