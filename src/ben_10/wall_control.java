package ben_10;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class wall_control implements VisibleObjects {
MyCanvas canvas ;
wall[] w;
hero h ;
public int map[][];
public int sx ;
public int y ;
public wall_control(MyCanvas canvas,hero h) throws IOException {
	this.canvas = canvas;
	w = new wall[5];
	this.h = h;
	map = new int[canvas.max_width_wall][canvas.max_height_wall];
	build();
	init_map();
}
public void init_map() throws IOException {
	InputStream is = getClass().getResourceAsStream("/map/map.csv");
	BufferedReader br = new BufferedReader(new InputStreamReader(is));
	int row = 0 , col = 0;
	while(col < canvas.max_width_wall && row < canvas.max_height_wall) {
		String line = br.readLine();
		while(col < canvas.max_width_wall) {
			String numbers[] = line.split(",");
			int num  = Integer.parseInt(numbers[col]);
			map[col][row]= num;
			col++;
		}
		if(col == canvas.max_width_wall) {
			col = 0;
			row++;
		}
	}
	br.close();
}
public void build() throws IOException {
	w[0] = new wall();
	w[0].img = ImageIO.read(getClass().getResource("/images/wall.png"));
	w[1] = new wall();
	w[1].img =  ImageIO.read(getClass().getResource("/images/water_img.png"));
	w[2] = new wall();
	w[2].img =  ImageIO.read(getClass().getResource("/images/grass.png"));
	w[3] = new wall();
	w[3].img =  ImageIO.read(getClass().getResource("/images/boat.png"));
	w[4] = new wall();
	w[4].img =  ImageIO.read(getClass().getResource("/images/win.png"));
 }


@Override
public Graphics display(Graphics g) {
	// TODO Auto-generated method stub
	int row  = 0 , col = 0;
	while( col < canvas.max_width_wall && row < canvas.max_height_wall) {
		int key = map[col][row];
		 int x = col *canvas.wall_size;
		  y = row *canvas.wall_size;
		 sx = x - h.w_x + h.screen_x;
		
		if(key == 1) {
			
			g.drawImage(w[0].img,sx,y,50,50,null);
		}
		if(key == 2) {
			
			g.drawImage(w[1].img,sx,y,50,50,null);
		}
		if(key == 3) {
			
			g.drawImage(w[2].img,sx,y,50,50,null);
		}
		if(key == 4) {
			
			g.drawImage(w[3].img,sx,y,60,60,null);
		}
		if(key == 5) {
			
			g.drawImage(w[4].img,sx,y,50,50,null);
		}
		col++;
		
		if(col == canvas.max_width_wall) {
			col = 0;
			row++;
			y += canvas.wall_size;
		}
	}
	return g;
}
}
