package com.brotherchun.tetris.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.brotherchun.tetris.utils.Utils;

public class Shape {
	private int[][] body;
	private int status=0;
	private int left=4;
	private int top=0;
	
	public Shape(int[][] body){
		this.body=body;
	}
	
	public void draw(Graphics g){
		Color c=g.getColor();
		
		g.setColor(Color.BLACK);
		for(int x=0;x<4;x++){
			for(int y=0;y<4;y++){
				if(body[status][4*x+y]==1){
					g.draw3DRect((left+x)*Utils.SIZE, (top+y)*Utils.SIZE, Utils.SIZE, Utils.SIZE, true);
				}
			}
		}
		g.setColor(c);
		
		top++;
	}
	
}
