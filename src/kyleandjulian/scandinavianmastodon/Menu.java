package kyleandjulian.scandinavianmastodon;

import java.util.*;
import org.newdawn.slick.*;



public class Menu {
	Image backgroundImage;
	int scrollValue = 0;  // amount the menu is scrolled down from top of menu frame
	int currentSelection = -1;  // -1 as default value with nothing selected
	List<MenuBarButton> buttonArray = new ArrayList<MenuBarButton>();
	
	
	// constructor, imageRef refers to filepath of background
	public Menu(String imageRef) throws SlickException {
		backgroundImage = new Image(imageRef);
	}
	
	
	// scroll menu up
	public void scrollUp() {
		scrollValue = scrollValue - GlobalConfig.MENU_SCROLL_SPEED;
		if (scrollValue < 0) {
			scrollValue = 0;
		}
	}
	
	
	// scroll menu down
	public void scrollDown() {
		scrollValue = scrollValue + GlobalConfig.MENU_SCROLL_SPEED;		
		if (scrollValue + GlobalConfig.PLAYSTATE_MENUBAR_HEIGHT > backgroundImage.getHeight()) {
			scrollValue = backgroundImage.getHeight() - GlobalConfig.PLAYSTATE_MENUBAR_HEIGHT;
		}
	}
	
	
	// reset scroll
	public void resetScroll() {
		scrollValue = 0;
	}
	
	
	// add button
	public void addButton(String imageRef, int x, int y, int value) throws SlickException {
		buttonArray.add(new MenuBarButton(imageRef, x, y, value));
	}
	
	
	// return value
	public int getCurrentSelection() {
		return currentSelection;
	}
	
	
	// render all menu elements on screen
	public void render(Graphics g) {
		g.setClip(GlobalConfig.PLAYSTATE_MENUBAR_X, GlobalConfig.PLAYSTATE_MENUBAR_Y, GlobalConfig.PLAYSTATE_MENUBAR_WIDTH, GlobalConfig.PLAYSTATE_MENUBAR_HEIGHT);
		backgroundImage.draw(GlobalConfig.PLAYSTATE_MENUBAR_X, GlobalConfig.PLAYSTATE_MENUBAR_Y - scrollValue);
		for (int i = 0; i < buttonArray.size(); i++) {
			buttonArray.get(i).render(GlobalConfig.PLAYSTATE_MENUBAR_X, GlobalConfig.PLAYSTATE_MENUBAR_Y - scrollValue);
		}
		g.clearClip();
	}
	
	// handle mouseclicks on menu
	public void clicked(int x, int y) {
		for (int i = 0; i < buttonArray.size(); i++) {
			if (buttonArray.get(i).isClicked(x, y, GlobalConfig.PLAYSTATE_MENUBAR_X, GlobalConfig.PLAYSTATE_MENUBAR_Y - scrollValue) == true) {
				buttonArray.get(i).toggleSelected();
				if (buttonArray.get(i).isSelected() == true) {
					currentSelection = buttonArray.get(i).getValue();
				} else {
					currentSelection = -1;
				}
				for (int j = 0; j < buttonArray.size(); j++) {
					if (j != i) {
						buttonArray.get(j).setSelected(false);
					}
				}
				i = buttonArray.size();
			}
		}		
	}
 	
}
