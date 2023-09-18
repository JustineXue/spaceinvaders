package invaders.entities.concrete;

import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;
import invaders.entities.projectile.ProjectileManager;
import invaders.entities.projectile.AlienProjectileCreator;
import invaders.entities.GameObject;

import javafx.scene.image.Image;
import java.util.List;
import java.util.ArrayList;

import java.io.File;

enum Direction{
    UP,
    DOWN,
    LEFT,
    RIGHT
}

public class Alien implements Moveable, Renderable, GameObject {

    private final Vector2D position;
    private final Animator anim = null;
    private boolean alive = true;

    private final double width = 25;
    private final double height = 30;
    private final Image image;

    private double xVel = 20;
    private double yVel = 20;

    private ProjectileManager projectileManager;

    private Direction direction;

    private int moveCounter = 720;

    public Alien(Vector2D position, String strategy){
        this.image = new Image(new File("src/main/resources/alien.png").toURI().toString(), width, height, true, true);
        this.position = position;
        AlienProjectileCreator projectileCreator = new AlienProjectileCreator(strategy);
        this.projectileManager = new ProjectileManager(projectileCreator);
    }

    public void kill() {
        this.alive = false;
    }

    public boolean getIsAlive() {
        return this.alive;
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
        this.projectileManager.addProjectile(this.position);
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

    @Override
    public void start(){}
    @Override
    public void update(){
        if (moveCounter % 120 == 0){
            if (this.moveCounter == 120){
                this.direction = direction.LEFT;
            } else if (this.moveCounter == 240) {
                this.direction = direction.LEFT;
            } else if (this.moveCounter == 360) {
                this.direction = direction.DOWN;
            } else if (this.moveCounter == 480){
                this.direction = direction.RIGHT;
            } else if (this.moveCounter == 600){
                this.direction = direction.RIGHT;
            } else if (this.moveCounter == 720){
                this.direction = direction.DOWN;
                this.moveCounter = 0;
            }
            this.move();
        }
        this.moveCounter += 1;
    }

}
