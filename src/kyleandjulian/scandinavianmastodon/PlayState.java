package kyleandjulian.scandinavianmastodon;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;


public class PlayState extends BasicGameState{
	private Image hudOverlay;	
	private Menu menu;
	private TiledMapExtended map;	
	private GameData gameData;
	private Music levelMusic;
	private boolean shiftMapUp = false;
	private boolean shiftMapDown = false;
	private boolean shiftMapLeft = false;
	private boolean shiftMapRight = false;
	
	
	// constructor
	public PlayState(int state){
	}
	
	
	// initialize images
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {	
		hudOverlay = new Image(GlobalConfig.HUD_OVERLAY);		
		menu = new Menu(GlobalConfig.MENUBAR);
		menu.addButton(GlobalConfig.MENU_BUTTON_1, 50, 100, 81);
		menu.addButton(GlobalConfig.MENU_BUTTON_2, 50, 200, 19);		
	}
	
	
	// accept game data object
	public void takeGameData(GameData gd) {
		gameData = gd;
	}
	
	
	// initialize TiledMap and music on enter
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		map = new TiledMapExtended(GlobalConfig.LEVEL_MAP[gameData.getLevel() - 1], true, true);
		map.centerViewArea();
		
		levelMusic = new Music(GlobalConfig.LEVEL_MUSIC[gameData.getLevel() - 1]);
		levelMusic.loop();
	}
   
	
	// render screen elements
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {	
		map.render(g);
		menu.render(g);
		hudOverlay.draw();	
	}
   
	
	// update state
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {	
		if (checkWinCondition() == true) {
			levelMusic.stop();
			InterlevelState is = (InterlevelState)sbg.getState(GlobalConfig.INTERLEVEL_STATE);
			is.takeGameData(gameData);	
			sbg.enterState(GlobalConfig.INTERLEVEL_STATE, new FadeOutTransition(GlobalConfig.OUT_TRANSITION_COLOR, GlobalConfig.OUT_TRANSITION_DURATION), new FadeInTransition(GlobalConfig.IN_TRANSITION_COLOR, GlobalConfig.IN_TRANSITION_DURATION));			
		}
		
		if (shiftMapUp == true) {
			map.updateViewAreaYLoc(-GlobalConfig.MAP_SCROLL_SPEED);
		}
		if (shiftMapDown == true) {
			map.updateViewAreaYLoc(GlobalConfig.MAP_SCROLL_SPEED);			
		}
		if (shiftMapLeft == true) {
			map.updateViewAreaXLoc(-GlobalConfig.MAP_SCROLL_SPEED);
		}
		if (shiftMapRight == true) {
			map.updateViewAreaXLoc(GlobalConfig.MAP_SCROLL_SPEED);			
		}			
	}
	
	
	// keyPressed event
	public void keyPressed(int key, char c) {
		switch(key) {
			case Input.KEY_UP:
				shiftMapUp = true;
				break;
			case Input.KEY_DOWN:
				shiftMapDown = true;			
				break;
			case Input.KEY_LEFT:
				shiftMapLeft = true;			
				break;
			case Input.KEY_RIGHT:
				shiftMapRight = true;			
				break;
			default:
				break;
		}
	}
	
	
	// keyReleased event
	public void keyReleased(int key, char c) {
		switch(key) {
			case Input.KEY_UP:
				shiftMapUp = false;
				break;
			case Input.KEY_DOWN:
				shiftMapDown = false;			
				break;
			case Input.KEY_LEFT:
				shiftMapLeft = false;			
				break;
			case Input.KEY_RIGHT:
				shiftMapRight = false;			
				break;
			default:
				break;
		}
	}	
	
	
	// mouseDragged event
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		if (newx > oldx) {
			shiftMapRight = true;
		} else if (newx < oldx) {
			shiftMapLeft = true;
		}
		if (newy > oldy) {
			shiftMapDown = true;
		} else if (newy < oldy) {
			shiftMapUp = true;
		}
	}
	
	
	// mouseReleased event
	public void mouseReleased(int button, int x, int y) {
		shiftMapUp = false;
		shiftMapDown = false;
		shiftMapLeft = false;
		shiftMapRight = false;
	}
	
	// mousePressed event
	public void mousePressed(int button, int x, int y) {
		if (x >= GlobalConfig.PLAYSTATE_VIEWPORT_X && x <= GlobalConfig.PLAYSTATE_VIEWPORT_X + GlobalConfig.PLAYSTATE_VIEWPORT_WIDTH && y >= GlobalConfig.PLAYSTATE_VIEWPORT_Y && y <= GlobalConfig.PLAYSTATE_VIEWPORT_Y + GlobalConfig.PLAYSTATE_VIEWPORT_HEIGHT) {
			// click is in the main viewport
			map.mainViewPortClicked(button, x, y, menu.getCurrentSelection());
		} else if (x >= GlobalConfig.PLAYSTATE_MINIMAP_X && x <= GlobalConfig.PLAYSTATE_MINIMAP_X + GlobalConfig.PLAYSTATE_MINIMAP_WIDTH && y >= GlobalConfig.PLAYSTATE_MINIMAP_Y && y <= GlobalConfig.PLAYSTATE_MINIMAP_Y + GlobalConfig.PLAYSTATE_MINIMAP_HEIGHT) {
			// click is in the minimap
			if (button == 0) {
				map.miniMapClicked(x, y);
			}
		} else if (x >= GlobalConfig.PLAYSTATE_MENUBAR_X && x <= GlobalConfig.PLAYSTATE_MENUBAR_X + GlobalConfig.PLAYSTATE_MENUBAR_WIDTH && y >= GlobalConfig.PLAYSTATE_MENUBAR_Y && y <= GlobalConfig.PLAYSTATE_MENUBAR_Y + GlobalConfig.PLAYSTATE_MENUBAR_HEIGHT) {
			// click is in the menubar
			if (button == 0) {
				menu.clicked(x, y);
			}
		}
	}
	
	
	// mouseWheelMoved event
	public void mouseWheelMoved(int change) {
		if (change > 0) {
			menu.scrollUp();
		} else if (change < 0) {
			menu.scrollDown();
		}
	}
	
	
	// check victory condition
	private boolean checkWinCondition() {
		int differences = 0;
		int tileValue;
		
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				if (map.getTileId(x, y, 1) != 0) {					
					if (map.getTileId(x, y, 2) == 0) {
						differences++;
					}
				}
			}
		}
		
		if (differences == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public int getID(){
		return GlobalConfig.PLAY_STATE;
	}
}