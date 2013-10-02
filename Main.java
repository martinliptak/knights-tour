import java.util.*;

public class Main
{
	/**
	 * Finds the solution for the initial state.
	 */
	private static State findSolution(State initial)
	{
		Deque<State> states = new ArrayDeque<State>(); // stack
		int steps = 0;
		
		// push the initial state
		states.addFirst(initial);

		for (;;)
		{
			// iteration limit
			steps++;
			if (steps > 100000000)
				return null; // limit exceeded, no solution found
				
			State state;
			if (states.size() > 0) 
				state = states.removeFirst(); // pop new current state from stack
			else
				return null; // empty stack, no solution found
				
			if (state.isFinal()) // final state
				return state; // we have found the solution
			else
				state.pushActions(states); // push ancestors of the current state
		}
	}
    
    private static boolean argumentCheck(String [] args)
    {
        int sizeOfTable=0;
        
        if (args.length != 1 && args.length != 3)   // testing the number of arguments
        {
            System.out.println("Wrong number of arguments!\nPlease folow the instuctions and put 1 or 3 arguments!");
            return false;
        }
        
        
        if ( !isNumeric(args[0]) )      // testing the first argument
        {
            System.out.println("The given argument is not numeric!\nPlease folow the instuctions and use an integer value from 5 to 7!");
            return false;
        }
        else        // saving the size of the chess table for testing the next arguments
        {
            sizeOfTable = Integer.parseInt(args[0]);
            if (sizeOfTable < 5 || sizeOfTable > 7)
            {
                System.out.println("The given argument is not valid!\nPlease folow the instuctions and use a number from 5 to 7!");
                return false;
            }
        }
        
        if (args.length > 1)
        {
            if ( !isNumeric(args[1]) )  //testing the second argument
            {
                System.out.println("The second argument is not numeric!\nPlease folow the instuctions and use an integer value!");
                return false;
            }
            else
            {
                int x = Integer.parseInt(args[1]);
                if ( x < 1 || x > sizeOfTable)
                {
                    System.out.println("The second argument is not valid!\nPlease folow the instuctions and use a number according to the size of the table!");
                    return false;
                }
            }
            
            if ( !isNumeric(args[2]) )  //testing the third argument
            {
                System.out.println("The third argument is not numeric!\nPlease folow the instuctions and use an integer value!");
                return false;
            }
            else
            {
                int y = Integer.parseInt(args[2]);
                if ( y < 1 || y > sizeOfTable)
                {
                    System.out.println("The third argument is not valid!\nPlease folow the instuctions and use a number according to the size of the table!");
                    return false;
                }
            }
            
        }
        
        return true;
    }
    
    public static boolean isNumeric(String s)
    {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }
	
	public static void main(String [] args)
	{
        if( !argumentCheck(args) )
        {
            return;
        }
        
		State found;
		Random randomGenerator = new Random();
		
		Integer customX = null, customY = null;
		
		// measure computation time
		long start = System.currentTimeMillis();

		// size of the chessboard
		int size = Integer.parseInt(args[0]);
		
		// custom initial point
		if (args.length > 1)
		{
			customX = Integer.parseInt(args[1]);
			customY = Integer.parseInt(args[2]);
		}
		
		System.out.println("Size " + size + "x" + size);

		if (customX == null || customY == null)
		{
			// try initial position 1 1 and 4 random positions
			System.out.println("Solving field from start-position " + 1 + " " + 1);
			System.out.println(findSolution(new State(size, 0, 0, 1, null)));
			for (int i = 0; i < 4; i++)
			{
				// nahodne pociatocne pozicie
				int x = randomGenerator.nextInt(size);
				int y = randomGenerator.nextInt(size);
			
				System.out.println("Solving field from start-position " + (x + 1) + " " + (y + 1));
				System.out.println(findSolution(new State(size, x, y, 1, null)));
			}
		}	
		else
		{
			// try custom initial position
			System.out.println("Solving field from start-position " + customX + " " + customY);
			System.out.println(findSolution(new State(size, customX, customY, 1, null)));
		}
			
		long end = System.currentTimeMillis();
		System.out.println("Execution time: " + (end - start) + "ms");
	}
}
