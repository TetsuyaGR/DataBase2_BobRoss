/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author alexm
 */
public class OrderFrame extends javax.swing.JFrame {

    /**
     * Creates new form OrderFrame
     */
    public OrderFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        Fagito = new javax.swing.JLabel();
        OkeyButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        TableNumber = new javax.swing.JLabel();
        KiriosButton = new javax.swing.JButton();
        OrektikoButton = new javax.swing.JButton();
        EpidorpioButton = new javax.swing.JButton();
        MpiraButton = new javax.swing.JButton();
        SalataButton = new javax.swing.JButton();
        AnapsiktikoButton = new javax.swing.JButton();
        KrasiButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        KatalogosList = new javax.swing.JList<>();
        KatigoriaFagitouLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        AddButton = new javax.swing.JButton();
        RemoveButton = new javax.swing.JButton();
        NextOrder = new javax.swing.JButton();

        jFrame1.setLocation(new java.awt.Point(500, 250));
        jFrame1.setMinimumSize(new java.awt.Dimension(400, 250));

        Fagito.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Fagito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fagito.setToolTipText("");

        OkeyButton.setText("Okey");
        OkeyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkeyButtonActionPerformed(evt);
            }
        });

        CancelButton.setText("Cancel");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(OkeyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(Fagito, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(Fagito, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OkeyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(500, 250));

        TableNumber.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TableNumber.setText("table 1");

        KiriosButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        KiriosButton.setText("Κυρίος");
        KiriosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KiriosButtonActionPerformed(evt);
            }
        });

        OrektikoButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        OrektikoButton.setText("Ορεκτικό");
        OrektikoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrektikoButtonActionPerformed(evt);
            }
        });

        EpidorpioButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        EpidorpioButton.setText("Επιδόρπιο");
        EpidorpioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EpidorpioButtonActionPerformed(evt);
            }
        });

        MpiraButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MpiraButton.setText("Μπύρα");
        MpiraButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MpiraButtonActionPerformed(evt);
            }
        });

        SalataButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        SalataButton.setText("Σαλάτα");
        SalataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalataButtonActionPerformed(evt);
            }
        });

        AnapsiktikoButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        AnapsiktikoButton.setText("Αναψυκτικό");
        AnapsiktikoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnapsiktikoButtonActionPerformed(evt);
            }
        });

        KrasiButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        KrasiButton.setText("Κρασί");
        KrasiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KrasiButtonActionPerformed(evt);
            }
        });

        KatalogosList.setToolTipText("");
        jScrollPane2.setViewportView(KatalogosList);

        KatigoriaFagitouLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        KatigoriaFagitouLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        KatigoriaFagitouLabel.setText("Επιλέξτε κατηγορία φαγητού");
        KatigoriaFagitouLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        KatigoriaFagitouLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jScrollPane1.setViewportView(jList1);

        AddButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        AddButton.setText("add");
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        RemoveButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RemoveButton.setText("remove");
        RemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveButtonActionPerformed(evt);
            }
        });

        NextOrder.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        NextOrder.setText("next order");
        NextOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MpiraButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KrasiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EpidorpioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KiriosButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OrektikoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SalataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AnapsiktikoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TableNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(KatigoriaFagitouLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(RemoveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addComponent(AddButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(NextOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(KatigoriaFagitouLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TableNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(KiriosButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(OrektikoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SalataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AnapsiktikoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(MpiraButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(KrasiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(EpidorpioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RemoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NextOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    DefaultListModel mod = new DefaultListModel();
    DefaultListModel mod2 = new DefaultListModel();
    private void KiriosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KiriosButtonActionPerformed
        
        KatigoriaFagitouLabel.setText("Κυρίως πιάτα");
        KatalogosList.setModel(mod);
        mod.removeAllElements();
        for (int i=0 ; i!=11 ; i++){
            mod.addElement("Κυρίως πιάτο "+i);
        }
    }//GEN-LAST:event_KiriosButtonActionPerformed

    private void OrektikoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrektikoButtonActionPerformed
        KatigoriaFagitouLabel.setText("Ορεκτικά");
        KatalogosList.setModel(mod);
        mod.removeAllElements();
        for (int i=0 ; i!=11 ; i++){
            mod.addElement("Ορεκτικό "+i);
        }
    }//GEN-LAST:event_OrektikoButtonActionPerformed
    
    private void EpidorpioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EpidorpioButtonActionPerformed
        KatigoriaFagitouLabel.setText("Επιδόρπια"); 
        KatalogosList.setModel(mod);
        mod.removeAllElements();
        for (int i=0 ; i!=11 ; i++){
            mod.addElement("Επιδόρπιο "+i);
        }
    }//GEN-LAST:event_EpidorpioButtonActionPerformed

    private void MpiraButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MpiraButtonActionPerformed
        KatigoriaFagitouLabel.setText("Μπύρες");
        KatalogosList.setModel(mod);
        mod.removeAllElements();
        for (int i=0 ; i!=11 ; i++){
            mod.addElement("Μπύρα "+i);
        }
    }//GEN-LAST:event_MpiraButtonActionPerformed

    private void SalataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalataButtonActionPerformed
        KatigoriaFagitouLabel.setText("Σαλάτες");
        KatalogosList.setModel(mod);
        mod.removeAllElements();
        for (int i=0 ; i!=11 ; i++){
            mod.addElement("Σαλάτα "+i);
        }
    }//GEN-LAST:event_SalataButtonActionPerformed

    private void AnapsiktikoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnapsiktikoButtonActionPerformed
        KatigoriaFagitouLabel.setText("Αναψυκτικά");
        KatalogosList.setModel(mod);
        mod.removeAllElements();
        for (int i=0 ; i!=11 ; i++){
            mod.addElement("Αναψυκρικό "+i);
        }
    }//GEN-LAST:event_AnapsiktikoButtonActionPerformed

    private void KrasiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KrasiButtonActionPerformed
        KatigoriaFagitouLabel.setText("Κρασιά");
        KatalogosList.setModel(mod);
        mod.removeAllElements();
        for (int i=0 ; i!=11 ; i++){
            mod.addElement("Κρασί "+i);
        }
    }//GEN-LAST:event_KrasiButtonActionPerformed

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
        jFrame1.show();
        Fagito.setText(KatalogosList.getSelectedValue());
        
    }//GEN-LAST:event_AddButtonActionPerformed

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        jFrame1.dispose();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void OkeyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkeyButtonActionPerformed
        jFrame1.dispose();
        jList1.setModel(mod2);
        mod2.addElement(KatalogosList.getSelectedValue());
        
        
    }//GEN-LAST:event_OkeyButtonActionPerformed

    private void RemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveButtonActionPerformed
        
    }//GEN-LAST:event_RemoveButtonActionPerformed

    private void NextOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextOrderActionPerformed
        new StrartingFrame().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_NextOrderActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JButton AnapsiktikoButton;
    private javax.swing.JButton CancelButton;
    private javax.swing.JButton EpidorpioButton;
    private javax.swing.JLabel Fagito;
    private javax.swing.JList<String> KatalogosList;
    private javax.swing.JLabel KatigoriaFagitouLabel;
    private javax.swing.JButton KiriosButton;
    private javax.swing.JButton KrasiButton;
    private javax.swing.JButton MpiraButton;
    private javax.swing.JButton NextOrder;
    private javax.swing.JButton OkeyButton;
    private javax.swing.JButton OrektikoButton;
    private javax.swing.JButton RemoveButton;
    private javax.swing.JButton SalataButton;
    private javax.swing.JLabel TableNumber;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
