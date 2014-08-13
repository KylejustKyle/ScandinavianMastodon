package kyleandjulian.scandinavianmastodon;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;

public class PlayState extends BasicGameState{
	private Image minimap, menubar;
	private TiledMap map;
	private float mapCurrentX;  // current top left corner of viewable part of tiledmap
	private float mapCurrentY;
	private float minimapFocusRectX;
	private float minimapFocusRectY;
	private float minimapFocusRectWidth;
	private float minimapFocusRectHeight;
	private final int minimapFrameX = 700;  // minimap frame dimensions
	private final int minimapFrameY = 0;
	private final int minimapFrameWidth = 100;
	private final int minimapFrameHeight = 80;
	
	
	public PlayState(int state){
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		minimap = new Image("res/tiledmap_temp.png");
		menubar = new Image("res/menubar.png");
		map = new TiledMap("res/tiledmap_temp.tmx");
		mapCurrentX = -1000;
		mapCurrentY = -500;
		minimapFocusRectWidth = minimapFrameWidth * ((((float)gc.getWidth() - minimapFrameWidth))/(map.getWidth() * 32));
		minimapFocusRectHeight = minimapFrameHeight * (((float)gc.getHeight())/(map.getHeight() * 32));
	}
   
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		map.render((int)mapCurrentX, (int)mapCurrentY);
		g.drawImage(minimap, minimapFrameX, minimapFrameY, minimapFrameX + minimapFrameWidth, minimapFrameY + minimapFrameHeight, 0, 0, minimap.getWidth(), minimap.getHeight());
		g.drawImage(menubar, minimapFrameX, minimapFrameY + minimapFrameHeight, minimapFrameX + minimapFrameWidth, gc.getHeight(), 0, 0, menubar.getWidth(), menubar.getHeight());
		g.drawRect(getMinimapFocusRectX(), getMinimapFocusRectY(), minimapFocusRectWidth, minimapFocusRectHeight);
	}
   
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {	
		Input input = gc.getInput();
		
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			if (mapCurrentX - (gc.getWidth() - minimapFrameWidth) > -(map.getWidth() * 32)) {
				mapCurrentX = mapCurrentX - delta * 0.4f;
			} else {
				mapCurrentX = -(map.getWidth() * 32) + (gc.getWidth() - minimapFrameWidth);
			}
		}
		
		if (input.isKeyDown(Input.KEY_LEFT)) {
			if (mapCurrentX < 0) {
				mapCurrentX = mapCurrentX + delta * 0.4f;
			} else {
				mapCurrentX = 0;
			}
		}
		
		if (input.isKeyDown(Input.KEY_DOWN)) {
			if (mapCurrentY - gc.getHeight() > -(map.getHeight() * 32)) {
				mapCurrentY = mapCurrentY - delta * 0.4f;
			} else {
				mapCurrentY = -(map.getHeight() * 32) + gc.getHeight();
			}
		}	
		
		if (input.isKeyDown(Input.KEY_UP)) {
			if (mapCurrentY < 0) {
				mapCurrentY = mapCurrentY + delta * 0.4f;
			} else {
				mapCurrentY = 0;
			}
		}		
	}
	
	private float getMinimapFocusRectX() {
		float xLoc;
		
		xLoc = minimapFrameWidth * -(mapCurrentX)/(map.getWidth() * 32);
		
		return xLoc + minimapFrameX;
	}
	
	private float getMinimapFocusRectY() {
		float yLoc;
		
		yLoc = minimapFrameHeight * -(mapCurrentY)/(map.getHeight() * 32);
		
		return yLoc;
	}
	
	public int getID(){
		return 1;
	}
}