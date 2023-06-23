package background;

import java.io.FileWriter;
import java.io.IOException;

import ben_10.MyCanvas;

public class CreateZerosCSV extends MyCanvas {
static int row = new MyCanvas().max_height_wall;
static int col = new MyCanvas().max_width_wall;
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("zeros.csv");
            for (int i = 0; i <row; i++) {
                for (int j = 0; j < col; j++) {
                    writer.append("0");
                    if (j != col - 1) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }
            writer.close();
            System.out.println("CSV file created successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
