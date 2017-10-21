package de.ek.ui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
import de.ek.data.GameConfig;
import de.ek.data.GameLogic;
import de.ek.data.Player;

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

class Surface extends JPanel implements ActionListener, MouseListener, MouseMotionListener, LogicResponseListener {
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
        this.game.uiHandler.setListener(this);
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
        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(5));
        
        
        drawLiftedStone(g2d);
        drawStonesOnHand(g2d);
        g2d.translate(100, 100);
        drawBackground(g2d);
        drawFields(g2d);
        drawPlayersTurn(g2d);
        
        

    }

	private void drawLiftedStone(Graphics2D g2d) {
		if (this.mouseX != -1 && this.mouseY != -1){
        	g2d.setPaint(game.data.activePlayer.color);
        	Ellipse2D.Double circle = new Ellipse2D.Double(this.mouseX-GameConfig.STONE_DIAMETER/2, this.mouseY-GameConfig.STONE_DIAMETER/2, GameConfig.STONE_DIAMETER, GameConfig.STONE_DIAMETER);
        	g2d.fill(circle);
        }
	}

	private void drawStonesOnHand(Graphics2D g2d) {
		for (Player player : game.data.players){
			int startX = 100;
			if (player.color == Color.WHITE){
				//left side
				startX = 100;
			}else if (player.color == Color.BLACK){
				startX = width/2 + 100;
			}
			
			for (int i=0; i<player.stonesInHand;i++){
				Ellipse2D.Double circle = new Ellipse2D.Double(startX, 50, 30, 30);
				g2d.setPaint(player.color);
	        	g2d.fill(circle);
	        	startX += 32;
			}
			
		}
		
	}

	private void drawPlayersTurn(Graphics2D g2d) {
		String text ="Hi";
		if (this.game.data.activePlayer.color == Color.WHITE){
			text = "WHITE'S TURN";
		}else{
			text = "BLACK'S TURN";
		}
		
		
		Font font = new Font("verdana", Font.BOLD, 22);
		Rectangle rect = new Rectangle(0, 600, GameConfig.GAME_AREA_WIDTH, 200);
	    FontMetrics metrics = g2d.getFontMetrics(font);
	    // Determine the X coordinate for the text
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;

	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    // Set the font
	    g2d.setFont(font);
	    g2d.setPaint(Color.BLACK);
	    // Draw the String
	    g2d.drawString(text, x, y);		
		
		
	}

	private void drawFields(Graphics2D g2d) {
		for (Field f : game.data.fields.values()) {
			
			
			if (f.player != null){
				g2d.setPaint(f.player.color);
				Ellipse2D.Double circle = new Ellipse2D.Double(calculateXOfCircle(f), calculateYofCircle(f), GameConfig.STONE_DIAMETER, GameConfig.STONE_DIAMETER);
	        	g2d.fill(circle);
			}

			
		}
		
	}
	
	private int calculateXOfCircle(Field f){
		return (int) (FieldPositions.fieldPosistionTable.get(f.id).x*GameConfig.GAME_AREA_WIDTH) - (GameConfig.STONE_DIAMETER/2);
	}
	private int calculateYofCircle(Field f){
		return (int) (FieldPositions.fieldPosistionTable.get(f.id).y*GameConfig.GAME_AREA_HEIGHT) - (GameConfig.STONE_DIAMETER/2);
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
	public void mouseClicked(MouseEvent e) {
		if (game.data.activePlayer.isPutPhase() || game.uiHandler.stateKickStone){
			game.uiHandler.clickOnField(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if (game.data.activePlayer.isMovePhase() && game.uiHandler.isLiftable(e.getX(), e.getY())){
			this.mouseX = e.getX();
			this.mouseY = e.getY();
			repaint();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (game.uiHandler.stateLiftedStone){
			game.uiHandler.dropLiftedStone(e.getX(), e.getY());
			this.mouseX = -1;
			this.mouseY = -1;
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (game.uiHandler.stateLiftedStone){
			this.mouseX = e.getX();
			this.mouseY = e.getY();
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMoveForbidden() {
		System.err.println("Move forbidden");
		
	}

	@Override
	public void onMillDetected() {
		System.out.println("Bitte schlagen");
		
	}
}