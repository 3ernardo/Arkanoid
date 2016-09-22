package Game;

import com.senac.SimpleJava.Graphics.Sprite;

public class Ball extends Sprite {
	
	private int dy = -1;
	private int dx = 1;
	
	public Ball() {
		super(Config.ballRadius,Config.ballRadius,Config.COL4);		
	}

	public void move() {
		super.move(dx, dy);
	}

	public void invertHorizontal() {
		dx *= -1;
	}

	public void invertVertical() {
		dy *= -1;
	}
	
	public void resetVertical() {
		dy = -1;
	}
	
	public void verticalDown() {
		dy = 1;
	}

	public void horizonRigh() {
		dx = 1;
	}

	public void horizonLeft() {
		dx = -1;
	}
	
}
