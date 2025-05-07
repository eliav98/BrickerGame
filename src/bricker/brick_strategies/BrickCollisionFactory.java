package bricker.brick_strategies;

import java.util.ArrayList;

import static bricker.main.BrickerUtils.*;


/**
 * Factory for creating brick collision strategies.
 */
public class BrickCollisionFactory {

    private CollisionStrategy[] strategies;


    /**
     * Construct a new BrickCollisionFactory instance.
     *
     * @param handler The handler for brick collisions.
     */
    public BrickCollisionFactory(BrickCollisionHandler handler) {
        this.strategies = new CollisionStrategy[]{
                new BasicCollisionStrategy(handler),
                new ExtraPucks(handler),
                new ExtraTempPaddle(handler),
                new CameraFollowsBall(handler),
                new FallingHeart(handler),
        };

    }

    /**
     * Build a double strategy.
     *
     * @return A double strategy.
     */
    public CollisionStrategy buildDoubleStrategy() {
        ArrayList<CollisionStrategy> strats = new ArrayList<>();
        int strat1 = random.nextInt(DOUBLE_STRATEGY) + 1;
        int strat2 = random.nextInt(DOUBLE_STRATEGY) + 1;
        if (strat1 == DOUBLE_STRATEGY || strat2 == DOUBLE_STRATEGY) {
            strats.add(strategies[random.nextInt(4) + 1]);
            strats.add(strategies[random.nextInt(4) + 1]);
            strats.add(strategies[random.nextInt(4) + 1]);
        } else {
            strats.add(strategies[strat1]);
            strats.add(strategies[strat2]);
        }
        return new DoubleStrategy(strats);
    }

    /**
     * Pick a random strategy.
     *
     * @return A random strategy.
     */
    public CollisionStrategy pickRandomStrategy() {

        int chance = random.nextInt(10);
        CollisionStrategy strategy;
        if (chance < 5) {  // 5/10
            strategy = strategies[BASIC_STRATEGY];
        } else if (chance < 6) {  // 1/10
            strategy = strategies[EXTRA_PUCKS_STRATEGY];
        } else if (chance < 7) {  // 1/10
            strategy = strategies[EXTRA_TEMP_PADDLE_STRATEGY];
        } else if (chance < 8) {  // 1/10
            strategy = strategies[CAMERA_FOLLOWS_BALL_STRATEGY];
        } else if (chance < 9) {  // 1/10
            strategy = strategies[FALLING_HEART_STRATEGY];
        } else {  // 1/10
            strategy = buildDoubleStrategy();
        }
        return strategy;
    }
}




