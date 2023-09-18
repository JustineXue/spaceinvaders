package invaders.entities.bunker;

import invaders.entities.bunker.BunkerState;

import javafx.scene.image.Image;

import java.io.File;


public class RedBunker implements BunkerState{

    private final Image image;

    private String colour;

    public RedBunker(double height, double width){
        this.colour = "red";
        this.image = new Image(new File("src/main/resources/bunkerred.png").toURI().toString(), width, height, true, true);
    }

    public Image getBunkerImage(){
        return this.image;
    }

    public String getColour(){
        return this.colour;
    }

}