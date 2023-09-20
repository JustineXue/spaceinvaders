package invaders.entities.projectile;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;
import invaders.entities.GameObject;

public interface Projectile extends Renderable, GameObject {

    public double getyVel();

}
