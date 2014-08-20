package kyleandjulian.scandinavianmastodon;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;

public class PlayState extends BasicGameState{
	private Image blackBlock;	
	private Image menubar;
	private TiledMapExtended map;
	private float mapCurrentX;  // current top left corner of viewable part of tiledmap
	private float mapCurrentY;
	private float minimapFocusRectWidth;
	private float minimapFocusRectHeight;
	private float minimapScaleX;
	private float minimapScaleY;
	private float minimapFrameX;
	private float minimapFrameY;
	private int viewportWidth;
	private int viewportHeight;
	private int zoomState;
	private float zoomScale;
	
	
	public PlayState(int state){
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		blackBlock = new Image("res/black_block.png");		
		menubar = new Image("res/menubar.png");
		map = new TiledMapExtended("res/tiledmap_temp.tmx");
		
		// initialize viewport at center of map
		mapCurrentX = map.initializeViewportX(gc.getWidth(), GlobalConfig.PLAYSTATE_BORDER * 3 + GlobalConfig.PLAYSTATE_RIGHTPANEL_WIDTH);
		mapCurrentY = map.initializeViewportY(gc.getHeight(), GlobalConfig.PLAYSTATE_BORDER * 2);
		
		// calculate minimap focus rectangle dimensions
		minimapFocusRectWidth = map.getMinimapFocusRectWidth(gc.getWidth(), GlobalConfig.PLAYSTATE_BORDER * 3 + GlobalConfig.PLAYSTATE_RIGHTPANEL_WIDTH, GlobalConfig.PLAYSTATE_RIGHTPANEL_WIDTH);
		minimapFocusRectHeight = map.getMinimapFocusRectHeight(gc.getHeight(), GlobalConfig.PLAYSTATE_BORDER * 2, GlobalConfig.PLAYSTATE_MINIMAP_HEIGHT);
	
		// calculate minimap scale
		minimapScaleX = map.getMinimapScaleX();
		minimapScaleY = map.getMinimapScaleY();
		
		// calculate minimap coords
		minimapFrameX = gc.getWidth() - GlobalConfig.PLAYSTATE_BORDER - GlobalConfig.PLAYSTATE_RIGHTPANEL_WIDTH;
		minimapFrameY = GlobalConfig.PLAYSTATE_BORDER;
		
		// calculate viewport size
		viewportWidth = gc.getWidth() - GlobalConfig.PLAYSTATE_BORDER * 3 - GlobalConfig.PLAYSTATE_RIGHTPANEL_WIDTH;
		viewportHeight = gc.getHeight() - GlobalConfig.PLAYSTATE_BORDER * 2;
		
		// set zoom state
		zoomState = 0;
		zoomScale = 1;
	}
   
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// draw main map
		g.scale(zoomScale, zoomScale);
		map.render((int)mapCurrentX, (int)mapCurrentY);
		g.resetTransform();
		
		// draw borders
		blackBlock.draw(0, 0, GlobalConfig.PLAYSTATE_BORDER, gc.getHeight());
		blackBlock.draw(0, 0, gc.getWidth(), GlobalConfig.PLAYSTATE_BORDER);
		blackBlock.draw(0, gc.getHeight() - GlobalConfig.PLAYSTATE_BORDER, gc.getWidth(), GlobalConfig.PLAYSTATE_BORDER);
		blackBlock.draw(gc.getWidth() - GlobalConfig.PLAYSTATE_RIGHTPANEL_WIDTH - (GlobalConfig.PLAYSTATE_BORDER * 2), 0, GlobalConfig.PLAYSTATE_RIGHTPANEL_WIDTH + (GlobalConfig.PLAYSTATE_BORDER * 2), gc.getHeight());
		
		// draw minimap
		g.scale(minimapScaleX, minimapScaleY);
		map.render((int)((1/minimapScaleX) * minimapFrameX), (int)((1/minimapScaleY) * minimapFrameY));
		g.resetTransform();
		g.drawRect(map.getMinimapFocusRectX(gc.getWidth(), mapCurrentX), map.getMinimapFocusRectY(gc.getHeight(), mapCurrentY), minimapFocusRectWidth, minimapFocusRectHeight);	
	
		// draw menu pane
		menubar.draw(gc.getWidth() - GlobalConfig.PLAYSTATE_RIGHTPANEL_WIDTH - GlobalConfig.PLAYSTATE_BORDER, GlobalConfig.PLAYSTATE_BORDER * 2 + GlobalConfig.PLAYSTATE_MINIMAP_HEIGHT, GlobalConfig.PLAYSTATE_RIGHTPANEL_WIDTH, GlobalConfig.PLAYSTATE_MENU_HEIGHT);
	}
   
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {	
		Input input = gc.getInput();
		
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			if (mapCurrentX - viewportWidth > -(map.getPixelWidth())) {
				mapCurrentX = mapCurrentX - delta * 0.4f;
			} else {
				mapCurrentX = -(map.getPixelWidth()) + viewportWidth;
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
			if (mapCurrentY - viewportHeight > -(map.getPixelHeight())) {
				mapCurrentY = mapCurrentY - delta * 0.4f;
			} else {
				mapCurrentY = -(map.getPixelHeight()) + viewportHeight;
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
		if (button == 0 && x >= minimapFrameX && x <= minimapFrameX + GlobalConfig.PLAYSTATE_RIGHTPANEL_WIDTH && y >= minimapFrameY && y <= minimapFrameY + GlobalConfig.PLAYSTATE_MINIMAP_HEIGHT) {
			float tempX = x - minimapFrameX - minimapFocusRectWidth/2;
			mapCurrentX = -(tempX * ((float)map.getPixelWidth())/GlobalConfig.PLAYSTATE_RIGHTPANEL_WIDTH);
			
			float tempY = y - minimapFrameY - minimapFocusRectHeight/2;  // realign y and calculate mapCurrentY
			mapCurrentY = -(tempY * ((float)map.getPixelHeight())/GlobalConfig.PLAYSTATE_MINIMAP_HEIGHT);
			
			// make sure the new focus view is in x boundaries
			if (mapCurrentX > 0 ) {
				mapCurrentX = 0;
			} else if (mapCurrentX < -(map.getPixelWidth() - viewportWidth)) {
				mapCurrentX = -(map.getPixelWidth() - viewportWidth);
			}
			
			// make sure the new focus view is in y boundaries			
			if (mapCurrentY > 0 ) {
				mapCurrentY = 0;
			} else if (mapCurrentY < -(map.getPixelHeight() - viewportHeight)) {
				mapCurrentY = -(map.getPixelHeight() - viewportHeight);
			}			
		}	
		
		//click on main map, change tile
		if (button== 0 && x > GlobalConfig.PLAYSTATE_BORDER && x <= GlobalConfig.PLAYSTATE_BORDER + viewportWidth && y > GlobalConfig.PLAYSTATE_BORDER && y <= GlobalConfig.PLAYSTATE_BORDER + viewportHeight) {
			int newTileX = (int)(-(mapCurrentX) + x)/map.getTileWidth();
			int newTileY = (int)(-(mapCurrentY) + y)/map.getTileHeight();
			map.setTileId(newTileX, newTileY, 0, 79);
		}
	}	
	
	public void mouseWheelMoved(int change) {
		if (change < 0) {
			if ( zoomState > -GlobalConfig.PLAYSTATE_MOUSE_ZOOMLIMIT) {
				zoomState--;
				zoomScale = zoomScale - GlobalConfig.PLAYSTATE_ZOOMSTEP;
			}
		} else if (change > 0) {
			if ( zoomState < GlobalConfig.PLAYSTATE_MOUSE_ZOOMLIMIT) {
				zoomState++;
				zoomScale = zoomScale + GlobalConfig.PLAYSTATE_ZOOMSTEP;				
			}			
		}
	}
	
	public int getID(){
		return GlobalConfig.PLAY_STATE;
	}
}