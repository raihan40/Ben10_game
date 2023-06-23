package ben_10;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class v_fire extends Thread implements VisibleObjects {
	public Point p;
	Image icon = null;
	public v_fire (Point p) {
		start();
		this.p = p;
		icon = new ImageIcon(getClass().getResource("/images/v_fire.png")).getImage();

	}

	public Graphics display(Graphics g) {
		g.drawImage(icon, p.x, p.y, 30, 30, null);
		return g;
	}

	public void run() {
		while(p.x > -100 ) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 p.x -= 1;
		}
	}

	
}
	

	
