package invaders.entities.projectile;

import invaders.physics.Vector2D;
import invaders.entities.concrete.ShipProjectile;
import invaders.entities.GameObject;
import invaders.entities.projectile.ProjectileCreator;
import invaders.entities.concrete.Player;

public class ShipProjectileCreator implements ProjectileCreator{

    public ShipProjectileCreator(){}

    public Projectile createProjectile(GameObject go){
        Vector2D clonePos = null;
        if (go instanceof Player){
            Player p = (Player) go;
            Vector2D pPos = p.getPosition();
            clonePos = new Vector2D(pPos.getX(), pPos.getY());
        }

        ShipProjectile newProjectile = new ShipProjectile(clonePos);
        return newProjectile;
    }

}