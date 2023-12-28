package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity{

	public static final String objName = "Lantern";
	GamePanel gp;
	public OBJ_Lantern(GamePanel gp) {
		super(gp);
//		this.gp = gp;
		type=type_light;
		name = objName;
		down1 = setup("/objects/lantern", gp.tileSize, gp.tileSize);
		desription = "[Latern]\n Shows your\nsurroundings at night!";
		price = 50;
		lightRadius = 250;
	}

	
}
