package com.dialtech.engine.game;

import java.awt.Color;

public class Entity {

	public int posX, posY, size, layer;
	public Sprite sprite;
	
	public Entity(int x, int y, int s, int l, Color c) {
		posX = x;
		posY = y;
		size = s;
		layer = l;
		sprite = new Sprite(s, c);
	}
}
