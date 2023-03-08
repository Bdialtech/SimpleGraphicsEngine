# Paragon Engine
I've always been interested in game design. Like most people, I learned how to use powerful tools like Unity and GameMaker, but I also had a desire to learn more roots and grass design elements, like generating graphics. In this pursuit, I learned how to make a simple raster graphics engine for use in making games. I never got far with it, because I realized the time investment was simply too great for what I wanted to accomplish. But, it served as a valuable educational experience with lower-level programming than what I was used to.

## Inteded Use
"Paragon" was the working name for a game I wanted to make. I didn't keep good design documentation about the game itself, as I wanted to make working code before I bogged myself down with the more artistic side of things, so what the game actually was about is lost to time. However, I know I wanted it to be in an intentionally retro style (a common practice in indie game development). To this end, I didn't just want to emulate a style by making high-resolution graphics that aped a style. I actually copied the resolution of the Gameboy Advance's screen and upscaled it, to ensure a genuine graphical style.
I had big plans for implementing everything I could need for the game at a base level, but in the end I only had a few working components, listed below.

## Core.java
This was the launchpad for the engine. The game loop, initialization, everything needed to get the program running. All the core logic and the test game state and test automations are contained here. The current state of the loop is to generate a debug Floormap (explained below) and animate an Entity across it, first going left, then after 120 updates, go right, indefinitely.

## Render.java
This is where the magic happens. Core calls Render to draw images onto the screen. I admit the algorithms used are not optimized in the slightest, but they worked for educational purposes. First, the screen is drawn blank with black. Then, the floormap is rendered, then the entities on top - a simple painter's algorithm. In Core, the render loop is separate from the game logic loop, which is critical to maintain consistent game performance, even under periods of high and low graphical performance.

## Gamestate.java
The Gamestate is a container for all game components - in this iteration, just the Floormap and the Entities involved.

## Floormap.java
This is an abstraction of the game world. The game was inteded to work on a tile system, like a chessboard. A matrix of Tiles is contained within the Floormap, as well as methods to control and manipulate these Tiles.

## Tile.java
This is a single square on the chessboard. The tiles may have properties that determine how Entities can interact with it, such as being solid (entities cannot pass through it), or being hazardous (entities would take damage upon entering the tile). They are usually not intended to be moved at all, though may be altered in some other ways.

## Entity.java
This is a moving part on top of the Floormap. Characters, items, anything that is a dynamic game piece would be considered an entity.

## Sprite.java
This is a graphical unit in the game. In theory a bitmap file would be referenced in a Sprite object and rendered at game time, but practically only color flood demo pieces were used in this iteration.

## Mouse.java
This was inteded to be input control, but nothing was ever implemented.
