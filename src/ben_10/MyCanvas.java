package ben_10;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyCanvas extends JPanel{
	List<VisibleObjects> objects = new ArrayList<VisibleObjects>();
	Image background=null;
	Dimension d1 = Toolkit.getDefaultToolkit().getScreenSize();
	public int  wall_size = 50;
	public int height =(int) d1.getHeight()+1;
	public int max_width_wall =126 ;
	public int max_height_wall = 18;
	public int width =  max_width_wall*wall_size;
	public MyCanvas() {
    background = new ImageIcon(getClass().getResource("/images/backgroud4.gif")).getImage();
	this.setPreferredSize(new Dimension(width,height));
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(background,0,0,this.getWidth(),this.getHeight(),null);
		for(VisibleObjects o : objects)
			o.display(g);
		
	}

}