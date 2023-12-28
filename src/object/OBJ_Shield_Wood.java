package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Wood extends Entity{

	public static final String objName = "Shield Wood";
	public OBJ_Shield_Wood(GamePanel gp) {
		super(gp);
		
		type = type_shield;
		name = objName;
		down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
		defenceValue = 1;
		desription = "[" + name + "]\nMade by wood.";
		price = 95;
	}

}
