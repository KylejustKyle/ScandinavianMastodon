package kyleandjulian.scandinavianmastodon;

import java.awt.Toolkit;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class MenuState extends BasicGameState {
	private Image titleBanner;
	private Image playGame;
	private Image playGameInverted;
	private Image exitGame;
	private Image exitGameInverted;		
	private boolean playGameHover;
	private boolean exitGameHover; 
	private boolean playGameClicked;
	private boolean exitGameClicked;
	
	// used for screen positioning of elements
	private int titleBannerX;
	private int titleBannerY;
	private int playGameX;
	private int playGameY;
	private int exitGameX;
	private int exitGameY;
	
	
	public MenuState(int state) {
	}
	
	// initialize images and variables
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
		
		// calculate element positioning based on screen size
		titleBannerX = (gc.getWidth() - titleBanner.getWidth())/2;  // center title banner
		titleBannerY = gc.getHeight()/3 - (titleBanner.getHeight()/2);  // place title banner roughly 1/3 down the screen
		playGameX = (gc.getWidth() - playGame.getWidth())/2;  // center playGame button
		playGameY = (gc.getHeight()/3) * 2; // place the playGame button in the lower 1/3 of the screen
		exitGameX = playGameX;  // stack exitGame button under playGame button
		exitGameY = playGameX + playGame.getHeight() + 100;
	}

	// draw menu elements
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		titleBanner.draw(titleBannerX, titleBannerY);
		if (playGameHover == false) {
			playGame.draw(playGameX, playGameY);
		} else {
			playGameInverted.draw(playGameX, playGameY);
		}
		if (exitGameHover == false) {
			exitGame.draw(exitGameX, exitGameY);
		} else {
			exitGameInverted.draw(exitGameX, exitGameY);
		}
	}
	
	// update state depending on button click flags
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (playGameClicked == true) {
			playGameClicked = false;
			sbg.enterState(1);
		} else if (exitGameClicked == true) {
			System.exit(0);
		}
	}
	
	// handle mouse movement input
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// invert playGame image on hover
		if (newx >= playGameX && newx <= playGameX + playGame.getWidth() && newy >= playGameY && newy <= playGameY + playGame.getHeight()) {
			playGameHover = true;
		} else {
			playGameHover = false;
		}
		
		// invert exitGame image on hover
		if (newx >= exitGameX && newx <= exitGameX + exitGame.getWidth() && newy >= exitGameY && newy <= exitGameY + exitGame.getWidth()) {
			exitGameHover = true;
		} else {
			exitGameHover = false;
		}		
	}
	
	// handle mouse click input
	public void mousePressed(int button, int x, int y) {
		if (button == 0 && x >= playGameX && x <= playGameX + playGame.getWidth() && y >= playGameY && y <= playGameY + playGame.getHeight()) {
			playGameClicked = true;
		} 
		
		if (button == 0 && x >= exitGameX && x <= exitGameX + exitGame.getWidth() && y >= exitGameY && y <= exitGameY + exitGame.getHeight()) {
			exitGameClicked = true;
		} 		
		
		System.out.println();
	}
	
	public int getID() {
		return GlobalConfig.MENU_STATE;
	}
}
