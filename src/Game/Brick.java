package Game;

import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Rect;
import com.senac.SimpleJava.Graphics.Sprite;

public class Brick extends Sprite {
	
	public Brick(Color col) {
		super(Config.brickLength,Config.brickHeight,col);
	}

	public int[] level1() {
		int[] lvl1 = {
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 2, 2, 0, 0, 0, 0,
				0, 0, 0, 1, 2, 2, 1, 0, 0, 0,
				0, 0, 1, 1, 2, 2, 1, 1, 0, 0,
				0, 1, 1, 1, 2, 2, 1, 1, 1, 0,
				1, 1, 1, 1, 2, 2, 1, 1, 1, 1,
				1, 1, 1, 1, 2, 2, 1, 1, 1, 1,
				};
		return lvl1;
	}
	
	public int[] level2() {
		int[] lvl2 = {
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 1, 1, 1, 1, 1, 1, 1, 0,
				0, 1, 1, 1, 1, 1, 1, 1, 1, 0,
				0, 1, 1, 2, 2, 2, 2, 1, 1, 0,
				0, 1, 1, 1, 1, 1, 1, 1, 1, 0,
				0, 1, 1, 1, 1, 1, 1, 1, 1, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				};
		return lvl2;
	}
	
	public int[] level3() {
		int[] lvl3 = {
				1, 1, 2, 1, 1, 1, 1, 2, 1, 1,
				1, 1, 1, 2, 1, 1, 2, 1, 1, 1,
				2, 1, 1, 1, 2, 2, 1, 1, 1, 2,
				1, 2, 1, 1, 1, 1, 1, 1, 2, 1,
				0, 1, 2, 1, 1, 1, 1, 2, 1, 0,
				0, 0, 1, 2, 1, 1, 2, 1, 0, 0,
				0, 0, 0, 1, 2, 2, 1, 0, 0, 0,
				};
		return lvl3;
	}
	
	public int hit(Ball ball, Point ballPosition) {

		Rect brickPos = getBounds();
		int brickTop = brickPos.y - Config.ballRadius;
		int brickBot = brickPos.y + brickPos.height + 1;
		int brickLef = brickPos.x - Config.ballRadius;
		int brickRig = brickPos.x + brickPos.width + 1;

		//Point brickTopLeft = new Point (brickLef,brickTop);
		//Point brickBotLeft = new Point (brickLef,brickBot);
		
		if (brickTop <= ballPosition.y && brickBot >= ballPosition.y) {
			if (brickLef == ballPosition.x || brickRig == ballPosition.x) {
				// counter();
				return 1;
			}
		}
		if (brickLef <= ballPosition.x && brickRig >= ballPosition.x) {
			if (brickTop == ballPosition.y || brickBot == ballPosition.y) {
				// counter();
				return 2;
			}
		}
		/*if (ballPosition == brickBotLeft) {
			return 3;
		}*/
		return 4;
	}

}
