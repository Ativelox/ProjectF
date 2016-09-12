package de.cormag.projectf.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class Utils {

	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(Utils.class.getResource("/worlds/" + path).openStream()));
			String line;
			while ((line = br.readLine()) != null)
				builder.append(line + "\n");

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return builder.toString();
	}

	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static void openNotificationWindow(Graphics g, String content) {

		g.setFont(new Font(Font.DIALOG_INPUT, 1, 30));
		g.setColor(new Color(255, 255, 255, 120));
		g.fillRoundRect(780, 20, 200, 85, 20, 20);
		g.setColor(Color.BLACK);
		g.drawString(content, 800, 70);

	}

	public static double roundToDecimals(double numberToRound, int scale) {

		return new BigDecimal(numberToRound).setScale(scale, BigDecimal.ROUND_FLOOR).doubleValue();

	}
	
	public static Point2D differenceVector(Point2D src, Point2D dest){
		double srcX = src.getX();
		double srcY = src.getY();
		double destX = dest.getX();
		double destY = dest.getY();
		
		return  new Point2D.Double(destX - srcX, destY - srcY);
	}
	
	public static double lengthVector(Point2D vector){
		double vectorX = vector.getX();
		double vectorY = vector.getY();
		
		return Math.sqrt(Math.pow(vectorX, 2) + Math.pow(vectorY, 2));
	}
	
	public static Point2D multiplyScalarVector(Point2D vector, double scalar){
		
		return new Point2D.Double((1 / scalar) * vector.getX(), (1 / scalar) * vector.getY()); 
	}
	

	public static Point2D normalizeVector(Point2D src, Point2D dest){
		Point2D differenceVector = differenceVector(src, dest);
		double length = lengthVector(differenceVector);
		Point2D normalizedVector = multiplyScalarVector(differenceVector, length);
		
		return normalizedVector;
	}
}