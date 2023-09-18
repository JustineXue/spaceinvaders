package invaders.entities.projectile;

import invaders.physics.Vector2D;
import invaders.entities.projectile.Projectile;

public interface ProjectileFactory{

    public Projectile createProjectile(Vector2D position);

}