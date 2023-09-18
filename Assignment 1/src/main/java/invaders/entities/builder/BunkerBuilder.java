package invaders.entities.builder;

import invaders.physics.Vector2D;
import invaders.entities.concrete.Bunker;
import invaders.entities.GameObject;

public class BunkerBuilder implements GameObjectBuilder{

    public BunkerBuilder(){
    }

    public GameObject buildPart(double height, double width, Vector2D position, String strategy){
        Bunker newBunker = new Bunker(height, width, position);
        return newBunker;
    }

}