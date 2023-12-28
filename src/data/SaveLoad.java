package data;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import entity.Entity;

import java.io.File;
import java.io.FileInputStream;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;

public class SaveLoad {

	GamePanel gp;
	
	public SaveLoad(GamePanel gp) {
		this.gp = gp;
	}
//	public Entity getObjects(String itemName) {
//		
//		Entity obj = null;
//		
//		switch (itemName) {
//		case "Axe": obj = new OBJ_Axe(gp); break;
//		case "Boots": obj = new OBJ_Boots(gp); break;
//		case "Key": obj = new OBJ_Key(gp); break;
//		case "Lantern": obj = new OBJ_Lantern(gp); break;
//		case "Red Potion": obj = new OBJ_Potion_Red(gp); break;
//		case "Shield Blue": obj = new OBJ_Shield_Blue(gp); break;
//		case "Shield Wood": obj = new OBJ_Shield_Wood(gp); break;
//		case "Normal Sword": obj = new OBJ_Sword_Normal(gp); break;
//		case "Tent": obj = new OBJ_Tent(gp); break;
//		case "Door": obj = new OBJ_Door(gp); break;
//		case "Chest": obj = new OBJ_Chest(gp); break;
//		}
//		return obj;
//	}
	public void save() {
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			
			DataStorage ds = new DataStorage();
			
			ds.level = gp.player.level;
			ds.maxLife = gp.player.maxLife;
			ds.life = gp.player.life;
			ds.maxMana = gp.player.maxMana;
			ds.mana = gp.player.mana;
			ds.strength = gp.player.strenght;
			ds.dexterity = gp.player.dexterity;
			ds.exp = gp.player.exp;
			ds.nextLevelExp = gp.player.nextLevelExp;
			ds.coin = gp.player.coin;
			
			// PLAYER INVENTORY
			for (int i = 0; i < gp.player.invenotry.size(); i++) {
				ds.itemNames.add(gp.player.invenotry.get(i).name);
				ds.itemAmounts.add(gp.player.invenotry.get(i).amount);
			}
			// PLAYER EQUITMENT
			ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
			ds.currentShieldSlot = gp.player.getCurrentShieldSlot();
			
			// OBJECT ON MAP
			ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
			ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectLootNames = new String[gp.maxMap][gp.obj[1].length];
			ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];
			
			for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
				for (int i = 0; i < gp.obj[1].length; i++) {
					
					if (gp.obj[mapNum][i] == null) {
						ds.mapObjectNames[mapNum][i] = "NA";
					}
					else {
						ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
						ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
						ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
						if (gp.obj[mapNum][i].loot != null) {
							ds.mapObjectLootNames[mapNum][i] = gp.obj[mapNum][i].loot.name;
						}
						ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
					}
				}
			}
			
			// Write the Data Storage object
			oos.writeObject(ds);
			
			
		} catch (Exception e) {
			System.out.println("Save Exception!");
		}
	}
	public void load() {
		
		try {
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			
			// Read the DataStorage object
			DataStorage ds = (DataStorage)ois.readObject();
			
			this.gp.player.level = ds.level;
			this.gp.player.maxLife = ds.maxLife;
			this.gp.player.life = ds.life;
			this.gp.player.maxMana = ds.maxMana;
			this.gp.player.mana = ds.mana;
			this.gp.player.strenght = ds.strength;
			this.gp.player.dexterity = ds.dexterity;
			this.gp.player.exp = ds.exp;
			this.gp.player.nextLevelExp = ds.nextLevelExp;
			this.gp.player.coin = ds.coin;
			
			// PLAYERS INVENTORY
			gp.player.invenotry.clear();
			for (int i = 0; i < ds.itemNames.size(); i++) {
				gp.player.invenotry.add(gp.eGenerator.getObjects(ds.itemNames.get(i)));
				gp.player.invenotry.get(i).amount = ds.itemAmounts.get(i);
			}
			
			gp.player.currentWeapon = gp.player.invenotry.get(ds.currentWeaponSlot);
			gp.player.currentShield = gp.player.invenotry.get(ds.currentShieldSlot);
			gp.player.getAttack();
			gp.player.getDefense();
			gp.player.getAttackImage();
			
			// OBJECT ON MAP
			for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
				for (int i = 0; i < gp.obj[1].length; i++) {
					
					if (ds.mapObjectNames[mapNum][i].equals("NA")) {
						gp.obj[mapNum][i] = null;
					}
					else {
						gp.obj[mapNum][i] = gp.eGenerator.getObjects(ds.mapObjectNames[mapNum][i]);
						gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
						gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
						if (ds.mapObjectLootNames[mapNum][i] != null) {
							
							gp.obj[mapNum][i].loot = gp.eGenerator.getObjects(ds.mapObjectLootNames[mapNum][i]);
						}
						gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
						if (gp.obj[mapNum][i].opened == true) {
							gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
						}
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println("Load Exception!");
		}
	}
}
