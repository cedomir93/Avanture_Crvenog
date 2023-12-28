package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Blue extends Entity{

	public static final String objName = "Shield Blue";
	public OBJ_Shield_Blue(GamePanel gp) {
		super(gp);
		
		type = type_shield;
		name = objName;
		down1 = setup("/objects/shield_blue", gp.tileSize, gp.tileSize);
		defenceValue = 2;
		desription = "[" + name + "]\nA metal shiny shield.";
		price = 150;
	}

}
