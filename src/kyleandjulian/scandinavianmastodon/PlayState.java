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
		if (mapCurrentX < -((map.getWidth() * 32) - (gc.getWidth() - minimapFrameX))) {
			mapCurrentX = -((map.getWidth() * 32) - (gc.getWidth() - minimapFrameX));
		} else if (mapCurrentX > 0) {
			mapCurrentX = 0;
		}
		
		if (mapCurrentY < -((map.getHeight() * 32) - gc.getHeight())) {
			mapCurrentY = -((map.getHeight() * 32) - gc.getHeight());
		} else if (mapCurrentY > 0) {
			mapCurrentY = 0;
		}		
		
		map.render((int)mapCurrentX, (int)mapCurrentY);
		
		float scaleX = (float)minimapFrameWidth/(map.getWidth() * 32);
		float scaleY = (float)minimapFrameHeight/(map.getHeight() * 32);
		g.scale(scaleX, scaleY);
		map.render((int)((1/scaleX)*minimapFrameX), (int)(1/scaleY) * minimapFrameY);
		g.resetTransform();
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
	
	public void mousePressed(int button, int x, int y) {
		// click on minimap, shift viewport
		if (button == 0 && x >= minimapFrameX && x <= minimapFrameX + minimapFrameWidth && y >= minimapFrameY && y <= minimapFrameY + minimapFrameHeight) {
			float tempX = x - minimapFrameX - minimapFocusRectWidth/2;
			mapCurrentX = -(tempX * ((float)(map.getWidth() * 32))/minimapFrameWidth);
			
			float tempY = y - minimapFrameY - minimapFocusRectHeight/2;  // realign y and calculate mapCurrentY
			mapCurrentY = -(tempY * ((float)(map.getHeight() * 32))/minimapFrameHeight);
		}	
		
		//click on main map, change tile
		if (button== 0 && x >= 0 && x <= minimapFrameX) {
			int newTileX = (int)(-(mapCurrentX) + x)/32;
			int newTileY = (int)(-(mapCurrentY) + y)/32;
			map.setTileId(newTileX, newTileY, 0, 79);
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