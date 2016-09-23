package Game;

import com.senac.SimpleJava.Graphics.Color;

/*/
 * x - The X (often horizontal) coordinate.
 * y - The Y (often vertical) coordinate.
/*/

public class Config {

	static int boundaryX = 600;
	static int boundaryY = 500;
	static int vertexX = 50;
	static int vertexY = 50;
	
	static int ballRadius = 16;
	
	static int paddleLength = 100;
	static int paddleHeight = 10;
	static int paddleMovement = 3;
	static int paddleMargin = 30;
	
	static int brickLength = 49;
	static int brickHeight = 18;
	static int brickRow = 7;
	static int brickColumn = 10;
	static int brickFirstXPosition = 60;
	static int brickFirstYPosition = 90;
	static int brickHorizontalSpacing = 10;
	static int brickVerticalSpacing = 10;
	
	static Color COL1 = new Color(27,28,82);	// Dark blue;
	static Color COL2 = new Color(123,74,165);	// Purple;
	static Color COL3 = new Color(171,250,234);	// Light blue;
	static Color COL4 = new Color(254,178,104);	// Light orange;
	static Color COL5 = new Color(202,62,160);	// Pink;
	static Color COL6 = new Color(255,234,228);	// Extra-light orange;
	static Color COL7 = new Color(46,168,193);	// Blue;
	
}
