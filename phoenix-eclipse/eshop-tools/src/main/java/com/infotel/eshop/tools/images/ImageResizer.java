package com.infotel.eshop.tools.images;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageResizer {
	public InputStream resize(InputStream imageStream, int width, int height) throws IOException {
		// Lecture image
		BufferedImage originalImage = ImageIO.read(imageStream);
		
		// redimensionnement
		BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		
		// lecture flux
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(resizedImage, "png", baos);
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		
		return is;
	}

}
