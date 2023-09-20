package invaders.entities.projectile;

import invaders.entities.projectile.ProjectileStrategy;

public class SlowStrategy implements ProjectileStrategy{

    private double yVel = 1;

    public SlowStrategy(){}

    @Override
    public double getyVel(){ return this.yVel; }

}