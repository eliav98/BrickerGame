package bricker.main;

import danogl.collisions.Layer;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Vector2;

import java.awt.*;
import java.util.Random;

import java.util.Map;

/**
 * Utility class for the Bricker game.
 */
public class BrickerUtils {


//    public static final Vector2 DESIRED_WINDOW_DIMENSIONS = new Vector2(1500, 700).mult(0.9f);
//    public static final Renderable BORDER_RENDERABLE = new RectangleRenderable(new Color(80, 140, 250));


    private BrickerUtils() {
//        throw new IllegalStateException("Utility class");
    }


    /**
     * see constant name
     */
    public static final String WIN_PROMPT = "You Win!";

    /**
     * see constant name
     */
    public static final String LOSE_PROMPT = "You Lose!";

    /**
     * see constant name
     */
    public static final String RESTART_PROMPT = "Play again?";

    /**
     * see constant name
     */
    public static final int LEGAL_NUM_ARGS = 2;

    /**
     * see constant name
     */
    public static final int WINDOW_WIDTH = 700;

    /**
     * see constant name
     */
    public static final int WINDOW_HEIGHT = 500;

    /**
     * see constant name
     */
    public static final Vector2 WINDOW_DIMENSIONS = new Vector2(WINDOW_WIDTH, WINDOW_HEIGHT);

    /**
     * see constant name
     */
    public static final String WINDOW_TITLE = "Bricker Game";

    /**
     * see constant name
     */
    public static final int SPACING = 1;

    /**
     * see constant name
     */
    public static final int DEFAULT_BRICKS_IN_ROW = 8;

    /**
     * see constant name
     */
    public static final int DEFAULT_NUM_ROWS = 7;

    // Brick collision constants

    /**
     * see constant name
     */
    public static final int MAX_COLLISION_STRATEGY = 3;

    /**
     * see constant name
     */
    public static final int MAX_PADDLES = 2;

    /**
     * see constant name
     */
    public static final int MAX_TEMP_PADDLE_HITS = 4;

    // Constants

    /**
     * see constant name
     */
    public static final Random random = new Random();

    // Window constants

    /**
     * see constant name
     */
    public static final float BORDER_WIDTH = 10.5f;


    //    public static final int DESIRED_WINDOW_WIDTH = 750; // 1400

    //    public static final int WINDOW_HEIGHT = 375;

    //    public static final Vector2 DESIRED_WINDOW_DIMENSIONS = new Vector2(DESIRED_WINDOW_WIDTH,
    //    WINDOW_HEIGHT);

    // Lives constants
    /**
     * see constant name
     */
    public static final Vector2 LIVES_COUNTER_POSITION = new Vector2(20, 20);

    /**
     * see constant name
     */
    public static final int DEFAULT_INITIAL_LIVES = 3;

    /**
     * see constant name
     */
    public static final int MAX_LIVES = 4;


    // Heart constants
    /**
     * see constant name
     */
    public static final int HEART_WIDTH = 20;

    /**
     * see constant name
     */
    public static final int HEART_HEIGHT = 20;

    /**
     * see constant name
     */
    public static final Vector2 HEART_DIMENSIONS = new Vector2(HEART_WIDTH, HEART_HEIGHT);

    /**
     * see constant name
     */
    public static final int HEART_FALL_SPEED = 100;

    /**
     * see constant name
     */
    public static final Vector2 FALLING_HEART_INITIAL_VELOCITY = Vector2.DOWN.mult(HEART_FALL_SPEED);

    // Ball constants
    /**
     * see constant name
     */
    public static final int BALL_DIAMETER = 20;

    /**
     * see constant name
     */
    public static final float BALL_SPEED = 200;

    /**
     * see constant name
     */
    public static final Vector2 BALL_DIMENSIONS = new Vector2(BALL_DIAMETER, BALL_DIAMETER);

    // Puck constants

    /**
     * see constant name
     */
    public static final int PUCK_DIAMETER = (3 * BALL_DIAMETER) / 4;

    /**
     * see constant name
     */
    public static final Vector2 PUCK_DIMENSIONS = new Vector2(PUCK_DIAMETER, PUCK_DIAMETER);

    // Paddle constants
    /**
     * see constant name
     */
    public static final int PADDLE_HEIGHT = 15;

    /**
     * see constant name
     */
    public static final int PADDLE_WIDTH = 100;

    /**
     * see constant name
     */
    public static final Vector2 PADDLE_DIMENSIONS = new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT);

    /**
     * see constant name
     */
    public static final float LIVES_COUNTER_WIDTH = HEART_WIDTH;

    /**
     * see constant name
     */
    public static final float LIVES_COUNTER_HEIGHT = HEART_HEIGHT;

    /**
     * see constant name
     */
    public static final float PADDLE_MOVEMENT_SPEED = 400;

    /**
     * see constant name
     */
    public static final float UI_CENTER_Y = WINDOW_HEIGHT - HEART_HEIGHT / 2 - 5 * SPACING;

    /**
     * see constant name
     */
    public static final float LIVE_COUNTER_CENTER_X = BORDER_WIDTH + SPACING + LIVES_COUNTER_WIDTH / 2;

    /**
     * see constant name
     */
    public static final float HEART_START_X = LIVE_COUNTER_CENTER_X + LIVES_COUNTER_WIDTH + SPACING;

    /**
     * see constant name
     */
    public static final Vector2 LIVE_COUNTER_CENTER_POSITION = new Vector2(LIVE_COUNTER_CENTER_X,
            UI_CENTER_Y);

    /**
     * see constant name
     */
    public static final Vector2 LIVES_COUNTER_DIMENSION = new Vector2(LIVES_COUNTER_WIDTH,
            LIVES_COUNTER_HEIGHT);


//    public static final Vector2 PADDLE_INITIAL_POSITION = new Vector2(WINDOW_WIDTH / 2,
//            WINDOW_HEIGHT - 2.5f * PADDLE_HEIGHT);

    // Brick constants

    /**
     * see constant name
     */
    public static final float BRICK_HEIGHT = 15;

//    public static final float BRICK_WIDTH = 84;

    /**
     * see constant name
     */
    public static final float BRICK_WIDTH =
            (WINDOW_WIDTH - 2 * BORDER_WIDTH - (DEFAULT_BRICKS_IN_ROW - 1) * SPACING) / DEFAULT_BRICKS_IN_ROW;

    /**
     * see constant name
     */
    public static final Vector2 BRICK_DIMENSIONS = new Vector2(BRICK_WIDTH, BRICK_HEIGHT);

    // Tags

    /**
     * see constant name
     */
    public static final String LIFE = "heart";

    /**
     * see constant name
     */
    public static final String LIVES_DIGITAL_COUNTER = "livesDigitalCounter";

    /**
     * see constant name
     */
    public static final String BRICK = "brick";

    /**
     * see constant name
     */
    public static final String BALL = "ball";

    /**
     * see constant name
     */
    public static final String PUCK = "puck";

    /**
     * see constant name
     */
    public static final String TEMP_PADDLE = "tempPaddle";

    /**
     * see constant name
     */
    public static final String PADDLE = "paddle";

    /**
     * see constant name
     */
    public static final String FALLING_HEART = "fallingHeart";

    /**
     * see constant name
     */
    public static final String FALLING_HEART_HIT_PADDLE = "fallingHeartHitPaddle";

    // Image paths

    /**
     * see constant name
     */
    public static final String BALL_IMAGE_PATH = "assets/ball.png";

    /**
     * see constant name
     */
    public static final String PUCK_IMAGE_PATH = "assets/mockBall.png";

    /**
     * see constant name
     */
    public static final String BRICK_IMAGE_PATH = "assets/brick.png";

    /**
     * see constant name
     */
    public static final String PADDLE_IMAGE_PATH = "assets/paddle.png";

    /**
     * see constant name
     */
    public static final String HEART_IMAGE_PATH = "assets/heart.png";

    /**
     * see constant name
     */
    public static final String BALL_COLLISION_SOUND_PATH = "assets/blop.wav";

    /**
     * see constant name
     */
    public static final String BACKGROUND_IMAGE_PATH = "assets/DARK_BG2_small.jpeg";

    // Tag: Layer map

    /**
     * see constant name
     */
    public static final Map<String, Integer> TAG_TO_LAYER = Map.ofEntries(
            Map.entry(LIFE, Layer.UI),
            Map.entry(LIVES_DIGITAL_COUNTER, Layer.UI),
            Map.entry(BRICK, Layer.STATIC_OBJECTS),
            Map.entry(BALL, Layer.DEFAULT),
            Map.entry(PUCK, Layer.DEFAULT),
            Map.entry(TEMP_PADDLE, Layer.DEFAULT),
            Map.entry(PADDLE, Layer.DEFAULT),
            Map.entry(FALLING_HEART, Layer.DEFAULT)
    );

    // Strategies

    /**
     * see constant name
     */
    public static final int BASIC_STRATEGY = 0;

    /**
     * see constant name
     */
    public static final int EXTRA_PUCKS_STRATEGY = 1;

    /**
     * see constant name
     */
    public static final int EXTRA_TEMP_PADDLE_STRATEGY = 2;

    /**
     * see constant name
     */
    public static final int CAMERA_FOLLOWS_BALL_STRATEGY = 3;

    /**
     * see constant name
     */
    public static final int FALLING_HEART_STRATEGY = 4;

    /**
     * see constant name
     */
    public static final int DOUBLE_STRATEGY = 4;

    // Methods

    /**
     * see constant name
     */
    public static Vector2 windowCenter(Vector2 windowDimensions) {
        return windowDimensions.mult(0.5f);
    }


    /**
     * Returns a random velocity for the ball.
     * The ball will always move in the x direction, and will move in the y direction
     * with a 50% chance of moving up or down.
     *
     * @return A random velocity for the ball.
     */
    public static Vector2 randomBallVelocity() {
        float ballVelX = BALL_SPEED;
        float ballVelY = BALL_SPEED;
        if (random.nextBoolean())
            ballVelX *= -1;
        if (random.nextBoolean())
            ballVelY *= -1;
        return new Vector2(ballVelX, ballVelY);
    }


    /**
     * Returns a random velocity for the puck.
     *
     * @return A random velocity for the puck.
     */
    public static Vector2 randomPuckVelocity() {
        double angle = random.nextDouble() * Math.PI;
        return new Vector2(
                (float) Math.cos(angle),
                (float) Math.sin(angle)).mult(BALL_SPEED);
    }

    /**
     * Returns a random velocity for the falling heart.
     *
     * @return A random velocity for the falling heart.
     */
    public static TextRenderable getLivesDisplayRenderable(int numLives) {
        TextRenderable livesRenderable = new TextRenderable(String.valueOf(numLives));
        Color color;
        if (numLives <= 1)
            color = Color.red;
        else if (numLives == 2)
            color = Color.yellow;
        else color = Color.green;

        livesRenderable.setColor(color);
        return livesRenderable;
    }

//    public static int getWindowWidth(int desiredWindowWidth, int bricksInRow) {
//        int desiredGameWidth = desiredWindowWidth - 20;
//        int brickWidth = (desiredGameWidth - bricksInRow + 1) / bricksInRow;
//        int actualGameWidth = bricksInRow * brickWidth + (bricksInRow - 1);
//        int windowWidth = actualGameWidth + 20;
//        return windowWidth;
//    }


}
