// -------------------------------------------------------- 
// Assignment 1
// Written by: Samir Younuszada 40248181
// For COMP 249 Section QQ
// --------------------------------------------------------

import java.util.Scanner;
/**
 * Driver program that starts and launches the game
 * @author Samir Younuszada
 */
public class PlayLadderAndSnake {
	
	/**
	 * Driver that displays a welcome message, asks for the number of players and the names of the players that'll play the game and calls the play() method in order to start and play the game.
	 * 
	 */
	public static void main(String[] args) {
		Scanner keyIn = new Scanner(System.in);
		
		String junk;
		
		System.out.println("Welcome to the Snakes & Ladders game!");
		System.out.print("Please enter the number of players (2 to 4 players): ");
		
			
		int nbOfPlayers = 0;
		
		if (!keyIn.hasNextInt()) {
			System.out.println("Error: Invalid input, exiting program.");
			System.exit(0);
		}
		
		do {
			nbOfPlayers = keyIn.nextInt();
			if ((nbOfPlayers) < 2) {
				System.out.print("Error: Insufficient number of players entered, please try again: ");
			}
			else if (nbOfPlayers > 4) {
				System.out.print("Error: Exceeding number of players entered, please try again: ");
			}
		} while (nbOfPlayers < 2 || nbOfPlayers > 4);
		
		LadderAndSnake game = new LadderAndSnake(nbOfPlayers);
		//Junk since we'll ask for the name of the players (String).
		junk = keyIn.nextLine();
		game.setPlayerNames(keyIn);
		System.out.println();
		
		game.play(game.Order(), keyIn);
		System.out.println("Thanks for playing Snakes And Ladders!");
		keyIn.close();
	}

}