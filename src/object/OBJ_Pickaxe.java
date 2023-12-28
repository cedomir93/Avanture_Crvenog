package object;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Pickaxe;

public class OBJ_Pickaxe extends Entity {
	public static final String objName = "Pickaxe";
	
	public OBJ_Pickaxe(GamePanel gp) {
		super(gp);
		
		type = type_pickaxe;
		name = objName;
		down1 = setup("/objects/pickaxe", gp.tileSize, gp.tileSize);
		attackValue = 4;
		attackArea.width = 30;
		attackArea.height = 30;
		desription = "[Pickaxe]\nYou can break the wall with it!";
		price = 75;
		knockBackPower = 10;
		motion1_duration = 15;
		motion2_duration = 25;
	}
}
