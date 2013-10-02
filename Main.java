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
	
	public static void main(String [] args)
	{
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
