import java.util.List;
import java.util.Random;
import java.util.Vector;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * Simple main is the stage for the 
 * game
 * 
 * */
public class SimpleMain extends Application {

	Pane p;
	HBox blockBox;
	Player player;
	Scene mainScene;
	Vector<StackPane> blockPanes;
	public AnimationTimer timer;
	final static double SCREEN_WIDTH = 400;
	final static double SCREEN_HEIGHT = 640;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		initializeVars();
		setUpScene();
		
		primaryStage.setTitle("Snake vs. Block");
		primaryStage.setScene(mainScene);
		primaryStage.show();
		
	}

	private void setUpScene() {
		// TODO Auto-generated method stub
		p.getChildren().addAll(player.mCoins);
		p.getChildren().add(player.life);
		blockBox.getChildren().addAll(blockPanes);
		p.getChildren().add(blockBox);
	}

	private void initializeVars () {
		p = new Pane();
		blockBox = new HBox(5);
		blockBox.setPadding(new Insets(5,5,5,5));
		mainScene = new Scene(p, SCREEN_WIDTH, SCREEN_HEIGHT);
		player = new Player(mainScene, this);
		timer = new AnimationTimer(){
			boolean collided = false;
			private long lastUpdate = 0;
			@Override
			public void handle(long now) {
				// if there's a collision then I want the block to freeze for a second
				// so that the user could see the process of breaking blocks 
				// Not working though right now
				if (collided) {
					if (now - lastUpdate >= (long)500000000) {
						System.out.println("Waited" + now + " " + lastUpdate);
						moveBlock(blockBox, now);
						lastUpdate = now;
					} 
				}
				else {
					moveBlock(blockBox, now);
				}
				
			}

			private void moveBlock(HBox b, long now) {
				// TODO Auto-generated method stub
				if (b.getBoundsInParent().intersects(player.life.getBoundsInParent()))
				{
					// check which exact block is intersecting
					
					double location = player.mCoins.get(0).getCenterX();
					int index = (int) (location * 10 / SCREEN_WIDTH /2);
					StackPane sp = (StackPane)(b.getChildren().get(index));
					Block currBlock = (Block)sp.getChildren().get(0);
					int blockCost = currBlock.getCost();
					currBlock.mediaPlayer.stop();
					if (blockCost > 0) {
						System.out.println("Index: " + index + " Block Cost: " + blockCost);
						lastUpdate = now;
						collided = true;
						currBlock.mediaPlayer.play();
						player.collideBlock();
						currBlock.collide(); 
						((Text)sp.getChildren().get(1)).setText("" + currBlock.getCost());
					}
					else {
						// end collision
						collided = false;
						b.setLayoutY(b.getLayoutY() + 2);
					}
				}
				else {
					b.setLayoutY(b.getLayoutY() + 2);
				}
				
			}
		};
		player.addCoin(7);
		
		blockPanes = new Vector<>();
		Random r = new Random();
		for (int i = 0; i < 5; ++i)
		{
			StackPane blockNum = new StackPane();
			Block b = new Block(r.nextInt(player.mCoins.size()) + 1, i); 
			Text t = new Text();
			t.setText(b.getCost() +"");
			blockNum.getChildren().addAll(b, t);
			blockPanes.add(blockNum);
		}
		
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}

	public void endGame() {
		// TODO Auto-generated method stub
		timer.stop();
	}

}
