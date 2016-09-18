package Game;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.GraphicApplication;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Resolution;
import com.senac.SimpleJava.Graphics.events.KeyboardAction;

public class Arkanoid extends GraphicApplication {

	private Ball ball;
	private Brick bricks[] = new Brick[60];
	private boolean alive[] = new boolean[60];
	private Paddle paddle;
	private int score = 0;
	
	@Override
	protected void setup() {
		this.setTitle("Arkanoid");
		this.setResolution(Resolution.HIGHRES);
		this.setFramesPerSecond(120);
		
		ball = new Ball();
		ball.setPosition(100,300);
		
		int brickY = 15;
		int brickX = 80;
		int i = 0;
		for (int l = 0; l < 5; l++){
			for (int c = 0; c < 12; c++){
				bricks[i] = new Brick();
				bricks[i].setPosition(brickY,brickX);
				alive[i] = true;
				i++;
				brickY += 65;
			}
			brickY = 15;
			brickX += 30;
		}
		
		paddle = new Paddle();
		paddle.setPosition(
				Resolution.HIGHRES.width/2-50,
				Resolution.HIGHRES.height-40
				);


		
	}
	
	@Override
	protected void draw(Canvas canvas) {
		
		canvas.clear();
        canvas.putText(120, 200, 130, "Arkanoid!");
        ball.draw(canvas);
        for (int i = 0; i < 60; i++){
        	if (alive[i] == true) {
        		bricks[i].draw(canvas);
        	}
		}
        paddle.draw(canvas);
        canvas.putText(250, 400, 60, "Score: " + score);
		
	}

	@Override
	protected void loop() {
		
		Point ballPosition = ball.getPosition();
		checkBoundaries(ball, ballPosition);
		int action = 0;
		boolean changeY = false;
		boolean changeX = false;
		for (int i = 0; i < bricks.length; i++) {
			if (alive[i] == true) {
				action = bricks[i].hit(ball, ballPosition);
				switch (action) {
					case 1:
						alive[i] = false;
						changeY = true;
						score++;
						break;
					case 2:
						alive[i] = false;
						changeX = true;
						score++;
						break;
					case 3:
						alive[i] = true;
						break;
				}
			}
		}
		if (changeY == true) {
			ball.invertHorizontal();
		}
		if (changeX == true) {
			ball.invertVertical();
		}
		
		paddle.returnBall(ball, ballPosition);
		
		ball.move();
		
		Point paddlePosition = paddle.getPosition();

			bindKeyPressed("LEFT", new KeyboardAction() { @Override
				public void handleEvent() {
					if (paddlePosition.x >= 0) {
						paddle.move(-12,0);
					}
				}
			});

			bindKeyPressed("RIGHT", new KeyboardAction() { @Override
				public void handleEvent() {
					if (paddlePosition.x <= Resolution.HIGHRES.width-100) {
						paddle.move(12,0);
					}
				}
			});
			
			//Console.println(score);
			
		redraw();
		
	}
	
	private void checkBoundaries(Ball ball, Point ballPosition) {
	
		if (ballPosition.x < 0 || ballPosition.x >= Resolution.HIGHRES.width-16) {
			ball.invertHorizontal();
		}
		if (ballPosition.y < 0 || ballPosition.y >= Resolution.HIGHRES.height-16) {
			ball.invertVertical();
		}

	}
	
}
