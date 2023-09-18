package invaders.entities.projectile;

import invaders.physics.Vector2D;
import invaders.entities.projectile.Projectile;

import java.util.List;
import java.util.ArrayList;

public class ProjectileManager{

    private ProjectileFactory projectileFactory;

    private List<Projectile> projectiles = new ArrayList<Projectile>();

    public ProjectileManager(ProjectileFactory projectileFactory){
        this.projectileFactory = projectileFactory;
    }

    public void addProjectile(Vector2D position){
        Projectile newProjectile = this.projectileFactory.createProjectile(position);
        this.projectiles.add(newProjectile);
    }

    public void removeProjectile(Projectile p){
        this.projectiles.remove(p);
    }

    public List<Projectile> getProjectiles(){
        return this.projectiles;
    }

}