package kyleandjulian.scandinavianmastodon;

import org.newdawn.slick.SlickException;

public class MenuBarButton extends Button {
	private int buttonValue;  // value associated with button
	private boolean isSelected = false;  // button currently selected?
	
	
	// constructor with an int associated with the button
	public MenuBarButton(String imageRef, int x, int y, int value) throws SlickException {
		super(imageRef, x, y);
		buttonValue = value;
	}
	
	
	// constructor with an int associated and a hover image
	public MenuBarButton(String imageRef, String hoverImageRef, int x, int y, int value) throws SlickException {
		super(imageRef, hoverImageRef, x, y);
		buttonValue = value;
	}
	
	
	// determine whether the button was clicked
	public boolean isClicked(int cursorX, int cursorY, int offsetX, int offsetY) {
		if (cursorX >= xLoc + offsetX && cursorX <= xLoc + offsetX + width && cursorY >= yLoc + offsetY && cursorY <= yLoc + offsetY + height) {
			return true;
		} else {
			return false;
		}
	}	
	
	
	// return the isSelected value
	public boolean isSelected() {
		return isSelected;
	}
	
	// toggle the button's isSelected value
	public void toggleSelected() {
		if (isSelected == true) {
			isSelected = false;
		} else {
			isSelected = true;
		}
	}
	
	
	// set the button's isSelected value
	public void setSelected(boolean b) {
		isSelected = b;
	}
	
	
	// return the button's value
	public int getValue() {
		return buttonValue;
	}
	
	
	// render with an offset for buttons on specific screen elements
	public void render(int offsetX, int offsetY) {
		if (isSelected == true) {
			image.drawFlash(xLoc + offsetX, yLoc + offsetY, image.getWidth(), image.getHeight(), GlobalConfig.BUTTON_SELECTED_COLOR);
		} else {
			image.draw(xLoc + offsetX, yLoc + offsetY);		
		}	
	}	
}
