package gui;

import java.awt.*;
import javax.swing.*;

public class Application {

    /**
     * Construct and show the application.
     */
    public Application() {
        MainFrame frame = new MainFrame();
        // Validate frames that have preset sizes
        frame.validate();

        // Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width - 1000) / 2,
                (screenSize.height - 700) / 2);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Application entry point.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Application();
            }
        });
    }
}
