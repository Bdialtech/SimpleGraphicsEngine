package com.dialtech.engine.game;

import java.awt.Color;

public class Tile extends Entity{

	public static final int TILE_SIZE = 16;
	public boolean isOccupied, isSolid, isHazard, isSelected, isHovered;
	
	public Tile() {
		super(0,0,TILE_SIZE,0,Color.BLACK);
	}
	
	public Tile(int x, int y, Color c) {
		super(x, y, TILE_SIZE, 0, c);
	}

	public void Highlight() {
		for (int i = 0; i < TILE_SIZE; i++) {
			sprite.pixels[i] = Color.GREEN.getRGB();
			sprite.pixels[i*TILE_SIZE] = Color.GREEN.getRGB();
			sprite.pixels[i*TILE_SIZE + TILE_SIZE - 1] = Color.GREEN.getRGB();
			sprite.pixels[TILE_SIZE * TILE_SIZE - i - 1] = Color.GREEN.getRGB();
		}
	}
	
}
