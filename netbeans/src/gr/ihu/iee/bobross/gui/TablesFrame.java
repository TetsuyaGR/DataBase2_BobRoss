package gr.ihu.iee.bobross.gui;

import gr.ihu.iee.bobross.controller.DatabaseController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import gr.ihu.iee.bobross.objects.BobTable;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static utils.Helpers.centreWindow;

public class TablesFrame extends JFrame {
    private JPanel panel;
    private DatabaseController db;
    private BobTable selectedLabel = null;
    private ImageIcon redTableIcon = new ImageIcon(getClass().getResource("/gui/table_red.png"));
    private ImageIcon greenTableIcon = new ImageIcon(getClass().getResource("/gui/table_green.png"));
    
    private class BobMouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent evt) {
            BobTable label = (BobTable) evt.getSource();
            if(selectedLabel != null) {
                selectedLabel.setBackground(null);
            }
            label.setBackground(Color.gray);
            selectedLabel = label;
        }
    }
    
    public TablesFrame() {
        List<BobTable> jtables = new ArrayList<>();
        HashMap<Integer, Integer> trapezia = null;
        
        BobMouseHandler mouseHandler = new BobMouseHandler();
        panel = new JPanel();
        db = new DatabaseController();
        panel.setLayout(new BorderLayout(25,25));
        JScrollPane scrollPane = new JScrollPane(new JPanel());
        panel.setPreferredSize(new Dimension(1280, 800));
        scrollPane.setPreferredSize(new Dimension(512, 280));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(50, 300, 50, 300));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        JPanel view = (JPanel) scrollPane.getViewport().getView();
        view.setLayout(new GridLayout(0,6,6,6));
        trapezia = db.getAllTrapezia();
        for(Map.Entry trapezi : trapezia.entrySet()) {
            BobTable table = new BobTable((int) trapezi.getKey(), (int) trapezi.getValue());
            table.setOpaque(true);
            table.setText(String.valueOf(table.getTableId()));
            if(table.isAvailable())
                table.setIcon(greenTableIcon);
            else
                table.setIcon(redTableIcon);
            table.setSize(64, 64);
            table.setBorder(BorderFactory.createLineBorder(Color.black));
            table.addMouseListener(mouseHandler);
            table.setHorizontalAlignment(JLabel.CENTER);
            table.setHorizontalTextPosition(JLabel.CENTER);
            table.setVerticalTextPosition(JLabel.TOP);
            table.setVisible(true);
            jtables.add(table);
            view.add(table);
            view.validate();
        }
        initFrameComponents(view, scrollPane);
        initFrame();
    }
    
    private void initFrameComponents(JPanel view, JScrollPane scrollPane) {
        Font buttonFont = new Font("Dialog", 0, 14);
        JLabel availableTables = new JLabel();
        availableTables.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        availableTables.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        availableTables.setText("BobRoss Restaurant Tables");
        availableTables.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        JButton orderButton = new JButton();
        orderButton.setFont(buttonFont);
        orderButton.setText("Order");
        orderButton.setPreferredSize(new Dimension(200, 30));
        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                OrderButtonActionPerformed(evt);
            }
        });
        JButton payButton = new JButton();
        payButton.setFont(buttonFont);
        payButton.setText("Pay");
        payButton.setPreferredSize(new Dimension(200,30));
        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                PayButtonActionPerformed(evt);
            }
        });
        JButton addButton = new JButton();
        addButton.setFont(buttonFont);
        addButton.setText("Add");
        addButton.setPreferredSize(new Dimension(200, 30));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new AddItemFrame(db).setVisible(true);
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
        buttonContainer.add(addButton);
        buttonContainer.setVisible(true);
        panel.add(buttonContainer, BorderLayout.SOUTH);
        panel.setVisible(true);
    }
    
    private void PayButtonActionPerformed(ActionEvent evt) {
        // TODO
    }
    
    private void OrderButtonActionPerformed(ActionEvent evt) {
        if(selectedLabel == null)
            return;
        new OrderFrame(selectedLabel, db).setVisible(true);
    }
    
    private void initFrame() {
        add(panel);
        setTitle("BobRoss Restaurant GUI");
        //setLocation(new java.awt.Point(500, 250));
        pack();
        centreWindow(this);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                db.close();
                System.exit(0);
            }
        });
    }
}
