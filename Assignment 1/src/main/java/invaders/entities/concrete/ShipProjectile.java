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

    public ShipProjectile(Vector2D position){
        this.image = new Image(new File("src/main/resources/shipprojectile.png").toURI().toString(), width, height, true, true);
        this.position = position;
    }

    @Override
    public void up() {
        this.position.setY(this.position.getY() - this.yVel);
    }

    @Override
    public void down() {
        return;
    }

    @Override
    public void left() {
        return;
    }

    @Override
    public void right() {
        return;
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
        this.up();
    }

    public double getyVel(){ return this.yVel; }

}
