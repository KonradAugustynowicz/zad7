import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PicturePanel extends JPanel{
    BufferedImage image;
    int r = 0;
    int g = 0;
    int b = 0;
    int[][][] pixels;
    InputWindow dialog;

    public PicturePanel() throws IOException {
        setSize(1000, 1000);
        image = ImageIO.read((getClass().getResource("kampus-PB-analiza-terenow-zielonych.png")));
        JLabel label = new JLabel("", new ImageIcon(image), 0);
        pixels = new int[image.getHeight()][image.getWidth()][3];
        dialog = new InputWindow();
    }

    public void displayImage(Graphics g) {
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void paintComponent(Graphics g) {
        this.displayImage(g);
    }

    public void loadPicture() {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int color = image.getRGB(i, j);
                Color c = new Color(color);
                b = color & 0xff;
                g = (color & 0xff00) >> 8;
                r = (color & 0xff0000) >> 16;

                pixels[j][i][0] = r;
                pixels[j][i][1] = g;
                pixels[j][i][2] = b;
                image.setRGB(i, j, c.getRGB());
            }
        }
    }

    public void binaryByInput(int r, int g, int b) {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int color = image.getRGB(i, j);
                this.b = color & 0xff;
                this.g = (color & 0xff00) >> 8;
                this.r = (color & 0xff0000) >> 16;
                if (this.r >= r - 20 && this.r <= r + 20  && this.g >= g - 20   && this.g <= g + 20   && this.b >= b - 20  && this.b <= b + 20 ) {
                    image.setRGB(i, j, Color.WHITE.getRGB());
                }else {
                    image.setRGB(i, j, Color.BLACK.getRGB());
                }
            }
        }
        Graphics graphics = getGraphics();
        graphics.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public Color validateColor(float red, float green, float blue) {
        if (red >= 255) {
            red = 255;
        } else if (red < 0) {
            red = 0;
        }
        if (green >= 255) {
            green = 255;
        } else if (green < 0) {
            green = 0;
        }
        if (blue >= 255) {
            blue = 255;
        } else if (blue < 0) {
            blue = 0;
        }
        return new Color((int) red, (int) green, (int) blue);
    }

    public String percentageOfColor(){
        long whiteCounter = 0;
        double resoult = 0;
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int color = image.getRGB(i, j);
                Color c = new Color(color);
                if (c.equals(Color.WHITE)){
                    whiteCounter++;
                }
            }
        }
        resoult = (double)whiteCounter / (double)(image.getWidth()*image.getHeight()) * 100;

        return "Obraz skalda sie w " + resoult + "% z wybranego koloru";
    }
}