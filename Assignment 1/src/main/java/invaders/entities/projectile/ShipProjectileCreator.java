package invaders.entities.projectile;

import invaders.physics.Vector2D;
import invaders.entities.concrete.ShipProjectile;

public class ShipProjectileCreator implements ProjectileFactory{

    public ShipProjectileCreator(){}

    public Projectile createProjectile(Vector2D position){
        ShipProjectile newProjectile = new ShipProjectile(position);
        return newProjectile;
    }

}