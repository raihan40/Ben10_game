package ben_10;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class hero extends JPanel implements VisibleObjects{

    Image icon =null,icon1=null;
	public Point p, map;
	public int screen_x , screen_y , w_x , w_y;
	public int live;
	MyCanvas canvas = null;
	String v;
	int flag = 0;
    Image[] sprites;
    int cur = 0;
	public hero(Point p,MyCanvas canvas,String v) {
		this.canvas = canvas;
		this.w_x = p.x;
		this.w_y = p.y;
		this.screen_x = this.w_x;
		this.screen_y = this.w_y;
		this.v = v;
		this.live= 5;
		//icon = new ImageIcon(getClass().getResource("/images/hero.png")).getImage();
		icon1 = new ImageIcon(getClass().getResource("/images/life.png")).getImage();
		sprites = new Image[8];
        for (int i = 0; i <8; i++) {
            sprites[i] =  new ImageIcon(getClass().getResource("/images/sprite"+i+".png")).getImage();
        }
		
	}
	public Graphics display(Graphics g) {
		// TODO Auto-generated method stub
		 super.paintComponent(g);
		 g.setColor(Color.white);
		 Font font = new Font("Arial", Font.BOLD, 20);
		 g.setFont(font);
		 g.drawString(v, screen_x, screen_y-20);
		 for(int i= 0 ; i < live ;i++) {
			 g.drawImage(icon1,screen_x+i*20,screen_y-20,10,10,null);
		 }
			 g.drawImage(sprites[cur],screen_x,screen_y,50,120,null);
			
			// cur++;
		
		return g;
	}


}
