package invaders.entities.concrete;

import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;
import invaders.entities.GameObject;
import invaders.logic.Damagable;

import invaders.entities.projectile.ProjectileStrategy;

import javafx.scene.image.Image;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import java.io.File;

enum Direction{
    UP,
    DOWN,
    LEFT,
    RIGHT
}

public class Alien implements Moveable, Damagable, Renderable, GameObject {

    private Vector2D position;
    private final Animator anim = null;
    private boolean alive = true;

    private final double width = 25;
    private final double height = 25;
    private final Image image;

    private double xVel = 0.2;
    private double yVel = 0.2;

    private double health = 1;

    private Direction direction;

    private int moveCounter = 720;

    private int coolDown = 720;

    private boolean coolingDown = false;

    private Random random = new Random();

    private int randomInterval;

    private ProjectileStrategy strategy;

    private boolean shoot = false;

    public int moveSpeed = 120;

    public Alien(){
        this.image = new Image(new File("src/main/resources/alien.png").toURI().toString(), width, height, true, true);
    }

    public void setPosition(Vector2D position){
        this.position = position;
    }

    public void setStrategy(ProjectileStrategy strategy){
        this.strategy = strategy;
    }

    @Override
    public void takeDamage(double amount){
        this.health -= amount;
    }

    @Override
    public double getHealth(){
        return this.health;
    }
    @Override
    public boolean isAlive(){
        return this.health > 0;
    }

    @Override
    public void up() {
        this.position.setY(this.position.getY() - this.yVel);
    }

    @Override
    public void down() {
        this.position.setY(this.position.getY() + this.yVel);
    }

    @Override
    public void left() {
        this.position.setX(this.position.getX() - this.xVel);
    }

    @Override
    public void right() {
        this.position.setX(this.position.getX() + this.xVel);
    }

    public void shoot(){
        this.shoot = true;
    }

    @Override
    public Image getImage() {
        return this.image;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }

    @Override
    public Layer getLayer() {
        return Layer.FOREGROUND;
    }

    public void move(){
        if (this.direction == direction.UP){
            this.up();
        } else if (this.direction == direction.DOWN){
            this.down();
        } else if (this.direction == direction.LEFT){
            this.left();
        } else if (this.direction == direction.RIGHT){
            this.right();
        }
    }

    public int generateRandom(){
        int randomNumber = random.nextInt(2001);
        return randomNumber;
        //generates a random number between 0 and 2000
    }

    @Override
    public void start(){}
    @Override
    public void update(){
        if (coolingDown == true){
            coolDown--;
            if (coolDown == 0){
                coolingDown = false;
                coolDown = 720;
            }
        } else {
            randomInterval = generateRandom();
            if (randomInterval == 0){
                this.shoot();
            }
        }
        if (moveCounter % moveSpeed == 0){
            if ((int) this.moveCounter/moveSpeed == 1){
                this.direction = direction.LEFT;
            } else if ((int) this.moveCounter/moveSpeed == 2) {
                this.direction = direction.LEFT;
            } else if ((int) this.moveCounter/moveSpeed == 3) {
                this.direction = direction.DOWN;
            } else if ((int) this.moveCounter/moveSpeed == 4){
                this.direction = direction.RIGHT;
            } else if ((int) this.moveCounter/moveSpeed == 5){
                this.direction = direction.RIGHT;
            } else if ((int) this.moveCounter/moveSpeed == 6){
                this.direction = direction.DOWN;
                this.moveCounter = 0;
            }
        }
        this.move();
        this.moveCounter += 1;
    }

    public ProjectileStrategy getStrategy(){ return this.strategy; }

    public boolean getShoot(){ return this.shoot; }

    public void setShoot(){ this.shoot = false; }

    public void increaseMoveSpeed(){
        xVel += 0.1;
        yVel += 0.1;
    }

}
