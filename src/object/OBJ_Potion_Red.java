package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity{

	public static final String objName = "Red Potion";
	GamePanel gp;
	
	public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_consumable;
		name = objName;
		value = 5;
		down1 = setup("/objects/potion_red", gp.tileSize, gp.tileSize);
		desription = "[Red Potion]\nHeals your life by "+ value +".";
		price = 25;
		stackable = true;
		setDialogue();
	}
	public void setDialogue() {
		dialouge[0][0] = "You drink the " + this.name + "!\n" + 
			"Your life has been recovered by " + this.value + ".";
	}
	
	public boolean use(Entity entity) {
		startDialogue(this, 0);
		entity.life += this.value;
		this.gp.playSE(2);
		return true;
	}
}
