package Game;

import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Rect;
import com.senac.SimpleJava.Graphics.Sprite;

public class Paddle extends Sprite {
	
	public Paddle(){
		super(Config.paddleLength,Config.paddleHeight,Config.COL3);
	}

public void returnBall(Ball ball, Point ballPosition){
		
		Rect paddlePos = getBounds();
		int paddleTop = paddlePos.y - Config.ballRadius;
		int paddleBot = paddlePos.y + paddlePos.height + 1;
		int paddleLef = paddlePos.x - Config.ballRadius;
		int paddleRig = paddlePos.x + paddlePos.width + 1;
		
		if (paddleTop < ballPosition.y && paddleBot >= ballPosition.y) {
			if 	(paddleLef <= ballPosition.x && paddleLef + Config.paddleLength / 2 >= ballPosition.x) {
				ball.horizonLeft();
			}
			if 	(paddleRig >= ballPosition.x && paddleRig - Config.paddleLength / 2 <= ballPosition.x) {
				ball.horizonRigh();
			}
		}
		if (paddleLef <= ballPosition.x && paddleRig >= ballPosition.x) {
			if (paddleTop == ballPosition.y/* || paddleBot == ballPosition.y*/) {
				ball.invertVertical();
			}
		}


	}
}
