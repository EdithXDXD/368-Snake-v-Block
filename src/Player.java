import java.util.Vector;
import java.awt.MouseInfo;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
/**
 * Player is the main character 
 * 
 * */
public class Player {
	public Vector<Circle> mCoins;
	public int coinNum;
	public Label life;
	private final int RADIUS = 10;
	private final Color COINSTYLE = Color.GOLD;
	private Scene scene;
	private SimpleMain main;

	Player(Scene s, SimpleMain m){
		mCoins = new Vector<>();
		coinNum = 0;
		scene = s;
		main = m;
		initializePlayer();

	}
	
	public void addCoin(int num) {
		coinNum += num;
		for (int i = 0; i < num; ++i) {
			createCircle();
		}
		
	}
	
	public void collide() {
		coinNum -= 1;
		if (coinNum < 0) {
			main.endGame();
		}
		else {
			mCoins.get(mCoins.size() - 1).setCenterY(SimpleMain.SCREEN_HEIGHT*2);;
			mCoins.remove(mCoins.size() - 1);
			life.setText("" + coinNum);
		}
		
	}
	private void initializePlayer() {
		Circle c = new Circle(SimpleMain.SCREEN_WIDTH/2.0, SimpleMain.SCREEN_HEIGHT*2/3, RADIUS, COINSTYLE);
		
		// the life display label
		String coinNumDisplay = "" + coinNum;
		life = new Label(coinNumDisplay);
		life.setTranslateX(c.getCenterX() - c.getRadius()/2);
		life.setTranslateY(c.getCenterY() - c.getRadius() * 2.5);
		
		mCoins.add(c);
		c.setOnMouseClicked(e ->{
			// Game started!
			scene.setOnMouseMoved(e1 ->{
				// bind circle movement and life display movement
				c.setCenterX(e1.getX());
				life.setText("" + coinNum);
				life.setTranslateX(c.getCenterX() - c.getRadius()/2);
			});
			
			main.timer.start();
		});
		
	}
	
	private void createCircle() {
		Circle c = new Circle(mCoins.get(mCoins.size() - 1).getCenterX(),  
				mCoins.get(mCoins.size() - 1).getCenterY() + RADIUS*2.1, RADIUS, COINSTYLE);
		// bind to its c
		c.centerXProperty().bind(mCoins.get(0).centerXProperty());
		life.setText("" + coinNum);
		mCoins.add(c);
	}
	
}
