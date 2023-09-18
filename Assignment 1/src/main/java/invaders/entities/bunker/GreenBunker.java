package invaders.entities.bunker;

import invaders.entities.bunker.BunkerState;

import javafx.scene.image.Image;

import java.io.File;

public class GreenBunker implements BunkerState{

    private final Image image;
    private String colour;

    public GreenBunker(double height, double width){
        this.colour = "green";
        this.image = new Image(new File("src/main/resources/bunkergreen.png").toURI().toString(), width, height, true, true);
    }

    public Image getBunkerImage(){
        return this.image;
    }

    public String getColour(){
        return this.colour;
    }

}