# Specification

## Definitions

- Chessboard size. Dimension N of square chessboard NxN where the knight moves.
- Initial point. The point where the knight starts his tour.
- Stack. Last-in first-out data structure.
- State. Contains current position of the knight, visited fields of the chessboard and path of the knight.
- State ancestors. New states created from the original state using all possible knight moves.

## Command line

Application reads command line arguments from the operating systems.

- when 1 argument is provided
  - must be integer 
  - must be from 5 to 7 (closed interval; including 5 and 7)
  - interpreted as chessboard size
  - finds solutions for 5 default initial points
    - x = 1 and y = 1
    - 4 more random initial points
- when 3 arguments are provided
  - all must be integers
  - argument 1
    - must be from 5 to 7 (closed interval; including 5 and 7)
    - interpreted as chessboard size
  - arguments 2 and 3
    - must be from 1 to the chessboard size (closed interval; including 1 and chessboard size)
    - finds solutions for 1 custom initial point with x = argument 1 and y = argument 2
- prints help when other number of arguments is provided or arguments are incorrect

## Output

Application writes its output to the stdout file descriptor provided by the operating system.

### EBNF

Formal specification using Extended Backus-Naur Form. 

```
output              = size, {solution}, execution time ;
size                = "Size ", <number>, "x", <number>, <new line> ;
solution            = initial field, knights tour ;
initial field       = "Solving field ", <number>, " ", <number>, <new line> ;
knights tour        = ({knights tour line} | no solution found), <new line> ;
knights tour line   = {knights tour number}, <new line> ;
knights tour number = <number padded with spaces to 2 characters> ;
no solution found   = "null" ;
execution time      = "Execution time ", <number>, "ms", <new line> ;
```

### Example

```
Size 5x5
Solving field 1 1
 3 12  7 16 25 
 6 17  4 21  8 
11  2 13 24 15 
18  5 22  9 20 
 1 10 19 14 23 

Solving field 3 4
null
Solving field 1 3
25  4 15 10 19 
14  9 18  5 16 
 1 24  3 20 11 
 8 13 22 17  6 
23  2  7 12 21 

Solving field 2 1
null
Solving field 2 5
null
Execution time 483ms
```

## Finding solution

- application writes chessboard size
- application writes solutions
  - for every initial point (see command line arguments)
    - application writes initial point coordinates
    - application searches for the solution
      - first state at the initial point is pushed to the stack
      - internal loop
        - at last 100,000,000 iterations are allowed 
        - if stack is empty, the solution does not exist
        - a state is popped from the stack
        - if the popped state is final, we have found the solution
        - otherwise all the ancestors of the popped state are pushed to the stack
    - if solutions exists
      - application writes path of the knight
    - if solution does not exist
      - application writes "null"
- application writes execution time
