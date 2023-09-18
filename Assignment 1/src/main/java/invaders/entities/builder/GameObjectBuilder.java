package invaders.entities.builder;

import invaders.entities.GameObject;
import invaders.physics.Vector2D;

public interface GameObjectBuilder{

    public GameObject buildPart(int height, int width, Vector2D position, String strategy);

}
