package invaders.entities.builder;

import javafx.scene.image.Image;
import invaders.physics.Vector2D;
import invaders.entities.concrete.Alien;

public class AlienBuilder implements GameObjectBuilder{

    public AlienBuilder(){
    }

    public Alien buildPart(int height, int width, Vector2D position, String strategy){
        Alien newAlien = new Alien(position, strategy);
        return newAlien;
    }

}