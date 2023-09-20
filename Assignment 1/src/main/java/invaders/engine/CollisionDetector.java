package invaders.engine;

import invaders.physics.Vector2D;
import invaders.entities.GameObject;
import invaders.entities.concrete.Alien;
import invaders.entities.concrete.Bunker;
import invaders.entities.concrete.Player;
import invaders.entities.concrete.AlienProjectile;
import invaders.entities.concrete.ShipProjectile;

public class CollisionDetector{

    public CollisionDetector(){}

    public boolean isColliding(double x1, double y1, double width1, double height1,
        double x2, double y2, double width2, double height2) {
            // Calculate the right and bottom edges of the objects
        double right1 = x1 + width1;
        double bottom1 = y1 + height1;
        double right2 = x2 + width2;
        double bottom2 = y2 + height2;

        // Check for collision
        if (x1 < right2 && right1 > x2 && y1 < bottom2 && bottom1 > y2) {
            // The objects are colliding
            return true;
        }

        // No collision
        return false;
    }

    public boolean detectShipCollision(Player player, AlienProjectile projectile){
        double x1 = player.getPosition().getX();
        double y1 = player.getPosition().getY();
        double width1 = player.getWidth();
        double height1 = player.getHeight();

        double x2 = projectile.getPosition().getX();
        double y2 = projectile.getPosition().getY();
        double width2 = projectile.getWidth();
        double height2 = projectile.getHeight();

        boolean isColliding = isColliding(
                x1, y1, width1, height1,
                x2, y2, width2, height2
        );

        return isColliding;
    }

    public boolean detectAlienCollision(Alien alien, ShipProjectile projectile){
        double x1 = alien.getPosition().getX();
        double y1 = alien.getPosition().getY();
        double width1 = alien.getWidth();
        double height1 = alien.getHeight();

        double x2 = projectile.getPosition().getX();
        double y2 = projectile.getPosition().getY();
        double width2 = projectile.getWidth();
        double height2 = projectile.getHeight();

        boolean isColliding = isColliding(
                x1, y1, width1, height1,
                x2, y2, width2, height2
        );

        return isColliding;
    }

    public boolean detectBunkerCollision(Bunker bunker, AlienProjectile projectile){
        double x1 = bunker.getPosition().getX();
        double y1 = bunker.getPosition().getY();
        double width1 = bunker.getWidth();
        double height1 = bunker.getHeight();

        double x2 = projectile.getPosition().getX();
        double y2 = projectile.getPosition().getY();
        double width2 = projectile.getWidth();
        double height2 = projectile.getHeight();

        boolean isColliding = isColliding(
                x1, y1, width1, height1,
                x2, y2, width2, height2
        );

        return isColliding;
    }

}

