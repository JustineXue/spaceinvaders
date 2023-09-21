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
import invaders.entities.concrete.ShipProjectile;
import invaders.entities.concrete.AlienProjectile;
import invaders.entities.projectile.Projectile;
import invaders.entities.projectile.ProjectileCreator;
import invaders.entities.projectile.AlienProjectileCreator;
import invaders.entities.projectile.ShipProjectileCreator;
import invaders.physics.CollisionDetector;
import invaders.logic.Damagable;
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

	private List<Projectile> shipProjectiles = new ArrayList<Projectile>();

	private List<Projectile> alienProjectiles = new ArrayList<Projectile>();

	private ProjectileCreator alienProjectileCreator = new AlienProjectileCreator();

	private ProjectileCreator shipProjectileCreator = new ShipProjectileCreator();

	private CollisionDetector collisionDetector = new CollisionDetector();

	private boolean hasPlayerWon;

	private int alienProjectileCount = 0;

	private int aliensKilled = 0;

	private boolean bunkersTouched = false;

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
				Alien a = (Alien) go;
				aliens.add(a);
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
		List<Projectile> tempProjectiles = new ArrayList<Projectile>();
		List<Projectile> removeProjectiles = new ArrayList<Projectile>();
		for(GameObject go: gameobjects){
			if (go instanceof Alien){
				Alien alien = (Alien) go;
				if (alien.getShoot()){
					if (alienProjectileCount < 3){
						Projectile newProjectile = alienProjectileCreator.createProjectile(go);
						alienProjectiles.add(newProjectile);
						tempProjectiles.add(newProjectile);
						alienProjectileCount += 1;
					}
					alien.setShoot();
				}
			} else if (go instanceof Player){
				if (player.getShoot()){
					player.setShoot();
					Projectile newProjectile = shipProjectileCreator.createProjectile(go);
					shipProjectiles.add(newProjectile);
					tempProjectiles.add(newProjectile);
				}
			}
			go.update();
		}

		// ensure that renderable foreground objects don't go off-screen
		for(Renderable ro: renderables){
			if (!(ro instanceof Projectile)){
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
			} else {
				if(ro.getPosition().getY() > gameSizeY) {
					alienProjectileCount -= 1;
					Projectile p = (Projectile) ro;
					removeProjectiles.add(p);
				} else if (ro.getPosition().getY() + ro.getHeight() < 0){
					Projectile p = (Projectile) ro;
					removeProjectiles.add(p);
				}
			}
		}
		gameobjects.removeAll(removeProjectiles);
		renderables.removeAll(removeProjectiles);
		alienProjectiles.remove(removeProjectiles);
		gameobjects.addAll(tempProjectiles);
		renderables.addAll(tempProjectiles);

		checkCollision();
	}

	public List<Renderable> getRenderables(){
		return renderables;
	}

	public List<GameObject> getGameObjects(){
		return gameobjects;
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

	public void checkCollision(){
		List<Projectile> removedProjectiles = new ArrayList<Projectile>();
		List<Alien> removedAliens = new ArrayList<Alien>();
		List<Bunker> removedBunkers = new ArrayList<Bunker>();
		for (Projectile p: alienProjectiles){
			if (collisionDetector.detectShipCollision(player, (AlienProjectile) p)){
				removedProjectiles.add(p);
				p.takeDamage(1);
				player.takeDamage(1);
				alienProjectileCount -= 1;
			}
			for (Bunker b: bunkers){
				if (collisionDetector.detectBunkerCollision(b, (AlienProjectile) p)){
					removedProjectiles.add(p);
					p.takeDamage(1);
					b.takeDamage(1);
					if (!b.isAlive()){
						removedBunkers.add(b);
						for (Alien a: aliens){
							a.increaseMoveSpeed();
						}
					}
					alienProjectileCount -= 1;
				}
			}
		}

		for (Projectile p: shipProjectiles){
			for (Alien a: aliens){
				if (collisionDetector.detectAlienCollision(a, (ShipProjectile) p)){
					removedProjectiles.add(p);
					p.takeDamage(1);
					a.takeDamage(1);
					removedAliens.add(a);
				}
			}
		}
		for (Alien a: aliens){
			for (Bunker b: bunkers){
				if (collisionDetector.detectAlienBunkerCollision(a, b)){
					b.takeDamage(3);
					bunkersTouched = true;
					removedBunkers.add(b);
				}
			}
		}
		alienProjectiles.removeAll(removedProjectiles);
		shipProjectiles.removeAll(removedProjectiles);
		gameobjects.removeAll(removedProjectiles);

		for (Alien a: removedAliens){
			if (aliens.remove(a)){
				aliensKilled += 1;
			}
		}
		gameobjects.removeAll(removedAliens);
		bunkers.removeAll(removedBunkers);
		gameobjects.removeAll(removedBunkers);
	}

	public boolean isGameOver(){
		hasPlayerWon = false;
		if (bunkers.size() == 0){
			return true;
		} else if (aliens.size() == 0){
			hasPlayerWon = true;
			return true;
		} else if (!player.isAlive()){
			return true;
		} else if (bunkersTouched){
			return true;
		} else {
			return false;
		}
	}

	public boolean hasPlayerWon(){
		return hasPlayerWon;
	}

	public int getAliensKilled(){ return this.aliensKilled; }

	public int getPlayerLives(){ return (int) this.player.getHealth(); }

}
