package game;

/**************
 * This class manages the entire gameboard, players, external connections,
 * as well as the status of the game.
 * 
 * @author Geoffrey Saunders
 *
 */
public class GameManager {
	
	public GameManager(int width, int height, int num_bombs) {
		this.setNumPlayers(0);
		this.gameBoard = new Board(width, height, num_bombs);
	}
	
	public void startNewGame(int board_width, int board_height, int num_bombs) {
		this.gameBoard = new Board(board_width, board_height, num_bombs);
	}
	
	public void setNumPlayers(int num_players) {
		this.numPlayers = num_players;
	}
	public int getNumPlayers() {
		return numPlayers;
	}

	public GameState getGameState() {
		return gameBoard.getGameState();
	}
	
	public Board getGameBoard() {
		return gameBoard;
	}
	

	private int numPlayers;
	private Board gameBoard;
}
