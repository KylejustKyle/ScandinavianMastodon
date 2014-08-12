package newGame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Game extends StateBasedGame {
	
	public static final String gameName = "Awesome Game";
	public static final int menu = 0;
	public static final int play = 2;
	
	public Game(String gameName) {
		super(gameName);
		this.addState(new Menu(menu));
		this.addState(new Play(play));
	}
	
	public void initStatesList(GameContainer gameContainer) throws SlickException {
		this.getState(menu).init(gameContainer, this);
		this.getState(play).init(gameContainer, this);
		this.enterState(menu);
	}
	
	public static void main(String[] args) {
		AppGameContainer appGameContainer;
//Test
		try {
			appGameContainer = new AppGameContainer(new Game(gameName));
			appGameContainer.setDisplayMode(640, 480, false);
			appGameContainer.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

}
