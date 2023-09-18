package invaders.entities.builder;

import javafx.scene.image.Image;
import invaders.physics.Vector2D;
import invaders.entities.concrete.Alien;
import invaders.entities.GameObject;

public class AlienBuilder implements GameObjectBuilder{

    public AlienBuilder(){
    }

    public GameObject buildPart(double height, double width, Vector2D position, String strategy){
        Alien newAlien = new Alien(position, strategy);
        return newAlien;
    }

}