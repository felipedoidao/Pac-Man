package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.gcstudios.main.Game;
import com.gcstudios.world.AStar;
import com.gcstudios.world.Vector2i;
import com.gcstudios.world.World;


public class Enemy extends Entity{

	public Enemy(int x, int y, int width, int height, int speed, BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
	}

	public void tick(){
		depth = 0;
		
		moveEnemy();

	}

	public void moveEnemy(){

		int randomDirectionX = new Random().nextInt((30 -(-30)) + 1) + (-30);
		int randomDirectionY = new Random().nextInt((30 -(-30)) + 1) + (-30);

		int endX = Game.player.getX() + randomDirectionX;
		int endY = Game.player.getY() + randomDirectionY;

		if(path == null || path.size() == 0) {
			Vector2i start = new Vector2i(((this.getX() / 16)), ((this.getY() / 16)));
			Vector2i end = new Vector2i((endX / 16), ((endY / 16)));
			path = AStar.findPath(Game.world, start, end);
		}
	    
		if(World.locked == false && Game.player.dead == false)
			followPath(path);
		
		if(x % 16 == 0 && y % 16 == 0) {
			if(new Random().nextInt(100) < 10) {
				Vector2i start = new Vector2i(((this.getX() / 16)), ((this.getY() / 16)));
				Vector2i end = new Vector2i(( endX / 16), (endY / 16));
				path = AStar.findPath(Game.world, start, end);
			}
		}

	}
	
	public void render(Graphics g) {
		super.render(g);
	}
	
	
}
