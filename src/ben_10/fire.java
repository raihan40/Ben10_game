package ben_10;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;



public class fire extends Thread implements VisibleObjects{
	MyCanvas canvas;
	hero h = null ;
	Image icon =null;
	int dir;
	Point p;
	fire(Point  p){
		this.p = p;
		icon = new ImageIcon(getClass().getResource("/images/fire.png")).getImage();
		start();
	}
	public Graphics display(Graphics g) {
		// TODO Auto-generated method stub
		 g.drawImage(icon,p.x,p.y,30,30,null);
		return g;
	}
	public void run() {

			while(p.x < 2000 ) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			 p.x += 1;
			}
	}
	
}
	
	

