package invaders.entities.projectile;

public class FastProjectileStrategy implements AlienProjectileStrategy{

    private double yVel = 1;

    public FastProjectileStrategy(){

    }

    public double getyVel(){
        return this.yVel;
    }

}
