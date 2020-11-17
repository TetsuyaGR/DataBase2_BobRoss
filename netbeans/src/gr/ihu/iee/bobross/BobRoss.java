package gr.ihu.iee.bobross;

import gr.ihu.iee.bobross.controller.DatabaseController;
import gr.ihu.iee.bobross.gui.TablesFrame;
import static javax.swing.SwingUtilities.invokeLater;

public class BobRoss {

    public static void main(String args[]) {
       try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TablesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Create and display the form
        invokeLater(new Runnable() {
            @Override
            public void run() {
                new TablesFrame();
            }
        });
    }
    
}
