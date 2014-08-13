package kyleandjulian.scandinavianmastodon;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class StartState extends StateBasedGame {
	
	public static final String gameName = "Awesome Game";
	public static final int menuState = 0;
	public static final int playState = 1;
	
	public StartState(String gameName) {
		super(gameName);
		this.addState(new MenuState(menuState));
		this.addState(new PlayState(playState));
	}
	
	public void initStatesList(GameContainer gameContainer) throws SlickException {
		this.getState(menuState).init(gameContainer, this);
		this.getState(playState).init(gameContainer, this);
		this.enterState(menuState);
	}
	
	public static void main(String[] args) {
		AppGameContainer appGameContainer;

		try {
			appGameContainer = new AppGameContainer(new StartState(gameName));
			appGameContainer.setDisplayMode(800, 600, false);
			appGameContainer.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

}
