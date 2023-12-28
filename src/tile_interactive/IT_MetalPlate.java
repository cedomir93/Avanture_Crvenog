package tile_interactive;

import main.GamePanel;
import tile_interactive.IT_MetalPlate;
import tile_interactive.InteractiveTile;

public class IT_MetalPlate extends InteractiveTile {
	GamePanel gp;
	
	public static final String itName = "Metal Plate";
	
	public IT_MetalPlate(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		name = itName;
		worldX = gp.tileSize * col;
		worldY = gp.tileSize * row;
		
		
		down1 = setup("/tiles_interactive/metalplate", gp.tileSize, gp.tileSize);
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 0;
		solidArea.height = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
}