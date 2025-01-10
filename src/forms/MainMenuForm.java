/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import javax.swing.JFrame;

public class MainMenuForm extends javax.swing.JFrame {

    
    
   

   
    public MainMenuForm() {
        initComponents();
        
    }
    
     private static MainMenuForm instance;

    
    public static MainMenuForm getInstance() {
    if (instance == null) {
        instance = new MainMenuForm();
    }
    return instance;
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        formPegawaiButton = new javax.swing.JButton();
        formJabatanButton = new javax.swing.JButton();
        formGajiButton = new javax.swing.JButton();
        formLaporanButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Aplikasi Kepegawaian", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 36))); // NOI18N

        formPegawaiButton.setText("Pegawai");
        formPegawaiButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        formPegawaiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formPegawaiButtonActionPerformed(evt);
            }
        });

        formJabatanButton.setText("Jabatan");
        formJabatanButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        formJabatanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formJabatanButtonActionPerformed(evt);
            }
        });

        formGajiButton.setText("Gaji");
        formGajiButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        formGajiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formGajiButtonActionPerformed(evt);
            }
        });

        formLaporanButton.setText("Laporan");
        formLaporanButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        formLaporanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formLaporanButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(formPegawaiButton, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(formGajiButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(formLaporanButton, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(formJabatanButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(formPegawaiButton, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(formJabatanButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(formGajiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formLaporanButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(174, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formGajiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formGajiButtonActionPerformed
        this.setVisible(false); // Sembunyikan MainMenu
    FormGaji formGaji = new FormGaji(); // Buat instance PelangganForm
    formGaji.setVisible(true); // Tampilkan PelangganForm
    formGaji.setLocationRelativeTo(null);
    }//GEN-LAST:event_formGajiButtonActionPerformed

    private void formPegawaiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formPegawaiButtonActionPerformed
      this.setVisible(false); // Sembunyikan MainMenu
    FormPegawai formPegawai = new FormPegawai(); // Buat instance PelangganForm
    formPegawai.setVisible(true); // Tampilkan PelangganForm
    formPegawai.setLocationRelativeTo(null);      
    }//GEN-LAST:event_formPegawaiButtonActionPerformed

    private void formJabatanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formJabatanButtonActionPerformed
        this.setVisible(false); // Sembunyikan MainMenu
    FormJabatan formJabatan = new FormJabatan(); // Buat instance PelangganForm
    formJabatan.setVisible(true); // Tampilkan PelangganForm
    formJabatan.setLocationRelativeTo(null);
    }//GEN-LAST:event_formJabatanButtonActionPerformed

    private void formLaporanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formLaporanButtonActionPerformed
        this.setVisible(false); // Sembunyikan MainMenu
    FormLaporan formLaporan = new FormLaporan(); // Buat instance PelangganForm
    formLaporan.setVisible(true); // Tampilkan PelangganForm
    formLaporan.setLocationRelativeTo(null);
    }//GEN-LAST:event_formLaporanButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenuForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton formGajiButton;
    private javax.swing.JButton formJabatanButton;
    private javax.swing.JButton formLaporanButton;
    private javax.swing.JButton formPegawaiButton;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}