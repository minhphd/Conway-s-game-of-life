# John Conway's game of life
Java based program for visualizing of John Conway's game of life. The program use Queue amnd multidimensional arrays for storing game datas. The program allows user to generate a random grid of cells or an empty grid of cell so users can edit each cell values later.

## About the game of life
According to Wikipedia
> The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970. It is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves. It is Turing complete and can simulate a universal constructor or any other Turing machine.

The game of life is unpredictable, with each new state is determined by the previous state based on a specific set of rules.

### Rules
The rules are applied to every cells and its neighbors. Every cells will interact with eight neighbouring cells. A cell has two state, dead and alive.
1. Any live cell with fewer than two live neighbours dies due to underpopulation.
2. Any live cell with two or three live neighbours lives on to the next generation.
3. Any live cell with more than three live neighbours dies due to overpopulation.
4. Any dead cell with exactly three live neighbours becomes a live cell from reproduction.

## Usage
1. Git clone the repo to your local folder and cd into Conway-s-game-of-life
2. compiled all java files using the following terminal command
```
javac *.java
```
3. than run LandscapeDisplay.java
```
java LandscapeDisplay
```
4. The terminal will ask for your inputs for the game, go ahead and enter number of rows and columns you want for your simulation landscape
5. After putting in the dimension, u will have two option. 
    - Creating a blank landscape: for this option, the program will repeatedly ask you the coordinates and status you want to set for the cell. To finish setting up, press enter without writing anything.

    ![](https://github.com/minhphd/Conway-s-game-of-life/blob/main/img/terminalBlank.gif)

    - Creating a landscape with randomly generated Cells with random state of alive and dead based on user-defined probability

    ![](https://github.com/minhphd/Conway-s-game-of-life/blob/main/img/terminalNoBlank.gif)

6. Enjoy watching the evolution of cells

![](https://github.com/minhphd/Conway-s-game-of-life/blob/main/img/run-20220922-2106/run-20220922-2106.gif)

The game will end when the program detect that the simulation has become a loop

**By default, the simulation will output a file into the img folder with each frame of a run, you can turn that off by passing false as a parameter instead of true in line 138 of LandscapeDisplay.java**

```
life.runSimulation(false);
```