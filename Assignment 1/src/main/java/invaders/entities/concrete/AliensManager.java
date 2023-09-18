package invaders.entities.concrete;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.image.Image;

public class AliensManager{
    private List<Alien> aliens = new ArrayList<Alien>();

    public AliensManager(){}

    public void addAlien(Alien a){
        aliens.add(a);
    }

    public void removeAlien(Alien a){
        aliens.remove(a);
    }

    public List<Alien> getAliens(){
        return aliens;
    }

}