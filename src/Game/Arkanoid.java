package Game;

import com.senac.SimpleJava.Console;
import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.GraphicApplication;
import com.senac.SimpleJava.Graphics.Resolution;

public class Arkanoid extends GraphicApplication {

	private Ball ball;
	
	@Override
	protected void draw(Canvas canvas) {
		
	}

	@Override
	protected void setup() {
		this.setTitle("Arkanoid");
		this.setResolution(Resolution.HIGHRES);
		this.setFramesPerSecond(60);
		
		ball = new Ball();
		
		
	}

	@Override
	protected void loop() {
		ball.move();
		redraw();
		
	}
	
}
