
# Bricker Game

A feature-rich, extensible Java implementation of an *Arkanoid*-style game, built using the [DanoGameLab](https://github.com/danorg/DanoGameLab) game framework.

## 🎯 Purpose

This project was developed as a comprehensive object-oriented programming assignment. I used it to explore and demonstrate strong design principles (SRP, OCP, composition over inheritance), clean code structure, and careful architectural choices — with the goal of delivering a polished, extensible game system.

---

## 🧱 Game Overview

Bricker is a brick-breaking game where the player controls a paddle to bounce a ball and destroy bricks. It includes:

- Multiple brick behaviors (extra pucks, extra paddle, falling heart, camera follow, and a double strategy).
- Both graphical and numerical life counters.
- Temporary paddle mechanics.
- Responsive paddle and puck physics.
- A modular architecture for scalable feature expansion.

---

## 🧩 Architecture Highlights

### 📁 Modular Package Structure
```bash
src/bricker/
├── brick_strategies/     # Collision strategies, extensible and decoupled
├── gameobjects/          # Custom game entities (Ball, Paddle, Heart, etc.)
└── main/                 # Game initialization, logic, and state management
````

### 🎮 Game Object Management

* `BrickerObjectManager`: Inherits from `GameObjectCollection`, implements a `BrickCollisionHandler` interface to encapsulate and centralize game logic.
* `CallbackComponent`: Enables dynamic behavior via runtime-attached callbacks (composition over inheritance).
* `BrickerObjectFactory`: Clean creation and initialization of game objects, supporting easy expansion and reuse.

### 🧠 Behavioral Design Patterns

* **Strategy Pattern**: For encapsulating brick collision effects (`CollisionStrategy`, and its implementations).
* **Decorator-like Composition**: `DoubleStrategy` allows combining behaviors without tight coupling or rigid inheritance.

---

## 🛠 Notable Features

| Feature                    | Description                                                           |
| -------------------------- | --------------------------------------------------------------------- |
| 🧱 Modular Collision Logic | Easily expandable collision effects using strategy & factory patterns |
| 🧠 Smart Object Callbacks  | Decoupled, composable behaviors using `CallbackComponent`             |
| ❤️ Life Management         | Numeric and graphical life indicators with UI layer management        |
| 🎥 Dynamic Camera          | Smooth camera follow with automated reversion using ball hit tracking |
| 🎮 Input Handling          | Keyboard-driven paddle and temporary paddle controls                  |

---

## 🔧 Setup & Requirements

* **Language**: Java
* **Game Framework**: [DanoGameLab](https://github.com/danorg/DanoGameLab)
* **IDE**: Recommended: IntelliJ IDEA with JDK 17+

### 🔌 External Dependencies

* Place `DanoGameLab.jar` in the `lib/` directory.
* Ensure project `.iml` or module structure references this library under **Project Structure > Libraries**.

---

## 🧪 Run the Game

```bash
javac -cp lib/DanoGameLab.jar -d out src/bricker/main/BrickerGameManager.java
java -cp lib/DanoGameLab.jar;out bricker.main.BrickerGameManager
```

*Or run directly via IntelliJ by configuring the main class as `bricker.main.BrickerGameManager`.*

---

## 📸 Screenshots & Demo

*Add screenshots or a short demo video link here to visually showcase the game.*

---

## 📝 Design Reflections

This project reflects my ability to:

* Independently analyze and break down complex requirements.
* Translate them into clean, maintainable, object-oriented code.
* Think critically about design decisions, tradeoffs, and extensibility.

The project went far beyond a basic exercise: I structured it with real-world readability, testability, and extensibility in mind — and used the opportunity to push architectural clarity and code discipline.

---

## 📂 Assets & Credits

See [`assets/Attribution.txt`](assets/Attribution.txt) for sound/image source attributions.

---

## 💡 Future Improvements

* Add game menus, pause/resume functionality.
* Include power-ups and score tracking.
* Refactor `BrickerObjectManager` further if logic grows more complex.
* Add test scaffolding (mocking game loop elements).

---

## 👤 Author

**Eliav**
GitHub: [eliav98](https://github.com/eliav98)

---

## 🔗 License

Assets under their respective licenses. Code is free to use and extend for educational or personal purposes.
