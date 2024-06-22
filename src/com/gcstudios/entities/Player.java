package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.World;

public class Player extends Entity{
	
	public boolean right,up,left,down;
	public int hori_dir = 0, vert_dir = 0,  sprite_dir = 0;

	public int desiredHoriDir = 0;
    public int desiredVertDir = 0;

	public boolean dead = false;

	public BufferedImage sprite_left, sprite_up, sprite_down;

	private int frames = 0, maxFrames = 5, index = 0, maxIndex = 1;
	private int deadFrames = 0, maxdeadFrames = 10, deadIndex = 0, maxdeadIndex = 5;

	private BufferedImage[] rightPlayer;
    private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
    private BufferedImage[] downPlayer;
	private BufferedImage[] playerDeath;

	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		sprite_left = Game.spritesheet.getSprite(16*2, 16*1, 16, 16);
		sprite_down = Game.spritesheet.getSprite(16*2, 16*2, 16, 16);
		sprite_up = Game.spritesheet.getSprite(16*2, 16*3, 16, 16);

		rightPlayer = new BufferedImage[2];
		leftPlayer = new BufferedImage[2];
		upPlayer = new BufferedImage[2];
		downPlayer = new BufferedImage[2];
		playerDeath = new BufferedImage[6];

		for(int i=0; i<=1; i++){
            rightPlayer[i] = Game.spritesheet.getSprite(16*2 + (i*16), 0, 16, 16);
        }
        for(int i=0; i<=1; i++){
            leftPlayer[i] = Game.spritesheet.getSprite(16*2 + (i*16), 16, 16, 16);
        }
		for(int i=0; i<=1; i++){
            upPlayer[i] = Game.spritesheet.getSprite(16*2 + (i*16), 48, 16, 16);
        }
        for(int i=0; i<=1; i++){
            downPlayer[i] = Game.spritesheet.getSprite(16*2 + (i*16), 32, 16, 16);
        }
		for(int i=0; i<=5; i++){
            playerDeath[i] = Game.spritesheet.getSprite(0 + (i*16), 16*4, 16, 16);
        }

	}
	
	public void tick(){
		depth = 1;

		collidingEnemy();
		animFrames();
		if(dead == false)
			movePlayer();
		takingDots();
		

	}

	public void animFrames(){
		if(dead == false){
			frames++;
        	if(frames >= maxFrames){
            	frames = 0;
            	index++;
            	if(index > maxIndex){
                	index = 0;
            	}
			}
		}else{
			deadFrames++;
        	if(deadFrames >= maxdeadFrames){
            	deadFrames = 0;
            	deadIndex++;
            	if(deadIndex > maxdeadIndex){
                	deadIndex= 0;
            	}
			}
		}
		
	}

	public void movePlayer(){

        if (desiredHoriDir != 0 && World.isFree((int)(x + speed * desiredHoriDir), this.getY())) {
            hori_dir = desiredHoriDir;
            vert_dir = 0;
            desiredHoriDir = 0;
        }

        if (desiredVertDir != 0 && World.isFree(this.getX(), (int)(y + speed * (desiredVertDir / 2)))) { // Dividir por 2 para ajustar a direção
            vert_dir = desiredVertDir;
            hori_dir = 0;
            desiredVertDir = 0;
        }


		if(hori_dir == 1 && World.isFree((int)(x+speed),this.getY())) {
			x+=speed;
			sprite_dir = 1;


		}
		else if(hori_dir == -1 && World.isFree((int)(x-speed),this.getY())) {
			x-=speed;
			sprite_dir = -1;

		}
		if(vert_dir == -2 && World.isFree(this.getX(),(int)(y-speed))){
			y-=speed;
			sprite_dir = -2;

		}
		else if(vert_dir == 2 && World.isFree(this.getX(),(int)(y+speed))){
			y+=speed;
			sprite_dir = 2;

		}
	}

	public void takingDots(){
		for(int i=0; i < Game.entities.size(); i++){
			Entity current = Game.entities.get(i);
			if(current instanceof Moeda){
				if(Entity.isColidding(this, current)){
					Game.entities.remove(i);
					Game.pontos++;
					return;
				}
			}
		}
	}

	public void collidingEnemy(){
		for(int i=0; i < Game.entities.size(); i++){
			Entity current = Game.entities.get(i);
			if(current instanceof Enemy){
				if(Entity.isColidding(this, current)){
					dead = true;
					return;
				}
			}
		}
	}

	public void render(Graphics g){
		if(dead == false){
			if(sprite_dir == 1 || sprite_dir == 0){
				g.drawImage(rightPlayer[index], this.getX(), this.getY(), null);
				
	
			}else if(sprite_dir == -1){
				g.drawImage(leftPlayer[index], this.getX(), this.getY(), null);
				
	
			}
			else if(sprite_dir == -2){
				g.drawImage(upPlayer[index], this.getX(), this.getY(), null);
				
	
			}
			else if(sprite_dir == 2){
				g.drawImage(downPlayer[index], this.getX(), this.getY(), null);
				
	
			}
		}else {
			g.drawImage(playerDeath[deadIndex], this.getX(), this.getY(), null);
		}

	}


}
