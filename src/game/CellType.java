package game;

/***
 * Contains all of the states that a Minesweeper cell uses
 * 
 * 
 * @author Geoffrey Saunders
 *
 */
public enum CellType {
	EMPTY(0), BOMB(-1), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8);
	
	private CellType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	private final int value;
}