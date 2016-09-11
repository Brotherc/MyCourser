package com.brotherchun.tetris.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.brotherchun.tetris.entities.Shape;
import com.brotherchun.tetris.entities.ShapeFactory;
import com.brotherchun.tetris.utils.Utils;
/**
 * ��ʾ����(������)
 * 
 * @author brotherChun
 *
 */
public class GameFrame extends JFrame {
	
	private boolean pause=false;//�����Ƿ���ͣ
	private Shape shape;//�ƶ��񷽿�
	
	public GameFrame(){
		super("����˹����");
		init();
	}
	
	//��ʼ������
	private void init(){
	    Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width-Utils.WIDTH*Utils.SIZE)/2,(screenSize.height-Utils.HIGHT*Utils.SIZE)/2, Utils.WIDTH*Utils.SIZE,Utils.HIGHT*Utils.SIZE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(new MyJPanel());
		setVisible(true);
		
		shape=ShapeFactory.getShape();
		//�����ػ��߳�
		new Thread(new PaintThread()).start();
	}
	
	private class MyJPanel extends JPanel{
		//ÿ�ػ�һ�Σ�����һ��
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Color c=g.getColor();
			
			g.setColor(Color.PINK);
			g.fillRect(0, 0, 10*Utils.SIZE, 20*Utils.SIZE);
			
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
					Thread.sleep(150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
