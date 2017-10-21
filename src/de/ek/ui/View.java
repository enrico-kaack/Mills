package de.ek.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import de.ek.data.Game;

public class View extends JFrame{
	private Surface area;
	private Game game;
	
	public View(Game game) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Mills");
		this.setSize(new Dimension(900, 900));
		this.setVisible(true);
		
		area = new Surface(game);
		this.add(area);
		
		generateBackground();
	}

	private void generateBackground() {
		
		
	}
}

class Surface extends JPanel implements ActionListener {
	private Game game;
    private final int DELAY = 150;
    private Timer timer;
    private int width = 600;
    private int height = 600;
    private int margin = 100;
    private int spaceBetween = width/6;
    

    public Surface(Game game) {
    	this.game = game;
        initTimer();
    }

    private void initTimer() {

        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public Timer getTimer() {
        
        return timer;
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(100, 100);
        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(5));
        
        drawBackground(g2d);
        drawGameState(g2d);
        
    }

	private void drawGameState(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	private void drawBackground(Graphics2D g2d) {
		//Draw the background field of mil
        //outer ring
        g2d.drawLine(0, 0, width, 0);
        g2d.drawLine(0, 0, 0, height);
        g2d.drawLine(0, height, width, height);
        g2d.drawLine(width, height, width, 0);
        
        //middle ring
        g2d.drawLine(spaceBetween, spaceBetween, width-spaceBetween, spaceBetween);
        g2d.drawLine(spaceBetween, spaceBetween, spaceBetween, height-spaceBetween);
        g2d.drawLine(spaceBetween, height-spaceBetween, width-spaceBetween, height-spaceBetween);
        g2d.drawLine(width-spaceBetween, height-spaceBetween, width-spaceBetween, spaceBetween);
        
        //inner ring
        g2d.drawLine(spaceBetween*2, spaceBetween*2, width-spaceBetween*2, spaceBetween*2);
        g2d.drawLine(spaceBetween*2, spaceBetween*2, spaceBetween*2, height-spaceBetween*2);
        g2d.drawLine(spaceBetween*2, height-spaceBetween*2, width-spaceBetween*2, height-spaceBetween*2);
        g2d.drawLine(width-spaceBetween*2, height-spaceBetween*2, width-spaceBetween*2, spaceBetween*2);
        
        //upper line
        g2d.drawLine(width/2, 0, width/2, spaceBetween*2);
        
        //right line
        g2d.drawLine(width, height/2, width-spaceBetween*2, height/2);
        
        //bottom line
        g2d.drawLine(width/2, height, width/2, height-spaceBetween*2);
        
        //right line
        g2d.drawLine(0, height/2, spaceBetween*2, height/2);
	}

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}