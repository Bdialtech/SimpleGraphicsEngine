package com.dialtech.engine.graphics;

import com.dialtech.engine.game.Entity;
import com.dialtech.engine.game.Gamestate;
import com.dialtech.engine.game.Tile;

public class Render {

	private int width, height;
	public int[] pixels;
	
	public Render(int w, int h) {
		width = w;
		height = h;
		pixels = new int[width * height];
	}
	
	public void render() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x+y*width] = 0xff000000;
			}
		}
	}
	
	public void renderEntities(Gamestate gs) {
		for (Entity e : gs.entities) {
			int[] readPixels = e.sprite.pixels;
			int ex = e.posX;
			int ey = e.posY;
			int size = e.size;
			for (int py = 0; py < size; py++) {
				if (py + ey >= height || py + ey < 0) continue;
				for (int px = 0; px < size; px++) {
					if (px + ex >= width || px + ex < 0) continue;
					System.out.println(readPixels[px + py*size]);
					if (0==0) {
							pixels[(px + ex) + (py + ey)*width] = readPixels[px + py*size];
					}
				}
			}
		}
	}
	
	public void renderTiles(Gamestate gs) {
		for (Tile t : gs.map.tiles) {
			int[] readPixels = t.sprite.pixels;
			int ex = t.posX;
			int ey = t.posY;
			int size = t.size;
			for (int py = 0; py < size; py++) {
				if (py + ey >= height || py + ey < 0) continue;
				for (int px = 0; px < size; px++) {
					if (px + ex >= width || px + ex < 0) continue;
					pixels[(px + ex) + (py + ey)*width] = readPixels[px + py*size];
				}
			}
		}
	}
}
