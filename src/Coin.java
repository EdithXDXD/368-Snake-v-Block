import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Coin extends Circle {
	public int x;
	public int y;
	
	Coin (int xLoc, int yLoc)
	{
		x = xLoc;
		y = yLoc;
	}
	
	public Coin(int x, int y, double radius, Color c, int xLoc, int yLoc)
	{
		super(x, y, radius, c);
		this.x = xLoc;
		this.y = yLoc;
	}
	
}
