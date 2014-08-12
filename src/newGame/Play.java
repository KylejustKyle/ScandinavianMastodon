package newGame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;

public class Play extends BasicGameState{

   Animation charSprite, movingUp, movingDown, movingLeft, movingRight;
   private TiledMap levelMap;
   private int mapWidth = 1600;
   private final int screenWidth = 640;
   private float charMapPosX, charMapPosY, charScreenPosX, charScreenPosY, mapPosX, mapPosY;
   private boolean dead = false;
   private double fallAmount = 0;
   private double jumpAmount;
   private int inJump;
   
   public Play(int state){
   }
   
   public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
      levelMap = new TiledMap("res/test.tmx");
      mapPosX = 0;
      mapPosY = 0;
      
      Image[] walkUp = {new Image("res/char_back1.png"), new Image("res/char_back2.png")}; //these are the images to be used in the "walkUp" animation
      Image[] walkDown = {new Image("res/char_front1.png"), new Image("res/char_front2.png")};
      Image[] walkLeft = {new Image("res/char_left1.png"), new Image("res/char_left2.png")};
      Image[] walkRight = {new Image("res/char_right1.png"), new Image("res/char_right2.png")};
      int[] duration = {200,200};
      
      movingUp = new Animation(walkUp, duration, false); //each animation takes array of images, duration for each image, and autoUpdate (just set to false)
      movingDown = new Animation(walkDown, duration, false);
      movingLeft = new Animation(walkLeft, duration, false);
      movingRight = new Animation(walkRight, duration, false);
      charSprite = movingRight;
   
      charMapPosX = 40;
      charScreenPosX = charMapPosX;
      charMapPosY = 40;
      charScreenPosY = charMapPosY; 
      
      inJump = 0;
   }
   
   public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
	   levelMap.render((int)mapPosX, (int)mapPosY);
	   charSprite.draw(charScreenPosX, charScreenPosY);
	   
	   if (dead == true) {
		   g.drawString("You Suck", 100, 100);
	   }
   }
   
   public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {	   
	   Input input = gc.getInput();
	   
	   if (input.isKeyDown(Input.KEY_RIGHT)) {
		   charSprite = movingRight;
		   if (charScreenPosX + 32 < screenWidth) {	   
			   if (charScreenPosX + 32 >= screenWidth - 100) {
				   if (screenWidth - mapWidth < mapPosX) {
					   mapPosX = mapPosX - delta * 0.2f;
					   charMapPosX = charMapPosX + delta * 0.2f;					   
				   } else {
					   charScreenPosX = charScreenPosX + delta * 0.2f;
					   charMapPosX = charMapPosX + delta * 0.2f;				   
				   }
			   } else {
				   charScreenPosX = charScreenPosX + delta * 0.2f;
				   charMapPosX = charMapPosX + delta * 0.2f;			   
			   }
		   }
		   charSprite.update(delta);
	   }  
	   
	   if (input.isKeyDown(Input.KEY_LEFT)) {
		   charSprite = movingLeft;		   
		   if (charScreenPosX > 0) {		   
			   if (charScreenPosX <= 100) {
				   if (mapPosX < 0) {
					   mapPosX = mapPosX + delta * 0.2f;
					   charMapPosX = charMapPosX - delta * 0.2f;					   
				   } else {
					   charScreenPosX = charScreenPosX - delta * 0.2f;
					   charMapPosX = charMapPosX - delta * 0.2f;				   
				   }
			   } else {
				   charScreenPosX = charScreenPosX - delta * 0.2f;
				   charMapPosX = charMapPosX - delta * 0.2f;			   
			   }
		   }
		   charSprite.update(delta);
	   }   	
	   
	   if (inJump == 0) {
		   if (input.isKeyDown(Input.KEY_SPACE)) {
			   inJump = 1;
			   jumpAmount = 1.5;
			   charScreenPosY = charScreenPosY - (delta * 0.4f * (float)jumpAmount);			   
			   charMapPosY = charMapPosY - (delta * 0.4f * (float)jumpAmount);		   
		   }
	   } else {
		   jumpAmount = jumpAmount - 0.003;
		   charScreenPosY = charScreenPosY - (delta * 0.4f * (float)jumpAmount);			   
		   charMapPosY = charMapPosY - (delta * 0.4f * (float)jumpAmount);			   
	   }
	   
	   int collisionState = checkGroundCollision(charMapPosX, charMapPosY);
	   if (collisionState == 0) {
		   if (fallAmount < 1.0) {
			   fallAmount = fallAmount + 0.003;
		   }
		   
		   charScreenPosY = charScreenPosY + (delta * 0.4f * (float)fallAmount);
		   charMapPosY = charMapPosY + (delta * 0.4f * (float)fallAmount);
	   } else { 
		   fallAmount = 0;
		   if (collisionState == 2) {
			   dead = true;
		   }
	   }
	   
   }
   
   // 0 for no collision, 1 for collision, 2 for death
   private int checkGroundCollision(float x, float y) {  
	   y = y + 32;
	   int xBlock = (int)x/32;
	   int yBlock = (int)y/32;
	   int collisionLayer = levelMap.getLayerIndex("Midground");
	   
	   if ( yBlock == levelMap.getHeight()) {
		   inJump = 0;
		   return 2;
	   } else if (levelMap.getTileId(xBlock, yBlock, collisionLayer) == 0) {
		   return 0;
	   } else {
		   inJump = 0;
		   return 1;
	   }
   }
   
   public int getID(){
      return 1;
   }
}