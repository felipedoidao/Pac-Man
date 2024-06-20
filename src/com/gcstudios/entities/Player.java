package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.Camera;
import com.gcstudios.world.World;

public class Player extends Entity{
	
	public boolean right,up,left,down;
	public int hori_dir = 0, vert_dir = 0,  sprite_dir = 0;
	public boolean moved_hori = false, moved_vert = false;

	public BufferedImage sprite_left, sprite_up, sprite_down;

	public Player(int x, int y, int width, int height,int speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		sprite_left = Game.spritesheet.getSprite(16*2, 16*1, 16, 16);
		sprite_down = Game.spritesheet.getSprite(16*2, 16*2, 16, 16);
		sprite_up = Game.spritesheet.getSprite(16*2, 16*3, 16, 16);
	}
	
	public void tick(){
		depth = 1;

		movePlayer();
		pegarMoeda();

	}

	public void movePlayer(){

		if(hori_dir == 1 && World.isFree((int)(x+speed),this.getY())) {
			moved_hori = true;
			x+=speed;
			sprite_dir = 1;
			

		}
		else if(hori_dir == -1 && World.isFree((int)(x-speed),this.getY())) {
			moved_hori = true;
			x-=speed;
			sprite_dir = -1;

		}
		if(vert_dir == -2 && World.isFree(this.getX(),(int)(y-speed))){
			moved_vert = true;
			y-=speed;
			sprite_dir = -2;

		}
		else if(vert_dir == 2 && World.isFree(this.getX(),(int)(y+speed))){
			moved_vert = true;
			y+=speed;
			sprite_dir = 2;

		}
	}

	public void pegarMoeda(){
		for(int i=0; i < Game.entities.size(); i++){
			Entity current = Game.entities.get(i);
			if(current instanceof Moeda){
				if(Entity.isColidding(this, current)){
					Game.entities.remove(i);
					Game.pontos++;
					Game.num_moedas--;
					return;
				}
			}
		}
	}

	public void render(Graphics g){
		if(sprite_dir == 1 || sprite_dir == 0){
			super.render(g);

		}else if(sprite_dir == -1){
			g.drawImage(sprite_left,this.getX() - Camera.x,this.getY() - Camera.y,null);

		}
		else if(sprite_dir == -2){
			g.drawImage(sprite_up,this.getX() - Camera.x,this.getY() - Camera.y,null);

		}
		else if(sprite_dir == 2){
			g.drawImage(sprite_down,this.getX() - Camera.x,this.getY() - Camera.y,null);

		}
	}


}
