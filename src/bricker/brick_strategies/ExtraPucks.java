package bricker.brick_strategies;

import danogl.GameObject;
import danogl.util.Vector2;


/**
 * Extra pucks collision strategy.
 */
public class ExtraPucks implements CollisionStrategy {


    private BrickCollisionHandler collisionHandler;

    /**
     * Construct a new ExtraPucks instance.
     *
     * @param collisionHandler The handler for brick collisions.
     */
    public ExtraPucks(BrickCollisionHandler collisionHandler) {
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
        Vector2 brickCenter = thisObj.getCenter();
        collisionHandler.addPuck(brickCenter);
        collisionHandler.addPuck(brickCenter);
    }

}
