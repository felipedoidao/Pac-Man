package com.gcstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.gcstudios.entities.Enemy;
import com.gcstudios.entities.Entity;
import com.gcstudios.entities.Moeda;
import com.gcstudios.main.Game;

public class World {

	public static Tile[] tiles;
	public static int WIDTH,HEIGHT;
	public static final int TILE_SIZE = 16;
	
	
	public World(String path){

		try {

			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(),pixels, 0, map.getWidth());

			for(int xx = 0; xx < map.getWidth(); xx++){
				for(int yy = 0; yy < map.getHeight(); yy++){

					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					if(pixelAtual == 0xFF000000){
						//Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);

					}else if(pixelAtual == 0xFFFFFFFF){
						// Variáveis para verificar paredes adjacentes
						boolean paredeDireita = false;
						boolean paredeEsquerda = false;
						boolean paredeCima = false;
						boolean paredeBaixo = false;
							
						// Verificar a parede à direita
						if (xx + 1 < map.getWidth() && map.getRGB(xx + 1, yy) == 0xFFFFFFFF) {
							paredeDireita = true;
						}
							
						// Verificar a parede à esquerda
						if (xx - 1 >= 0 && map.getRGB(xx - 1, yy) == 0xFFFFFFFF) {
							paredeEsquerda = true;
						}
							
						// Verificar a parede acima
						if (yy - 1 >= 0 && map.getRGB(xx, yy - 1) == 0xFFFFFFFF) {
							paredeCima = true;
						}
							
						// Verificar a parede abaixo
						if (yy + 1 < map.getHeight() && map.getRGB(xx, yy + 1) == 0xFFFFFFFF) {
							paredeBaixo = true;
						}
							
						// Escolher o sprite baseado nas paredes adjacentes
						if(paredeBaixo && paredeCima && paredeDireita){
							tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL_BAIXO_CIMA_DIREITA);

						} else if(paredeBaixo && paredeCima && paredeEsquerda){
							tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL_BAIXO_CIMA_ESQUERDA);

						} else if(paredeEsquerda && paredeDireita && paredeCima){
							tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL_ESQUERDA_DIREITA_CIMA);
						
						} else if(paredeEsquerda && paredeDireita && paredeBaixo){
							tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL_ESQUERDA_DIREITA_BAIXO);
						
						} else if (paredeCima && paredeBaixo) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL_BAIXO_CIMA);

						} else if (paredeEsquerda && paredeDireita) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL_ESQUERDA_DIREITA);

						} else if (paredeCima && paredeDireita) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL_CIMA_DIREITA);

						} else if (paredeCima && paredeEsquerda) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL_CIMA_ESQUERDA);

						} else if (paredeBaixo && paredeDireita) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL_BAIXO_DIREITA);

						} else if (paredeBaixo && paredeEsquerda) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL_BAIXO_ESQUERDA);

						} else if (paredeCima) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL_CIMA);

						} else if (paredeBaixo) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL_BAIXO);

						} else if (paredeDireita) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL_DIREITA);

						} else if (paredeEsquerda) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL_ESQUERDA);

						} else {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL);

						}

					}else if(pixelAtual == 0xFF0026FF) {
						//Player
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);

					}else if(pixelAtual == 0xFFFF0000) {
						//Instanciar inimigo e adicionar a lista das entities
						Enemy enemy = new Enemy(xx*16, yy*16, 16, 16, 1, Entity.ENEMY);
						Game.entities.add(enemy);

					}else if(pixelAtual == 0xFFFFE900) {
						Moeda moeda = new Moeda(xx*16, yy*16, 16, 16, 0, Entity.MOEDA);
						Game.entities.add(moeda);
						Game.num_moedas++;

					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int xnext,int ynext){
		
		int x1 = (xnext) / TILE_SIZE;
		int y1 = (ynext) / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y2 = (ynext) / TILE_SIZE;
		
		int x3 = (xnext) / TILE_SIZE;
		int y3 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
	}
	
	public static void restartGame(String level){
		new Game();
		return;
	}
	
	public void render(Graphics g){
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
	
}
