package ben_10;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class villain extends Thread implements VisibleObjects {
	int count = 0, step_count = 0;
	public Point p;
	Image icon = null;
	wall_control wc;
    int choice = 0;
    MyCanvas canvas =null;
    hero h = null;
    int fire_flag  = 0;
	public villain(Point p, wall_control wc , int choice,MyCanvas canvas,hero h) {
		this.choice = choice;
		this.p = p;
		this.wc = wc;
		this.canvas = canvas;

		icon = new ImageIcon(getClass().getResource("/images/villain.png")).getImage();
	
		start();
		
	}

	public Graphics display(Graphics g) {
		g.drawImage(icon, p.x, p.y, 50, 50, null);
		return g;
	}

	public void run() {
		int flag = 0 , flag1  = 0;
	//	fire();
	while(true) {
		  if(choice == 1) {
		   if( flag == 0 ) {
			   p.x += 2;
			   flag  =1;
			   try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			   
		   }
		   else {
			   if( this.p.x > 1) {
			   flag = 0;
			   this.p.x -= 2;
			   try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			   }
		   }
			  
			   
		   }
		  if (choice == 2) {
		   if( flag1 == 0 && choice == 2 ) {
			   p.y-= 20;
			   flag1  =1;
			   try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			   
		   }
		   else {
			  
			   flag1 = 0;
			   p.y+= 20;
			   try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			   }
			  
			   
		   }
	}
	}





}
	

	
