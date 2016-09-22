package Game;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Sprite;

public class Board extends Sprite{

	private Brick brickTest;
	
	public Board(int bX, int bY, Color col) {
		super(bX,bY,col);
	}

	public void testSet() {
		brickTest.setPosition(500,500);
	}
	
	public void testDraw(Canvas canvas) {
		brickTest.draw(canvas);
	}
}