package monster;

import entity.Entity;
import main.GamePanel;
import entity.Entity;
import entity.Projectile;
import java.util.Random;
import main.GamePanel;
import monster.MON_RedSlime;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;
import object.OBJ_Rock;
import object.OBJ_Shield_Wood;

public class MON_RedSlime extends Entity{

	GamePanel gp;
	public MON_RedSlime(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_monster;
		name = "Red Slime";
		defaultSpeed = 2;
		speed = defaultSpeed;
		maxLife = 10;
		life = this.maxLife;
		attack = 7;
		defense = 0;
		exp = 6;
		projectile = new OBJ_Rock(gp);
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	public void getImage() {
		
		up1 = setup("/monster/redslime_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/redslime_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/redslime_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/redslime_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/redslime_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/redslime_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/redslime_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/redslime_down_2", gp.tileSize, gp.tileSize);
	}
	public void setAction() {
		
		int xDistance = Math.abs(worldX - gp.player.worldX);
		int yDistance = Math.abs(worldY - gp.player.worldY);
		int tileDistance = (xDistance + yDistance)/gp.tileSize;
		
		if (onPath == true) {
			
			// Check if it stops chasing
			checkStopChasingOrNot(gp.player, 15, 100);
			if (tileDistance > 20) {
				onPath = false;
			} 

			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

			checkShootOrNot(200, 30);
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
		int i = new Random().nextInt(100)+1;
		
		// SET THE MONSTER DROP
		if (i < 50) {
			dropItem(new OBJ_Coin_Bronze(gp));
		}
		if (i >= 50 && i < 75) {
			dropItem(new OBJ_Heart(gp));
		}
		if (i >= 75 && i < 100) {
			dropItem(new OBJ_ManaCrystal(gp));
		}
	}
}
