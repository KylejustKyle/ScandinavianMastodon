package kyleandjulian.scandinavianmastodon;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;


public class TiledMapExtended extends TiledMap {
	private float viewAreaXLoc = 0;  // top left corner of current view area, relative to TiledMap's coordinates
	private float viewAreaYLoc = 0;  // top left corner of current view area, relative to TiledMap's coordinates
	private float miniMapScaleX;  // value to scale TiledMap down to fit in minimap frame
	private float miniMapScaleY;  // value to scale TiledMap down to fit in minimap frame
	private int miniMapXShift;  // X-hift for rendering minimap after scaling
	private int miniMapYShift;  // Y-shift for rendering minimap after scaling
	private int miniMapFocusRectWidth;  // width of the focus rectangle showing current viewing area on minimap
	private int miniMapFocusRectHeight;  // height of the focus rectangle showing current viewing area on minimap
	private boolean renderMiniMap;
	private boolean renderMiniMapFocusRect;
	
	
	// constructor
	public TiledMapExtended(String s, boolean drawMiniMap, boolean drawMiniMapFocusRect) throws SlickException {
		super(s);
		renderMiniMap = drawMiniMap;
		renderMiniMapFocusRect = drawMiniMapFocusRect;
		
		if (renderMiniMap == true) {
			miniMapScaleX = GlobalConfig.PLAYSTATE_MINIMAP_WIDTH/(float)getPixelWidth();
			miniMapScaleY = GlobalConfig.PLAYSTATE_MINIMAP_HEIGHT/(float)getPixelHeight();				
			miniMapXShift = (int)((1/miniMapScaleX) * GlobalConfig.PLAYSTATE_MINIMAP_X);
			miniMapYShift = (int)((1/miniMapScaleY) * GlobalConfig.PLAYSTATE_MINIMAP_Y);
			if (renderMiniMapFocusRect == true) {
				miniMapFocusRectWidth = (int)(miniMapScaleX * GlobalConfig.PLAYSTATE_VIEWPORT_WIDTH);
				miniMapFocusRectHeight = (int)(miniMapScaleY * GlobalConfig.PLAYSTATE_VIEWPORT_HEIGHT);			
			}			
		}
	}
	
	
	// center the current view area
	public void centerViewArea() {
		viewAreaXLoc = (getPixelWidth() - GlobalConfig.PLAYSTATE_VIEWPORT_WIDTH)/2;
		viewAreaYLoc = (getPixelHeight() - GlobalConfig.PLAYSTATE_VIEWPORT_HEIGHT)/2;		
	}
	
	
	// change the X of the current view area, positive changeX shifts right, negative changeX shifts left
	public void updateViewAreaXLoc(float changeX) {
		viewAreaXLoc = viewAreaXLoc + changeX;
		
		// keep view area from crossing TiledMap's left edge
		if (viewAreaXLoc < 0) {
			viewAreaXLoc = 0;
		}
		
		// keep the view area from crossing TiledMap's right edge
		if (viewAreaXLoc > getPixelWidth() - GlobalConfig.PLAYSTATE_VIEWPORT_WIDTH) {
			viewAreaXLoc = getPixelWidth() - GlobalConfig.PLAYSTATE_VIEWPORT_WIDTH;
		}
	}
	
	
	// change the Y of the current view area, positive changeY shifts right, negative changeY shifts left
	public void updateViewAreaYLoc(float changeY) {
		viewAreaYLoc = viewAreaYLoc + changeY;
		
		// keep view area from crossing TiledMap's top edge
		if (viewAreaYLoc < 0) {
			viewAreaYLoc = 0;
		}
		
		// keep the view area from crossing TiledMap's bottom edge
		if (viewAreaYLoc > getPixelHeight() - GlobalConfig.PLAYSTATE_VIEWPORT_HEIGHT) {
			viewAreaYLoc = getPixelHeight() - GlobalConfig.PLAYSTATE_VIEWPORT_HEIGHT;
		}
	}	
	
	
	// render all elements on screen
	public void render(Graphics g) {
		renderMainMap(g);
		
		if (renderMiniMap == true) {
			renderMiniMap(g);
		}
		
		if (renderMiniMapFocusRect == true) {
			renderMiniMapFocusRectangle(g);
		}
	}
	
	
	// render the TiledMap on screen
	private void renderMainMap(Graphics g) {
		int renderXLoc;
		int renderYLoc;
		
		renderXLoc = (int)(-viewAreaXLoc) + GlobalConfig.PLAYSTATE_VIEWPORT_X;
		renderYLoc = (int)(-viewAreaYLoc) + GlobalConfig.PLAYSTATE_VIEWPORT_Y;
		g.setClip(GlobalConfig.PLAYSTATE_VIEWPORT_X, GlobalConfig.PLAYSTATE_VIEWPORT_Y, GlobalConfig.PLAYSTATE_VIEWPORT_WIDTH, GlobalConfig.PLAYSTATE_VIEWPORT_HEIGHT);
		render(renderXLoc, renderYLoc);
		g.clearClip();
	}
	
	
	// render the TiledMap into the minimap frame
	private void renderMiniMap(Graphics g) {	
		g.scale(miniMapScaleX, miniMapScaleY);
		render(miniMapXShift, miniMapYShift);
		g.resetTransform();
	}
	
	
	// render the focus rectangle on the minimap
	private void renderMiniMapFocusRectangle(Graphics g) {
		int renderXLoc;
		int renderYLoc;
		
		// get X and Y of focus rectangle relative to minimap frame
		renderXLoc = (int)(viewAreaXLoc * miniMapScaleX);
		renderYLoc = (int)(viewAreaYLoc * miniMapScaleY);
		
		// adjust X and Y of focus rectangle for minimap location
		renderXLoc = renderXLoc + GlobalConfig.PLAYSTATE_MINIMAP_X;
		renderYLoc = renderYLoc + GlobalConfig.PLAYSTATE_MINIMAP_Y;
		
		g.drawRect(renderXLoc, renderYLoc, miniMapFocusRectWidth, miniMapFocusRectHeight);
	}
	
	
	// handle mouseclicks on the main viewport
	public void mainViewPortClicked(int button, int x, int y, int currentMenuSelection) {
		int tileX;
		int tileY;
		
		tileX = (int)(viewAreaXLoc + x - GlobalConfig.PLAYSTATE_VIEWPORT_X)/getTileWidth();
		tileY = (int)(viewAreaYLoc + y - GlobalConfig.PLAYSTATE_VIEWPORT_Y)/getTileHeight();
		
		if (button == 0) {
			if (currentMenuSelection >= 0) {
				setTileId(tileX, tileY, 2, currentMenuSelection);
			}
		} else {
			setTileId(tileX, tileY, 2, 0);
		}
	}
	
	
	// handle mouseclicks on the minimap
	public void miniMapClicked(int x, int y) {
		float localX;
		float localY;
		
		localX = x - GlobalConfig.PLAYSTATE_MINIMAP_X;
		localY = y - GlobalConfig.PLAYSTATE_MINIMAP_Y;
		localX = localX * (1/miniMapScaleX);
		localY = localY * (1/miniMapScaleY);
		
		localX = localX - GlobalConfig.PLAYSTATE_VIEWPORT_WIDTH/2;
		localY = localY - GlobalConfig.PLAYSTATE_VIEWPORT_HEIGHT/2;
		
		if (localX < 0) {
			localX = 0;
		} else if (localX > getPixelWidth() - GlobalConfig.PLAYSTATE_VIEWPORT_WIDTH) {
			localX = getPixelWidth() - GlobalConfig.PLAYSTATE_VIEWPORT_WIDTH;
		}
		
		if (localY < 0) {
			localY = 0;
		} else if (localY > getPixelHeight() - GlobalConfig.PLAYSTATE_VIEWPORT_HEIGHT) {
			localY = getPixelHeight() - GlobalConfig.PLAYSTATE_VIEWPORT_HEIGHT;
		}
		
		viewAreaXLoc = localX;
		viewAreaYLoc = localY;
	}
	
	
	// return TiledMap width in pixels
	public int getPixelWidth() {
		return getWidth() * getTileWidth();
	}
	
	
	// return TiledMap height in pixels
	public int getPixelHeight() {
		return getHeight() * getTileHeight();
	}
}
