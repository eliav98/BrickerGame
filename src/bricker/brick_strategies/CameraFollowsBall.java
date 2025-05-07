package bricker.brick_strategies;

import danogl.GameObject;

import static bricker.main.BrickerUtils.BALL;

/**
 * Camera follows the ball collision strategy.
 */
public class CameraFollowsBall implements CollisionStrategy {

    private BrickCollisionHandler handler;

    /**
     * Construct a new CameraFollowsBall instance.
     *
     * @param handler The handler for brick collisions.
     */
    public CameraFollowsBall(BrickCollisionHandler handler) {
        this.handler = handler;
    }

    /**
     * Handle a collision between this object and another object.
     *
     * @param thisObj  The object that this strategy is attached to.
     * @param otherObj The object that this object collided with.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        handler.removeBrick(thisObj);
        if (otherObj.getTag().equals(BALL)) {
            handler.startCamFollowBall();
        }
    }
}
