package invaders.entities.concrete;

import invaders.logic.Damagable;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;
import invaders.entities.GameObject;

import javafx.scene.image.Image;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class Player implements Moveable, Damagable, Renderable, GameObject {

    private final Vector2D position;
    private final Animator anim = null;
    private double health = 3;

    private final double width = 25;
    private final double height = 30;
    private final Image image;

    private int speed;

    private int lives;

    private boolean shoot = false;
    public Player(Vector2D position, String playerColour, int playerSpeed, int playerLives){
        this.speed = playerSpeed;
        this.health = playerLives;
        if (playerColour.equals("blue")){
            this.image = new Image(new File("src/main/resources/playerblue.png").toURI().toString(), width, height, true, true);
        } else if (playerColour.equals("green")){
            this.image = new Image(new File("src/main/resources/playergreen.png").toURI().toString(), width, height, true, true);
        } else if (playerColour.equals("red")){
            this.image = new Image(new File("src/main/resources/playerred.png").toURI().toString(), width, height, true, true);
        } else {
            this.image = new Image(new File("src/main/resources/playerred.png").toURI().toString(), width, height, true, true);
        }
        this.position = position;
    }

    @Override
    public void takeDamage(double amount) {
        this.health -= amount;
    }

    @Override
    public double getHealth() {
        return this.health;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public void up() {
        return;
    }

    @Override
    public void down() {
        return;
    }

    @Override
    public void left() {
        this.position.setX(this.position.getX() - this.speed);
    }

    @Override
    public void right() {
        this.position.setX(this.position.getX() + this.speed);
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

    @Override
    public void start(){}
    @Override
    public void update(){}

    public int getSpeed(){ return this.speed; }

    public boolean getShoot(){ return this.shoot; }

    public void setShoot(){ this.shoot = false; }

}
