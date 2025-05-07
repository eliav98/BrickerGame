package bricker.main;

import bricker.brick_strategies.BrickCollisionHandler;
import bricker.gameobjects.CallbackComponent;
import bricker.gameobjects.BrickerObjectFactory;
import bricker.gameobjects.Heart;
import bricker.gameobjects.TempPaddle;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.MessageHandler;
import danogl.util.Vector2;

import java.sql.SQLOutput;

import static bricker.main.BrickerUtils.*;

/**
 * Manages the game objects in the Bricker game.
 */
public class BrickerObjectManager extends GameObjectCollection implements BrickCollisionHandler {
    private GameState gameState;
    private Vector2 windowDimensions;
    private BrickerObjectFactory factory;

    /**
     * Construct a new BrickerObjectManager instance.
     *
     * @param messages         The message handler to use for handling messages.
     * @param windowDimensions The dimensions of the window.
     * @param gameState        The state of the game.
     * @param factory          The factory for creating game objects.
     */
    public BrickerObjectManager(MessageHandler messages, Vector2 windowDimensions, GameState gameState,
                                BrickerObjectFactory factory) {
        super(messages);
        this.gameState = gameState;
        this.windowDimensions = windowDimensions;
        this.factory = factory;
    }


    /**
     * Add a game object to the collection.
     *
     * @param object The object to add.
     */
    @Override
    public void addGameObject(GameObject object) {
        String tag = object.getTag();
        int layer = TAG_TO_LAYER.getOrDefault(tag, Layer.DEFAULT); // Retrieve layer from the

        // Special handling for specific types of objects
        if (tag.equals(BRICK)) {
            gameState.incrementNumBricks();
        } else if (tag.equals(BALL)) {
            object.addComponent(new CallbackComponent(object, this::failIfBelowWindow));
            gameState.setBall(object);
        } else if (tag.equals(LIVES_DIGITAL_COUNTER)) {
            gameState.setLiveDisplay(object);
        } else if (tag.equals(LIFE)) {
            if (gameState.getNumLives() == MAX_LIVES) return;
            gameState.incrementLifeState(object);
        } else if (tag.equals(PADDLE)) {
            object.addComponent(new CallbackComponent(object, this::stopPaddleIfHitsBorders));
        }
//        System.out.println("Adding object: " + object.getTag());
        addGameObject(object, layer);
    }

    /**
     * Remove a game object from the collection.
     *
     * @param object The object to remove.
     * @return True if the object was removed, false otherwise.
     */
    @Override
    public boolean removeGameObject(GameObject object) {
        int layer = TAG_TO_LAYER.getOrDefault(object.getTag(), Layer.DEFAULT);
        boolean removed = removeGameObject(object, layer);
        if (!removed) return false;
        if (object.getTag().equals(BRICK))
            gameState.decrementNumBricks();
        else if (object.getTag().equals(LIFE))
            gameState.decrementLifeState();
        else if (object.getTag().equals(TEMP_PADDLE))
            gameState.setTempPaddleExists(false);
//        System.out.println("Removing object: " + object.getTag());
        return true;
    }

    private void failIfBelowWindow(GameObject object) {
        if (object.getCenter().y() > windowDimensions.y()) {
            removeGameObject(gameState.getTailLife());
            object.setCenter(windowCenter(windowDimensions));
            object.setVelocity(randomBallVelocity());
        }
    }

    private void stopPaddleIfHitsBorders(GameObject gameObject) {
        float x = gameObject.getTopLeftCorner().x();
        if (x <= BORDER_WIDTH)
            x = BORDER_WIDTH;
        else if (x >= windowDimensions.x() - BORDER_WIDTH - PADDLE_WIDTH)
            x = windowDimensions.x() - BORDER_WIDTH - PADDLE_WIDTH;
        else
            return;
        gameObject.setTopLeftCorner(new Vector2(x, gameObject.getTopLeftCorner().y()));
    }

    /**
     * Remove a brick from the game.
     *
     * @param gameObject The brick to remove.
     * @return True if the brick was removed, false otherwise.
     */
    @Override
    public boolean removeBrick(GameObject gameObject) {
        return gameObject.getTag().equals(BRICK) && removeGameObject(gameObject);
    }

    private void removeIfBelowWindow(GameObject object) {
        if (object.getCenter().y() > windowDimensions.y()) {
            removeGameObject(object);
        }
    }

    /**
     * Add a puck to the game.
     *
     * @param position The position to add the puck at.
     */
    @Override
    public void addPuck(Vector2 position) {
        GameObject puck = factory.buildBrickerObject(PUCK);
        puck.addComponent(new CallbackComponent(puck, this::removeIfBelowWindow));
        puck.setCenter(position);
        addGameObject(puck);
    }

    private void convertToLifeIfHitPaddle(GameObject object) {
        if (object.getTag().equals(FALLING_HEART) && ((Heart) object).hitPaddle()) {
            removeGameObject(object);
            addGameObject(factory.buildLife(gameState.getNumLives()));
        }
    }

    /**
     * Add a falling heart to the game.
     *
     * @param position The position to add the heart at.
     */
    @Override
    public void addFallingHeart(Vector2 position) {
        GameObject heart = factory.buildBrickerObject(FALLING_HEART);
        heart.addComponent(new CallbackComponent(heart, this::removeIfBelowWindow));
        heart.addComponent(new CallbackComponent(heart, this::convertToLifeIfHitPaddle));
        heart.setCenter(position);
        addGameObject(heart);
    }


    /**
     * Add a temporary paddle to the game.
     */
    @Override
    public void addTempPaddle() {
        if (gameState.tempPaddleExists()) return;
        gameState.setTempPaddleExists(true);
        GameObject tempPaddle = factory.buildBrickerObject(TEMP_PADDLE);
        tempPaddle.addComponent(new CallbackComponent(tempPaddle, this::removeIfMaxHitsReached));
        tempPaddle.addComponent(new CallbackComponent(tempPaddle, this::stopPaddleIfHitsBorders));
        addGameObject(tempPaddle);
    }

    private void removeIfMaxHitsReached(GameObject gameObject) {
        if (gameObject.getTag().equals(TEMP_PADDLE) &&
                ((TempPaddle) gameObject).getNumHits() >= MAX_TEMP_PADDLE_HITS) {
            removeGameObject(gameObject);
        }
    }

    /**
     * Start following the ball with the camera.
     */
    @Override
    public void startCamFollowBall() {
        if (gameState.getBallCamComponent() != null) return;
        gameState.setBallCamComponent(new CallbackComponent(gameState.getBall(),
                this::stopCamFollowIfBallHitsReached));
    }

    private void stopCamFollowIfBallHitsReached(GameObject gameObject) {
        if (gameState.ballHitsSinceCamFollowStarted() == 4) {
            gameState.setBallCamComponent(null);
        }
    }
}