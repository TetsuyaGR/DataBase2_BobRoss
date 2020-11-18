package utils;

import java.awt.Dimension;
import java.awt.Window;

public class Helpers {
    
    public static void centreWindow(Window frame) {
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

}
