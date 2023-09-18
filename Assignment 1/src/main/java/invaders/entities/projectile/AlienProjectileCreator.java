package invaders.entities.projectile;

import invaders.physics.Vector2D;
import invaders.entities.concrete.AlienProjectile;

public class AlienProjectileCreator implements ProjectileFactory{

    AlienProjectileStrategy strategy;

    public AlienProjectileCreator(String strategy){
        if (strategy.equals("slow_straight")){
            this.strategy = new SlowProjectileStrategy();
        } else if (strategy.equals("fast_straight")){
            this.strategy = new FastProjectileStrategy();
        }
    }

    public Projectile createProjectile(Vector2D position){
        AlienProjectile newProjectile = new AlienProjectile(position);
        newProjectile.setStrategy(this.strategy);
        return newProjectile;
    }

}