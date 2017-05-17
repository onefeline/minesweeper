package game;

import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MineCell extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8977864460635114234L;
	
	public MineCell() {
		this.isClicked = false;
		this.setCellType(CellType.EMPTY);
		this.setEnabled(true);
		
		setContentAreaFilled(true);
		setBorderPainted(true);
		setOpaque(true);
	}
	
	public void setClicked() {
		this.isClicked = true;
		//this.setEnabled(false);
		
		setContentAreaFilled(false);
		
		// If this is a bomb, then display a bomb icon
		if (this.cellType == CellType.BOMB) {

			ImageIcon img_icn = new ImageIcon("resources/exploded_bomb.jpg");
			Image img = img_icn.getImage();
			Image new_img = img.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
			this.setIcon(new ImageIcon(new_img));
		}
		else if (this.cellType != CellType.EMPTY) {
			this.setText(String.valueOf(this.cellType.getValue()));
			this.setForeground(BUTTON_NUMBER_TO_COLOR_DICTIONARY.get(this.cellType.getValue()));
		}
		
	}

	public boolean isClicked() {
		return isClicked;
	}

	public CellType getCellType() {
		return cellType;
	}

	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}

	private boolean isClicked;
	private CellType cellType;
	
	private static Map<Integer, Color> BUTTON_NUMBER_TO_COLOR_DICTIONARY;
	
	static {
		BUTTON_NUMBER_TO_COLOR_DICTIONARY = new HashMap<Integer, Color>();
		BUTTON_NUMBER_TO_COLOR_DICTIONARY.put(1, Color.BLACK);
		BUTTON_NUMBER_TO_COLOR_DICTIONARY.put(2, Color.BLUE);
		BUTTON_NUMBER_TO_COLOR_DICTIONARY.put(3, Color.GREEN);
		BUTTON_NUMBER_TO_COLOR_DICTIONARY.put(4, Color.ORANGE);
		BUTTON_NUMBER_TO_COLOR_DICTIONARY.put(5, Color.YELLOW);
		BUTTON_NUMBER_TO_COLOR_DICTIONARY.put(6, Color.PINK);
		BUTTON_NUMBER_TO_COLOR_DICTIONARY.put(7, Color.RED);
		BUTTON_NUMBER_TO_COLOR_DICTIONARY.put(8, Color.WHITE);
	}

}
