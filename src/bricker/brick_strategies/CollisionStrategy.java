package bricker.brick_strategies;

import danogl.GameObject;


/**
 * Interface for handling brick collisions.
 */
public interface CollisionStrategy {

    /**
     * Handle a collision between this object and another object.
     *
     * @param thisObj  The object that this strategy is attached to.
     * @param otherObj The object that this object collided with.
     */
    void onCollision(GameObject thisObj, GameObject otherObj);

}
