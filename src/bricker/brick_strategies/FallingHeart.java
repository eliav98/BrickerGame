package bricker.brick_strategies;

import danogl.GameObject;

/**
 * Falling heart collision strategy.
 */
public class FallingHeart implements CollisionStrategy {

    private BrickCollisionHandler brickCollisionHandler;

    /**
     * Construct a new FallingHeart instance.
     *
     * @param brickCollisionHandler The handler for brick collisions.
     */
    public FallingHeart(BrickCollisionHandler brickCollisionHandler) {
        this.brickCollisionHandler = brickCollisionHandler;
    }

    /**
     * Handle a collision between this object and another object.
     *
     * @param thisObj  The object that this strategy is attached to.
     * @param otherObj The object that this object collided with.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        brickCollisionHandler.removeBrick(thisObj);
        brickCollisionHandler.addFallingHeart(thisObj.getCenter());
    }
}
