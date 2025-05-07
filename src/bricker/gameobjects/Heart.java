package bricker.gameobjects;

import bricker.main.BrickerUtils;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;


/**
 * A heart GameObject.
 */
public class Heart extends GameObject {

    boolean hitPaddle = false;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     */

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     */
    public Heart(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable);
    }

    /**
     * Called when a collision occurs with another GameObject.
     *
     * @param other     The GameObject with which a collision occurred.
     * @param collision Information regarding this collision.
     *                  A reasonable elastic behavior can be achieved with:
     *                  setVelocity(getVelocity().flipped(collision.getNormal()));
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        if (this.getTag().equals(BrickerUtils.FALLING_HEART))
            hitPaddle = true;
    }


    /**
     * Should this object be allowed to collide the the specified other object.
     * If both this object returns true for the other, and the other returns true
     * for this one, the collisions may occur when they overlap, meaning that their
     * respective onCollisionEnter/onCollisionStay/onCollisionExit will be called.
     * Note that this assumes that both objects have been added to the same
     * GameObjectCollection, and that its handleCollisions() method is invoked.
     *
     * @param other The other GameObject.
     * @return true if the objects should collide. This does not guarantee a collision
     * would actually collide if they overlap, since the other object has to confirm
     * this one as well.
     */
    @Override
    public boolean shouldCollideWith(GameObject other) {
        if (!BrickerUtils.FALLING_HEART.equals(this.getTag())) return false;
        return other.getTag().equals(BrickerUtils.PADDLE);
    }


    /**
     * Get whether the heart has hit the paddle.
     *
     * @return True if the heart has hit the paddle, false otherwise.
     */
    public boolean hitPaddle() {
        return hitPaddle;
    }

}