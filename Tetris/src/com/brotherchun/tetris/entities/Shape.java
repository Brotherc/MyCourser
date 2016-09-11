package com.brotherchun.tetris.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.brotherchun.tetris.utils.Utils;

public class Shape {
	private int[][] body;
	private int status = 0;
	private int left = 4;
	private int top = 0;

	public Shape(int[][] body) {
		this.body = body;
	}

	public void left() {
		left--;
	}

	public void right() {
		left++;
	}

	public void rotate() {
		status = (status + 1) % 4;
	}

	public void down() {
		top++;
	}

	public void draw(Graphics g) {
		Color c = g.getColor();

		g.setColor(Color.BLACK);
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (body[status][4 * x + y] == 1) {
					g.draw3DRect((left + x) * Utils.SIZE, (top + y) * Utils.SIZE, Utils.SIZE, Utils.SIZE, true);
				}
			}
		}
		g.setColor(c);
		if (isMove(Utils.DOWN))
			top++;
	}

	//判断方块的下一个动作是否越界
	public boolean isMove(int nextAction) {
		int nextTop = top;
		int nextLeft = left;
		int nextStatus = status;
		switch (nextAction) {
		case Utils.LEFT:
			nextLeft--;
			break;
		case Utils.RIGHT:
			nextLeft++;
			break;
		case Utils.DOWN:
			nextTop++;
			break;
		case Utils.ROTATE:
			nextStatus = (nextStatus + 1) % 4;
			break;
		default:
			break;
		}

		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (body[nextStatus][4 * x + y] == 1) {
					if (nextLeft + x < 0 || nextLeft + x > Utils.WIDTH - Utils.WGAP || nextTop + y < 0
							|| nextTop + y> Utils.HIGHT-Utils.HGAP)
						return false;
				}
			}
		}
		return true;
	}

}
