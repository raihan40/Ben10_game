package ben_10;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class jumping extends Thread {
    boolean jumping;
    private int jumpHeight;
    private int jumpDuration;
    private int jumpTimer;
    hero h = null;
    MyCanvas canvas = null;
    MyFame f = null;
	public jumping(hero h,MyCanvas canvas,MyFame f) {
		    this.f = f;
		    this.h = h;
		    this.canvas = canvas;
		    jumpHeight = 350;
	        jumpDuration = 50;
	        jumpTimer = 0;
		start();
		
	}
	public void run() {
		
			while(f.jump) {
			
            int y = h.screen_y;
            if (jumpTimer < jumpDuration / 2) {
                y -= jumpHeight / (jumpDuration / 2);
            }
           
            jumpTimer++;
            if (jumpTimer >= jumpDuration) {
                
                f.jump= false;

                jumpTimer = 0;
            }
           h.screen_y = y;
        	canvas.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
		}
	
	
}



