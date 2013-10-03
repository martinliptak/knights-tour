# Knight's tour - Testing strategies

## I. Strategies to test if the input parameters are ok:
1. Cases in which a knight cannot move in the chessboard(the chessboard size is too small):
	a. $ java Main 1
	b. $ java Main 2
	c. $ java Main 3
	d. $ java Main 4

2. Cases in which the initial point given at the input exceeds the limits of the chessboard:
	a. $ java Main 5 6 6
	b. $ java Main 6 -1 0
	c. $ java Main 5 7 2

3. Cases in which the chessboard size is negative or equals 0:
	a. $ java Main 0
	b. $ java Main -1 


## II. Startegies to test the programme when the input parameters are ok:
1. The program was tested with the first input parameter having values between 5-7,
otherwise the waiting time is too long.
	a. $ java Main 5
	b. $ java Main 6
	c. $ java Main 7
	Faults found: sometimes there isn't any solution for some random generated points
	(this isn't quite a fault, it is normal that for some points there isn't solution)

2. The programme was tested for the custom initial point with the blind strategy,
taking into consideration that the initial poit must be between the limits of the chessboard
	a. $ java Main 5 2 4
	b. $ java Main 6 3 3
	c. $ java Main 6 2 4 
	Faults found: sometimes there isn't any solution for some random generated points
	(this isn't quite a fault, it is normal that for some points there isn't solution)
