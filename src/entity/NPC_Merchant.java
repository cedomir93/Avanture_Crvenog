package entity;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Key;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class NPC_Merchant extends Entity{

	public NPC_Merchant(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialouge();
		setItems();
	}
	public void getImage() {
		
		up1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
	}
	public void setDialouge() {
		
		dialouge[0][0] = "He, he! So, you have found me";
		dialouge[0][1] = "Do you want to trade?";
		
		dialouge[1][0] =  "Come back again, he he he!";
		
		dialouge[2][0] = "YOU'RE BROKE! Need more coin!";
		
		dialouge[3][0] = "Can't carry anymore!";
		
		dialouge[4][0] = "You cannot sell an equipt item";
	}
	public void setItems() {
		
		invenotry.add(new OBJ_Potion_Red(gp));
		invenotry.add(new OBJ_Key(gp));
		invenotry.add(new OBJ_Sword_Normal(gp));
		invenotry.add(new OBJ_Shield_Wood(gp));
		invenotry.add(new OBJ_Shield_Blue(gp));
		invenotry.add(new OBJ_Axe(gp));
	}
	public void speak() {
		
		facePlayer();
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
		
	}
}
