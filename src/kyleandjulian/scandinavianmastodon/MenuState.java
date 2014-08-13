package kyleandjulian.scandinavianmastodon;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class MenuState extends BasicGameState {
	
	private Image titleBanner, playGame, playGameInverted, exitGame, exitGameInverted;
	private boolean playGameHover, exitGameHover, playGameClicked, exitGameClicked;
	
	public MenuState(int state) {
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		titleBanner = new Image("res/banner_title.png");
		playGame = new Image("res/button_play.png");
		exitGame = new Image("res/button_exit.png");
		playGameInverted = new Image("res/button_play_inverted.png");
		exitGameInverted = new Image("res/button_exit_inverted.png");		
		playGameHover = false;
		exitGameHover = false;
		playGameClicked = false;
		exitGameClicked = false;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		titleBanner.draw(0,100);
		if (playGameHover == false) {
			playGame.draw(342,400);
		} else {
			playGameInverted.draw(342,400);
		}
		if (exitGameHover == false) {
			exitGame.draw(342,500);
		} else {
			exitGameInverted.draw(342,500);
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (playGameClicked == true) {
			playGameClicked = false;
			sbg.enterState(1);
		} else if (exitGameClicked == true) {
			System.exit(0);
		}
	}
	
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		if (newx >= 342 && newx <= 457 && newy >=400 && newy <= 455) {
			playGameHover = true;
		} else {
			playGameHover = false;
		}
		
		if (newx >= 342 && newx <= 457 && newy >=500 && newy <= 555) {
			exitGameHover = true;
		} else {
			exitGameHover = false;
		}		
	}
	
	public void mousePressed(int button, int x, int y) {
		if (button == 0 && x >= 342 && x <= 457 && y >= 400 && y <= 455) {
			playGameClicked = true;
		}
		
		if (button == 0 && x >= 342 && x <= 457 && y >=500 && y <= 555) {
			exitGameClicked = true;
		}		
	}
	
	public int getID() {
		return 0;
	}
}
