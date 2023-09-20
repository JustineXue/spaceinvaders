package invaders.entities.builder;

import invaders.entities.GameObject;
import invaders.physics.Vector2D;

public interface GameObjectBuilder{

    public void buildPosition(int x, int y);

    public void buildDimensions(int height, int width);


    public void buildStrategy(String strategy);

    public GameObject getProduct();

}
