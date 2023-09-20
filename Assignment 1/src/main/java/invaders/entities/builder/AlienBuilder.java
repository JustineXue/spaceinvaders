package invaders.entities.builder;

import javafx.scene.image.Image;
import invaders.physics.Vector2D;
import invaders.entities.concrete.Alien;
import invaders.entities.projectile.ProjectileStrategy;
import invaders.entities.projectile.FastStrategy;
import invaders.entities.projectile.SlowStrategy;
import invaders.entities.GameObject;

public class AlienBuilder implements GameObjectBuilder{

    private Alien alien;

    public AlienBuilder(){
        this.alien = new Alien();
    }

    @Override
    public void buildPosition(int x, int y){
        this.alien.setPosition(new Vector2D(x, y));
    }

    @Override
    public void buildDimensions(int height, int width){
        return;
    }


    @Override
    public void buildStrategy(String strategy){
        if (strategy.equals("slow_straight")){
            this.alien.setStrategy(new SlowStrategy());
        } else if (strategy.equals("fast_straight")){
            this.alien.setStrategy(new FastStrategy());
        }
    }

    @Override
    public GameObject getProduct(){ return this.alien; }


}