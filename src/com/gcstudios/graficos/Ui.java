package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.gcstudios.main.Game;

public class Ui {

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 20));
		g.drawString("Pontuação: " + Game.pontos, 10, 22);
		if(Game.gameMode == "Win"){
			Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(0,0,0,100));
            g2.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
            g.setFont(new Font("arial", Font.BOLD, 50));
            g.setColor(Color.white);
            g.drawString("Ganhou", (Game.WIDTH*Game.SCALE/2) - 100, (Game.HEIGHT*Game.SCALE/2) - 50);
			g.drawString("Pontuação: " + Game.pontos, (Game.WIDTH*Game.SCALE/2) - 160, (Game.HEIGHT*Game.SCALE/2) + 10);
            g.drawString(">Pressione Enter<", (Game.WIDTH*Game.SCALE/2) - 215, (Game.HEIGHT*Game.SCALE/2) + 70);
		}
	}
	
}
