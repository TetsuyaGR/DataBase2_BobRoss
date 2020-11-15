package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TablesFrame extends JFrame {
    private int tables = 20;
    private JPanel panel;
    
    public TablesFrame() {
        //initComponents();
        JLabel[] jtables = new JLabel[tables];
        panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(new JPanel());
        panel.setPreferredSize(new Dimension(640, 480));
        scrollPane.setPreferredSize(new Dimension(512, 280));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        JPanel view = (JPanel) scrollPane.getViewport().getView();
        view.setLayout(new GridLayout(0,6,6,6));
        for(int i = 0; i < tables;i++) {
            jtables[i] = new JLabel();
            jtables[i].setText(String.valueOf(i+1));
            jtables[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/table_red.png")));
            jtables[i].setSize(64, 64);
            jtables[i].setBorder(BorderFactory.createLineBorder(Color.black));

            jtables[i].setHorizontalAlignment(JButton.CENTER);
            jtables[i].setHorizontalTextPosition(JButton.CENTER);
            jtables[i].setVerticalTextPosition(JButton.TOP);
            
            jtables[i].setVisible(true);
            view.add(jtables[i]);
            view.validate();
        }
        JLabel availableTables = new JLabel();
        availableTables.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        availableTables.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        availableTables.setText("Available Tables");
        
        /*JButton payButton = new JButton();
        payButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        payButton.setText("Pay");
        payButton.setPreferredSize(new Dimension(200,30));*/
        
        view.setVisible(true);
        scrollPane.setVisible(true);
        panel.add(Box.createVerticalStrut(100));
        panel.add(availableTables);
        panel.add(scrollPane);
        //panel.add(Box.createVerticalStrut(50));
        //panel.add(payButton);
        panel.setVisible(true);
        
        initComponents();
        //System.out.println(view.getPreferredSize().height + ", " + view.getPreferredSize().width);
    }
    
    private void initComponents() {
        add(panel);
        setTitle("BobRoss Restaurant GUI");
        setLocation(new java.awt.Point(500, 250));
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
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
            java.util.logging.Logger.getLogger(TablesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TablesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TablesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TablesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TablesFrame().setVisible(true);
            }
        });
    }
}
