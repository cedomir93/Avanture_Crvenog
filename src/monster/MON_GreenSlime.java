package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Coin_Silver;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

public class MON_GreenSlime extends Entity {

	GamePanel gp;
	public MON_GreenSlime(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_monster;
		name = "Green Slime";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 4;
		life = maxLife;
		attack = 5;
		defense = 0;
		exp = 2;
		
		solidArea.x= 3;
		solidArea.y= 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
		update();
	}
	public void getImage() {
		
		up1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
	}
//	public void update() {
//		super.update();
//		
//		
//	}
	public void setAction() {
		
//		int xDistance = Math.abs(worldX - gp.player.worldX);
//		int yDistance = Math.abs(worldY - gp.player.worldY);
//		int tileDistance = (xDistance + yDistance)/gp.tileSize;
		
		if (onPath == true) {
			
			checkStopChasingOrNot(gp.player, 15, 100);

			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
		}
		else {
			
			checkStartChasingOrNot(gp.player, 5, 100);
			
			getRandomDiretion(120);
		}
	}
	public void damageReaction() {
		
		actionLockCounter = 0;
		direction = gp.player.direction;
//		onPath = true;
	}
	public void checkDrop() {
		
		// CAST A DIE
		int i = (new Random()).nextInt(100) + 1;
		
		// SET THE MONSTER DROP
		if (i < 60) {
			dropItem(new OBJ_Coin_Bronze(this.gp)); 
		}
			
		if (i >= 60 && i < 70) {
			dropItem((Entity)new OBJ_Coin_Silver(this.gp)); 
		}
			
		if (i >= 70 && i < 90) {
			dropItem((Entity)new OBJ_Heart(this.gp)); 
		}
			
		if (i >= 90 && i < 100) {
			dropItem((Entity)new OBJ_ManaCrystal(this.gp)); 
		}
			
	}
}
