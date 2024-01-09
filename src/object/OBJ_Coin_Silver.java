package object;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Silver;

public class OBJ_Coin_Silver extends Entity {
	GamePanel gp;
	
	public static final String objName = "Silver Coin";
	
	public OBJ_Coin_Silver(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickupOnly;
		name = objName;
		value = 5;
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/coin_silver", gp.tileSize, gp.tileSize);
	}
	
	public boolean use(Entity entity) {
		this.gp.playSE(1);
		this.gp.ui.addMessage("Coin +" + this.value);
		this.gp.player.coin += this.value;
		return true;
	}
}
