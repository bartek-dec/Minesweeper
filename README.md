# Minesweeper
### This is a console version of the 'Minesweeper' game. Game is played on the 9x9 field. User can define the number of mines from 1 to 64. After that, game starts: user provides coordinates followed by the command free/mine.
### The game uses the following rules:
#### 1) The game starts with an empty field.
#### 2) The user can mark (flag) some cells as cells that potentially have a mine. Any empty cell can be flagged, not just the cells that do contain a mine. After that, the cell is marked with a '*' (a mine flag).
#### 3) The user can also remove flags from cells. The user simply types 'mine' again after entering the coordinates of the flagged cell. After that, it's marked as an empty cell.
#### 4) The only way to get information about the field is to check it. This is done by typing 'free' after entering the coordinates of the cell. This means that the user thinks that this cell is free of mines and thus can be explored. The first cell checked is not a mine, it is always empty.
#### 5) If the user wants to explore a cell that contains a mine, the user loses. After that, all the mines are displayed on the field with symbol 'X'.
#### 6) If the user marks all the cells with potential mines, the user wins. The user must mark all the mines, but no empty cells; if the user has extra flags that do not mark mines, they continue playing. After clearing all excess mine flags, the user wins.
#### 7) There is another way to win. The user can simply check all safe cells, leaving only the cells with mines unexplored. After that, the user wins.