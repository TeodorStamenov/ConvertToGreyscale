package simple;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class ConvertToGreyscale {

	public static void main(String[] args) {
		System.out.print("Enter a path to the image: ");
		Scanner scan = new Scanner(System.in);
		String path = scan.nextLine().toString();
		BufferedImage img = null;
		File file = null;
		String dirName;
		String fileName;
		scan.close();
		
		//open image
		try {
			file = new File(path);
			img = ImageIO.read(file);
		}catch(IOException e) {
			System.out.println(e);
		}
		
		int width = img.getWidth();
		int height = img.getHeight();
		
		//convert to greyscale
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int pixel = img.getRGB(x, y);
				
				int alpha = (pixel >> 24) & 0xff;
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				int average = (red + green + blue) / 3;
				
				pixel = (alpha << 24) | (average << 16) | (average << 8) | average;
			
				img.setRGB(x, y, pixel);
			}
		}
		
		//write image the new image in same directory with different name
		try {
			dirName = file.getParent();
			fileName = file.getName();
			fileName = "\\Greyscale" + fileName;
			dirName = dirName + fileName;
			file = new File(dirName);
			ImageIO.write(img, "jpg", file);
			System.out.println("File is saved in: " + file.getParent());
			System.out.println("Output file name is: " + file.getName());
		}catch (IOException e) {
			System.out.println(e);
		}
	}
}
