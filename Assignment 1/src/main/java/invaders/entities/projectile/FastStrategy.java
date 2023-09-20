package invaders.entities.projectile;

import invaders.entities.projectile.ProjectileStrategy;

public class FastStrategy implements ProjectileStrategy{

    private double yVel = 2;

    public FastStrategy(){}


    @Override
    public double getyVel(){ return this.yVel; }

}