package object;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Door_Iron;

public class OBJ_Door_Iron extends Entity {
	GamePanel gp;
	
	public static final String objName = "Iron Door";
	
	public OBJ_Door_Iron(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = type_obstacle;
		this.name = objName;
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/door_iron", gp.tileSize, gp.tileSize);
		
		this.collision = true;
		this.solidArea.x = 0;
		this.solidArea.y = 16;
		this.solidArea.width = 48;
		this.solidArea.height = 32;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		setDialogue();
	}
	
	public void setDialogue() {
		dialouge[0][0] = "Hmm, there has to be a way to open this \ndoor...";
	}
	
	public void interact() {
		startDialogue(this, 0); 
	}
}