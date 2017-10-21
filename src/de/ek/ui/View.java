package de.ek.ui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import de.ek.data.Field;
import de.ek.data.Game;
import de.ek.data.GameLogic;

public class View extends JFrame{
	private Surface area;
	private Game game;
	
	public View(Game game) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Mills");
		this.setSize(new Dimension(850, 1000));
		this.setVisible(true);

		this.game = game;
		
		area = new Surface(this.game);
		this.add(area);

	}


}

class Surface extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
	private Game game;
    private final int DELAY = 150;
    private Timer timer;
    private int width = 600;
    private int height = 600;
    

    private int stoneSize= 100;
    private int spaceBetween = width/6;
    
    private int mouseX = -1;
    private int mouseY = -1;
    

    public Surface(Game game) {
    	this.game = game;
        initTimer();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    private void initTimer() {

        //timer = new Timer(DELAY, this);
        //timer.start();
    }
    
    public Timer getTimer() {
        
        return timer;
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(5));
        
        
        if (this.mouseX != -1 && this.mouseY != -1){
        	Ellipse2D.Double circle = new Ellipse2D.Double(this.mouseX-10, this.mouseY-10, 20, 20);
        	g2d.fill(circle);
        }
        
        
        g2d.translate(100, 100);
        drawBackground(g2d);
        drawFields(g2d);
        
        

    }

	private void drawFields(Graphics2D g2d) {
		for (Field f : game.data.fields.values()) {
			if (f.player != null){
				g2d.setPaint(f.player.color);
				g2d.drawOval(calculateXOfCircle(f), calculateYofCircle(f), stoneSize, stoneSize);
			}

			
		}
		
	}
	
	private int calculateXOfCircle(Field f){
		return ((int) FieldPositions.fieldPosistionTable.get(f.id).x*this.width) - (this.stoneSize/2);
	}
	private int calculateYofCircle(Field f){
		return ((int) FieldPositions.fieldPosistionTable.get(f.id).y*this.width) - (this.stoneSize/2);
	}

	private void drawBackground(Graphics2D g2d) {
        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(5));
		
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

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		this.mouseX = e.getX();
		this.mouseY = e.getY();
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.mouseX = -1;
		this.mouseY = -1;
		repaint();
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.mouseX = e.getX();
		this.mouseY = e.getY();
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}