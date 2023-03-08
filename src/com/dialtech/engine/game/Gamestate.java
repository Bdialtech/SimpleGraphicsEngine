package com.dialtech.engine.game;

import java.util.ArrayList;

public class Gamestate {

	public ArrayList<Entity> entities;
	public Floormap map;
	
	public Gamestate() {
		entities = new ArrayList<Entity>();
		map = new Floormap();
	}
	
	
}
