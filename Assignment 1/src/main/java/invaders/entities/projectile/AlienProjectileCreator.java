package invaders.entities.projectile;

import invaders.physics.Vector2D;
import invaders.entities.concrete.AlienProjectile;
import invaders.entities.GameObject;
import invaders.entities.projectile.ProjectileCreator;
import invaders.entities.concrete.Alien;

public class AlienProjectileCreator implements ProjectileCreator{

    public AlienProjectileCreator(){}

    public Projectile createProjectile(GameObject go){
        double yVel = 0.0;
        Vector2D clonePos = null;
        if (go instanceof Alien){
            Alien a = (Alien) go;
            yVel = a.getStrategy().getyVel();
            Vector2D aPos = a.getPosition();
            clonePos = new Vector2D(aPos.getX(), aPos.getY());
        }

        AlienProjectile newProjectile = new AlienProjectile(clonePos, yVel);
        return newProjectile;
    }

}