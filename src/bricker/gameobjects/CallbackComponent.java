package bricker.gameobjects;

import danogl.GameObject;
import danogl.components.Component;

import java.util.function.Consumer;


/**
 * Component that performs a callback when the GameObject is updated.
 */
public class CallbackComponent implements Component {

    private Consumer<GameObject> actionHandler;
    private GameObject gameObject;

    /**
     * Construct a new CallbackComponent instance.
     *
     * @param gameObject    The GameObject to which this component is attached.
     * @param actionHandler The action to be performed when the GameObject is updated.
     */
    public CallbackComponent(GameObject gameObject,
                             Consumer<GameObject> actionHandler) {
        this.gameObject = gameObject;
        this.actionHandler = actionHandler;
    }

    /**
     * Perform the action associated with this component.
     *
     * @param deltaTime Time since the last update, in seconds.
     */
    @Override
    public void update(float deltaTime) {
        actionHandler.accept(gameObject);
    }
}
