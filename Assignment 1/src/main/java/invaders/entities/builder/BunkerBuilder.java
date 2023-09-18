package invaders.entities.builder;

import invaders.physics.Vector2D;
import invaders.entities.concrete.Bunker;

public class BunkerBuilder implements GameObjectBuilder{

    public BunkerBuilder(){
    }

    public Bunker buildPart(int height, int width, Vector2D position, String strategy){
        double bunkerHeight = (double) height;
        double bunkerWidth = (double) width;
        Bunker newBunker = new Bunker(bunkerHeight, bunkerWidth, position);
        return newBunker;
    }

}