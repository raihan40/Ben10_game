package ben_10;

import java.awt.*;
import javax.swing.*;

public class SplashScreen extends JWindow {
    private final int duration;
    ImageIcon background=null;
    public SplashScreen(int duration) {
        this.duration = duration;
    }

    public void showSplash() {
    	 JPanel content = (JPanel) getContentPane();
         //content.setBackground(Color.white);

        // Set the window's bounds, centering the window
        int width = 800;
        int height = 600;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        // Build the splash screen
        System.out.print("hiiii");
        background = new ImageIcon(getClass().getResource("/images/loading.gif"));
       
        System.out.print("hiiii");
        JLabel splashLabel = new JLabel(background);
       

        content.add(splashLabel, BorderLayout.CENTER);

        setVisible(true);

        // Wait for the specified duration
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setVisible(false);
    }

	public void showMainForm() {
		// TODO Auto-generated method stub
		
	}

}
