package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.gcstudios.main.Game;

public class Ui {

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("arial", Font.BOLD, 20));
		g.drawString("Pontuação: " + Game.pontos, 10, 26);
	}
	
}
