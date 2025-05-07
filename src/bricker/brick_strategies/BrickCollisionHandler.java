package bricker.brick_strategies;

import danogl.GameObject;
import danogl.util.Vector2;

/**
 * Interface for handling brick collisions.
 */
public interface BrickCollisionHandler {

    /**
     * Remove a brick from the game.
     *
     * @param gameObject The brick to remove.
     * @return True if the brick was removed, false otherwise.
     */
    boolean removeBrick(GameObject gameObject);

    /**
     * Add a puck to the game.
     *
     * @param position The position to add the puck at.
     */
    void addPuck(Vector2 position);

    /**
     * Add a falling heart to the game.
     *
     * @param position The position to add the heart at.
     */
    void addFallingHeart(Vector2 position);

    /**
     * Add a temporary paddle to the game.
     */
    void addTempPaddle();

    /**
     * Start the camera following the ball.
     */
    void startCamFollowBall();

}
