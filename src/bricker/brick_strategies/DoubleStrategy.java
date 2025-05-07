package bricker.brick_strategies;

import danogl.GameObject;

import java.util.ArrayList;

/**
 * Double collision strategy.
 */
public class DoubleStrategy implements CollisionStrategy {

    ArrayList<CollisionStrategy> strategies;

    /**
     * Construct a new DoubleStrategy instance.
     *
     * @param strategies The strategies to use.
     */
    public DoubleStrategy(ArrayList<CollisionStrategy> strategies) {
        this.strategies = strategies;
    }

    /**
     * Handle a collision between this object and another object.
     *
     * @param thisObj  The object that this strategy is attached to.
     * @param otherObj The object that this object collided with.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        for (CollisionStrategy strategy : strategies) {
            strategy.onCollision(thisObj, otherObj);
        }
    }


}
