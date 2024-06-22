package com.gcstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class Tile {
	
	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0,0,16,16);

	public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(16,0,16,16);
	public static BufferedImage TILE_GATE = Game.spritesheet.getSprite(16*5,16*7,16,16);
	public static BufferedImage TILE_WALL_DIREITA = Game.spritesheet.getSprite(16,16*9,16,16);
	public static BufferedImage TILE_WALL_ESQUERDA = Game.spritesheet.getSprite(16*3,16*9,16,16);
	public static BufferedImage TILE_WALL_BAIXO = Game.spritesheet.getSprite(0,16*9,16,16);
	public static BufferedImage TILE_WALL_BAIXO_DIREITA = Game.spritesheet.getSprite(16*2,16*8,16,16);
	public static BufferedImage TILE_WALL_BAIXO_ESQUERDA = Game.spritesheet.getSprite(16*3,16*8,16,16);
	public static BufferedImage TILE_WALL_CIMA = Game.spritesheet.getSprite(16*2,16*9,16,16);
	public static BufferedImage TILE_WALL_CIMA_DIREITA = Game.spritesheet.getSprite(0,16*8,16,16);
	public static BufferedImage TILE_WALL_CIMA_ESQUERDA = Game.spritesheet.getSprite(16*4,16*8,16,16);
	public static BufferedImage TILE_WALL_BAIXO_CIMA = Game.spritesheet.getSprite(16*4,16*9,16,16);
	public static BufferedImage TILE_WALL_ESQUERDA_DIREITA = Game.spritesheet.getSprite(80, 144, 16, 16);
	public static BufferedImage TILE_WALL_BAIXO_CIMA_DIREITA = Game.spritesheet.getSprite(16*6, 16*7, 16, 16);
	public static BufferedImage TILE_WALL_BAIXO_CIMA_ESQUERDA = Game.spritesheet.getSprite(16*7, 16*7, 16, 16);
	public static BufferedImage TILE_WALL_ESQUERDA_DIREITA_CIMA = Game.spritesheet.getSprite(16*8, 16*7, 16, 16);
	public static BufferedImage TILE_WALL_ESQUERDA_DIREITA_BAIXO = Game.spritesheet.getSprite(16*9, 16*7, 16, 16);

	private BufferedImage sprite;
	private int x,y;
	
	public Tile(int x,int y,BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g){
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

}
