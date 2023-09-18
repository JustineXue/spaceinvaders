package invaders.entities.concrete;

import invaders.logic.Damagable;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;
import invaders.entities.bunker.BunkerState;
import invaders.entities.bunker.GreenBunker;
import invaders.entities.bunker.YellowBunker;
import invaders.entities.bunker.RedBunker;
import invaders.entities.GameObject;

import javafx.scene.image.Image;

import java.io.File;

public class Bunker implements Renderable, Damagable, GameObject{

    private final Vector2D position;
    private final Animator anim = null;

    private double health = 150;

    private BunkerState state;

    private final double width;
    private final double height;
    private final Image image;

    public Bunker(double height, double width, Vector2D position){
        this.state = new GreenBunker(height, width);
        this.image = this.state.getBunkerImage();
        this.position = position;
        this.height = height;
        this.width = width;
    }

    @Override
    public void takeDamage(double amount) {
        this.health -= amount;
        this.handle();
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
    public Image getImage() {
        return this.state.getBunkerImage();
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

    public void changeBunkerState(){
        if (this.state.getColour().equals("green")){
            this.state = new YellowBunker(height, width);
        } else if (this.state.getColour().equals("yellow")){
            this.state = new RedBunker(height, width);
        } else if (this.state.getColour().equals("red")){
            this.state = null;
        }
    }

    public void handle(){
        this.changeBunkerState();
    }

    @Override
    public void start(){}
    @Override
    public void update(){}

}
