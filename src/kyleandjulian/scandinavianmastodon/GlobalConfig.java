package kyleandjulian.scandinavianmastodon;

public class GlobalConfig {
	// base window size
	public static int SCREEN_WIDTH = 1024;
	public static int SCREEN_HEIGHT = 768;
	
	// PlayState window elements
	public static int PLAYSTATE_BORDER = 5;  // border width in pixels
	public static int PLAYSTATE_RIGHTPANEL_WIDTH = 150;  // width of right panel column
	public static int PLAYSTATE_MINIMAP_HEIGHT = 100;  // height of minimap
	public static int PLAYSTATE_MENU_HEIGHT = 500;  // height of menu bar
	
	// PlayState zoom settings
	public static int PLAYSTATE_MOUSE_ZOOMLIMIT = 2;
	public static float PLAYSTATE_ZOOMSTEP = (float)0.1;
	
	// other
	public static String GAME_NAME = "Scandinavian Mastodon";
	public static int MENU_STATE = 0;
	public static int PLAY_STATE = 1;	
}
