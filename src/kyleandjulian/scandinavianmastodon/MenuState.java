package kyleandjulian.scandinavianmastodon;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;


public class MenuState extends BasicGameState {
	private Image titleBanner;
	private Image instructions;
	private Button playButton;
	private Button instructionsButton;
	private Button exitButton;
	private boolean playButtonClicked = false;
	private boolean instructionsButtonClicked = false;
	private boolean exitButtonClicked = false;
	private boolean inInstructionsScreen = false;
	private Music menuMusic;
	private GameData gameData;
	
	
	public MenuState(int state) {
	}
	
	
	// initialize title banner, buttons, and music
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		titleBanner = new Image(GlobalConfig.BANNER_TITLE);
		instructions = new Image(GlobalConfig.INSTRUCTIONS);
		playButton = new Button(GlobalConfig.BUTTON_PLAY, GlobalConfig.BUTTON_PLAY_INVERTED, GlobalConfig.MENUSTATE_BUTTONPLAY_X, GlobalConfig.MENUSTATE_BUTTONPLAY_Y);
		instructionsButton = new Button(GlobalConfig.BUTTON_INSTRUCTIONS, GlobalConfig.BUTTON_INSTRUCTIONS_INVERTED, GlobalConfig.MENUSTATE_BUTTONINSTRUCTIONS_X, GlobalConfig.MENUSTATE_BUTTONINSTRUCTIONS_Y);
		exitButton = new Button(GlobalConfig.BUTTON_EXIT, GlobalConfig.BUTTON_EXIT_INVERTED, GlobalConfig.MENUSTATE_BUTTONEXIT_X, GlobalConfig.MENUSTATE_BUTTONEXIT_Y);					
		menuMusic = new Music(GlobalConfig.SONG_MENU);	
	}
	
	
	// accept game data object
	public void takeGameData(GameData gd) {
		gameData = gd;
	}	
	
	
	// start the music
	public void enter(GameContainer gc, StateBasedGame sbg) {
		menuMusic.loop();
	}

	
	// draw menu elements
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if (inInstructionsScreen == false) {
			titleBanner.draw(GlobalConfig.MENUSTATE_TITLEBANNER_X, GlobalConfig.MENUSTATE_TITLEBANNER_Y);
			playButton.render();
			instructionsButton.render();
			exitButton.render();		
		} else {
			instructions.draw(100,100);
		}
	}
	
	
	// update state depending on button click flags
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (playButtonClicked == true) {
			playButtonClicked = false;
			menuMusic.stop();
			PlayState ps = (PlayState)sbg.getState(GlobalConfig.PLAY_STATE);
			ps.takeGameData(gameData);	
			sbg.enterState(GlobalConfig.PLAY_STATE, new FadeOutTransition(GlobalConfig.OUT_TRANSITION_COLOR, GlobalConfig.OUT_TRANSITION_DURATION), new FadeInTransition(GlobalConfig.IN_TRANSITION_COLOR, GlobalConfig.IN_TRANSITION_DURATION));
		} else if (instructionsButtonClicked == true) {
			instructionsButtonClicked = false;
			inInstructionsScreen = true;
		} else if (exitButtonClicked == true) {
			exitButtonClicked = false;
			menuMusic.stop();
			System.exit(0);
		}
	}
	
	
	// handle mouse movement input
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		if (inInstructionsScreen == false) {
			playButton.checkMouseHover(newx, newy);
			instructionsButton.checkMouseHover(newx, newy);
			exitButton.checkMouseHover(newx, newy);
		}
	}
		
	
	
	// handle mouse click input
	public void mousePressed(int button, int x, int y) {
		if (inInstructionsScreen == false) {
			if (playButton.isClicked(x, y) == true) {
				playButtonClicked = true;
			}
			
			if (instructionsButton.isClicked(x, y) == true) {
				instructionsButtonClicked = true;
			}		
			
			if (exitButton.isClicked(x, y) == true) {
				exitButtonClicked = true;
			}		
		} else {
			inInstructionsScreen = false;
		}
	}

	
	public int getID() {
		return GlobalConfig.MENU_STATE;
	}
}
