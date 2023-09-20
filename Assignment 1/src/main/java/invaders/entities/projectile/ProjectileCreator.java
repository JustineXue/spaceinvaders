package invaders.entities.projectile;

import invaders.entities.projectile.Projectile;
import invaders.entities.GameObject;

public interface ProjectileCreator{

    public Projectile createProjectile(GameObject go);

}