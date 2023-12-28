package tile_interactive;

import entity.Entity;
import java.awt.Color;
import java.util.Random;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_Trunk;
import tile_interactive.InteractiveTile;

public class IT_DryTree extends InteractiveTile {
	GamePanel gp;
	
	public static final String objName = "Dry Tree";
	
	public IT_DryTree(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		gp.getClass();
		this.worldX = 48 * col;
		gp.getClass();
		this.worldY = 48 * row;
		this.name = "Dry Tree";
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/tiles_interactive/drytree", 48, 48);
		this.destructible = true;
		this.life = 1;
	}
	
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		if (entity.currentWeapon.type == type_axe)
			isCorrectItem = true; 
		return isCorrectItem;
	}
	
	public void playSE() { this.gp.playSE(11); }
	
	public InteractiveTile getDestroyedForm() { this.gp.getClass();
		this.gp.getClass();
		return (InteractiveTile) new IT_Trunk(this.gp, this.worldX / 48, this.worldY / 48); }
	
	public Color getParticleColor() {
		Color color = new Color(65, 50, 30);
		return color;
	}
	public int getParticleSize() {
		int size = 6; // 6 pixels
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
		if (i >= 70 && i < 90)
			dropItem((Entity)new OBJ_Coin_Bronze(this.gp)); 
		if (i >= 90 && i < 95)
			dropItem((Entity)new OBJ_Heart(this.gp)); 
		if (i >= 95 && i < 100)
			dropItem((Entity)new OBJ_ManaCrystal(this.gp)); 
	}
}
