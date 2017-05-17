package game;

/**************
 * This class manages the entire gameboard, players, external connections,
 * as well as the status of the game.
 * 
 * @author Geoffrey Saunders
 *
 */
public class GameManager {
	
	public GameManager() {
		this.setNumPlayers(0);
		this.gameState = GameState.OK;
		this.gameBoard = new Board(20, 20, 5);
	}
	
	public void startNewGame(int board_width, int board_height, int num_bombs) {
		this.gameBoard = new Board(board_width, board_height, num_bombs);
		this.gameState = GameState.OK;
	}
	
	public void setNumPlayers(int num_players) {
		this.numPlayers = num_players;
	}
	public int getNumPlayers() {
		return numPlayers;
	}

	public GameState getGameState() {
		return gameState;
	}
	
	public Board getGameBoard() {
		return gameBoard;
	}
	
	public void clickSpot(int x, int y) {
		if (this.gameState == GameState.GAME_OVER) {
			return;
		}
		
		this.gameState = this.gameBoard.clickSpot(x, y);
	}

	private int numPlayers;
	private GameState gameState;
	private Board gameBoard;
}
