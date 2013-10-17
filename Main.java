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
            // *** ERROR-8 Should limit the number of iterations, otherwise can take very long time to run
			/*
            steps++;
			if (steps > 100000000)
				return null; // limit exceeded, no solution found
            */
				
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
        
        // *** ERROR-6 The condition is not covering all the wrong cases. With this conditions 0 and 2 arguments are allowed too. The correct number of arguments should be only 1 or 3.
        if (args.length > 3)   // testing the number of arguments
        {
            System.out.println("Wrong number of arguments!\nPlease folow the instuctions and put 1 or 3 arguments!\n");
            return false;
        }
        
        
        if ( !isNumeric(args[0]) )      // testing the first argument
        {
            System.out.println("The first argument is not numeric!\nPlease folow the instuctions and use an integer value from 5 to 7!\n");
            return false;
        }
        
        /*
         //  *** ERROR-1 the method is not checking the argument wether it is integer or not
         if( !isInteger(args[0]))
         {
         System.out.println("The first argument is not an integer!\nPlease folow the instuctions and use an integer value from 5 to 7!\n");
         return false;
         }
         */
        
        sizeOfTable = Integer.parseInt(args[0]);
        
        /*
         // ****ERROR-2 The size of table is not tested => smaller number then 5 cause no solution OR error. Higher number than 5 cause too long waiting time for the solution.
         if (sizeOfTable < 5 || sizeOfTable > 7)     // testing the value of the given integer (argument 1)
         {
         System.out.println("The first argument is not valid!\nPlease folow the instuctions and use a number from 5 to 7!\n");
         return false;
         }
         */
        
        if (args.length > 1)
        {
            if ( !isNumeric(args[1]) )  //testing the second argument
            {
                System.out.println("The second argument is not numeric!\nPlease folow the instuctions and use an integer value!\n");
                return false;
            }
            
            /*
             //  *** ERROR-1 the method is not checking the argument wether it is integer or not
             if ( !isInteger(args[1]) )
             {
             System.out.println("The second argument is not an integer!\nPlease folow the instuctions and use an integer value!\n");
             return false;
             }
             */
            
            int x = Integer.parseInt(args[1]);
            
            
            // ***ERROR-3 with this conditions position "1" and the maximum position are not allowed. (The right solution would be x < 1 || x > sizeOfTable
            if ( x <= 1 || x >= sizeOfTable)
            {
                System.out.println("The second argument is not valid!\nPlease folow the instuctions and use a number in the interval: from 1 to \"the chessboard size\"!\nHelp: Chessboard size is given by the first argument.\n");
                return false;
            }
            
            
            if ( !isNumeric(args[2]) )  //testing the third argument
            {
                System.out.println("The third argument is not numeric!\nPlease folow the instuctions and use an integer value!\n");
                return false;
            }
            
            /*
             //  *** ERROR-1 the method is not checking the argument wether it is integer or not
             if ( !isInteger(args[2]) )
             {
             System.out.println("The third argument is not an integer!\nPlease folow the instuctions and use an integer value!\n");
             return false;
             }
             */
            
            int y = Integer.parseInt(args[2]);
            
             // ***ERROR-4 condition will be always FALSE because of the used && instead of ||
            if ( y < 1 && y > sizeOfTable)
            {
                System.out.println("The third argument is not valid!\nPlease folow the instuctions and use a number in the interval: from 1 to \"the chessboard size\"!\nHelp: Chessboard size is given by the first argument.\n");
                return false;
            }
            
            
            
        }
        
        return true;
    }
    
    public static boolean isNumeric(String s)
    {
        //return s.matches("[-+]?\\d*\\.?\\d+");
        
        // *** ERROR-7 Bad way of testing the numeric value of the given argument. This condition is testing only the first charakter of the given agrument (ex. in case of 50 it will test the value of 5) 
        if ( ((int) s.charAt(0)) >= 48 && ((int) s.charAt(0)) <= 57)
        {
            return true;
        }
        return false;
    }
    
    /*
     //  *** ERROR-1 the method what could check the arguments whether they are integers
     public static boolean isInteger(String s) {
     try
     {
     Integer.parseInt(s);
     }
     catch(NumberFormatException e)
     {
     return false;
     }
     
     return true;
     }
     */
	
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
			customX = Integer.parseInt(args[1]) - 1;
			customY = Integer.parseInt(args[2]) - 1;
		}
		
		System.out.println("Size " + size + "x" + size);

		if (customX == null || customY == null)
		{
			// try initial position 1 1 and 4 random positions
			System.out.println("Solving field from start-position " + 1 + " " + 1);
			System.out.println(findSolution(new State(size, 0, 0, 1, null)));
			for (int i = 0; i < 4; i++)
			{
				// random starting positions
				int x = randomGenerator.nextInt(size);
				int y = randomGenerator.nextInt(size);
			
				System.out.println("Solving field from start-position " + (x + 1) + " " + (y + 1));
				System.out.println(findSolution(new State(size, x, y, 1, null)));
			}
		}	
		else
		{
			// try custom initial position
			System.out.println("Solving field from start-position " + (customX + 1) + " " + (customY + 1) );
			System.out.println(findSolution(new State(size, (customX), (customY), 1, null)));
		}
			
		long end = System.currentTimeMillis();
		System.out.println("Execution time: " + (end - start) + "ms\n");
	}
}
