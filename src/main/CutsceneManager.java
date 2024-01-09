package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import entity.PlayerDummy;
import monster.MON_SkeletonLord;
import object.OBJ_BlueHeart;
import object.OBJ_Door_Iron;

public class CutsceneManager {

	GamePanel gp;
	
	Graphics2D g2;
	
	public int sceneNum;
	
	public int scenePhase;
	
	int counter = 0;
	
	float alpha = 0.0F;
	
	int y;
	
	String endCredit;
	
	public final int NA = 0;
	
	public final int opening = 1;
	
	public final int skeletonLord = 2;
	
	public final int ending = 3;
	
	public CutsceneManager(GamePanel gp) {
		this.gp = gp;
		setEndCredit();
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		switch (this.sceneNum) {
			case 1:
				scene_opening();
				break;
			case 2:
				scene_skeletonLord();
				break;
			case 3:
				scene_ending();
				break;
		} 
	}
	
	public void scene_opening() {
		int pressEnterY = 520;
		if (scenePhase == 0) {
			drawBlackBackground(1.0F);
			alpha += 0.005F;
			if (alpha > 1.0F) {
				alpha = 1.0F;
			}
			String text = "This is an island somewhere far away.\n\nBlue Boy, an aspiring adventurer, \ncomes to this island because he hears \nthat it holds a legendary treasure.\n\n\n\n\n";
			drawString(alpha, 35.0F, 170, text, 40);
			drawString(alpha, 35.0F, pressEnterY, "(Press Enter to continue)", 40);
			if (gp.keyH.enterPressed) {
				gp.keyH.enterPressed = false;
				scenePhase++;
			}
		}
		
		if (scenePhase == 1) {
			drawBlackBackground(1.0F);
			alpha -= 0.02F;
			if (this.alpha < 0.0F) {
				
				alpha = 0.0F;
				scenePhase++;
			} 
			String text = "This is an island somewhere far away.\n\nBlue Boy, an aspiring adventurer, \ncomes to this island because he hears \nthat it holds a legendary treasure.\n\n\n\n\n";
			drawString(alpha, 35.0F, 170, text, 40);
			drawString(alpha, 35.0F, pressEnterY, "(Press Enter to continue)", 40);
		} 
		if (scenePhase == 2) {
			drawBlackBackground(1.0F);
			alpha += 0.01F;
			if (alpha > 1.0F)
				alpha = 1.0F; 
			String text = "Can he safely find the treasure on this island,\nwhere dangerous monsters roam?\n\nIt all depends on you.\n\n\n\n\n\n";
			drawString(alpha, 35.0F, 200, text, 40);
			drawString(alpha, 35.0F, pressEnterY, "(Press Enter to continue)", 40);
			
			if (gp.keyH.enterPressed) {
				
				gp.keyH.enterPressed = false;
				scenePhase++;
			} 
		} 
		if (scenePhase == 3) {
			
			drawBlackBackground(1.0F);
			alpha -= 0.02F;
			if (alpha < 0.0F) {
				alpha = 0.0F;
				scenePhase++;
			} 
			String text = "Can he safely find the treasure on this island,\nwhere dangerous monsters roam?\n\nIt all depends on you.\n\n\n\n\n";
			drawString(alpha, 35.0F, 200, text, 40);
			drawString(alpha, 35.0F, pressEnterY, "(Press Enter to continue)", 40);
		} 
		if (scenePhase == 4) {
			
			drawBlackBackground(1.0F);
			alpha += 0.005F;
			
			if (alpha > 1.0F) {
				alpha = 1.0F;
			}
				
			drawString(alpha, 35.0F, 50, "<How to Play>", 40);
			String text = "Move: [Up/Down/Left/Right]\nAttack/Interact/Confirm: [ENTER]\nMagic: [F]\nGuard/Parry: [SPACE]\nInventory/Status: [C]\nMap: [M]   Mini Map: [X]\nPause: [P]\nOptions: [ESC]\n\n";
			drawString(alpha, 35.0F, 120, text, 45);
			drawString(alpha, 35.0F, pressEnterY, "(Press Enter to play)", 40);
			if (gp.keyH.enterPressed) {
				
				gp.keyH.enterPressed = false;
				scenePhase++;
			} 
		} 
		if (scenePhase == 5) {
			gp.keyH.enterPressed = false;
			sceneNum = 0;
			scenePhase = 0;
			gp.getClass();
			gp.gameState = gp.playState;
			gp.playMusic(0);
		} 
	}
	
	public void scene_skeletonLord() {
		if (scenePhase == 0) {
			gp.bossBattleOn = true;
			
			for (int i = 0; i < (gp.obj[1]).length; i++) {
				if (gp.obj[gp.currentMap][i] == null) {
					gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
					gp.getClass();
					gp.obj[gp.currentMap][i].worldX = gp.tileSize * 25;
					this.gp.getClass();
					gp.obj[gp.currentMap][i].worldY = gp.tileSize * 28;
					
					gp.obj[gp.currentMap][i].temp = true;
					gp.playSE(21);
					break;
				} 
			} 
			for (int i = 0; i < (gp.npc[1]).length; i++) {
				
				if (gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				} 
			} 
			this.gp.player.drawing = false;
			this.scenePhase++;
		} 
		if (scenePhase == 1) {
			
			gp.player.worldY -= 2;
			gp.getClass();
			if (gp.player.worldY < gp.tileSize * 16) {
				scenePhase++;
			}
				 
		} 
		if (scenePhase == 2)
			for (int i = 0; i < (gp.monster[1]).length; i++) {
				
				if (gp.monster[gp.currentMap][i] != null && gp.monster[gp.currentMap][i].name == MON_SkeletonLord.monName) {
					
					gp.monster[gp.currentMap][i].sleep = false;
					gp.ui.npc =gp.monster[gp.currentMap][i];
					scenePhase++;
					break;
				} 
			}  
		if (scenePhase == 3)
			gp.ui.drawDialougeScreen(); 
		if (scenePhase == 4) {
			for (int i = 0; i < gp.npc[1].length; i++) {
				if (gp.npc[gp.currentMap][i] != null && gp.npc[this.gp.currentMap][i].name == PlayerDummy.npcName) {
					
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				} 
			} 
			gp.player.drawing = true;
			sceneNum = 0;
			scenePhase = 0;
			gp.getClass();
			gp.gameState = 1;
			gp.stopMusic();
			gp.playMusic(22);
		} 
	}
	
	public void scene_ending() {
		
		if (scenePhase == 0) {
			gp.stopMusic();
			gp.ui.npc = new OBJ_BlueHeart(gp);
			scenePhase++;
		} 
		if (scenePhase == 1) {
			
			gp.ui.drawDialougeScreen();
		}	 
		if (scenePhase == 2) {
			
			gp.playSE(4);
			scenePhase++;
		} 
		if (scenePhase == 3) {
			
			if (counterReached(300) == true) {
				scenePhase++;
			}
		}		  
		if (scenePhase == 4) {
			alpha += 0.005F;
			if (alpha > 1.0F) {
				alpha = 1.0F; 
			}
			drawBlackBackground(alpha);
			if (alpha == 1.0F) {
				alpha = 0.0F;
				scenePhase++;
			} 
		} 
		if (scenePhase == 5) {
			drawBlackBackground(1.0F);
			alpha += 0.005F;
			if (alpha > 1.0F) {
				alpha = 1.0F; 
			}
				
			String text = "That Skeleton has been defeated,\nthe The Red Man contitiues his jorney.\nIt has just begun\nMore updates are coming soon...";
			drawString(alpha, 38.0F, 150, text, 60);
			if (counterReached(600)) { // wait 10 seconds
				gp.playMusic(0);
				scenePhase++;
				alpha = 0.0F;
			} 
		} 
		if (scenePhase == 6) {
			drawBlackBackground(1.0F);
			gp.getClass();
			drawString(1.0F, 120.0F, 576 / 2 + 10, "The Red Man", 40);
			if (counterReached(480))
				scenePhase++; 
		} 
		if (scenePhase == 7) {
			drawBlackBackground(1.0F);
			gp.getClass();
			drawString(1.0F, 38.0F, 576 / 2 - 40, "Program - Eclipse IDE\nMusic - Super Mario World \nand Donkey Kong Country\nPixel Art - 16x16 drawing online\n\n", 40);
			if (counterReached(400))
				scenePhase++; 
		} 
		if (scenePhase == 8) {
			drawBlackBackground(1.0F);
			this.gp.getClass();
			drawString(1.0F, 38.0F, 576 / 2 - 40, "Ајде на српском, бре :D\nЗахваљујем свима који су дошли вечерас \nна мој 30. рођендан.\nШта је моја рођенданска жеља?\nХоћу да сви ви и следеће године \nбудете успешни, да се борите и\nда будете најбоља верзија себе!", 40);
			if (counterReached(400))
				this.scenePhase++; 
		} 
		if (scenePhase == 9) {
			drawBlackBackground(1.0F);
			this.gp.getClass();
			drawString(1.0F, 38.0F, 576 / 2 - 40, "И нека свима вама 2024 буде \nјош јача и још успешнија!\nИ оно најважније, \nбудите захвални вашим успесима",40);
			if (counterReached(500)) {
				scenePhase++;
			}
				 
		} 
		if (scenePhase == 10) {
			drawBlackBackground(1.0F);
			gp.getClass();
			drawString(1.0F, 38.0F, 576 / 2 - 0, "Да ову годину, \nпроведите са људима које волите, \nкојима сте окружени, \nкоји вас чине срећним, \nкоји су уз вас и y добру и y злу.", 40);
			if (counterReached(360)) {
				scenePhase++; 
			}
				
		} 
		if (scenePhase == 11) {
			drawBlackBackground(1.0F);
			gp.getClass();
			drawString(1.0F, 38.0F, 576 / 2 - 0, "Хтео сам да одржим неки говор, \nали ова игрица има вама нешто да каже... \nшто ће вас погурати у 2024-ој ;)", 40);
			if (counterReached(400))
				scenePhase++; 
		} 
		if (scenePhase == 12) {
			drawBlackBackground(1.0F);
			gp.getClass();
			drawString(1.0F, 38.0F, 576 / 2, "Хвала опет свима, \nшто сте дошли.\nЧекајте мало, има још ..... ;)\nМало стрпљења молим ^_^ ", 40);
			if (counterReached(300)) {
				scenePhase++; 
			}
				
		} 
		if (scenePhase == 13) {
			drawBlackBackground(1f);
			// credits messsage
			setEndCredit();
			y--;
			drawString(1f, 38f, y, endCredit, 40);
		} 
		if (scenePhase == 14) {
			drawBlackBackground(1.0F);
			gp.getClass();
			drawString(1.0F, 60.0F, 576 / 2, "Крај", 40);
			gp.getClass();
			drawString(1.0F, 38.0F, 576 / 2 + 100, "Ко хоће игрицу, \nшаљем у .zip фајл", 40);
			alpha += 0.005F;
			if (alpha > 1.0F) {
				alpha = 1.0F; 
			}
				
			drawString(alpha, 38.0F, 520, "(Press Enter to return to the title screen)", 40);
			if (gp.keyH.enterPressed) {
				gp.keyH.enterPressed = false;
				sceneNum = 0;
				scenePhase = 0;
				alpha = 0.0F;
				gp.stopMusic();
				gp.getClass();
				gp.gameState = 0;
				gp.resetGame(true);
			} 
		} 
	}
	
	public boolean counterReached(int target) {
		
		boolean counterReached = false;
		counter++;
		
		if (counter > target) {
			
			counterReached = true;
			counter = 0;
		} 
		return counterReached;
	}
	
	public void drawBlackBackground(float alpha) {
		
		g2.setComposite(AlphaComposite.getInstance(3, alpha));
		g2.setColor(Color.black);
		gp.getClass();
		gp.getClass();
		g2.fillRect(0, 0, 960, 576);
		g2.setComposite(AlphaComposite.getInstance(3, 1.0F));
	}
	
	public void drawString(float alpha, float fontSize, int y, String text, int lineHeight) {
		
		g2.setComposite(AlphaComposite.getInstance(3, alpha));
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(fontSize));

		for (String line: text.split("\n")) {
			int x = gp.ui.getXforCenteredText(line);
			g2.drawString(line, x - 280, y);
			y += lineHeight;
		} 
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	
	public void setEndCredit() { 
		endCredit = "Да кажем нешто, за свакоме од вас: \n\n\n\nДанине,\nтебе видим као најбољег \nпрофесора, говорника и предавача. \nПун лавине знања, дисциплине и фокуса. \nУвек се држи тога! \n\n\n\nМилоше, увек си спреман на све и свашта. \nШта више, најбољи економиста \nа при том и добар ортак. \nВидим те, као највећег борца кога знам \nи увек зе бори за оно што желиш!\n\n\n\nЛаро, \nI tuoi discorsi in italiano \nsono straordinari, \ncosì come le tue poesie. \nTi vedo come una ragazza con grandi ambizioni, \nma anche la passione per \nla lettura e la conoscenza. \nContinuate così.\n\n\n\nЛео, \nнајбољи музичар, а и најбоље свираш клавир. \nВидим те као највеселијег човека кога познајем, \nрадознао, насмејан, пун музичког духа. \nА оно што би додао је, \nbajo jajo, bajo jajo :D\n\n\n\nМина, \nимамо сличан миндсет и енергију, \nи увек смо спремни на све или ништа. \nЗнам да ти је и инспирација, \nнемачки филозоф, Имануел Кант,  \nпоред тога, увек ћу подржати \nтвој циљ и да поред филозофије, \nда се увек проналазиш \nу многим пољима\nа поред тога, \nда ти и Лео пропутујете целим светом, \nа и да обиђете сва Грчка острва!\n\n\n\nМилице, \nдраго ми је да смо се упознали \nкод Мине кући \nа и драго ми је да смо се видели \nједном овде на Карабурми. \nУвек држи осмех на лице, \nа и увек се бори за нешто више \nу Кока-Коли. Навијам за тебе.\n\n\n\nПредо, друг, брат, најјачи лик. \nВидим те као најуспешнијег Гејм Дев. \nпоред тога \nи највећег авантуристу!\nНадам се да ћеш остварити сан \nу Сан Франциску, \nпоред тога, у фирми у којој си, \nда се прошири свуда у свету!\n\n\n\n";
	}
}
