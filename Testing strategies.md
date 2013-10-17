# Knight's tour - Testing strategies

## I. Strategies to test if the input parameters are valid:
1. Cases in which the given arguments are not numeric or not integer values:

```bash
$ java Main a
```
```bash
$ java Main 6.3 4 4
```
```bash
$ java Main 6 @ 3
```

2. Cases in which a knight cannot move in the chessboard(the chessboard size is too small):

```bash
$ java Main -2
```
```bash
$ java Main 0
```
```bash
$ java Main 1
```
```bash
$ java Main 4
```

3. Cases in which the chessboard is too big and the expected counting time is ower some minutes (hours):

```bash
$ java Main 8
```
```bash
$ java Main 12
```

4. Cases in which the initial point given at the input exceeds the limits of the chessboard:

```bash
$ java Main 5 6 6
```
```bash
$ java Main 6 -1 0
```
```bash
$ java Main 5 7 2
```

## II. Startegies to test the program when the input parameters are valid:
1. The program was tested with the first input parameter having values between 5-7.
	
```bash
$ java Main 5
```
```bash
$ java Main 6
```
```bash
$ java Main 7
```
	
Faults found: sometimes there isn't any solution for some random generated points
(this isn't quite a fault, it is normal that for some points there isn't solution. The output of the this kind of result is "null")

2. The programme was tested for the custom initial point with the blind strategy,
taking into consideration that the initial poit must be between the limits of the chessboard

```bash
$ java Main 5 2 4
```
```bash
$ java Main 6 3 3
```
```bash
$ java Main 6 2 4
```

Faults found: sometimes there isn't any solution for some random generated points
(this isn't quite a fault, it is normal that for some points there isn't solution. The output of the this kind of result is "null")
