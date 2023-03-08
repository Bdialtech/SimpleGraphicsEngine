package com.dialtech.engine.game;

import java.awt.Color;

public class Floormap {

	// Tailing two ints are map size x and map size y
	static int[] exampleMap = {0,0,0,0,0,0,0,0,
							   0,2,2,2,2,2,2,0,
							   0,2,1,1,1,1,2,0,
							   0,2,1,1,1,1,2,0,
							   0,2,1,1,1,1,2,0,
							   0,2,2,2,2,2,2,0,
							   0,0,0,0,0,0,0,0,8,7};
	
	public Tile[] tiles;
	public int mapSizeX, mapSizeY;
	
	// Test Constructor
	public Floormap(){
		mapSizeX = exampleMap[exampleMap.length -2];
		mapSizeY = exampleMap[exampleMap.length -1];
		tiles = new Tile[exampleMap.length -2];
		for (int i = 0; i < exampleMap.length - 2; i++) {
			switch(exampleMap[i]) {
			case 1:
				tiles[i] = new Tile((i % mapSizeX) * Tile.TILE_SIZE, (i / mapSizeX) * Tile.TILE_SIZE, Color.WHITE);
				break;
			case 2:
				tiles[i] = new Tile((i % mapSizeX) * Tile.TILE_SIZE, (i / mapSizeX) * Tile.TILE_SIZE, Color.GRAY);
				tiles[i].isSolid = true;
				break;
			default:
				tiles[i] = new Tile((i % mapSizeX) * Tile.TILE_SIZE, (i / mapSizeX) * Tile.TILE_SIZE, Color.BLACK);
				break;
			}
		}
	}
	
	// Default Constructor
	public Floormap(int[] data) {
		
	}
	
}
