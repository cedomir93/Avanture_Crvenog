package monster;

import java.util.Random;

import data.Progress;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Door_Iron;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;

public class MON_SkeletonLord extends Entity{

	GamePanel gp;
	
	public static final String monName = "Skeleton Lord";
	
	public MON_SkeletonLord(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_monster;
		boss = true;
		name = monName;
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 50;
		life = maxLife;
		attack = 15;
		defense = 2;
		exp = 100;
		knockBackPower = 7;
		sleep = true;
		int size = gp.tileSize * 5;
		
		solidArea.x = 48;
		solidArea.y = 48;
		solidArea.width = size - 48*2;
		solidArea.height = size - 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 170;
		attackArea.height = 170;
		motion1_duration = 25;
		motion2_duration = 40;
		
		getImage();
		getAttackImage();
		setDialogue();
		
	}
	public void getImage() {
		
		int i = 5;
		
		if (inRage == false) {
			up1 = setup("/monster/skeletonlord_up_1", gp.tileSize * i, gp.tileSize * i);
			up2 = setup("/monster/skeletonlord_up_2", gp.tileSize * i, gp.tileSize * i);
			down1 = setup("/monster/skeletonlord_down_1", gp.tileSize * i, gp.tileSize * i);
			down2 = setup("/monster/skeletonlord_down_2", gp.tileSize * i, gp.tileSize * i);
			left1 = setup("/monster/skeletonlord_left_1", gp.tileSize * i, gp.tileSize * i);
			left2 = setup("/monster/skeletonlord_left_2", gp.tileSize * i, gp.tileSize * i);
			right1 = setup("/monster/skeletonlord_right_1", gp.tileSize * i, gp.tileSize * i);
			right2 = setup("/monster/skeletonlord_right_2", gp.tileSize * i, gp.tileSize * i);
		}
		if (inRage == true) {
			up1 = setup("/monster/skeletonlord_phase2_up_1", gp.tileSize * i, gp.tileSize * i);
			up2 = setup("/monster/skeletonlord_phase2_up_2", gp.tileSize * i, gp.tileSize * i);
			down1 = setup("/monster/skeletonlord_phase2_down_1", gp.tileSize * i, gp.tileSize * i);
			down2 = setup("/monster/skeletonlord_phase2_down_2", gp.tileSize * i, gp.tileSize * i);
			left1 = setup("/monster/skeletonlord_phase2_left_1", gp.tileSize * i, gp.tileSize * i);
			left2 = setup("/monster/skeletonlord_phase2_left_2", gp.tileSize * i, gp.tileSize * i);
			right1 = setup("/monster/skeletonlord_phase2_right_1", gp.tileSize * i, gp.tileSize * i);
			right2 = setup("/monster/skeletonlord_phase2_right_2", gp.tileSize * i, gp.tileSize * i);
		}
	}
	public void getAttackImage() {
	
		int i = 5;
		
		if (inRage == false) {
			attackUp1 = setup("/monster/skeletonlord_attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
			attackUp2 = setup("/monster/skeletonlord_attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);
			attackDown1 = setup("/monster/skeletonlord_attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
			attackDown2 = setup("/monster/skeletonlord_attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);
			attackLeft1 = setup("/monster/skeletonlord_attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
			attackLeft2 = setup("/monster/skeletonlord_attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);
			attackRight1 = setup("/monster/skeletonlord_attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
			attackRight2 = setup("/monster/skeletonlord_attack_right_2", gp.tileSize * i * 2, gp.tileSize * i);
		}
		if (inRage == true) {
			attackUp1 = setup("/monster/skeletonlord_phase2_attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
			attackUp2 = setup("/monster/skeletonlord_phase2_attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);
			attackDown1 = setup("/monster/skeletonlord_phase2_attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
			attackDown2 = setup("/monster/skeletonlord_phase2_attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);
			attackLeft1 = setup("/monster/skeletonlord_phase2_attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
			attackLeft2 = setup("/monster/skeletonlord_phase2_attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);
			attackRight1 = setup("/monster/skeletonlord_phase2_attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
			attackRight2 = setup("/monster/skeletonlord_phase2_attack_right_2", gp.tileSize * i * 2, gp.tileSize * i);
		}
	}
	public void setDialogue() {
		dialouge[0][0] = "No one can steal my treasure!";
		dialouge[0][1] = "You will die here!";
		dialouge[0][2] = "WELCOME TO YOUR DOOM!";
	}
	public void setAction() {
		
		if (inRage == false && life < maxLife / 2) {
			inRage = true;
			getImage();
			getAttackImage();
			defaultSpeed++;
			speed = defaultSpeed;
			attack *= 2;	
		}
		if (getTileDistance(gp.player) < 10) {
			moveTowardPlayer(60);
		}
		else {
			getRandomDiretion(120);
		}
		// Check if it attacks
		if (attacking == false) {
			gp.getClass();
			checkAttackOrNot(60, gp.tileSize*7, gp.tileSize*5);
		}
	}
	public void damageReaction() { actionLockCounter = 0; }
	public void checkDrop() {
		
		gp.bossBattleOn = false;
		Progress.skeletonLordDefeated = true;
		gp.stopMusic();
		gp.playMusic(19);
		
		for (int i = 0; i < gp.obj[1].length; i++) {
			if (this.gp.obj[this.gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name == OBJ_Door_Iron.objName) {
				this.gp.playSE(21);
				this.gp.obj[this.gp.currentMap][i] = null;
			} 
		} 
		int i = new Random().nextInt(100) + 1;
		if (i < 50)
			dropItem((Entity)new OBJ_Coin_Bronze(this.gp)); 
		if (i >= 50 && i < 75)
			dropItem((Entity)new OBJ_Heart(this.gp)); 
		if (i >= 75 && i < 90)
			dropItem((Entity)new OBJ_ManaCrystal(this.gp)); 
		if (i >= 90)
			dropItem((Entity)new OBJ_Potion_Red(this.gp)); 
	}
}
