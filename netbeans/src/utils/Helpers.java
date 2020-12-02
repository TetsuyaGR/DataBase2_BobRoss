package utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import javax.swing.JOptionPane;

public class Helpers {
    
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
    
    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static double getFormattedPrice(String s) {
        NumberFormat f = NumberFormat.getInstance(Locale.getDefault());
        Number number;
        try {
            s = s.replace(',', '.');
            number = f.parse(s);
            return number.doubleValue();
        }
        catch(ParseException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

}
