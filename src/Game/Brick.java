package Game;

import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Rect;
import com.senac.SimpleJava.Graphics.Sprite;

public class Brick extends Sprite {

	int score = 0;
	
	public Brick() {
		super(55,20,Color.RED);
	}
	
	/*public void counter(){
		score += 10;
	}
	
	public int show(){
		return score;
	}*/

	public int hit(Ball ball, Point ballPosition){
		
		Rect brickPos = getBounds();
		int brickTop = brickPos.y - 16;
		int brickBot = brickPos.y + brickPos.height + 1;
		int brickLef = brickPos.x - 16;
		int brickRig = brickPos.x + brickPos.width + 1;
		
		if (brickTop <= ballPosition.y && brickBot >= ballPosition.y ) {
			if 	(brickLef == ballPosition.x || brickRig == ballPosition.x ) {
				//counter();
				return 1;
			}
		}
		if (brickLef <= ballPosition.x && brickRig >= ballPosition.x ) {
			if (brickTop == ballPosition.y || brickBot == ballPosition.y ) {
				//counter();
				return 2;
			}
		}

				return 3;

	}
}
