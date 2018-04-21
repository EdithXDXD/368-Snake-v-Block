import java.util.Random;
import java.util.Vector;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
			@Override
			public void handle(long now) {
				for (int i = 0; i < blockPanes.size(); ++i) {
					moveBlock((Block)blockPanes.get(i).getChildren().get(0));
				}
			}

			private void moveBlock(Block b) {
				// TODO Auto-generated method stub
				if (b.getBoundsInParent().intersects(player.mCoins.get(0).getBoundsInParent()))
				{
					timer.stop();
				}
				else {
					b.update();
				
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

}
