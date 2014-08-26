package kyleandjulian.scandinavianmastodon;

import org.newdawn.slick.*;
import java.awt.MouseInfo;

public class Button {
	protected Image image;  // button image
	private Image hoverImage;  // button image on hover, optional
	protected int xLoc;  // x location to render button on parent object
	protected int yLoc;  // y location to render button on parent object
	protected int width;  // button width
	protected int height;  // button height
	private boolean hasHoverImage = false;  // button has on hover image?
	private boolean isHover = false;  // button currently being hovered over?
	
	
	// constructor, imageRef refers to filepath, x and y are top left coordinates
	public Button(String imageRef, int x, int y) throws SlickException {
		image = new Image(imageRef);
		xLoc = x;
		yLoc = y;
		width = image.getWidth();
		height = image.getHeight();
	}
	
	
	// constructor with a hover image, to be displayed instead of base image on mouse hover
	public Button(String imageRef, String hoverImageRef, int x, int y) throws SlickException {
		this(imageRef, x, y);
		hasHoverImage = true;
		hoverImage = new Image(hoverImageRef);
	}	
	
	
	// public method to set X
	public void setX(int x) {
		xLoc = x;
	}
	
	
	// public method to set Y
	public void setY(int y) {
		yLoc = y;
	}
	
	
	// call on mouse update to check whether the cursor is hovering over the button
	public void checkMouseHover(int cursorX, int cursorY) {
		if (hasHoverImage == true) {
			if (cursorX >= xLoc && cursorX <= xLoc + width && cursorY >= yLoc && cursorY <= yLoc + height) {
				isHover = true;
			} else {
				isHover = false;
			}
		}
	}
	
	
	// determine whether the button was clicked
	public boolean isClicked(int cursorX, int cursorY) {
		if (cursorX >= xLoc && cursorX <= xLoc + width && cursorY >= yLoc && cursorY <= yLoc + height) {
			return true;
		} else {
			return false;
		}
	}
	
	
	// render the button on main screen
	public void render() {
		if (isHover == true) {
			hoverImage.draw(xLoc, yLoc);				
		} else {
			image.draw(xLoc, yLoc);			
		}
	}
}
