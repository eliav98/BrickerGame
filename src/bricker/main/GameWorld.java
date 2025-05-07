package bricker.main;

import bricker.brick_strategies.BrickCollisionHandler;
import bricker.gameobjects.BrickerObjectFactory;
import bricker.gameobjects.CallbackComponent;
import bricker.gameobjects.Heart;
import bricker.gameobjects.TempPaddle;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.util.Vector2;

import static bricker.main.BrickerUtils.*;

/**
 * GameWorld orchestrates game logic and implements BrickCollisionHandler.
 */
public class GameWorld implements BrickCollisionHandler {

    private final GameState gameState;
    private final Vector2 windowDimensions;
    private final BrickerObjectFactory factory;
    private final BrickerObjectManager objectManager;

    public GameWorld(GameState gameState, Vector2 windowDimensions,
                     BrickerObjectFactory factory, BrickerObjectManager objectManager) {
        this.gameState = gameState;
        this.windowDimensions = windowDimensions;
        this.factory = factory;
        this.objectManager = objectManager;
    }

    @Override
    public boolean removeBrick(GameObject gameObject) {
        return gameObject.getTag().equals(BRICK) && objectManager.removeGameObject(gameObject);
    }

    @Override
    public void addPuck(Vector2 position) {
        GameObject puck = factory.buildBrickerObject(PUCK);
        puck.addComponent(new CallbackComponent(puck, this::removeIfBelowWindow));
        puck.setCenter(position);
        objectManager.addGameObject(puck);
    }

    @Override
    public void addFallingHeart(Vector2 position) {
        GameObject heart = factory.buildBrickerObject(FALLING_HEART);
        heart.addComponent(new CallbackComponent(heart, this::removeIfBelowWindow));
        heart.addComponent(new CallbackComponent(heart, this::convertToLifeIfHitPaddle));
        heart.setCenter(position);
        objectManager.addGameObject(heart);
    }

    @Override
    public void addTempPaddle() {
        if (gameState.tempPaddleExists()) return;
        gameState.setTempPaddleExists(true);
        GameObject tempPaddle = factory.buildBrickerObject(TEMP_PADDLE);
        tempPaddle.addComponent(new CallbackComponent(tempPaddle, this::removeIfMaxHitsReached));
        tempPaddle.addComponent(new CallbackComponent(tempPaddle, this::stopPaddleIfHitsBorders));
        objectManager.addGameObject(tempPaddle);
    }

    @Override
    public void startCamFollowBall() {
        if (gameState.getBallCamComponent() != null) return;
        gameState.setBallCamComponent(new CallbackComponent(gameState.getBall(),
                this::stopCamFollowIfBallHitsReached));
    }

    // === Callback Methods ===
    private void removeIfBelowWindow(GameObject object) {
        if (object.getCenter().y() > windowDimensions.y()) {
            objectManager.removeGameObject(object);
        }
    }

    private void convertToLifeIfHitPaddle(GameObject object) {
        if (object.getTag().equals(FALLING_HEART) && ((Heart) object).hitPaddle()) {
            objectManager.removeGameObject(object);
            objectManager.addGameObject(factory.buildLife(gameState.getNumLives()));
        }
    }

    private void removeIfMaxHitsReached(GameObject gameObject) {
        if (gameObject.getTag().equals(TEMP_PADDLE) &&
                ((TempPaddle) gameObject).getNumHits() >= MAX_TEMP_PADDLE_HITS) {
            objectManager.removeGameObject(gameObject);
        }
    }

    private void stopCamFollowIfBallHitsReached(GameObject gameObject) {
        if (gameState.ballHitsSinceCamFollowStarted() == 4) {
            gameState.setBallCamComponent(null);
        }
    }

    public void failIfBelowWindow(GameObject object) {
        if (object.getCenter().y() > windowDimensions.y()) {
            objectManager.removeGameObject(gameState.getTailLife());
            object.setCenter(windowCenter(windowDimensions));
            object.setVelocity(randomBallVelocity());
        }
    }

    public void stopPaddleIfHitsBorders(GameObject gameObject) {
        float x = gameObject.getTopLeftCorner().x();
        if (x <= BORDER_WIDTH)
            x = BORDER_WIDTH;
        else if (x >= windowDimensions.x() - BORDER_WIDTH - PADDLE_WIDTH)
            x = windowDimensions.x() - BORDER_WIDTH - PADDLE_WIDTH;
        else
            return;
        gameObject.setTopLeftCorner(new Vector2(x, gameObject.getTopLeftCorner().y()));
    }
}
