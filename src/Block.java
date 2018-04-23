import java.io.File;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


/**
 * Block is the class for the barrier
 * It extends Rectangle class
 * 
 * */
public class Block extends Rectangle {
	private Color recColor;
	private int cost;
	private int speed;
	Media collisionSound;
	public MediaPlayer mediaPlayer;

	Block(int x, int order) {
		super(SimpleMain.SCREEN_WIDTH/5.5, SimpleMain.SCREEN_WIDTH/5.5, Color.rgb(66, 244, 209));
		if (x == 0) {
			this.setFill(Color.TRANSPARENT);
		}
		this.setX(SimpleMain.SCREEN_WIDTH/5.0 * order);
		this.setY(0);
		cost = x;
		speed = 5;
		String url = "src/collision.wav";
		collisionSound = new Media(new File(url).toURI().toString());
		mediaPlayer = new MediaPlayer(collisionSound);
	}
	
	public void setLocation(double x, double y)
	{
		this.setX(x);
		this.setY(y);
	}

	public int getCost() {
		return cost;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void update() {
		setY(getY() + speed); 
		System.out.println(getY());
	}

	public void collide() {
		// TODO Auto-generated method stub
		cost --;
		
		if (cost == 0) {
			// delete the block
			this.setFill(Color.TRANSPARENT);
		}
		
	}
}
