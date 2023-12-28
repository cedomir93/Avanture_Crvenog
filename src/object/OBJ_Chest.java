package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {

	GamePanel gp;
//	Entity loot;
//	boolean opened = false;
	public static final String objName = "Chest";
	public OBJ_Chest(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = objName;
		type = type_obstacle;
		image = setup("/objects/chest", gp.tileSize, gp.tileSize);
		image2 = setup("/objects/chest_opened", gp.tileSize, gp.tileSize);
		down1 = image;
		collision = true;
		
		solidArea.x= 4;
		solidArea.y= 16;
		solidArea.width = 40;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	public void setLoot(Entity loot) {
		this.loot = loot;
		setDialogue();
	}
	public void setDialogue() {
		this.dialouge[0][0] = "You open the chest and find a " + this.loot.name + "!\n...But you cannot carry any more!";
		this.dialouge[1][0] = "You open the chest and find a " + this.loot.name + "!\nYou obtain the " + this.loot.name + "!";
		this.dialouge[2][0] = "It's empty.";
	}
	public void interact() {
		
		if (opened == false) {
			gp.playSE(3);
			
			if (gp.player.canObtainItem(loot) == false){
				startDialogue(this, 0);
			}
			else {
				startDialogue(this, 1);
				down1 = image2;
				opened = true;
			}
		}
		else {
			startDialogue(this, 2);
		}
	}
}