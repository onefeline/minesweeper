package game;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ThreadLocalRandom;


public class Board extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7365863240813834587L;
	
	/**
	 * Initializes the game board with randomized spots where the bombs are located.
	 * All cells are filled in appropriately, but none of them are initially clicked.
	 * 
	 * @param x_size The width of the mine field. Must be a positive integer.
	 * @param y_size The height of the mine field. Must be a positive integer.
	 * @param num_bombs The number of bombs in the mine field
	 * @exception If the number of bombs can't fit then an exception is thrown
	 */
	public Board(int x_size, int y_size, int num_bombs) throws IllegalArgumentException {
		if(x_size < 1 || y_size < 1) {
			throw new IllegalArgumentException("The board sizes must be greater than zero");
		}
		else if(((x_size + 1) * (y_size + 1)) < num_bombs) {
			throw new IllegalArgumentException("The number of bombs requested cannot fit into the minefield with these dimensions.");
		}
		
		this.xSize = x_size;
		this.ySize = y_size;
		
		this.mineField = new MineCell[xSize][ySize];
		this.numBombs = num_bombs;
		
		randomizeBoard();
		
		
	}
	
	/**
	 * Reinitializes the mine field to be full of empty spots.
	 * 
	 * @param new_x_size The new width of the board
	 * @param new_y_size The new height of the board
	 */
	private void clearBoard(int new_x_size, int new_y_size) {
		// Remove everything from the frame
		this.removeAll();
				
		this.mineField = new MineCell[new_x_size][new_y_size];
			
		// Create a new layout using the given sizes
		GridLayout newLayout = new GridLayout(new_x_size, new_y_size);
		this.setLayout(newLayout);
		
		// Add all buttons to the frame
		for (int x = 0; x < new_x_size; ++x) {
			for (int y = 0; y < new_y_size; ++y) {
				this.mineField[x][y] = new MineCell();
				this.mineField[x][y].putClientProperty("x", String.valueOf(x));
				this.mineField[x][y].putClientProperty("y", String.valueOf(y));
				
				this.mineField[x][y].addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						JButton btn = (JButton) e.getSource();
						
						int x_pos = Integer.valueOf((String) btn.getClientProperty("x"));
						int y_pos = Integer.valueOf((String) btn.getClientProperty("y"));
						
					    clickSpot(x_pos, y_pos);
					}
				
				});
				this.add(this.mineField[x][y]);
			}
		}
		
		
	}
	
	/**
	 * Reinitializes the mine field to be full of empty spots. The size of the mine field does not change.
	 */
	public void clearBoard() {
		clearBoard(this.xSize, this.ySize);
	}
	
	/**
	 * Randomly places all of the bombs on the game board.
	 * Assumes that the board is initially clear.
	 */
	private void randomlyPlaceBombs() {
		int num_bombs_placed = 0;
		
		// Randomly place all of the bombs on the gameboard
		while (num_bombs_placed < this.numBombs) {
			int x_spot = ThreadLocalRandom.current().nextInt(0, this.xSize);
			int y_spot = ThreadLocalRandom.current().nextInt(0, this.ySize);
			
			// If this spot is already used occupied by a bomb, then simply recalculate a new spot to place the bomb.
			// Yes, this is bad code because it is inefficient. Ideally, we would not be considering spots where a bomb
			// has already been placed.
			if (this.mineField[x_spot][y_spot].getCellType() == CellType.BOMB) {
				continue;
			}
			
			this.mineField[x_spot][y_spot].setCellType(CellType.BOMB);
			num_bombs_placed++;
		}
	}
	
	/**
	 * Calculates every numerical cell based on the number of bombs surrounding them.
	 * Assumes that all bombs have been already placed.
	 */
	private void fillInNumericalValues() {
		// Calculate the contents of all cells on the board
		for (int x = 0; x < this.xSize; ++x) {
			for (int y = 0; y < this.ySize; ++y) {
				if (this.mineField[x][y].getCellType() == CellType.BOMB) {
					continue;
				}
				
				// Consider all 8 directions, if possible, and determine how many bombs are surrounding this spot
				int bombs_nearby = 0;
				
				// Upper-left cell - Make sure we don't go out of bounds
				if (x != 0 && y != 0 && this.mineField[x - 1][y - 1].getCellType() == CellType.BOMB) {
					bombs_nearby++;
				}
				
				// Upper cell - Make sure we don't go out of bounds
				if (y != 0 && this.mineField[x][y - 1].getCellType() == CellType.BOMB) {
					bombs_nearby++;
				}
				
				// Upper-right cell - Make sure we don't go out of bounds
				if (x != this.xSize - 1 && y != 0 && this.mineField[x + 1][y - 1].getCellType() == CellType.BOMB) {
					bombs_nearby++;
				}
				
				// Right cell - Make sure we don't go out of bounds
				if (x != this.xSize - 1 && this.mineField[x + 1][y].getCellType() == CellType.BOMB) {
					bombs_nearby++;
				}
				
				// Lower-right cell - Make sure we don't go out of bounds
				if (x != this.xSize - 1 && y != this.ySize - 1 && this.mineField[x + 1][y + 1].getCellType() == CellType.BOMB) {
					bombs_nearby++;
				}
				
				// Lower cell - Make sure we don't go out of bounds
				if (y != this.ySize - 1 && this.mineField[x][y + 1].getCellType() == CellType.BOMB) {
					bombs_nearby++;
				}
				
				// Lower-left cell - Make sure we don't go out of bounds
				if (x != 0 && y != this.ySize - 1 && this.mineField[x - 1][y + 1].getCellType() == CellType.BOMB) {
					bombs_nearby++;
				}
				
				// Left cell - Make sure we don't go out of bounds
				if (x != 0 && this.mineField[x - 1][y].getCellType() == CellType.BOMB) {
					bombs_nearby++;
				}
				
				switch (bombs_nearby) {
					case 1:
						this.mineField[x][y].setCellType(CellType.ONE);
						break;
					case 2:
						this.mineField[x][y].setCellType(CellType.TWO);
						break;
					case 3:
						this.mineField[x][y].setCellType(CellType.THREE);
						break;
					case 4:
						this.mineField[x][y].setCellType(CellType.FOUR);
						break;
					case 5:
						this.mineField[x][y].setCellType(CellType.FIVE);
						break;
					case 6:
						this.mineField[x][y].setCellType(CellType.SIX);
						break;
					case 7:
						this.mineField[x][y].setCellType(CellType.SEVEN);
						break;
					case 8:
						this.mineField[x][y].setCellType(CellType.EIGHT);
						break;
					default:
						// This spot should already be empty, so there is no need to set it again
						break;
						
				}
			}
		}
	}
	
	/**
	 * Initializes a new game board with randomly placed bombs
	 */
	public void randomizeBoard() {
		clearBoard();
		randomlyPlaceBombs();
		fillInNumericalValues();
	}
	
	/**
	 * A recursive function that will keep clicking on nearby spaces as long as the current space
	 * is empty and not yet clicked.
	 * @param x
	 * @param y
	 */
	public GameState clickSpot(int x, int y) {
		// If we are out-of-bounds
		if (x < 0 || x >= this.xSize || y < 0 || y >= this.ySize) {
			return GameState.OK;
		}
		
		if (mineField[x][y].isClicked() == true) {
			return GameState.OK;
		}
		
		mineField[x][y].setClicked();
		
		if (mineField[x][y].getCellType() == CellType.BOMB) {
			// Game over
			return GameState.GAME_OVER;
			
		}
		else if (mineField[x][y].getCellType() == CellType.EMPTY) {
			// If this cell is empty, then open all 8 directions
			
			clickSpot(x - 1, y - 1); // Upper-left
			clickSpot(x, y - 1);     // Upper
			clickSpot(x + 1, y - 1); // Upper-right
			clickSpot(x + 1, y);     // Right
			clickSpot(x + 1, y + 1); // Lower-right
			clickSpot(x, y + 1);     // Lower
			clickSpot(x - 1, y + 1); // Lower-left
			clickSpot(x - 1, y);     // Left
		}
		
		return GameState.OK;
		
	}
	
	private MineCell [][] mineField;
	private int xSize;
	private int ySize;
	private int numBombs;

}
