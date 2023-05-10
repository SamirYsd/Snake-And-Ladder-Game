// -------------------------------------------------------- 
// Assignment 1
// Written by: Samir Younuszada 40248181
// For COMP 249 Section QQ
// --------------------------------------------------------

/**
 * COMP249 |      
 *   Assignment #1 |        
 *   02/06/2022  |        
 * 
 *  The Player class initializes the information of each players that'll play the game.
 * 
 * @author Samir Younuszada ID:40248181	
 * 
 * 
 * @version 1.1
 * 
 *
 */
public class Player {
	
	//Initialization of instances.
	private String name;
	private int position;

	/**
	 * Constructor that initializes the name and the position of the player. 
	 * @param name the name of the player
	 * @param position the position of the player
	 */
	public Player(String name, int position) {
		this.setName(name);
		this.setPosition(position);
	}
	
	/**
	 * Constructor that creates a player with a given name and initializes the position to 0.
	 * @param name, the name of the player.
	 */
	public Player(String name) {
		this(name, 0);
	}
	
	/**
	 * Default constructor that initializes the instances to their default values.
	 */
	public Player() {
		this(null, 0);
	}
	
	/**
	 * Constructor that takes a player object as a parameter and creates a new with same name and position.
	 * @param player , player object
	 */
	public Player(Player player) {
		this.name = player.getName();
		this.position = player.getPosition();
	}
	
	/**
	 * Accessor that returns the name of the player
	 * @return the name of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Mutator that can change the name of the player.
	 * @param name the name of the player
	 */
	public void setName(String name) {
			this.name = name;
		}
	
	
	/**
	 * Accessor that return the position of the player
	 * @return the position of the player.
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Mutator that can change the position of the player.
	 * @param position the position of the player.
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * To string method that returns the name of the player as a string.
	 * @return a string representing the name of the player.
	 */
	public String toString() {
		return name;
	}

}
