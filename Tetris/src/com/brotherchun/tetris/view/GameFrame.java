package com.brotherchun.tetris.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.brotherchun.tetris.entities.Shape;
import com.brotherchun.tetris.entities.ShapeFactory;
import com.brotherchun.tetris.utils.Dir;
import com.brotherchun.tetris.utils.Utils;
/**
 * 显示界面(控制器)
 * 
 * @author brotherChun
 *
 */
public class GameFrame extends JFrame {
	
	private boolean pause=false;//程序是否暂停
	private Shape shape;//移动否方块
	
	public GameFrame(){
		super("俄罗斯方块");
		init();
	}
	
	//初始化界面
	private void init(){
	    Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width-Utils.WIDTH*Utils.SIZE)/2,(screenSize.height-Utils.HIGHT*Utils.SIZE)/2, Utils.WIDTH*Utils.SIZE,Utils.HIGHT*Utils.SIZE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(new MyJPanel());
		setVisible(true);
		//添加键盘监听器
		addKeyListener(new MyKeyListener());
		shape=ShapeFactory.getShape();
		//启动重画线程
		new Thread(new PaintThread()).start();
	}
	
	private class MyJPanel extends JPanel{
		//每重画一次，调用一次
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Color c=g.getColor();
			
			g.setColor(Color.PINK);
			g.fillRect(0, 0, Utils.MID*Utils.SIZE, Utils.HIGHT*Utils.SIZE);
			
			g.setColor(Color.BLACK);
			g.fillRect(10*Utils.SIZE, 0, Utils.WIDTH*Utils.SIZE,  Utils.HIGHT*Utils.SIZE);
			
			shape.draw(g);
			
			g.setColor(c);
		}
	}
	
	private class PaintThread implements Runnable{

		@Override
		public void run() {
			while(!pause){
				repaint();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	//自定义键盘监听器
	private class MyKeyListener extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			int code=e.getKeyCode();
			switch (code) {
			case KeyEvent.VK_LEFT:
				if(shape.isMove(Utils.LEFT))
					shape.left();
				break;
			case KeyEvent.VK_RIGHT:
				if(shape.isMove(Utils.RIGHT))
					shape.right();
				break;
			case KeyEvent.VK_UP:
				if(shape.isMove(Utils.ROTATE))
					shape.rotate();
				break;
			case KeyEvent.VK_DOWN:
				if(shape.isMove(Utils.DOWN))
					shape.down();
				break;
			default:
				break;
			}
		}
		
	}
}
