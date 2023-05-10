// -------------------------------------------------------- 
// Assignment 1
// Written by: Samir Younuszada 40248181
// For COMP 249 Section QQ
// --------------------------------------------------------

import java.util.Scanner;
import java.util.Random;

/**
 * The LadderAndSnake class contains all the essential methods for the development of the snake and ladder game.
 * 
 * @author Samir Younuszada
 */
public class LadderAndSnake {
	
	//Declaring all the instances.
	public static final int BOARD_SIZE = 10;
	public static final int MAX_POSITION = BOARD_SIZE*BOARD_SIZE;
	private static final int SNAKE_HEAD = 0;
	private static final int SNAKE_TAIL = 1;
	private static final int LADDER_BOTTOM = 0;
	private static final int LADDER_TOP = 1;
	// Declaring the 2d arrays the contain the positions on the board that have the heads and tails of each snakes and the tops and bottoms of each ladder.
	private static int[][] snakes = { {16, 6},{48, 30},{64, 60},{79, 19},{93, 68},{95, 24},{97, 76},{98, 78}};
	
	private static int[][] ladders = {
			{1 , 38}, {4 , 14}, {9 , 31}, {21, 42}, {28, 84}, {36, 44}, {51, 67}, {71, 91}, {80, 100}};	
	
	/**
	 * 2D array the represents the 10x10 board. 
	 */
	private int[][] board;
	
	/**
	 * Array that stores all the players that are actively playing.
	 */
	private Player[] players;
	
	/**
	 * Constructor that takes a number of Player variable as a parameter and intiliazes the number of players playing and the board.
	 * @param nbOfPlayers an integer representing the number of active players.
	 */
	public LadderAndSnake(int nbOfPlayers) {
		initializeBoard();
		initializePlayers(nbOfPlayers);
	}
	
	/**
	 * Default constructor that initializes the board and the number of players playing to the minimum of players possible which is two. 
	 */
	public LadderAndSnake() {
		initializeBoard();
		initializePlayers(2);
	}
	//Copy constructor!!
	
	// Method that initiliazes the board and sets the respective number to each square (from 1 to 99 inclusively).
	private void initializeBoard() {
		board = new int[BOARD_SIZE][BOARD_SIZE];
		int squareNumber = 1;
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				board[row][col] = squareNumber;
				squareNumber++;
			}
		}
	}
	
	// Initializes the number of players currently playing.
	private void initializePlayers(int nbOfPlayers) {
		players = new Player[nbOfPlayers];
		for (int i = 0; i < nbOfPlayers; i++) {
			players[i] = new Player();
		}
	}
	
	/**
	 * Assigns the name that is given by the user to the respective player
	 * @param keyin the scanner object used to read the user's input.
	 */
	public void setPlayerNames(Scanner keyin) {
		for (int i = 0; i < nbOfPlayers(); i++) {
			System.out.print("Enter the name of player " + (i+1) + ": ");
			players[i].setName(keyin.nextLine());
		}
	}
	
	/**
	 * Accessor that returns the players array
	 * @return the players array.
	 */
	public Player[] getPlayers() {
		return players;
	}
	
	/**
	 * Mutator that sets new players or modifies the players array.
	 * @param newPlayers the array of new {@code Player} objects to replace {@code players} with
	 */
	public void setPlayers(Player[] newPlayers) {
		this.players = new Player[newPlayers.length];
		for (int i = 0; i < this.players.length; i++) {
			this.players[i] = new Player(newPlayers[i]);
		}
	}
	
	
	/**
	 * Accessor that return the board array
	 * @return a 2D integer array representing the board. 
	 */
	public int[][] getBoard() {
		return board;
	}
	
	/**
	 * Mutator that modifies or sets a new board array.
	 * @param newBoard the array of new integer values to replace the initial board. 
	 */
	public void setBoard(int[][] newBoard) {
		this.board = new int[BOARD_SIZE][BOARD_SIZE];
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				this.board[row][col] = newBoard[row][col];
			}
		}
	}
	
	/**
	 * Returns a random number between 1 and 6 which is essentially the action of rolling a dice.
	 * @return a random number between 1 and 6
	 */
	public static int flipDice() {
		Random rand = new Random();
		int randomNb = rand.nextInt(6)+1;
		return randomNb;
	}

	/**
	 * Checks if the player is on a snake tail and returns true if it is and false otherwise
	 * @param player the player that we're checking its position
	 * @return a boolean value confirming if the player is indeed on a snake's head.
	 * 
	 */
	public boolean PlayerOnSnake(Player player) { 
		for (int i = 0; i < snakes.length; i++) {
			if (player.getPosition() == snakes[i][SNAKE_HEAD]) {
				player.setPosition(snakes[i][SNAKE_TAIL]);
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the player's position is on the bottom of one of the ladders and returns a boolean value confirming if it is or not.  
	 * @param player the player that we're checking its position.
	 * @return a boolean value confirming if the player is indeed on a ladder's bottom
	 */
	public boolean PlayerOnLadder(Player player) { 
		for (int i = 0; i < ladders.length; i++) {
			if (player.getPosition() == ladders[i][LADDER_BOTTOM]) {
				player.setPosition(ladders[i][LADDER_TOP]);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if two players are occupying the same square and returns the player that was initially on the square
	 * @param player the player that we're testing.
	 * @return the player that was intially on that specfic square. 
	 */
	public Player SamePosition(Player player) {
		for (int i = 0; i < players.length; i++) {
			if (player != players[i] && player.getPosition() == players[i].getPosition()) {
				players[i].setPosition(0);
				return players[i];
			}
		}
		return null;
	}
	
	/**
	 * Prints a string representation to the console. if the row is even we print the numbers in an increasing order otherwise we print in a decreasing order from left to right.
	 */
	public void printBoard() {
		printRowLine();
		for (int row = BOARD_SIZE - 1; row >= 0; row--) {
			if (row%2 == 0) 
				printIncreasingRow(row);
			else
				printDecreasingRow(row);
			System.out.println();
			printRowLine();
		}
	}
	
	// Prints a line that delimits each row.
	private void printRowLine() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.print("________");
		}
		System.out.println("_");
	}
	
	// Prints the row of squares in an increasing order.
	private void printIncreasingRow(int row) {
		for (int col = 0; col < BOARD_SIZE; col++) {
			printSquare(board[row][col]);
		}
	}
	
	// Prints the row of squares in a decreasing order.
	private void printDecreasingRow(int row) {
		for (int col = BOARD_SIZE - 1; col >= 0; col--) {
			printSquare(board[row][col]);
		}
	}
	
	
	// Checks if there's a snake on a specific square
		private boolean SnakeOnBoard(int position) {
			for (int i = 0; i < snakes.length; i++) {
				if (position == snakes[i][SNAKE_HEAD])
					return true;
			}
			return false;
		}
		
		// Checks if there's a ladder bottom on a specific square
		private boolean LadderOnBoard(int position) {
			for (int i = 0; i < ladders.length; i++) {
				if (position == ladders[i][LADDER_BOTTOM])
					return true;
			}
			return false;
		}
		
		/**
		 * Checks if a player is on a specific square
		 * @param position the position of an active player
		 * @return the player that is on that specific square.
		 */
		public Player playerOnSquare(int position) {
			for (int i = 0; i < players.length; i++) {
				if (players[i].getPosition() == position)
					return players[i];
			}
			return null;
		}
		
		
	// Prints each square of the board. Each square could either be empty or have a snake or ladder depending on their position on the board. 
	private void printSquare(int position) {
		if (SnakeOnBoard(position))
			System.out.print(position + "~~:>"  + "\t|");
		else if (LadderOnBoard(position))
			System.out.print(position + "|=|"  + "\t|");
		else if (playerOnSquare(position) != null)
			System.out.print(playerOnSquare(position) + "\t|");
		else
			System.out.print(position + "\t|");
	}
	

	/**
	 * Returns the number of players actively playing.
	 * @return the number of players playing.
	 */
	public int nbOfPlayers() {
		return players.length;
	}
	
	// Checks who had the biggest dice value and assign the player as the starter by returning the index of the first player. 
		private int getStartingPlayer(int[] dv) {
			int starter = 0;
			for (int i = 1; i < dv.length; i++) {
				if (dv[i] > dv[starter]) {
					starter = i;
				}
				else if (dv[i] == dv[starter]) {
					return -1;
				}
			}
			return starter;
		}
			
	/**
	 * Determines the order that the players will play the game depending on who has the biggest dice value once they all roll the dice. 
	 * @return the index position of the first player that'll start the game. 
	 */
	public int Order() {
		System.out.println("Let's see who's going to start! The player that has the largest dice value will start:");
		int counter = 1;
		int[] diceValue = new int[nbOfPlayers()];
		int starter;
		
		for (int i = 0; i < nbOfPlayers(); i++) {
				diceValue[i] = flipDice();
				System.out.println(players[i] + " got a dice value of " + diceValue[i]);
		}
		starter = getStartingPlayer(diceValue);
		
		while (starter == -1) {
			System.out.println("It's a tie! Please roll the dice again.\n");
			for (int i = 0; i < nbOfPlayers(); i++) {
				diceValue[i] = flipDice();
				System.out.println(players[i] + " got a dice value of " + diceValue[i]);
			}
			counter++;
			starter = getStartingPlayer(diceValue);
		}

		System.out.print(players[starter] + " will start! ");
		System.out.println("(Players had to roll the dice " + counter +" times to decide who starts.)\n");
		return starter;
	}
	
	
	/**
	 * Checks if a player reached the final position and is thus the winner. Returns the winning player object.
	 * @return a player object, the winner.
	 */
	public Player Winner() {
		return playerOnSquare(MAX_POSITION);
	}
	
	/**
	 * Depending on the dice value, the position of the player gets incremented by that specific value.
	 * @param a player object, the player that's currently playing
	 * @param diceValue, an integer representing the dice value of the player that rolled the dice. 
	 * @return the new position of the player that rolled the dice. 
	 */
	public int move(Player player, int diceValue) {
		player.setPosition(player.getPosition() + diceValue);
		//If the player exceeds the maximum position, we subtract the excess to the max position and that subtraction value will be the player's new position.
		if (player.getPosition() > MAX_POSITION) {
			player.setPosition(MAX_POSITION - (player.getPosition() - MAX_POSITION));
		}
		return player.getPosition();
	}
	
	
	/**
	 * This method is the core of the program which basically starts the game and calls all the essential methods.
	 * @param startingPlayerIndex of the player that has to start.
	 * @param keyin a scanner object used to read the user's input.
	 */
	public void play(int startingPlayerIndex, Scanner keyboard) {
		
		for (int i = 0; i < nbOfPlayers(); i++) {
			if (players[i].getName() == null) {
				System.out.println("Error: players haven't been named yet, will exit the program.");
				System.exit(0);
			}
		}
		
		boolean gameOver = false;
		int turnCounter = 0;
		
		int currentPlayerIndex = startingPlayerIndex;
		int currentPlayerPosition;
		int currentDiceValue;
		Player currentPlayer;
		Player kickedPlayer;
		String enter;
		
		System.out.println("Roll the dice if it's your turn by pressing ENTER!");
		
		while (!gameOver) {
			
			for (int i = 0; i < nbOfPlayers(); i++) {
				
				
				enter = keyboard.nextLine();
				currentPlayer = players[currentPlayerIndex%(nbOfPlayers())];
				
				currentDiceValue = flipDice();
				System.out.print(currentPlayer + " got a dice value of " + currentDiceValue + " --> ");
				
				currentPlayerPosition = move(currentPlayer, currentDiceValue);
				
				if (PlayerOnSnake(currentPlayer)) {
					System.out.println("Unfortunately, " + currentPlayer + " landed on a snake head at " + currentPlayerPosition + " and will slip to square " + currentPlayer.getPosition() + "!");
				}
				else if (PlayerOnLadder(currentPlayer)) {
					System.out.println("NICE! " + currentPlayer + " reached a ladder at " + currentPlayerPosition + " and climbs to square " + currentPlayer.getPosition() + "!");
				}
				else {
					System.out.println(currentPlayer + " is now in square " + currentPlayerPosition);
				}
				
				kickedPlayer = SamePosition(currentPlayer);
				if (kickedPlayer != null) {
					System.out.println(kickedPlayer + " has been kicked off the board by " + currentPlayer + "!");
				}
				kickedPlayer = null;
			
				currentPlayerIndex++;
				
				if (Winner() != null) {
					break;
				}
			
			}
			printBoard();
			gameOver = (Winner() != null);
			//System.out.println();
		}
		System.out.println(Winner() + " is the winner!");
		System.out.println("<:~~~~ GAME OVER ~~~~:>");
		
	}

}


  