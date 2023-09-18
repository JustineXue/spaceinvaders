package invaders.entities.concrete;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.image.Image;

public class BunkerManager{
    private List<Bunker> bunkers = new ArrayList<Bunker>();

    public BunkerManager(){}

    public void addBunker(Bunker b){
        bunkers.add(b);
    }

    public void removeBunker(Bunker b){
        bunkers.remove(b);
    }

    public List<Bunker> getBunkers(){
        return bunkers;
    }

}