package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class NPC_OldMan extends Entity{

	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		dialogueSet = -1;
		
		getImage();
		setDialouge();
	}
	public void getImage() {
		
		up1 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/oldman_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/oldman_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/oldman_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/oldman_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/oldman_right_2", gp.tileSize, gp.tileSize);
	}
	public void setDialouge() {
		
		dialouge[0][0] = "Hello, lad!";
		dialouge[0][1] = "You are in a deep sleep and cant \nwake up?";
		dialouge[0][2] = "I used to be a great wizard, now \nI can't fight. I am stuck\n into this dimension!";
		dialouge[0][3] = "Best of luck to wake up again";
		
		dialouge[1][0] = "To heal up, walk up to the water and \npress ENTER to drink it. It can restore your\nhealth and powers.";
		dialouge[1][1] = "Monsers are coming back if you rest.\nHave no idea why.";
		dialouge[1][2] = "Also, there is a Seller nerby, \nYou can buy supplies from him.\nHis hut is somewhere in the forest at the\nSouth-West!";
		
		dialouge[2][0] = "I wonder, how to open this door? Key should be\naround here somewhere";
		dialouge[2][1] = "It leads to the Dungeon!";
	}
	public void setAction() {
		
		// Set an if statement for Path Finder, Video 40
		if (onPath == true) {
			
//			int goalCol = 12;
//			int goalRow = 9;
			int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
			int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
			
			searchPath(goalCol, goalRow);
		}
		else {
			
			actionLockCounter++;
		
			if (actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1; // pick up a number from 1 to 100
		
			if (i <= 25) {
				direction = "up";
			}
			if (i > 25 && i <= 50) {
				direction = "down";
			}
			if (i > 50 && i <= 75) {
				direction = "left";
			}
			if (i > 75 && i <= 100) {
				direction = "right";
			}
			actionLockCounter = 0;
		
			}
		}
	}
	public void speak() {
		
		facePlayer();
		startDialogue(this, dialogueSet);
		dialogueSet++;
		
		if (dialouge[dialogueSet][0] == null) {
			dialogueSet--;
		}
//		if (gp.player.life < gp.player.maxLife / 3) {
//			dialogueSet = 1;
//		}
	}
}
