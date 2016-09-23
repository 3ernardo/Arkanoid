package Game;

import java.util.Random;
import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.GraphicApplication;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Resolution;
import com.senac.SimpleJava.Graphics.events.KeyboardAction;

public class Arkanoid extends GraphicApplication {

	private Ball ball;
	private Brick bricks[] = new Brick[70];
	private Brick bricksS[] = new Brick[70];
	private int alive[] = new int[70];
	private int aliveS[] = new int[70];
	private Paddle paddle;
	private int score = 0;
	private int highScore = 10;
	private int lives = 2;
	private boolean ballState = false;
	private Board board;
	private Board margin;
	Random random = new Random();
	Brick b = new Brick(Config.COL1);
	int gameState = 1;
	private int reset = 1;
	private int levelCounter = 0;
	private int level = 0;
	private int bricksTotal = 0;
	
	/*
	 * =========================
	 * 	Setup
	 * =========================
	 */
	
	@Override
	protected void setup() {
		this.setTitle("Arkanoid");
		this.setResolution(Resolution.HIGHRES);
		this.setFramesPerSecond(80);		

		// Board
		board = new Board(Config.boundaryX, Config.boundaryY, Config.COL2);
		board.setPosition(Config.vertexX, Config.vertexY);
		margin = new Board(Config.boundaryX, Config.ballRadius, Config.COL1);
		margin.setPosition(Config.vertexX, Config.vertexY + Config.boundaryY);
		
		// Paddle
		paddle = new Paddle();
		paddle.setPosition((Config.boundaryX - Config.paddleLength) / 2 + Config.vertexX, Config.vertexY + Config.boundaryY - Config.paddleMargin);

		// Ball
		ball = new Ball();
		if (random.nextBoolean()) {
			ball.invertHorizontal();
		}
	}
	
	/*
	 * =========================
	 * 	Draw
	 * =========================
	 */
	
	@Override
	protected void draw(Canvas canvas) {
		canvas.clear();						// Clears the canvas;
		canvas.setBackground(Config.COL1);
		canvas.setForeground(Config.COL6);
		
		switch (gameState){
		case 1: // Level 1
			drawLevel(1);					// Place the bricks according to the level;
			level = 1;						// Keeps track of the current level;
			gameState = 0;					// Alters the state to loop the mechanics;
			levelCounter = 0;				// Resets the timer for the opening message;
			ballState = false;				// Resets the ball position;
			ball.resetVertical();			// Points the ball up;
			if (random.nextBoolean()) {		// Randomizes ball's X direction; 
				ball.invertHorizontal();
			}
			break;
		case 2: // Level 2
			drawLevel(2);					// Place the bricks according to the level;
			level = 2;						// Keeps track of the current level;
			gameState = 0;					// Alters the state to loop the mechanics;
			levelCounter = 0;				// Resets the timer for the opening message;
			ballState = false;				// Resets the ball position;
			ball.resetVertical();			// Points the ball up;
			if (random.nextBoolean()) {		// Randomizes ball's X direction; 
				ball.invertHorizontal();
			}
			break;
		case 3: // Level 3
			drawLevel(3);					// Place the bricks according to the level;
			level = 3;						// Keeps track of the current level;
			gameState = 0;					// Alters the state to loop the mechanics;
			levelCounter = 0;				// Resets the timer for the opening message;
			ballState = false;				// Resets the ball position;
			ball.resetVertical();			// Points the ball up;
			if (random.nextBoolean()) {		// Randomizes ball's X direction; 
				ball.invertHorizontal();
			}
			break;
		case 4: // Level just for tests
			drawLevel(4);					// Place the bricks according to the level;
			level = 99;						// Keeps track of the current level;
			gameState = 0;					// Alters the state to loop the mechanics;
			levelCounter = 0;				// Resets the timer for the opening message;
			ballState = false;				// Resets the ball position;

			break;
		case 9: // Game Over
			// Board
			board.draw(canvas);
			margin.draw(canvas);
			
			// Paddle
			//paddle.draw(canvas);
			
			// Ball
			//ball.draw(canvas);
			
			// Info
			canvas.putText(660, 400, 18, "Score: " + score);
			canvas.putText(660, 430, 18, "Lives: 0");
			canvas.putText(660, 460, 18, "High score: " + highScore);
			canvas.putText(78, 235, 100, "Game Over!");
			break;
		default:
			// Board
			board.draw(canvas);
			
			// Paddle
			paddle.draw(canvas);
			
			// Ball
			ball.draw(canvas);
			margin.draw(canvas);
			
			// Bricks
			for (int i = 0; i < 70; i++) {
				if (alive[i] > 0) {
					bricks[i].draw(canvas);
				}
			}
			for (int i = 0; i < 70; i++) {
				if (aliveS[i] > 0) {
					bricksS[i].draw(canvas);
				}
			}
			
			// Info
			if (levelCounter < 360) {
				canvas.putText(200, 300, 100, "Level "+level);
			}
			canvas.putText(660, 400, 18, "Score: " + score);
			canvas.putText(660, 430, 18, "Lives: " + lives);
			canvas.putText(660, 460, 18, "High score: " + highScore);
			break;
		}
	}

	/*
	 * =========================
	 * 	Loop
	 * =========================
	 */
	
	@Override
	protected void loop() {
		Point ballPosition = ball.getPosition();
		Point paddlePosition = paddle.getPosition();

		controller(paddlePosition);
		brickCollision(ballPosition);
		checkBoundaries(ball, ballPosition);
		paddle.returnBall(ball, ballPosition);
		ballState(paddlePosition);

		if (score > highScore) {
			highScore = score;
		}
		
		// Leveling normal
		/*if (score >= 40 && reset == 1) {
			gameState = 2;
			reset++;
		}
		if (score >= 76 && reset == 2) {
			gameState = 3;
			reset++;
		}*/

		// Leveling debug
		if (score >= 8 && reset == 1) {
			for (int i = 0; i < 70; i++) {
				alive[i] = 0;
				aliveS[i] = 0;
			}
			gameState = 2;
			reset++;
		}
		if (score >= 16 && reset == 2) {
			for (int i = 0; i < 70; i++) {
				alive[i] = 0;
				aliveS[i] = 0;
			}
			gameState = 3;
			reset++;
		}
		if (score >= 24 && reset == 3) {
			for (int i = 0; i < 70; i++) {
				alive[i] = 0;
				aliveS[i] = 0;
			}
			gameState = 9;
			reset++;
		}
		
		

		levelCounter++;
		redraw();
	}

	/*
	 * =========================
	 * 	Methods
	 * =========================
	 */
	
	private void ballState(Point paddlePosition){
		if (ballState == true) {
			ball.move();
		}
		if (ballState == false) {
			ball.setPosition((int) paddlePosition.x + (Config.paddleLength - Config.ballRadius) / 2,
					(int) paddlePosition.y - Config.ballRadius * 2);
		}
	}
	
	
	
	private boolean moveRight = false;
	private boolean moveLeft = false;
	
	private void controller(Point paddlePosition) {

////////////////////////////////////////////////////////////////////////
		bindKeyPressed("LEFT", new KeyboardAction() {
			@Override
			public void handleEvent() {
				moveRight = true;
			}
		});
		bindKeyReleased("LEFT", new KeyboardAction() {
			@Override
			public void handleEvent() {
				moveRight = false;
			}
		});
		if (paddlePosition.x > Config.vertexX && moveRight == true) {
			paddle.move(Config.paddleMovement * -1, 0);
		}
		
		bindKeyPressed("RIGHT", new KeyboardAction() {
			@Override
			public void handleEvent() {
				moveLeft = true;
			}
		});
		bindKeyReleased("RIGHT", new KeyboardAction() {
			@Override
			public void handleEvent() {
				moveLeft = false;
			}
		});
		if (paddlePosition.x < Config.boundaryX + Config.vertexX - Config.paddleLength && moveLeft == true) {
			paddle.move(Config.paddleMovement, 0);
		}
////////////////////////////////////////////////////////////////////////		
		
		/*
		bindKeyPressed("LEFT", new KeyboardAction() {
			@Override
			public void handleEvent() {
				if (paddlePosition.x > Config.vertexX) {
					paddle.move(Config.paddleMovement * -1, 0);
				}
			}
		});

		bindKeyPressed("RIGHT", new KeyboardAction() {
			@Override
			public void handleEvent() {
				if (paddlePosition.x < Config.boundaryX + Config.vertexX - Config.paddleLength) {
					paddle.move(Config.paddleMovement, 0);
				}
			}
		});
		*/

		bindKeyPressed("SPACE", new KeyboardAction() {
			@Override
			public void handleEvent() {
				if (ballState == false) {
					ballState = true;
				}
			}
		});
		
		/*
		bindKeyPressed("UP", new KeyboardAction() {
			@Override
			public void handleEvent() {
				ball.horizonRigh();
			}
		});
		bindKeyPressed("DOWN", new KeyboardAction() {
			@Override
			public void handleEvent() {
				ball.horizonLeft();
			}
		});
		*/
		if (paddlePosition.x < Config.vertexX) {
			paddle.setPosition(Config.vertexX, Config.vertexY + Config.boundaryY - Config.paddleMargin);
		}
		if (paddlePosition.x + Config.paddleLength > Config.vertexX + Config.boundaryX) {
			paddle.setPosition(Config.vertexX + Config.boundaryX - Config.paddleLength, Config.vertexY + Config.boundaryY - Config.paddleMargin);
		}
	}
	
	private void brickCollision(Point ballPosition) {
		int action = 0;
		boolean changeY = false;
		boolean changeX = false;
		for (int i = 0; i < bricks.length; i++) {
			if (alive[i] > 0) {
				action = bricks[i].hit(ball, ballPosition);
				switch (action) {
				case 1:
					alive[i]--;
					changeY = true;
					score++;
					break;
				case 2:
					alive[i]--;
					changeX = true;
					score++;
					break;
				case 3:
					alive[i]--;
					changeX = true;
					changeY = true;
					score++;
					break;
				case 4:
					//alive[i] = true;
					break;
				}
			}
		}
		for (int i = 0; i < bricks.length; i++) {
			if (aliveS[i] > 0) {
				action = bricksS[i].hit(ball, ballPosition);
				switch (action) {
				case 1:
					aliveS[i]--;
					break;
				case 2:
					aliveS[i]--;
					break;
				case 3:
					aliveS[i]--;
					break;
				case 4:
					//alive[i] = true;
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
	}
	
	private void checkBoundaries(Ball ball, Point ballPosition) {
		if (ballPosition.x <= Config.vertexX || ballPosition.x >= Config.boundaryX + Config.vertexX - Config.ballRadius) {
			ball.invertHorizontal();
		}
		if (ballPosition.y <= Config.vertexY) {
			ball.invertVertical();
		}
		if (ballPosition.y >= Config.boundaryY + Config.vertexY) {
			death();
		}
	}

	private void death() {
		if (lives > 1) {
			lives--;
			ballState = false;
			ball.resetVertical();
			if (random.nextBoolean()) {
				ball.invertHorizontal();
			}
		} else {
			gameState = 9;
		}
	}
	
	private void drawLevel(int level) {
		int[] pattern = null;
		switch (level) {
		case 1:
			pattern = b.level1();
			break;
		case 2:
			pattern = b.level2();
			break;
		case 3:
			pattern = b.level3();
			break;
		/*case 4:
			bricks[1] = new Brick();
			bricks[1].setPosition(300, 300);
			alive[1] = true;
			ball.setPosition(280, 280);
			ball.verticalDown();
			ball.horizonRigh();
			break;*/
		}
		int brickX = Config.brickFirstXPosition;
		int brickY = Config.brickFirstYPosition;
		int i = 0;
		for (int r = 0; r < Config.brickRow; r++) {
			for (int c = 0; c < Config.brickColumn; c++) {
				if (pattern[i] > 0) {
					if (pattern[i] == 1) {
						bricks[i] = new Brick(Config.COL5);
						alive[i] = 1;
						bricks[i].setPosition(brickX, brickY);
					}
					if (pattern[i] == 2) {
						bricks[i] = new Brick(Config.COL5);
						alive[i] = 2;
						bricks[i].setPosition(brickX, brickY);
						bricksS[i]  = new Brick(Config.COL7);
						aliveS[i] = 1;
						bricksS[i].setPosition(brickX, brickY);
					}
					bricksTotal++;
				}
				i++;
				brickX += Config.brickLength + Config.brickHorizontalSpacing;
			}
			brickX = Config.brickFirstXPosition;
			brickY += Config.brickHeight + Config.brickVerticalSpacing;
		}
		System.out.println(bricksTotal);

	}
	
	
}
