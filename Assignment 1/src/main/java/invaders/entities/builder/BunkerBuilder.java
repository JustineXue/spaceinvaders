package invaders.entities.builder;

import invaders.physics.Vector2D;
import invaders.entities.concrete.Bunker;
import invaders.entities.GameObject;

public class BunkerBuilder implements GameObjectBuilder{

    private Bunker bunker;

    public BunkerBuilder(){
        this.bunker = new Bunker();
    }

    @Override
    public void buildPosition(int x, int y){
        this.bunker.setPosition(new Vector2D(x, y));
    }

    @Override
    public void buildDimensions(int height, int width){
        this.bunker.setHeight(30);
        this.bunker.setWidth(25);
    }


    @Override
    public void buildStrategy(String strategy){
        return;
    }

    @Override
    public GameObject getProduct(){ return this.bunker; }

}