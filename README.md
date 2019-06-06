# TicTacToe Java CI

A Tic Tac Toe game programed in Java, with a Command Line Interface.

---

## This program is superior because

  - It prints a **big** board!
  - You only need to enter one number, once per turn
    - Optimized for the number pad
  - Uses Emus ;)
  - **Uses cows**

## Questionable decisions

I made some questionable decisions in this program.

  - Use of `DoneException`, which is an exception that's raised when the game ends
  - 3 overloads for Board.getCharAt, Board.getStateAt, Board.setStateAt and Board.isEmpty, most of which aren't used
  - Using `throws`
  - The main class initalizes itself
