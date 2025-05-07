package bricker.main;

import bricker.brick_strategies.*;
import bricker.gameobjects.BrickerObjectFactory;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.components.CoordinateSpace;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.gui.rendering.Camera;
import danogl.gui.rendering.Renderable;
import danogl.util.Border;
import danogl.util.Vector2;

import static bricker.main.BrickerUtils.*;

import java.awt.Color;
import java.awt.event.KeyEvent;

/**
 * The main game manager for the Bricker game.
 */
public class BrickerGameManager extends GameManager {

    private WindowController windowController;
    private Vector2 windowDimensions;
    private ImageReader imageReader;
    private UserInputListener inputListener;
    private int bricksInRow;
    private int numRows;
    private int initialLivesCount;
    private BrickerObjectFactory brickerObjectFactory;
    private GameState gameState;


    /**
     * Construct a new GameManager instance.
     *
     * @param windowTitle       The title of the window.
     * @param windowDimensions  The dimensions of the window.
     * @param bricksInRow       The number of bricks in a row.
     * @param numRows           The number of rows of bricks.
     * @param initialLivesCount The number of lives the player starts with.
     */
    public BrickerGameManager(String windowTitle, Vector2 windowDimensions, int bricksInRow, int numRows,
                              int initialLivesCount) {
        super(windowTitle, windowDimensions);
        this.bricksInRow = bricksInRow;
        this.numRows = numRows;
        this.initialLivesCount = initialLivesCount;
    }


    /**
     * Initialize the game.
     *
     * @param imageReader      The image reader to use for reading images.
     * @param soundReader      The sound reader to use for reading sounds.
     * @param inputListener    The input listener to use for listening to user input.
     * @param windowController The window controller to use for controlling the window.
     */
    @Override
    public void initializeGame(ImageReader imageReader,
                               SoundReader soundReader,
                               UserInputListener inputListener,
                               WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        this.windowController = windowController;
        this.windowDimensions = windowController.getWindowDimensions();

        this.imageReader = imageReader;
        this.inputListener = inputListener;

        this.gameState = new GameState();
        this.brickerObjectFactory = new BrickerObjectFactory(imageReader, soundReader, inputListener,
                windowDimensions, bricksInRow);
        setGameObjectsCollection(new BrickerObjectManager(windowController.messages(), windowDimensions,
                gameState, brickerObjectFactory));

//        windowController.setTargetFramerate(10000);

        // Initialize game objects
        initBackground();
        initBorders();
        initBricks(bricksInRow, numRows);
        initPaddle();
        initBall();
        initLifeState(initialLivesCount);

    }


    /**
     * Update the game state.
     *
     * @param deltaTime The time that has passed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        checkForCamUpdate();
        checkForGameEnd();
    }


    private void initBackground() {
        Renderable backgroundImage = this.imageReader.readImage(BACKGROUND_IMAGE_PATH, false);
        GameObject background = new GameObject(Vector2.ZERO, windowDimensions, backgroundImage);
        background.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        gameObjects().addGameObject(background, Layer.BACKGROUND);
    }


    private void initBorders() {
        gameObjects().addGameObject(Border.atDirection(Vector2.UP, windowDimensions, BORDER_WIDTH,
                Color.black), Layer.STATIC_OBJECTS);
        gameObjects().addGameObject(Border.atDirection(Vector2.LEFT, windowDimensions, BORDER_WIDTH,
                Color.black), Layer.STATIC_OBJECTS);
        gameObjects().addGameObject(Border.atDirection(Vector2.RIGHT, windowDimensions, BORDER_WIDTH,
                Color.black), Layer.STATIC_OBJECTS);
    }

    private void initBricks(int bricksInRow, int numRows) {
        float maximalWidth = this.windowDimensions.x() - 2 * BORDER_WIDTH;
        float brickWidth = (maximalWidth - (bricksInRow - 1) * SPACING) / bricksInRow;
        BrickCollisionFactory collisionFactory =
                new BrickCollisionFactory((BrickerObjectManager) gameObjects());
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < bricksInRow; ++j) {
                GameObject brick = brickerObjectFactory.buildBrick(i, j,
                        collisionFactory.pickRandomStrategy());
                gameObjects().addGameObject(brick);
            }
        }

    }

    private void initPaddle() {
        gameObjects().addGameObject(brickerObjectFactory.buildBrickerObject(PADDLE));
    }

    private void initBall() {
        gameObjects().addGameObject(brickerObjectFactory.buildBrickerObject(BALL));
    }

    private void initLifeState(int initialLivesCount) {
        gameObjects().addGameObject((brickerObjectFactory.buildBrickerObject(LIVES_DIGITAL_COUNTER)));
        for (int i = 0; i < initialLivesCount; ++i) {
            gameObjects().addGameObject(brickerObjectFactory.buildLife(i));
        }
    }

    private void checkForCamUpdate() {
        if ((gameState.getBallCamComponent() == null) == (camera() == null)) {
            return;
        }
        if ((camera() == null)) {
            setCamera(
                    new Camera(
                            gameState.getBall(), //object to follow
                            Vector2.ZERO, //follow the center of the object
                            windowDimensions.mult(1.2f), //widen the frame a bit
                            windowDimensions //share the window dimensions
                    )
            );
        } else {
            setCamera(null);
        }
    }

    private void checkForGameEnd() {
        String prompt = "";
        if (gameState.getNumBricks() <= 0 || this.inputListener.isKeyPressed(KeyEvent.VK_W)) {
            prompt = WIN_PROMPT;
        }

        if (gameState.getNumLives() == 0) {
            prompt = LOSE_PROMPT;
        }

        if (!prompt.isEmpty()) {
            prompt += " " + RESTART_PROMPT;
            if (this.windowController.openYesNoDialog(prompt)) {
                this.windowController.resetGame();
            } else {
                this.windowController.closeWindow();
            }
        }

    }


    /**
     * Run the game.
     */
    public static void main(String[] args) {
        int bricksInRow = DEFAULT_BRICKS_IN_ROW;
        int numRows = DEFAULT_NUM_ROWS;
        if (args.length == LEGAL_NUM_ARGS) {
            bricksInRow = Integer.parseInt(args[0]);
            numRows = Integer.parseInt(args[1]);
        }
        BrickerGameManager gameManager = new BrickerGameManager(WINDOW_TITLE, WINDOW_DIMENSIONS,
                bricksInRow, numRows,
                DEFAULT_INITIAL_LIVES);
        gameManager.run();
    }


}
