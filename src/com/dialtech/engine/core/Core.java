package com.dialtech.engine.core;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.dialtech.engine.game.*;
import com.dialtech.engine.graphics.*;

public class Core extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	// Window components
	private JFrame frame;
	private Render screen;
	public Gamestate gs;
	
	// Window parameters
	public static int width = 240;
	public static int height = 160;
	public static int scale = 3;
	
	// Engine Core Components
	private Thread renderThread;
	private boolean running = false;
	
	// Graphical Components
	private BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	private int[] pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
	
	// Clock Components
	private long startTime, curTime, lastTime, delta, lastBenchmark, lastUpdate;
	private int renders, updates, updatesPerSecond;
	private final double NTM = 0.000001; 					// Decimal conversion for nano-to-milliseconds
	
	// Misc Variables
	int time = 0;
	
	
	public Core() {
		Dimension size = new Dimension(width * scale, height * scale);
		screen = new Render(width, height);
		gs = new Gamestate();
		gs.map.tiles[3 + 2 * 8].Highlight();
		setPreferredSize(size);
		updatesPerSecond = 60;
		windowInit();
		
		gs.entities.add(new Entity(32, 32, 16, 0, Color.RED));
		gs.entities.get(0).sprite.Shrink();
	}
	
	public static void main(String[] args) {
		Core core = new Core();
		core.start();
	}

	public void windowInit() {
		frame = new JFrame("Game");
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		running = true;
		renderThread = new Thread(this, "Render");
		renderThread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			renderThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		startTime = curTime = lastTime = System.nanoTime();
		lastUpdate = lastBenchmark = 0;
		while (running) {
			lastTime = curTime;
			curTime = System.nanoTime();
			delta = curTime - lastTime;
			lastUpdate += delta;
			if (lastUpdate >= 1000 / NTM / updatesPerSecond) {
				update();
				lastUpdate -= 1000 / NTM / updatesPerSecond;
			}
			render();
			
			lastBenchmark += delta;
			if (lastBenchmark >= 1000 / NTM) {
				frame.setTitle("Game - FPS: " + renders + " UPS: " + updates);
				renders = 0;
				updates = 0;
				lastBenchmark -= 1000 / NTM;
			}
		}
	}
	
	public void update() {
		updates++;
		time++;
		if (time % 10 == 0 && time < 120) {
			gs.entities.get(0).posX--;
		} else if (time % 10 == 0) {
			gs.entities.get(0).posX++;
		}
		
	}
	
	public void render() {
		renders++;
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.render();
		screen.renderTiles(gs);
		screen.renderEntities(gs);
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
			g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

}
