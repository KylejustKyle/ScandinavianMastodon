package kyleandjulian.scandinavianmastodon;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;


public class InterlevelState extends BasicGameState {
	boolean loadNext = false;
	GameData gameData;
	
	
	// constructor
	public InterlevelState(int state) {
	}
	
	
	// initialize variable
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		loadNext = false;
	}
	
	
	// accept game data object
	public void takeGameData(GameData gd) {
		gameData = gd;
	}
	
	
	// perform on enter
	public void enter(GameContainer gc, StateBasedGame sbg) {
		loadNext = false;
	}


	// update screen
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if (loadNext != true) {
			if (gameData.getLevel() < GlobalConfig.NUM_LEVELS) {
				g.drawString("You have completed Level " + gameData.getLevel(), 100, 100);
				g.drawString("Click to advance to next level", 100, 500);
			} else {
				g.drawString("You've beaten all the levels!", 100, 100);
				g.drawString("Now get the fuck out", 100, 500);			
			}
		}
	}
	

	// pass to Playstate with next level
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (loadNext == true) {
			if (gameData.getLevel() < GlobalConfig.NUM_LEVELS) {
				gameData.incrementLevel();
				PlayState ps = (PlayState)sbg.getState(GlobalConfig.PLAY_STATE);
				ps.takeGameData(gameData);
				sbg.enterState(GlobalConfig.PLAY_STATE, new FadeOutTransition(Color.black, 400), new FadeInTransition(Color.red, 400));
			} else {
				System.exit(0);
			}
		}
	}
	

	// handle mouse click input
	public void mousePressed(int button, int x, int y) {
		if (button == 0) {
			loadNext = true;
		}
	}

	
	public int getID() {
		return GlobalConfig.INTERLEVEL_STATE;
	}
}
