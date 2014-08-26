package kyleandjulian.scandinavianmastodon;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.transition.*;

public class GlobalConfig {
	// base window size
	public static int SCREEN_WIDTH = 1024;
	public static int SCREEN_HEIGHT = 768;
	
	// window elements: MenuState
	public static int MENUSTATE_TITLEBANNER_X = 112;
	public static int MENUSTATE_TITLEBANNER_Y = 200;
	public static int MENUSTATE_BUTTONPLAY_X = 454;
	public static int MENUSTATE_BUTTONPLAY_Y = 450;	
	public static int MENUSTATE_BUTTONINSTRUCTIONS_X = 327;
	public static int MENUSTATE_BUTTONINSTRUCTIONS_Y = 550;	
	public static int MENUSTATE_BUTTONEXIT_X = 454;
	public static int MENUSTATE_BUTTONEXIT_Y = 650;		
	
	// window elements: PlayState
	public static int PLAYSTATE_VIEWPORT_X = 6;
	public static int PLAYSTATE_VIEWPORT_Y = 6;
	public static int PLAYSTATE_VIEWPORT_WIDTH = 859;
	public static int PLAYSTATE_VIEWPORT_HEIGHT = 758;
	public static int PLAYSTATE_MINIMAP_X = 869;
	public static int PLAYSTATE_MINIMAP_Y = 6;
	public static int PLAYSTATE_MINIMAP_WIDTH = 150;
	public static int PLAYSTATE_MINIMAP_HEIGHT = 100;
	public static int PLAYSTATE_MENUBAR_X = 869;
	public static int PLAYSTATE_MENUBAR_Y = 111;
	public static int PLAYSTATE_MENUBAR_WIDTH = 150;
	public static int PLAYSTATE_MENUBAR_HEIGHT = 653;
	
	// other
	public static String GAME_NAME = "Scandinavian Mastodon";
	public static Color OUT_TRANSITION_COLOR = Color.black;
	public static int OUT_TRANSITION_DURATION = 400;
	public static Color IN_TRANSITION_COLOR = Color.black;
	public static int IN_TRANSITION_DURATION = 400;	
	public static int MENU_STATE = 0;
	public static int PLAY_STATE = 1;	
	public static int INTERLEVEL_STATE = 2;
	public static int NUM_LEVELS = 3;
	public static float MAP_SCROLL_SPEED = 0.5f;
	public static int MENU_SCROLL_SPEED = 30;
	public static Color BUTTON_SELECTED_COLOR = Color.red;
	
	// file names: MenuState
	public static String BANNER_TITLE = "res/banner_title.png";
	public static String BUTTON_PLAY = "res/button_play.png";
	public static String BUTTON_PLAY_INVERTED = "res/button_play_inverted.png";
	public static String BUTTON_INSTRUCTIONS = "res/button_instructions.png";
	public static String BUTTON_INSTRUCTIONS_INVERTED = "res/button_instructions_inverted.png";	
	public static String BUTTON_EXIT = "res/button_exit.png";
	public static String BUTTON_EXIT_INVERTED = "res/button_exit_inverted.png";
	public static String INSTRUCTIONS = "res/instructions.png";
	public static String SONG_MENU = "res/Song_Menu.ogg";
	
	// file names: PlayState
	public static String HUD_OVERLAY = "res/hud_overlay.png";
	public static String MENUBAR = "res/menubar.png";
	public static String MENU_BUTTON_1 = "res/menu_button_1.png";
	public static String MENU_BUTTON_2 = "res/menu_button_2.png";	
	public static String VILLAGER_FRONT1 = "res/char_front1.png";
	public static String VILLAGER_FRONT2 = "res/char_front2.png";
	public static String VILLAGER_BACK1 = "res/char_back1.png";
	public static String VILLAGER_BACK2 = "res/char_back2.png";	
	public static String VILLAGER_LEFT1 = "res/char_left1.png";
	public static String VILLAGER_LEFT2 = "res/char_left2.png";
	public static String VILLAGER_RIGHT1 = "res/char_right1.png";
	public static String VILLAGER_RIGHT2 = "res/char_right2.png";		
	
	public static String[] LEVEL_MAP = {"res/tiledmap_level1.tmx","res/tiledmap_level1.tmx","res/tiledmap_level1.tmx"};
	public static String[] LEVEL_MUSIC = {"res/Song_Level1.ogg", "res/Song_Level2.ogg", "res/Song_Level3.ogg"};
}
