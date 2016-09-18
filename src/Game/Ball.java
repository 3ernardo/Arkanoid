package Game;

import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Sprite;

public class Ball extends Sprite {
	
	private int dy = 1;
	private int dx = 1;
	
	public Ball() {
		super(16,16,Color.BLACK);		
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
	
}
