package kyleandjulian.scandinavianmastodon;

public class GameData {
	private int level;
	
	public GameData() {
		level = 1;
	}
	
	// return current level
	public int getLevel() {
		return level;
	}	
	
	// set a level
	public void setLevel(int i) {
		level = i;
	}
	
	// increment to next level
	public void incrementLevel() {
		level = level + 1;
	}
}
