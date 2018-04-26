import java.io.File;

import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Block extends StackPane {
	private Rectangle blockRec;
	private Color blockColor;
	private Text t;
	private int cost;
	private int speed;
	Media collisionSound;
	public MediaPlayer mediaPlayer;
	
	public Block(int cost, int order) {
		super();
		blockRec = new Rectangle(SimpleMain.SCREEN_WIDTH/5.5, SimpleMain.SCREEN_WIDTH/5.5, Color.rgb(66, 244, 209));
		if (cost == 0) {
			blockRec.setFill(Color.TRANSPARENT);
		}
		blockRec.setX(SimpleMain.SCREEN_WIDTH/5 * order);
		blockRec.setY(-50);// negative so that the user would not view the generation of the blocks
		
		this.cost = cost;
		speed = 5;
		
		String url = "src/collision.wav";
		collisionSound = new Media(new File(url).toURI().toString());
		mediaPlayer = new MediaPlayer(collisionSound);

		t = new Text();
		if (cost > 0) {
			t.setText(cost +"");
		}
		else {
			t.setText(" ");
		}
		t.setStyle("-fx-font-family: TRON; -fx-font-size: 20;");
		this.getChildren().addAll(blockRec, t);
	}

	public Rectangle getBlockRec() {
		return blockRec;
	}

	public Color getBlockColor() {
		return blockColor;
	}

	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getSpeed() {
		return speed;
	}
	
	public void collide() {
		setCost(cost - 1);
		if (cost > 0) {
			t.setText(cost + "");
		}
		else {
			t.setText("");
			blockRec.setFill(Color.TRANSPARENT);
		}
	}

	
}
