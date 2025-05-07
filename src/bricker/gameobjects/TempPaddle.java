package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * A temporary paddle GameObject.
 */
public class TempPaddle extends Paddle {
    private int numHits;


    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     * @param inputListener
     */
    public TempPaddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                      UserInputListener inputListener) {
        super(topLeftCorner, dimensions, renderable, inputListener);
        this.numHits = 0;
    }

    /**
     * Get the number of hits that have occurred with this paddle.
     *
     * @return The number of hits.
     */
    public int getNumHits() {
        return numHits;
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
        super.onCollisionEnter(other, collision);
        if (other.getTag().equals("ball") || other.getTag().equals("puck")) {
            numHits++;
        }
    }

}

