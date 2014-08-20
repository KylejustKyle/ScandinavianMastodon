package kyleandjulian.scandinavianmastodon;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;


public class TiledMapExtended extends TiledMap {
	
	public TiledMapExtended(String s) throws SlickException {
		super(s);
	}
	
	// return x coord of top left corner of viewport centered on TiledMap
	// screenWidth is total width of the window in pixels, allocatedPixels is horizontal pixels taken up by non-viewport elements
	public float initializeViewportX(int screenWidth, int allocatedPixels) {
		return (float)-(getPixelWidth() - (screenWidth - allocatedPixels))/2;
	}
	
	// return y coord of top left corner of viewport centered on TiledMap
	// screenHeight is total height of the window in pixels, allocatedPixels is vertical pixels taken up by non-viewport elements
	public float initializeViewportY(int screenHeight, int allocatedPixels) {
		return (float)-(getPixelHeight() - (screenHeight - allocatedPixels))/2;
	}	
	
	// return the width of the minimap focus rectangle 
	// screenWidth is total width of the window in pixels, allocatedPixels is horizontal pixels taken up by non-viewport elements	
	public float getMinimapFocusRectWidth(int screenWidth, int allocatedPixels, int minimapWidth) {
		return minimapWidth * (screenWidth - allocatedPixels)/getPixelWidth();
	}
	
	// return the height of the minimap focus rectangle 
	// screenHeight is total height of the window in pixels, allocatedPixels is vertical pixels taken up by non-viewport elements	
	public float getMinimapFocusRectHeight(int screenHeight, int allocatedPixels, int minimapHeight) {
		return minimapHeight * (screenHeight - allocatedPixels)/getPixelHeight();
	}	
	
	// get the scale to shrink the TiledMap to fit in the minimap
	public float getMinimapScaleX() {
		return GlobalConfig.PLAYSTATE_RIGHTPANEL_WIDTH / (float)getPixelWidth();
	}
	
	// get the scale to shrink the TiledMap to fit in the minimap
	public float getMinimapScaleY() {
		return GlobalConfig.PLAYSTATE_MINIMAP_HEIGHT / (float)getPixelHeight();
	}	
	
	// return x coord of the top left corner of the minimap focus rectangle
	// screenWidth is total width of the window in pixels, mapCurrentX is the x coord of the top left corner of the main map's current viewport
	public float getMinimapFocusRectX(int screenWidth, float mapCurrentX) {
		return (GlobalConfig.PLAYSTATE_RIGHTPANEL_WIDTH * -(mapCurrentX/getPixelWidth())) + (screenWidth - GlobalConfig.PLAYSTATE_BORDER - GlobalConfig.PLAYSTATE_RIGHTPANEL_WIDTH);
	}
	
	// return y coord of the top left corner of the minimap focus rectangle
	// screenHeight is total width of the window in pixels, mapCurrentY is the y coord of the top left corner of the main map's current viewport
	public float getMinimapFocusRectY(int screenHeight, float mapCurrentY) {
		return (GlobalConfig.PLAYSTATE_MINIMAP_HEIGHT * -(mapCurrentY/getPixelHeight())) + GlobalConfig.PLAYSTATE_BORDER;
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
