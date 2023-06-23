package ben_10;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
public class MyFame extends JFrame implements ActionListener,KeyListener{
	 public  int track_x=1;
	 boolean jump = false;
	 Dimension d1 = Toolkit.getDefaultToolkit().getScreenSize();
	 MyCanvas canvas = new MyCanvas();
	 String v = JOptionPane.showInputDialog("Enter your name:");
	 hero h = new hero(new Point(50,0),canvas,v);
	 wall_control wc =  new wall_control(canvas,h);
	 ArrayList<villain> vi_arr = new ArrayList<>();
	 ArrayList<fire> f_arr = new ArrayList<>();
	 ArrayList<v_fire> fi_arr = new ArrayList<>();	
	 public MyFame() throws IOException {
		setTitle("Ben 10");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(canvas);
		addKeyListener(this);
		canvas.objects.add(h);
		canvas.objects.add(wc);
		MyAudioPlayer ad  = new MyAudioPlayer();
		ad.play("bgm", true);
		free_fall();
		villian_init();
		hero_attack();
		villain_attack();
		win();
		Timer timer = new Timer(10, this);
		timer.start();
	}
	 public void win() {
		 Timer timerCollision = new Timer(50, new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if(h.w_x == 6150 && h.screen_y >= 595  && h.screen_y <=615) {
					  Icon icon =  new ImageIcon("/images/win.png");
					  JOptionPane.showMessageDialog(null, v+", YOU WON!", "RESULT", JOptionPane.INFORMATION_MESSAGE, icon);
					 System.exit(0);
			 }
		  }

		});
		timerCollision.start();
	 }
	 public void fire() {
			Timer timerCollision = new Timer(900, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					for(int i = 0 ;i< vi_arr.size();i++) {
					if(vi_arr.get(i).p.x - h.screen_x <=350 && vi_arr.get(i).p.x  - h.screen_y  <= 350) {
					int x = vi_arr.get(i).p.x+70;
					int y =  vi_arr.get(i).p.y;
					v_fire temp = new v_fire(new Point(x,y));
					fi_arr.add(temp);
					canvas.objects.add(temp);
				}
					}
					 try {
							Thread.sleep(100);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
				}
				
				});
			timerCollision.start();
		   
	   }
	 public void villain_attack() {
			Timer timerCollision = new Timer(10, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
						int fx = h.screen_x-25;
						int fy = h.screen_y;
						int fx1 = h.screen_x+50;
						int fy1 = h.screen_y+ 50;
						int j = fi_arr.size();
						while (j != 0) {
							v_fire Vtemp = fi_arr.get(j - 1);  
							int Vx = Vtemp.p.x;
							int Vy = Vtemp.p.y;
							int Vx1 = Vx+30;
							int Vy1 = Vy+30;
							if (fx < Vx && Vx < fx1  ) {
								if(fy < Vy && Vy < fy1) {
									canvas.objects.remove(Vtemp);
									fi_arr.remove(fi_arr.size()-1);
									if(h.live >=1) {
										h.live = h.live - 1;
										MyAudioPlayer ad  = new MyAudioPlayer();
										ad.play("hurt", false);
									 try {
											Thread.sleep(100);
										 } catch (InterruptedException e1) {
											e1.printStackTrace();
										 }
									try {
										live_game_over();
										} catch (LineUnavailableException | IOException e1) {
										e1.printStackTrace();
										}
									}
								
								} 
							}
							j--;
						
					}
				}

			});
			timerCollision.start();
		}


	 public boolean right_checkCollision() {
	
		 int width1 = track_x+ 1;
	     int height1 = (h.screen_y / 50);
		 if(width1 < 126 && wc.map[width1][height1]==1) {
			 return true;
		     }
			 return false;
		}
	 
	 public boolean left_checkCollision() {
			 int width1 = track_x - 1;
		      int height1 = (h.screen_y / 50);
		        if(width1 >= 0 && wc.map[width1][height1]==1) {
		        	return true;
		        }
				return false;
			}	
	 
	 public boolean up_checkCollision() {
			 int width1 = track_x;
		     int height1 = (h.screen_y / 50)-1;
		      if(height1 >= 0 && wc.map[width1][height1]==1) {
		        	return true;
		        }
				return false;
			}
	 
	 public boolean down_checkCollision() {
			 int width1 = track_x;
		     int height1 = (h.screen_y / 50)+2;
		     if(height1 < 18 && wc.map[width1][height1]==1) {
		        	return true;
		        }
				return false;
			}
	 public void villian_init() {
		
		 int x1 = 450;
		 int y1 = 500;
		 int x2 = 1100;
		 int y2 = 650;
		 int x3 = 750;
		 int y3 = 400;
		 int x4 = 950;
		 int y4 = 200;
		 int x5 = 1300;
		 int y5 = 400;
		 villain temp ;
		 Point p = null ;
		 for(int i = 0 ;i < 4 ;i++) {
			 temp = new villain((new Point(x1,y1)),wc,(int)(Math.random()*(2-1+1)+1),canvas,h);
			 vi_arr.add(temp);
		
			 canvas.objects.add(temp);
			 temp = new villain((new Point(x2,y2)),wc,(int)(Math.random()*(2-1+1)+1),canvas,h);
			 vi_arr.add(temp);
			 
			
			 canvas.objects.add(temp);
			 temp = new villain((new Point(x3,y3)),wc,(int)(Math.random()*(2-1+1)+1),canvas,h);
			 vi_arr.add(temp);
			
			 canvas.objects.add(temp);
			 temp = new villain((new Point(x4,y4)),wc,(int)(Math.random()*(2-1+1)+1),canvas,h);
			 vi_arr.add(temp);
			 
		
			 canvas.objects.add(temp);
			 temp = new villain((new Point(x5,y5)),wc,(int)(Math.random()*(2-1+1)+1),canvas,h);
			 vi_arr.add(temp);
			
			
			 canvas.objects.add(temp);
			 x1 =1600+ x1;
			 x2 = 1600 +x2;
			 x3 = 1600+x3;
			 x4 = 1600+x4;
			 x5 = 1600+x5;
		 }
		fire(); 
	 }
	public void free_fall() {
			Timer timerCollision = new Timer(50, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 int width1 = track_x;
				      int height1 = (h.screen_y / 50)+2;
				      if(wc.map[width1][height1]!= 1) {
				            h.screen_y+=20;
				         }
				      else {
				    	  h.screen_y -=4;
				      }
			
				      
				        if(height1 > 14) {
				        	MyAudioPlayer ad  = new MyAudioPlayer();
				    		ad.play("water", false);
				    		try {
								fall_game_over();
							} catch (LineUnavailableException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				        }
				}
		
			});
			timerCollision.start();
}

	
	 public void fall_game_over() throws LineUnavailableException, IOException {
		  Icon icon = null;
		  JOptionPane.showMessageDialog(null,"YOU LOSE!", "RESULT", JOptionPane.INFORMATION_MESSAGE, icon);
		  System.exit(0);
        }
	 public void live_game_over() throws LineUnavailableException, IOException {
		 if(h.live==0 ) {
			 canvas.objects.remove(h);
			  Icon icon =  null;
			  JOptionPane.showMessageDialog(null,"YOU LOSE!", "RESULT", JOptionPane.INFORMATION_MESSAGE, icon);
		     System.exit(0);
       }
	 }
	 
	/*public static void main(String[] args) throws IOException {

		MyFame f = new MyFame();
	    f.setSize(600, 600);
		f.setVisible(true);
	}*/
	  public static void showMainForm() {
	        MyFame f;
	        try {
	            f = new MyFame();
	            f.setSize(600, 600);
	            f.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public static void main(String[] args) {
	        SplashScreen splash = new SplashScreen(7000);
	        splash.showSplash();
	        showMainForm();
	    }


	
	public void actionPerformed(ActionEvent e) {
		canvas.repaint();
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		MyAudioPlayer ad  = new MyAudioPlayer();
        if (keyCode == KeyEvent.VK_DOWN ) {
        	
        } 
       int x = (int)h.screen_x/50;
       int y = (int)h.screen_y/50 ;
       int key =  wc.map[x][y];
       villain temp = null;
        if (!left_checkCollision()&&keyCode == KeyEvent.VK_LEFT ) {
 
            h.w_x = h.w_x - 50 ;
            h.cur = Math.abs((h.cur - 1) %6);
            h.screen_x -= 1;
            
           for(int i = 0 ; i < vi_arr.size() ;i++) {
        	   temp = vi_arr.get(i);
        	    temp.p.x +=50;
           }
            
            track_x--;
        		 
		}
        if ( !right_checkCollision() &&keyCode == KeyEvent.VK_RIGHT  ) {
        	   h.screen_x += 1;
        	   h.cur = (h.cur + 1) % 6;
               h.w_x += 50 ;
               for(int i = 0 ; i < vi_arr.size() ;i++) {
            	   temp = vi_arr.get(i);
            	   if(temp.p.x - 50 > 0) {
            	   temp.p.x -= 50;
            	   }
            	   else {
            		   canvas.objects.remove(temp);
            	   }
               }
               track_x++;
        		 
        }
       
      
	}



	public void hero_attack() {
		Timer timerCollision = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = f_arr.size();
				while (i != 0) {
					fire Ftemp = f_arr.get(i - 1);
					int fx = Ftemp.p.x;
					int fy = Ftemp.p.y;
					int j = vi_arr.size();
					while (j != 0) {
						villain Vtemp = vi_arr.get(j - 1);  
						int Vx = Vtemp.p.x;
						int Vy = Vtemp.p.y-25;
						int Vx1 = Vx+70;
						int Vy1 = Vy+70;
						if ((fx > Vx && fx< Vx1 )) {
							if(fy > Vy && fy < Vy1 ) {
								MyAudioPlayer ad  = new MyAudioPlayer();
					    		ad.play("kill", false);
					    		canvas.objects.remove(Vtemp);
					    		canvas.objects.remove(Ftemp);
								vi_arr.remove(j - 1);
								f_arr.remove(i-1);
							} 
						}
						j--;
					}
					i--;
				}
			}

		});
		timerCollision.start();
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP && !up_checkCollision()) {
        	h.cur = 0;
        	MyAudioPlayer ad  = new MyAudioPlayer();
        	if(this.jump == false) {
    		ad.play("jump", false);
    		this.jump = true;
        	new jumping(h,canvas,this);
}
        }
        if( keyCode == KeyEvent.VK_SPACE) {
         	MyAudioPlayer ad  = new MyAudioPlayer();
    		ad.play("fire", false);
    		h.cur = 7;
        	fire f = new fire(new Point(h.screen_x+25,h.screen_y+50));
        	f_arr.add(f);
        	canvas.objects.add(f);
        }
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}