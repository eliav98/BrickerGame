# Implementation Notes

1.
    I decided to to create an interface called BrickCollisionHandler which exposes the minimally required api
    in order to facilitate the functionality of the special collisions. In addition, I created a child class
    for the GameObjectCollection class called BrickerObjectManager which inherents from GameObjectCollection
    and implements the BrickCollisionHandler interface. This class is responsible for managing game logic, and
    in particular, the special collisions.
    Pros: a) Centralized Control: All modifications to game logic are managed from a single point, enhancing
          consistency and making updates easier.
          b) Encapsulation: The details of game logic are hidden from the collision strategies, reducing the
          risk of errors, unauthorized changes and coupling between the strategies and the
           BrickerObjectManager, all I need is a class that implements the API.
          c) It also aligns with the Single Responsibility Principle, as BrickerObjectManager focuses solely
          on adhering to the game logic.
    Cons:
          a) Potential Complexity: As more game logic is added BrickerObjectManager may become more complex,
          in which case we'd perhaps want to consider breaking it down into smaller classes responsible for
          different aspects of the game logic.
          b) Potential Coupling: If the game logic is tightly coupled to the BrickerObjectManager, it may be
          difficult to change the game logic without affecting the BrickerObjectManager class.
2. Explain how you implemented the display of the player's life number (graphically and numerically) from
   part 1.8 of the exercise. Explain In short, what is the function of each class you added in the code.
    I implemented the life counters utilizing the GameObject class and a GameObjectFactory class I
    Implemented which streamlines the process of building all game objects in these in particular.
    The graphical life counter is actually a Heart object that serves the purpose of all hearts in the game.
    Either graphical ones which don't collide with anything or the falling ones. The numerical life counter is
    a simple game object. In order to get the appropriate text renderable object I wrote a static utility
    method "getLivesDisplayRenderable" that lives inside the static class BrickerUtils, it returns a
    TextRenderable object with the current number of lives.
3. Briefly explain how you implemented each of the special behaviors (except the double). \
   Explain the role of the classes you added to implement the behavior, if you added them.
    As I mentioned in the first question, I created an interface called BrickCollisionHandler which exposes
    the minimally required api in order to facilitate the functionality of the special collisions.
    Each special behavior (besides the double strategy) is handled by a BrickCollisionHandler, which
    in our case is implemented by the BrickerObjectManager class.
    In addition, I created a CallbackComponent class which enables the dynamic addition of behaviors to game
    objects.
    a) ExtraPucks: I simply use the handler methods: "removeBrick" and "addPuck" to remove the brick and add
    the pucks. I utilize the CallbackComponent to overload the created pucks with callbacks in charge of
    performing the appropriate puck behaviors, i.e. "removeIfBelowWindow".
    b) ExtraPaddle: I use the handler methods: "removeBrick" and "addTempPaddle". I utilize the
    CallbackComponent to overload the created paddle with callbacks in charge of performing the appropriate
    paddle behaviors, i.e. "removeIfMaxHitsReached" and "stopPaddleIfHitsBorders".
    c) CameraFollowsBall: I use the handler methods: "removeBrick" and "startCamFollowBall". I utilize the
    CallbackComponent to overload the ball with the "stopCamFollowIfBallHitsReached" callback.
    d) FallingHeart: I use the handler methods: "removeBrick" and "addFallingHeart". I utilize the
    CallbackComponent to overload the created heart with the "removeIfBelowWindow" and
    "convertToLifeIfHitPaddle" callbacks.
4. Explain how you implemented the double behavior in part 2.2.5. Refer in the explanation to the design of
   the code, as well as how you limited the number of behaviors to 3 in the code.
    The double strategy is implemented by using (roughly) the decorator design pattern. Thought I didn't
    create a new interface for a decorator, but I use composition to maintain a list of strategies needed
    to be executed on collision. I also created a BrickCollisionFactory class that is responsible for
    sampling random strategies for a brick and for the double strategy. Using the factory I build a
    DoubleStrategy object that contains the array list of strategies that need to be executed on collision.
    The logic for the limitation of max 3 behaviors is implemented by sampling first 2 strategies from the 5
    special ones, and if one of which is the double strategy, I sample 3 strategies. If not, I sample 2. It
    adheres to the probability requirements and is simple and good, and easy to expand in the future since
    the arraylist is dynamic.
5.
    Not that I remember.