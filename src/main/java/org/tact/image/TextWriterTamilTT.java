package org.tact.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextWriterTamilTT {

	public static void main(String arg[]) throws IOException, FontFormatException {
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("c:/test/Lohit-Tamil.ttf")));
		
		Font localFont = Font.createFont(Font.TRUETYPE_FONT, new File("c:/test/Lohit-Tamil.ttf"));
		localFont.getFontName();		
		System.out.println(localFont.getFontName());
		
        String key = "இவன் ரெம்ப நல்லவன் போல";
        
        BufferedImage bufferedImage = ImageIO.read(new File("c:/test/tt.jpg"));
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Lohit Tamil", Font.BOLD, 20));
        graphics.drawString(key, 160, 370);
        ImageIO.write(bufferedImage, "jpg", new File("C:/test/tt2.jpg"));
        System.out.println("Image Created");
    }
}
