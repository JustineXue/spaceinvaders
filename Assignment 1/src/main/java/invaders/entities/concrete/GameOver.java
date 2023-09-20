package invaders.entities.concrete;

import java.io.File;
import javafx.scene.image.Image;

import invaders.rendering.Renderable;
import invaders.rendering.Animator;
import invaders.physics.Vector2D;

public class GameOver implements Renderable{

    private Vector2D position;
    private final Animator anim = null;

    private double width = 100;
    private double height = 30;
    private final Image image;

    public GameOver(Vector2D position){
        this.image = new Image(new File("src/main/resources/gameover.png").toURI().toString(), width, height, true, true);
        this.position = position;
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

}
