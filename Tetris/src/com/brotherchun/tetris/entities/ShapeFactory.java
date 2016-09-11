package com.brotherchun.tetris.entities;

import java.util.Random;

public class ShapeFactory {
	protected static int shapes[][][] = new int[][][] {
		{
			{ 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

			{ 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },

			{ 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

			{ 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 }
		}
	};
	
	public static Shape getShape(){
		Random r=new Random();
		int type=r.nextInt(shapes.length);
		Shape shape=new Shape(shapes[type]);
		return shape;
	}
}
