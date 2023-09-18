package invaders.entities.concrete;

import invaders.physics.Vector2D;
import invaders.entities.projectile.Projectile;
import invaders.entities.projectile.AlienProjectileStrategy;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;
import invaders.entities.GameObject;

import javafx.scene.image.Image;

import java.io.File;

import javafx.scene.image.Image;

import java.io.File;

public class AlienProjectile implements Projectile, Renderable, GameObject{

    private Vector2D position;
    private final Animator anim = null;
    private boolean alive = true;

    private final double width = 5;
    private final double height = 5;
    private Image image;

    private double yVel;
    private AlienProjectileStrategy strategy;

    public AlienProjectile(Vector2D position){
        this.image = new Image(new File("src/main/resources/alienprojectile.png").toURI().toString(), width, height, true, true);
        this.position = position;
    }

    public void setStrategy(AlienProjectileStrategy strategy){
        this.strategy = strategy;
        this.setyVel(this.strategy.getyVel());
    }
    @Override
    public void remove() {
        this.alive = false;
    }

    @Override
    public boolean isAlive() {
        return this.alive;
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

    public void setyVel(double yVel){
        this.yVel = yVel;
    }

    public double getyVel() {
        return this.yVel;
    }

    @Override
    public void start(){}
    @Override
    public void update(){}

}
