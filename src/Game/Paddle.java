package Game;

import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Rect;
import com.senac.SimpleJava.Graphics.Sprite;

public class Paddle extends Sprite {
	
	public Paddle(){
		super(100,10,Color.GRAY);
	}

public void returnBall(Ball ball, Point ballPosition){
		
		Rect paddlePos = getBounds();
		int paddleTop = paddlePos.y - 16;
		int paddleBot = paddlePos.y + paddlePos.height + 1;
		int paddleLef = paddlePos.x - 16;
		int paddleRig = paddlePos.x + paddlePos.width + 1;
		
		if (paddleTop <= ballPosition.y && paddleBot >= ballPosition.y ) {
			if 	(paddleLef == ballPosition.x || paddleRig == ballPosition.x ) {
				ball.invertHorizontal();
			}
		}
		if (paddleLef <= ballPosition.x && paddleRig >= ballPosition.x ) {
			if (paddleTop == ballPosition.y || paddleBot == ballPosition.y ) {
				ball.invertVertical();
			}
		}


	}
}
