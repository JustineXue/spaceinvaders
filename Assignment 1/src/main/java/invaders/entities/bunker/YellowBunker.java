package invaders.entities.bunker;

import invaders.entities.bunker.BunkerState;

import javafx.scene.image.Image;

import java.io.File;

public class YellowBunker implements BunkerState{

    private final Image image;

    private String colour;

    public YellowBunker(double height, double width){
        this.colour = "yellow";
        this.image = new Image(new File("src/main/resources/bunkeryellow.png").toURI().toString(), width, height, true, true);
    }

    public Image getBunkerImage(){
        return this.image;
    }

    public String getColour() {
        return colour;
    }
}