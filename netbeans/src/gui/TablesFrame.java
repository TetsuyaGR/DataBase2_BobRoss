package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TablesFrame extends JFrame {
    private int tables = 100;
    private JPanel panel;
    private JLabel selectedLabel = null;
    
    private class BobMouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent evt) {
            JLabel label = (JLabel) evt.getSource();
            if(selectedLabel != null) {
                selectedLabel.setBackground(null);
                selectedLabel.setForeground(null);
            }
            label.setBackground(Color.GREEN);
            label.setForeground(Color.GREEN);
            selectedLabel = label;
            System.out.println(label.getText());
        }
    }
    
    public TablesFrame() {
        //initComponents();
        JLabel[] jtables = new JLabel[tables];
        Font buttonFont = new Font("Dialog", 0, 14);
        BobMouseHandler mouseHandler = new BobMouseHandler();
        panel = new JPanel();
        panel.setLayout(new BorderLayout(25,25));
        JScrollPane scrollPane = new JScrollPane(new JPanel());
        panel.setPreferredSize(new Dimension(1280, 800));
        scrollPane.setPreferredSize(new Dimension(512, 280));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(50, 300, 50, 300));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        JPanel view = (JPanel) scrollPane.getViewport().getView();
        view.setLayout(new GridLayout(0,6,6,6));
        for(int i = 0; i < tables;i++) {
            jtables[i] = new JLabel();
            jtables[i].setText(String.valueOf(i+1));
            jtables[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/table_red.png")));
            jtables[i].setSize(64, 64);
            jtables[i].setBorder(BorderFactory.createLineBorder(Color.black));
            jtables[i].addMouseListener(mouseHandler);
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
        availableTables.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        JButton orderButton = new JButton();
        orderButton.setFont(buttonFont);
        orderButton.setText("Order");
        orderButton.setPreferredSize(new Dimension(200, 30));
        orderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                OrderButtonActionPerformed(evt);
            }
        });
        JButton payButton = new JButton();
        payButton.setFont(buttonFont);
        payButton.setText("Pay");
        payButton.setPreferredSize(new Dimension(200,30));
        payButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                PayButtonActionPerformed(evt);
            }
        });
        view.setVisible(true);
        scrollPane.setVisible(true);
        //panel.add(Box.createVerticalStrut(100));
        panel.add(availableTables, BorderLayout.NORTH);
        //panel.add(Box.createHorizontalStrut(200));
        panel.add(scrollPane, BorderLayout.CENTER);
        //panel.add(Box.createVerticalStrut(50));
        JPanel buttonContainer = new JPanel();
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        buttonContainer.add(orderButton);
        buttonContainer.add(payButton);
        buttonContainer.setVisible(true);
        panel.add(buttonContainer, BorderLayout.SOUTH);
        panel.setVisible(true);
        
        initComponents();
        //System.out.println(view.getPreferredSize().height + ", " + view.getPreferredSize().width);
    }
    
    private void PayButtonActionPerformed(ActionEvent evt) {
        // TODO
    }
    
    private void OrderButtonActionPerformed(ActionEvent evt) {
        new OrderFrame(selectedLabel).setVisible(true);
    }
    
    private void initComponents() {
        add(panel);
        setTitle("BobRoss Restaurant GUI");
        //setLocation(new java.awt.Point(500, 250));
        pack();
        centreWindow(this);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void centreWindow(Window frame) {
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
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
