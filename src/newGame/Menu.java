package newGame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Menu extends BasicGameState {
	
	Image playGame, exitGame;
	
	
	public Menu(int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		playGame = new Image("res/playNow.png");
		exitGame = new Image("res/exitGame.png");		
	}
	//test
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("Welcome to an Awesome Game!", 100, 50);
		playGame.draw(100,100);
		exitGame.draw(100,200);		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		int xPos, yPos;
		
		xPos = Mouse.getX();
		yPos = Mouse.getY();
		
		if (xPos > 100 && xPos < 311 && yPos > 329 && yPos < 380) {
			if (Mouse.isButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				sbg.enterState(1);
			}
		} else if (xPos > 100 && xPos < 311 && yPos > 229 && yPos < 280) {
			if (Mouse.isButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				System.exit(0);
			}			
		}
	}
	
	public int getID() {
		return 0;
	}
}
