package bricker.brick_strategies;

import danogl.GameObject;

/**
 * Extra temporary paddle collision strategy.
 */
public class ExtraTempPaddle implements CollisionStrategy {
    private BrickCollisionHandler collisionHandler;


    /**
     * Construct a new ExtraTempPaddle instance.
     *
     * @param collisionHandler The handler for brick collisions.
     */
    public ExtraTempPaddle(BrickCollisionHandler collisionHandler) {
        this.collisionHandler = collisionHandler;
    }


    /**
     * Handle a collision between this object and another object.
     *
     * @param thisObj  The object that this strategy is attached to.
     * @param otherObj The object that this object collided with.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        collisionHandler.removeBrick(thisObj);
        collisionHandler.addTempPaddle();
    }
}
