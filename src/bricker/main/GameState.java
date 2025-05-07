package bricker.main;

import bricker.gameobjects.CallbackComponent;
import bricker.gameobjects.Ball;
import danogl.GameObject;
import danogl.components.Component;
import danogl.util.Vector2;

import java.util.Stack;

/**
 * The game state of the Bricker game.
 */
public class GameState {

    private int numLives;
    private int numBricks;
    private boolean tempPaddleExists;
    private int ballCollisionCounterWhenFollowStarted;
    private GameObject livesDisplay;
    private Stack<GameObject> lives;
    private GameObject ball;
    private Component ballCameraFollowComponent;


    /**
     * Construct a new GameState instance.
     */
    public GameState() {
    }

    // Getters

    /**
     * Get the ball.
     *
     * @return The ball.
     */
    public Ball getBall() {
        return (Ball) ball;
    }


    /**
     * Get the number of lives.
     *
     * @return The number of lives.
     */
    public int getNumLives() {
        return numLives;
    }


    /**
     * Get the number of bricks.
     *
     * @return The number of bricks.
     */
    public int getNumBricks() {
        return numBricks;
    }

    /**
     * Get the tail life.
     *
     * @return The tail life.
     */
    public GameObject getTailLife() {
        return lives.peek();
    }


    /**
     * Check if a temporary paddle exists.
     *
     * @return True if a temporary paddle exists, false otherwise.
     */
    public boolean tempPaddleExists() {
        return tempPaddleExists;
    }


    /**
     * Get the ball camera component.
     *
     * @return The ball camera component.
     */
    public Component getBallCamComponent() {
        return ballCameraFollowComponent;
    }

    // Setters

    /**
     * Set the ball.
     *
     * @param ball The ball.
     */
    public void setBall(GameObject ball) {
        this.ball = ball;
    }


    /**
     * Set whether a temporary paddle exists.
     *
     * @param tempPaddleExists True if a temporary paddle exists, false otherwise.
     */
    public void setTempPaddleExists(boolean tempPaddleExists) {
        this.tempPaddleExists = tempPaddleExists;
    }


    /**
     * Set the lives display.
     *
     * @param object The lives display.
     */
    public void setLiveDisplay(GameObject object) {
        livesDisplay = object;
    }


    /**
     * Increment the life state.
     *
     * @param life The life to increment.
     */
    public void incrementLifeState(GameObject life) {
        if (lives == null) {
            lives = new Stack<>();
        }
        lives.push(life);
        numLives++;
        livesDisplay.renderer().setRenderable(BrickerUtils.getLivesDisplayRenderable(numLives));
    }

    /**
     * Decrement the life state.
     */
    public void decrementLifeState() {
        lives.pop();
        numLives--;
        livesDisplay.renderer().setRenderable(BrickerUtils.getLivesDisplayRenderable(numLives));
    }


    /**
     * Decrement the number of bricks.
     */
    public void decrementNumBricks() {
        numBricks--;
    }


    /**
     * Increment the number of bricks.
     */
    public void incrementNumBricks() {
        numBricks++;
    }

    /**
     * Fail if the ball is below the window.
     */
    public int ballHitsSinceCamFollowStarted() {
        return getBall().getCollisionCounter() - ballCollisionCounterWhenFollowStarted;
    }


    /**
     * Set the ball camera component.
     *
     * @param callbackComponent The ball camera component.
     */
    public void setBallCamComponent(CallbackComponent callbackComponent) {
        if (callbackComponent != null) {
            ballCollisionCounterWhenFollowStarted = getBall().getCollisionCounter();
            ball.addComponent(callbackComponent);
        } else {
            ball.removeComponent(ballCameraFollowComponent);
        }
        ballCameraFollowComponent = callbackComponent;

    }
}
