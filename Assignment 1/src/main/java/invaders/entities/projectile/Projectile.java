package invaders.entities.projectile;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;
import invaders.entities.GameObject;
import invaders.logic.Damagable;

public interface Projectile extends Renderable, GameObject, Damagable {

    public double getyVel();

}
