package bricker.gameobjects;

import bricker.brick_strategies.CollisionStrategy;
import danogl.GameObject;
import danogl.gui.ImageReader;
import danogl.gui.Sound;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import static bricker.main.BrickerUtils.*;

/**
 * Factory for creating Bricker GameObjects.
 */
public class BrickerObjectFactory {
    private Vector2 windowDimensions;
    private UserInputListener inputListener;
    private ImageReader imageReader;
    private Sound collisionSound;
    private int numBricksInRow;
    private Renderable ballRenderable;
    private Renderable puckRenderable;
    private Renderable brickRenderable;
    private Renderable paddleRenderable;
    private Renderable heartRenderable;


    /**
     * Construct a new BrickerObjectFactory instance.
     *
     * @param imageReader      The image reader to use for reading images.
     * @param soundReader      The sound reader to use for reading sounds.
     * @param inputListener    The input listener to use for listening to user input.
     * @param windowDimensions The dimensions of the window.
     * @param numBricksInRow   The number of bricks in a row.
     */
    public BrickerObjectFactory(ImageReader imageReader, SoundReader soundReader,
                                UserInputListener inputListener, Vector2 windowDimensions,
                                int numBricksInRow) {
        this.imageReader = imageReader;
        this.inputListener = inputListener;
        this.windowDimensions = windowDimensions;
        this.collisionSound = soundReader.readSound(BALL_COLLISION_SOUND_PATH);
        this.numBricksInRow = numBricksInRow;
        initRenderables();
    }

    private void initRenderables() {
        this.ballRenderable = imageReader.readImage(BALL_IMAGE_PATH, true);
        this.puckRenderable = imageReader.readImage(PUCK_IMAGE_PATH, true);
        this.brickRenderable = imageReader.readImage(BRICK_IMAGE_PATH, false);
        this.paddleRenderable = imageReader.readImage(PADDLE_IMAGE_PATH, false);
        this.heartRenderable = imageReader.readImage(HEART_IMAGE_PATH, true);
    }


    /**
     * Create a Bricker GameObject with all the none-dynamic inherent properties set, i.e. dimensions,
     * velocity,
     * renderable, sound, input listener, and in some cases position.
     *
     * @param tag The tag of the object to create.
     * @return The created GameObject.
     */
    public GameObject buildBrickerObject(String tag) {
        GameObject object = null;

        if (BALL.equals(tag)) {
            object = new Ball(Vector2.ZERO, BALL_DIMENSIONS, ballRenderable, collisionSound);
            object.setCenter(windowCenter(windowDimensions));
            object.setVelocity(randomBallVelocity());
        } else if (PUCK.equals(tag)) {
            object = new Ball(Vector2.ZERO, PUCK_DIMENSIONS, puckRenderable, collisionSound);
            object.setVelocity(randomPuckVelocity());
        } else if (PADDLE.equals(tag)) {
            object = new Paddle(Vector2.ZERO, PADDLE_DIMENSIONS, paddleRenderable, inputListener);
            object.setCenter(paddleCenterPosition());
        } else if (TEMP_PADDLE.equals(tag)) {
            object = new TempPaddle(Vector2.ZERO, PADDLE_DIMENSIONS, paddleRenderable, inputListener);
            object.setCenter(windowCenter(windowDimensions));
        } else if (FALLING_HEART.equals(tag)) {
            object = new Heart(Vector2.ZERO, HEART_DIMENSIONS, heartRenderable);
            object.setVelocity(FALLING_HEART_INITIAL_VELOCITY);
        } else if (LIVES_DIGITAL_COUNTER.equals(tag))
            object = buildLivesDisplay();
        else {
            return object;
        }

        object.setTag(tag);
        return object;
    }

    /**
     * Create a brick GameObject with the given position and collision strategy.
     *
     * @param i        The row index of the brick.
     * @param j        The column index of the brick.
     * @param strategy The collision strategy to use for the brick.
     * @return The created brick GameObject.
     */
    public Brick buildBrick(int i, int j, CollisionStrategy strategy) {
        float brickWidth = (windowDimensions.x() - 2 * BORDER_WIDTH - (numBricksInRow - 1) * SPACING)
                / numBricksInRow;
        Brick brick = new Brick(
                new Vector2(
                        j * (brickWidth + SPACING) + BORDER_WIDTH,
                        i * (BRICK_HEIGHT + SPACING) + BORDER_WIDTH),
                new Vector2(brickWidth, BRICK_HEIGHT), brickRenderable, strategy);
        brick.setTag(BRICK);
        return brick;
    }


    /**
     * Create a life GameObject with the given index.
     *
     * @param lifeIndex The index of the life.
     * @return The created life GameObject.
     */
    public Heart buildLife(int lifeIndex) {
        float livesStartX = BORDER_WIDTH + SPACING + LIVES_COUNTER_WIDTH + SPACING;
        Heart life = new Heart(
                new Vector2(livesStartX + lifeIndex * (HEART_WIDTH + SPACING),
                        windowDimensions.y() - HEART_HEIGHT - SPACING),
                HEART_DIMENSIONS, heartRenderable);
        life.setTag(LIFE);
        return life;
    }

    private GameObject buildLivesDisplay() {
        Renderable renderable = getLivesDisplayRenderable(0);
        GameObject livesDisplay = new GameObject(
                new Vector2(BORDER_WIDTH + SPACING, windowDimensions.y() - LIVES_COUNTER_HEIGHT - SPACING),
                LIVES_COUNTER_DIMENSION,
                renderable
        );
        return livesDisplay;
    }

    private Vector2 paddleCenterPosition() {
        return new Vector2(windowDimensions.x() / 2, windowDimensions.y() - 2.5f * PADDLE_HEIGHT);
    }

}

