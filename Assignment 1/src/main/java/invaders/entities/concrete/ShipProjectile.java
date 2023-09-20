package invaders.entities.concrete;

import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import invaders.entities.projectile.Projectile;

import javafx.scene.image.Image;

import java.io.File;
public class ShipProjectile implements Projectile {

    private final Vector2D position;
    private final Animator anim = null;
    private final double width = 15;
    private final double height = 15;
    private final Image image;
    private double yVel = 2;

    private double health = 1;

    public ShipProjectile(Vector2D position){
        this.image = new Image(new File("src/main/resources/shipprojectile.png").toURI().toString(), width, height, true, true);
        this.position = position;
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
    public void update(){
        this.position.setY(this.position.getY() - this.yVel);
    }

    public double getyVel(){ return this.yVel; }

}
