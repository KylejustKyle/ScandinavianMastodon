package kyleandjulian.scandinavianmastodon;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class StartState extends StateBasedGame {
	
	// start the game
	public StartState(String gameName) {
		super(gameName);
		this.addState(new MenuState(GlobalConfig.MENU_STATE));
		this.addState(new PlayState(GlobalConfig.PLAY_STATE));
	}
	
	// initialize states
	public void initStatesList(GameContainer gameContainer) throws SlickException {
		this.getState(GlobalConfig.MENU_STATE).init(gameContainer, this);
		this.getState(GlobalConfig.PLAY_STATE).init(gameContainer, this);
		this.enterState(GlobalConfig.MENU_STATE);
	}
	
	// main
	public static void main(String[] args) {
		AppGameContainer appGameContainer;

		try {
			appGameContainer = new AppGameContainer(new StartState(GlobalConfig.GAME_NAME));
			appGameContainer.setDisplayMode(GlobalConfig.SCREEN_WIDTH, GlobalConfig.SCREEN_HEIGHT, false);
			appGameContainer.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

}
