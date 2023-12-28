package tile_interactive;


import entity.Entity;
import java.awt.Color;
import java.util.Random;
import main.GamePanel;
//import object.OBJ_Coin_Gold;
//import object.OBJ_Coin_Silver;
import object.OBJ_Heart;
import object.OBJ_Potion_Red;
import tile_interactive.IT_DestructibleWall;
import tile_interactive.InteractiveTile;

public class IT_DestructibleWall extends InteractiveTile {
	GamePanel gp;
	
	public static final String objName = "Destructible Wall";
	
	public IT_DestructibleWall(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		gp.getClass();
		this.worldX = 48 * col;
		gp.getClass();
		this.worldY = 48 * row;
		this.name = "Destructible Wall";
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/tiles_interactive/destructiblewall", 48, 48);
		this.destructible = true;
		this.life = 2;
	}
	
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		if (entity.currentWeapon.type == 10)
			isCorrectItem = true; 
		return isCorrectItem;
	}
	
	public void playSE() { this.gp.playSE(20); }
	
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = null;
		return tile;
	}
	
	public Color getParticleColor() {
		Color color = new Color(65, 65, 65);
		return color;
	}
	
	public int getParticleSize() {
		int size = 6;
		return size;
	}
	
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}
	
	public void checkDrop() {
		int i = (new Random()).nextInt(100) + 1;
		if (i >= 70 && i < 85)
//			dropItem((Entity)new OBJ_Coin_Silver(this.gp)); 
		if (i >= 85 && i < 90)
			dropItem((Entity)new OBJ_Heart(this.gp)); 
		if (i >= 90 && i < 95)
//			dropItem((Entity)new OBJ_Coin_Gold(this.gp)); 
		if (i >= 95 && i < 100)
			dropItem((Entity)new OBJ_Potion_Red(this.gp)); 
	}
}
