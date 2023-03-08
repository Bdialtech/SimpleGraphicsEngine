package com.dialtech.engine.game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Sprite {
	
	public int size;
	public BufferedImage img;
	public int[] pixels;
	
	// Test Constructor
	public Sprite(int s, Color c) {
		size = s;
		img = new BufferedImage(s, s, BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = c.getRGB();
		}
	}
	
	public void Color(Color c) {
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
		for (int i : pixels) {
			i = c.getRGB();
		}
	}
	
	public void Shrink() {
		for (int i = 0; i < size; i++) {
			pixels[i] = 0;
			pixels[i*size] = 0;
			pixels[i*size + size - 1] = 0;
			pixels[size * size - i - 1] = 0;
		}
	}
}
