package invaders.entities.projectile;

public class SlowProjectileStrategy implements AlienProjectileStrategy{

    private double yVel = 0.5;

    public SlowProjectileStrategy(){

    }

    public double getyVel(){
        return this.yVel;
    }

}
