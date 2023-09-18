package invaders.engine;

import java.util.ArrayList;
import java.util.List;

import invaders.entities.GameObject;
import invaders.entities.concrete.Player;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import invaders.entities.concrete.Bunker;
import invaders.entities.concrete.Alien;

/**
 * This class manages the main loop and logic of the game
 */
public class GameEngine {

	private List<GameObject> gameobjects;
	private List<Renderable> renderables;
	private Player player;

	private boolean left;
	private boolean right;

	private Parser parser;

	private int gameSizeX;

	private int gameSizeY;

	private String playerColour;
	private int playerSpeed;
	private int playerLives;
	private int playerPosX;
	private int playerPosY;

	private List<Bunker> bunkers = new ArrayList<Bunker>();
	private List<Alien> aliens = new ArrayList<Alien>();

	public GameEngine(String config){
		// read the config here
		gameobjects = new ArrayList<GameObject>();
		renderables = new ArrayList<Renderable>();

		parser = new Parser(config);
		parser.parse();
		this.gameSizeX = parser.getGameSizeX();
		this.gameSizeY = parser.getGameSizeY();
		this.playerColour = parser.getPlayerColour();
		this.playerSpeed = parser.getPlayerSpeed();
		this.playerLives = parser.getPlayerLives();
		this.playerPosX = parser.getPlayerPosX();
		this.playerPosY = parser.getPlayerPosY();

		List<GameObject> tempBunkers = parser.getBunkers();
		List<GameObject> tempAliens = parser.getAliens();

		player = new Player(new Vector2D(playerPosX, playerPosY), playerColour, playerSpeed, playerLives);
		renderables.add(player);
		gameobjects.add(player);

		for (GameObject go: tempBunkers){
			if (go instanceof Bunker) {
				bunkers.add((Bunker) go);
			}
		}
		renderables.addAll(bunkers);
		gameobjects.addAll(bunkers);
		for (GameObject go: tempAliens){
			if (go instanceof Alien) {
				aliens.add((Alien) go);
			}
		}
		renderables.addAll(aliens);
		gameobjects.addAll(aliens);
	}

	/**
	 * Updates the game/simulation
	 */
	public void update(){
		movePlayer();
		for(GameObject go: gameobjects){
			go.update();
		}

		// ensure that renderable foreground objects don't go off-screen
		for(Renderable ro: renderables){
			if(!ro.getLayer().equals(Renderable.Layer.FOREGROUND)){
				continue;
			}
			if(ro.getPosition().getX() + ro.getWidth() >= gameSizeX) {
				ro.getPosition().setX(gameSizeX - 1 -ro.getWidth());
			}

			if(ro.getPosition().getX() <= 0) {
				ro.getPosition().setX(1);
			}

			if(ro.getPosition().getY() + ro.getHeight() >= gameSizeY) {
				ro.getPosition().setY(gameSizeY - 1 - ro.getHeight());
			}

			if(ro.getPosition().getY() <= 0) {
				ro.getPosition().setY(1);
			}
		}
	}

	public List<Renderable> getRenderables(){
		return renderables;
	}


	public void leftReleased() {
		this.left = false;
	}

	public void rightReleased(){
		this.right = false;
	}

	public void leftPressed() {
		this.left = true;
	}
	public void rightPressed(){
		this.right = true;
	}

	public boolean shootPressed(){
		player.shoot();
		return true;
	}

	private void movePlayer(){
		if(left){
			player.left();
		}

		if(right){
			player.right();
		}
	}

	public int getGameSizeX(){
		return this.gameSizeX;
	}

	public int getGameSizeY(){
		return this.gameSizeY;
	}

}
